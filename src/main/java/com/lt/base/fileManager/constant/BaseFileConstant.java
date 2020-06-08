package com.lt.base.fileManager.constant;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.io.File;

public class BaseFileConstant {
    /**
     * 枚举 常量 详单与代码注释
     * BaseFileConstant.StaticPath
     */
    public enum StaticPath {
        UPLOAD_LOCAL_PATH(
                0,
                "E:/file/uploads/",
                "/server/templete/file/"),
        STATIC_UPLOAD(
                1,
                "/uploads/**",
                "E:/file/uploads/",
                "/server/templete/file/"),
        STATIC_APK(
                2,
                "/download/**",
                "E:/file/uploads/",
                "/server/templete/file/"),
        STATIC_UE(
                3,
                "/ue/**",
                "E:/file/uploads//ue/",//这个在java 源码中添加了 ueditor
                "/server/templete/file/ue/"),
        STATIC_MP( 
                4,
                "/MP_verify_pzo7Gi2YRdxupVnw.txt",
                "E:/file/uploads/mp/MP_verify_pzo7Gi2YRdxupVnw.txt",//这个在java 源码中添加了 ueditor
                "/server/templete/file/mp/MP_verify_pzo7Gi2YRdxupVnw.txt"),
        STATIC_MP2( 
                5,
                "/MP_verify_iF6msRZ1zGy00JGw.txt",
                "E:/file/uploads/mp/MP_verify_iF6msRZ1zGy00JGw.txt",//这个在java 源码中添加了 ueditor
                "/server/templete/file/mp/MP_verify_iF6msRZ1zGy00JGw.txt"),
        STATIC_MP3( 
                5,
                "/tool/ueditor/php/**",
                "E:/file/uploads/",//这个在java 源码中添加了 ueditor
                "/server/templete/file/mp/MP_verify_iF6msRZ1zGy00JGw.txt"),
        ;

        private int code;
        private String access_path;
        private String winLocPath;
        private String linLocPath;


        StaticPath(int code, String access_path, String winLocPath, String linLocPath) {
            this.code = code;
            this.access_path = access_path;
            this.winLocPath = winLocPath;
            this.linLocPath = linLocPath;
        }

        StaticPath(int code, String winLocPath, String linLocPath) {
            this.code = code;
            this.winLocPath = winLocPath;
            this.linLocPath = linLocPath;
        }

        StaticPath(int code, String access_path) {
            this.code = code;
            this.access_path = access_path;
        }

        public static void getLocPath(ResourceHandlerRegistry registry, StaticPath staticPath) throws Exception {
            String locPath;
            if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                locPath = staticPath.getWinLocPath();
            } else {
                locPath = staticPath.getLinLocPath();
            }
            registry.addResourceHandler(staticPath.getAccess_path())
                    .addResourceLocations("file:" + locPath);
            File file = new File(locPath);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    throw new Exception("资源文件创建失败：" + file.getAbsolutePath());
                }
            }
        }

        public static String getLocPath(StaticPath staticPath) {
            String locPath;
            if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                locPath = staticPath.getWinLocPath();
            } else {
                locPath = staticPath.getLinLocPath();
            }
            return locPath;
        }


        public String getWinLocPath(StaticPath staticPath) {
            return winLocPath;
        }

        public String getWinLocPath() {
            return winLocPath;
        }

        public void setWinLocPath(String winLocPath) {
            this.winLocPath = winLocPath;
        }

        public String getLinLocPath() {
            return linLocPath;
        }

        public void setLinLocPath(String linLocPath) {
            this.linLocPath = linLocPath;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getAccess_path() {
            return access_path;
        }

        public void setAccess_path(String access_path) {
            this.access_path = access_path;
        }
    }

}
