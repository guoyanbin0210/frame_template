package com.lt.base.ueditor.hunter;

import com.lt.base.ueditor.PathFormat;
import com.lt.base.ueditor.define.AppInfo;
import com.lt.base.ueditor.define.BaseState;
import com.lt.base.ueditor.define.MultiState;
import com.lt.base.ueditor.define.State;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class FileManager {

    private String dir;
    private String rootPath;
    private String[] allowFiles;
    private int count;

    /**
     * 用于替换rootath
     */
    private String basePath;

    public FileManager(Map<String, Object> conf, String basePath) {
        this.basePath = basePath;
        this.rootPath = (String) conf.get("rootPath");
        this.dir = this.rootPath + conf.get("dir");
        this.allowFiles = this.getAllowFiles(conf.get("allowFiles"));
        this.count = (Integer) conf.get("count");
    }

    public State listFile(int index) {
        File dir = new File(this.dir);
        State state;
        if (!dir.exists()) {
            return new BaseState(false, AppInfo.NOT_EXIST);
        }
        if (!dir.isDirectory()) {
            return new BaseState(false, AppInfo.NOT_DIRECTORY);
        }
        Collection<File> list = FileUtils.listFiles(dir, this.allowFiles, true);
        if (index < 0 || index > list.size()) {
            state = new MultiState(true);
        } else {
            Object[] fileList = Arrays.copyOfRange(list.toArray(), index, index + this.count);
            state = this.getState(fileList);
        }
        state.putInfo("start", index);
        state.putInfo("total", list.size());
        return state;
    }

    private State getState(Object[] files) {
        MultiState state = new MultiState(true);
        BaseState fileState;
        File file;
        for (Object obj : files) {
            if (obj == null) {
                break;
            }
            file = (File) obj;
            fileState = new BaseState(true);
            fileState.putInfo("url", PathFormat.format(this.getPath(file)));
            state.addState(fileState);
        }
        return state;
    }

    /**
     * modify time :2017年8月28日 14:17:28
     * modify by ： GS
     * 说明,使用basepath替换前缀rootpath
     *
     * @param file
     * @return
     */
    private String getPath(File file) {
        String path = file.getAbsolutePath();
        return path.replace(this.rootPath, basePath);
    }
    private String[] getAllowFiles(Object fileExt) {
        String[] exts;
        String ext;
        if (fileExt == null) {
            return new String[0];
        }
        exts = (String[]) fileExt;
        for (int i = 0, len = exts.length; i < len; i++) {
            ext = exts[i];
            exts[i] = ext.replace(".", "");
        }
        return exts;
    }
}
