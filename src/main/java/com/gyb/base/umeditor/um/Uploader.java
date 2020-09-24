package com.gyb.base.umeditor.um;

import com.alibaba.fastjson.annotation.JSONField;
import com.gyb.base.fileManager.constant.BaseFileConstant;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * UEditor文件上传辅助类
 */
public class Uploader {

    // 输出文件地址
    @JSONField(name = "url")
    private String url = "";
    // 上传文件名
    @JSONField(name = "name")
    private String fileName = "";
    // 状态
    @JSONField(name = "state")
    private String state = "";
    // 文件类型
    @JSONField(name = "type")
    private String type = "";
    // 原始文件名
    @JSONField(name = "originalName")
    private String originalName = "";
    // 文件大小
    @JSONField(name = "size")
    private long size = 0;

    private HttpServletRequest request = null;
    private String title = "";

    // 保存路径
    private String savePath = BaseFileConstant.StaticPath.getLocPath(BaseFileConstant.StaticPath.STATIC_UPLOAD);
    // 文件允许格式
    private String[] allowFiles = {".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp"};
    // 文件大小限制，单位KB
    private int maxSize = 10000;

    private HashMap<String, String> errorInfo = new HashMap<String, String>();

    public Uploader(HttpServletRequest request) {
        this.request = request;
        HashMap<String, String> tmp = this.errorInfo;
        tmp.put("SUCCESS", "SUCCESS"); //默认成功
        tmp.put("NOFILE", "未包含文件上传域");
        tmp.put("TYPE", "不允许的文件格式");
        tmp.put("SIZE", "文件大小超出限制");
        tmp.put("ENTYPE", "请求类型ENTYPE错误");
        tmp.put("REQUEST", "上传请求异常");
        tmp.put("IO", "IO异常");
        tmp.put("DIR", "目录创建失败");
        tmp.put("UNKNOWN", "未知错误");

    }

    public String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    }

    public void doIfNotExitCreate(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

    public void upload(MultipartFile upfile) {
        if (upfile.isEmpty()) {
            this.state = this.errorInfo.get("NOFILE");
        } else {
            this.type = upfile.getContentType();
            this.size = upfile.getSize();
            this.originalName = upfile.getOriginalFilename();
            if (originalName != null) {
                originalName = originalName.substring(originalName.lastIndexOf(File.separator) + 1);
                if (!this.checkFileType(this.originalName)) {
                    this.state = this.errorInfo.get("TYPE");
                    return;
                }
            }
            this.fileName = getName(originalName);
            String currDay = new SimpleDateFormat("yyyyMMdd").format(new Date());
            savePath += currDay + File.separator + fileName;
            this.url = getBaseUrl(request) + "upload/" + currDay + "/" + fileName;
            Path newPath = Paths.get(savePath);

            try {
                doIfNotExitCreate(newPath);
            } catch (IOException e) {
                e.printStackTrace();
                this.state = this.errorInfo.get("TYPE");
                return;
            }
            try {
                upfile.transferTo(newPath);
                this.state = this.errorInfo.get("SUCCESS");
            } catch (IOException e) {
                e.printStackTrace();
                this.state = this.errorInfo.get("IO");
            }
        }
    }




//    /**
//     * 接受并保存以base64格式上传的文件
//     *
//     * @param fieldName
//     */
//    public void uploadBase64(String fieldName) {
//        String savePath = this.getFolder(this.savePath);
//        String base64Data = this.request.getParameter(fieldName);
//        this.fileName = this.getName("test.png");
//        this.url = savePath + "/" + this.fileName;
//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            File outFile = new File(this.getPhysicalPath(this.url));
//            OutputStream ro = new FileOutputStream(outFile);
//            byte[] b = decoder.decodeBuffer(base64Data);
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {
//                    b[i] += 256;
//                }
//            }
//            ro.write(b);
//            ro.flush();
//            ro.close();
//            this.state = this.errorInfo.get("SUCCESS");
//        } catch (Exception e) {
//            this.state = this.errorInfo.get("IO");
//        }
//    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    private boolean checkFileType(String fileName) {
        for (String ext : this.allowFiles) {
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @return
     */
    private String getName(String fileName) {
        Random random = new Random();
        return this.fileName = "" + random.nextInt(10000)
                + System.currentTimeMillis() + this.getFileExt(fileName);
    }

    /**
     * 根据字符串创建本地目录 并按照日期建立子目录返回
     *
     * @param path
     * @return
     */
    private String getFolder(String path) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        File dir = new File(this.getPhysicalPath(path));
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                this.state = this.errorInfo.get("DIR");
                return "";
            }
        }
        return path;
    }

    /**
     * 根据传入的虚拟路径获取物理路径
     *
     * @param path
     * @return
     */
    private String getPhysicalPath(String path) {
        String servletPath = this.request.getServletPath();
        String realPath = this.request.getSession().getServletContext()
                .getRealPath(servletPath);
        return new File(realPath).getParent() + "/" + path;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setAllowFiles(String[] allowFiles) {
        this.allowFiles = allowFiles;
    }

    public void setMaxSize(int size) {
        this.maxSize = size;
    }

    public long getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public String getOriginalName() {
        return this.originalName;
    }
}
