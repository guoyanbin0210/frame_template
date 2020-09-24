package com.gyb.base.ueditor.hunter;

import com.gyb.base.ueditor.PathFormat;
import com.gyb.base.ueditor.define.*;
import com.gyb.base.ueditor.upload.StorageManager;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 图片抓取器
 *
 * @author hancong03@baidu.com
 */
public class ImageHunter {

    private String filename;
    private String savePath;
    private String rootPath;
    private String baseUrlPath;

    private List<String> allowTypes;
    private long maxSize;
    private List<String> filters;

    public ImageHunter(Map<String, Object> conf) {
        this.baseUrlPath = (String) conf.get("baseUrlPath");
        this.filename = (String) conf.get("filename");
        this.savePath = (String) conf.get("savePath");
        this.rootPath = (String) conf.get("rootPath");
        this.maxSize = (Long) conf.get("maxSize");
        this.allowTypes = Arrays.asList((String[]) conf.get("allowFiles"));
        filters = null;
        this.filters = Arrays.asList((String[]) conf.get("filter"));
    }

    public State capture(String[] list) {
        MultiState state = new MultiState(true);
        for (String source : list) {
            state.addState(captureRemoteData(source));
        }
        return state;
    }

    public State captureRemoteData(String urlStr) {
        HttpURLConnection connection;
        URL url;
        String suffix;
        try {
            url = new URL(urlStr);
            if (!validHost(url.getHost())) {
                return new BaseState(false, AppInfo.PREVENT_HOST);
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(true);
            if (!validContentState(connection.getResponseCode())) {
                return new BaseState(false, AppInfo.CONNECTION_ERROR);
            }
            suffix = MIMEType.getSuffix(connection.getContentType());
            if (!validFileType(suffix)) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }
            if (!validFileSize(connection.getContentLength())) {
                return new BaseState(false, AppInfo.MAX_SIZE);
            }
            String savePath = this.getPath(this.savePath, this.filename, suffix);
            String physicalPath = this.rootPath + savePath;
            State state = StorageManager.saveFileByInputStream(connection.getInputStream(), physicalPath);
            if (state.isSuccess()) {
                state.putInfo("url", baseUrlPath+ PathFormat.format(savePath));
                state.putInfo("source", urlStr);
            }
            return state;
        } catch (Exception e) {
            return new BaseState(false, AppInfo.REMOTE_FAIL);
        }
    }

    private String getPath(String savePath, String filename, String suffix) {
        return PathFormat.parse(savePath + suffix, filename);
    }

    private boolean validHost(String hostname) {
        try {
            InetAddress ip = InetAddress.getByName(hostname);
            if (ip.isSiteLocalAddress()) {
                return false;
            }
        } catch (UnknownHostException e) {
            return false;
        }
        return !filters.contains(hostname);
    }

    private boolean validContentState(int code) {
        return HttpURLConnection.HTTP_OK == code;
    }

    private boolean validFileType(String type) {
        return this.allowTypes.contains(type);
    }

    private boolean validFileSize(int size) {
        return size < this.maxSize;
    }

}
