package com.lt.base.ueditor.controller;

import com.lt.base.controller.BaseController;
import com.lt.base.fileManager.controller.UploadController;
import com.lt.base.ueditor.ActionEnter;
import com.lt.base.util.BaseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-17
 * Time: 14:45
 */
@Controller
@ApiIgnore
public class UeController extends BaseController {
    Logger logger = LogManager.getLogger(UeController.class);

    @Resource
    UploadController uploadController;

    /**
     * /ue/config
     *
     * @param request
     * @param response
     * @param action
     */
    @RequestMapping("/uEditor/ue/config")
    public void config(HttpServletRequest request, HttpServletResponse response, String action) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //D:/web_file/my360_ecological_miyun/ue/
        String physicsPath = BaseUtils.getUeUploadPath();//
        //http://localhost:8082/
        //String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/ue/";
        String basePath = request.getScheme() + "://39.104.118.198:" + request.getServerPort() + request.getContextPath() + "/ue/";
        try {
            ActionEnter actionEnter = new ActionEnter(request, physicsPath, basePath);
            String exec = actionEnter.exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 百度图片上传  {}
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uEditor/uploadUe.do", method = RequestMethod.POST)
    public HashMap uploadUe(HttpServletRequest request, @RequestParam("upfile") MultipartFile multipartFile) {
        return uploadController.uploadFile(request, multipartFile);
    }

    /**
     * 百度图片上传   []
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uEditor/uploadUeList.do", method = RequestMethod.POST)
    public HashMap uploadUeList(HttpServletRequest request, @RequestParam("source") MultipartFile[] multipartFile) {
        return uploadController.uploadFileList(request, multipartFile);
    }
}
