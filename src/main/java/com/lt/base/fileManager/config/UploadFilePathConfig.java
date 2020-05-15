package com.lt.base.fileManager.config;

import com.lt.base.fileManager.constant.BaseFileConstant.StaticPath;
import com.lt.base.util.BaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * Created with GaoShan.
 * Description: 用于访问磁盘文件做映射
 * Date: 2018-12-13
 * Time: 9:58
 */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            StaticPath.getLocPath(registry, StaticPath.STATIC_UPLOAD);//上传的资源
            StaticPath.getLocPath(registry, StaticPath.STATIC_APK);//上传apk
            StaticPath.getLocPath(registry, StaticPath.STATIC_UE);//上传apk
            StaticPath.getLocPath(registry, StaticPath.STATIC_MP);//密云生活网
            StaticPath.getLocPath(registry, StaticPath.STATIC_MP2);//北京密云（生态密云）
            StaticPath.getLocPath(registry, StaticPath.STATIC_MP3);//北京密云（生态密云）
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    /**
     *
     * 文件上传 配置
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(BaseUtils.getSystemTypePath());//文件山川路径
        //文件最大
        factory.setMaxFileSize(DataSize.parse("500MB")); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("1000MB"));
        return factory.createMultipartConfig();
    }
}
