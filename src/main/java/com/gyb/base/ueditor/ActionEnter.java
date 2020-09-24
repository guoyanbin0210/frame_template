package com.gyb.base.ueditor;

import com.gyb.base.ueditor.define.ActionMap;
import com.gyb.base.ueditor.define.AppInfo;
import com.gyb.base.ueditor.define.BaseState;
import com.gyb.base.ueditor.define.State;
import com.gyb.base.ueditor.hunter.FileManager;
import com.gyb.base.ueditor.hunter.ImageHunter;
import com.gyb.base.ueditor.upload.Uploader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class ActionEnter {
    Logger logger = LogManager.getLogger(ActionEnter.class);


    private HttpServletRequest request;
    private String physicsPath;
    private String baseUrlPath;
    private String actionType;
    private ConfigManager configManager;

    public ActionEnter(HttpServletRequest request, String physicsPath, String baseUrlPath) {
        this.request = request;
        this.physicsPath = physicsPath;
        this.baseUrlPath = baseUrlPath;
        this.actionType = request.getParameter("action");
        this.configManager = ConfigManager.getInstance(this.physicsPath, this.baseUrlPath);
    }

    public String exec() {
        String callbackName = this.request.getParameter("callback");
        if (callbackName != null) {
            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
            }
            return callbackName + "(" + this.invoke() + ");";
        } else {
            return this.invoke();
        }
    }


    public String invoke() {


        if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
        }
        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
        }

        State state = null;
        int actionCode = ActionMap.getType(this.actionType);
        Map<String, Object> conf;
        switch (actionCode) {
            case ActionMap.CONFIG:
                return this.configManager.getAllConfig().toString();
            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                conf = this.configManager.getConfig(actionCode);
                state = new Uploader(request, conf).doExec();

                break;
            case ActionMap.CATCH_IMAGE:
                conf = configManager.getConfig(actionCode);
                String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
                ImageHunter imageHunter = new ImageHunter(conf);
                state = imageHunter.capture(list);

                break;
            case ActionMap.LIST_IMAGE:
            case ActionMap.LIST_FILE:
                conf = configManager.getConfig(actionCode);
                int start = this.getStartIndex();
                state = new FileManager(conf, baseUrlPath).listFile(start);
                break;
        }
        return state != null ? state.toJSONString() : null;
    }

    public int getStartIndex() {
        String start = this.request.getParameter("start");
        try {
            return Integer.parseInt(start);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * callback参数验证
     */
    private boolean validCallbackName(String name) {
        return name.matches("^[a-zA-Z_]+[\\w0-9_]*$");
    }

}