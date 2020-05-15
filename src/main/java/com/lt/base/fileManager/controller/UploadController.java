package com.lt.base.fileManager.controller;

import com.lt.base.controller.BaseController;
import com.lt.base.model.SysFileModel;
import com.lt.base.service.SysFileService;
import com.lt.base.util.BaseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-12
 * Time: 15:58
 */
@Api(value = "文件上传操作接口", tags = {"e文件上传操作接口"})
@RestController
public class UploadController extends BaseController {

    @Resource
    private SysFileService sysFileService;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ApiIgnore
    @ApiOperation(value = "文件上传", notes = "")
    @PostMapping(value = "/api/fileManager/upload")
    public HashMap uploadFile2(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
        return uploadFile(request, multipartFile);
    }

    @ApiOperation(value = "文件上传", notes = "")
    @PostMapping(value = "/api_p/Eleditor/fileManager/upload")
    public HashMap api_p(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
        HashMap hashMap = uploadFile(request, multipartFile);
        String s = (String) hashMap.get("urlPath");
        hashMap.put("url", s);
        return hashMap;
    }

    @ApiIgnore
    @PostMapping(value = "/fileManager/upload")
    public HashMap uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
        HashMap<String, Object> returnMap = getReturnMap();
        long size = multipartFile.getSize();
        System.out.println(size/1024.0/1024.0);
        if (multipartFile != null) {
            //上传文件名
            String filename = multipartFile.getOriginalFilename();
            String fileType = multipartFile.getContentType();//image or xxx
            if (Objects.requireNonNull(fileType).lastIndexOf("/") > 0
                    && Objects.requireNonNull(filename).lastIndexOf(".") > 0
            ) {
                fileType = fileType.substring(0, fileType.lastIndexOf("/"));
                filename = System.currentTimeMillis() + "." + filename.substring(filename.lastIndexOf(".") + 1);
            } else {
                fileType = "other";
                filename = System.currentTimeMillis() + "";
            }
            //http://localhost:8080/projeptName/
            //保存时添加域名
           // String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/";
          //  String basePath = "/upload/";
            String basePath = "/uploads/";
            // /upload/image/20170905
           // String videoPath = fileType + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
            String path;
            path = BaseUtils.getSystemTypePath();
          //  path += videoPath;
            // /upload/image/20170905/文件名
           // videoPath += filename;
            File newFile = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个SysFileService
           /* if (!newFile.getParentFile().exists()) {
                if (!newFile.getParentFile().mkdirs()) {
                    returnMap.put("message", "上传路径创建失败,请联系管理员");
                    return returnMap;
                }
            }*/
            //添加访问前缀
            //http://localhost:8080/projeptName/upload/image/20170905/文件名
           // String urlPath = basePath + videoPath;
            String urlPath = basePath + filename;
            //将上传文件保存到一个目标文件当中
            try {
                multipartFile.transferTo(newFile);
                long length = newFile.length();
                String absolutePath = newFile.getAbsolutePath();
                //String url = "/upload/" + videoPath;
                String url = "/uploads/"+ filename;
                SysFileModel sysFileModel = new SysFileModel();
                sysFileModel.init(sysFileModel);
                sysFileModel.setSf_show_name(multipartFile.getName());
                sysFileModel.setSf_norm_local_path(absolutePath);
                sysFileModel.setSf_norm_url_path(url);
                sysFileModel.setSf_norm_size(length);
                sysFileModel.setSf_compress_local_path("");
                sysFileModel.setSf_compress_url_path("");
                sysFileModel.setSf_compress_size(0);
                sysFileModel.setSf_type("");
                Integer insert = sysFileService.insert(sysFileModel);
                //封装百度上传信息
                returnMap.put("state", "SUCCESS");
                returnMap.put("id", sysFileModel.getId());
                returnMap.put("url", url);
                returnMap.put("urlPath", urlPath);
                returnMap.put("path", absolutePath);
                returnMap.put("original", filename);
                returnMap.put("file_size", length);
                returnMap.put("msg", "SUCCESS");
                getReturnMapSuccess(returnMap);
            } catch (IOException e) {
                e.printStackTrace();
                returnMap.put("state", e.getMessage());
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;

    }


    @ApiIgnore
    @PostMapping(value = "/fileManager/uploadList")
    public HashMap uploadFileList(HttpServletRequest request, @RequestParam("source") MultipartFile[] multipartFile) {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            for (MultipartFile file : multipartFile) {
                //上传文件名
                String filename = file.getOriginalFilename();
                String fileType = file.getContentType();//image or xxx
                if (Objects.requireNonNull(fileType).lastIndexOf("/") > 0
                        && Objects.requireNonNull(filename).lastIndexOf(".") > 0
                ) {
                    fileType = fileType.substring(0, fileType.lastIndexOf("/"));
                    filename = System.currentTimeMillis() + "." + filename.substring(filename.lastIndexOf(".") + 1);
                } else {
                    fileType = "other";
                    filename = System.currentTimeMillis() + "";
                }
                //http://localhost:8080/projeptName/
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/";
                // /upload/image/20170905
                //String videoPath = fileType + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
                String videoPath = fileType + "/";
                String path;
                path = BaseUtils.getSystemTypePath();
                path += videoPath;
                // /upload/image/20170905/文件名
                videoPath += filename;
                File newFile = new File(path, filename);
                //判断路径是否存在，如果不存在就创建一个SysFileService
                if (!newFile.getParentFile().exists()) {
                    if (!newFile.getParentFile().mkdirs()) {
                        returnMap.put("message", "上传路径创建失败,请联系管理员");
                        return returnMap;
                    }
                }
                //添加访问前缀
                //http://localhost:8080/projeptName/upload/image/20170905/文件名
                String urlPath = basePath + videoPath;
                //将上传文件保存到一个目标文件当中
                try {
                    file.transferTo(newFile);
                    long length = newFile.length();
                    String absolutePath = newFile.getAbsolutePath();
                    String url = "/upload/" + videoPath;
                    SysFileModel sysFileModel = new SysFileModel();
                    sysFileModel.init(sysFileModel);
                    sysFileModel.setSf_show_name(file.getName());
                    sysFileModel.setSf_norm_local_path(absolutePath);
                    sysFileModel.setSf_norm_url_path(url);
                    sysFileModel.setSf_norm_size(length);
                    sysFileModel.setSf_compress_local_path("");
                    sysFileModel.setSf_compress_url_path("");
                    sysFileModel.setSf_compress_size(0);
                    sysFileModel.setSf_type("");
                    Integer insert = sysFileService.insert(sysFileModel);
                    //封装百度上传信息
                    returnMap.put("state", "SUCCESS");
                    returnMap.put("id", sysFileModel.getId());
                    returnMap.put("url", url);
                    returnMap.put("urlPath", urlPath);
                    returnMap.put("path", absolutePath);
                    returnMap.put("original", filename);
                    returnMap.put("file_size", length);
                    returnMap.put("msg", "SUCCESS");
                    getReturnMapSuccess(returnMap);
                } catch (IOException e) {
                    e.printStackTrace();
                    returnMap.put("state", e.getMessage());
                }
            }
        }else{
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }

        return returnMap;

    }

    @ApiIgnore
    @ApiOperation(value = "文件上传-根据id查询", notes = "")
    @PostMapping(value = "/api/fileManager/getFileById")
    public HashMap getFileById2(HttpServletRequest request, @RequestParam("id") String id) {
        return getFileById2(request, id);
    }

    @ApiIgnore
    @PostMapping(value = "/fileManager/getFileById")
    public HashMap getFileById(HttpServletRequest request, @RequestParam("id") String id) {
        HashMap<String, Object> returnMap = getReturnMap();
        SysFileModel sysFileModel = sysFileService.selectById(id);
        if (sysFileModel != null) {
            getReturnMapSelect(returnMap, sysFileModel);
        }
        return returnMap;
    }


}
