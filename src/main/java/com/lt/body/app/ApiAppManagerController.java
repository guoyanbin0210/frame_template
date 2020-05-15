/*
package com.lt.body.app;

import com.gs.base.controller.BaseController;
import com.gs.base.utils.BaseModelUtils;
import com.gs.base.utils.BaseUtils;
import com.gs.base.vo.ResponseVo;
import com.gs.xinlianxin.model.AppManagerModel;
import com.gs.xinlianxin.service.AppManagerService;
import com.gs.xinlianxin.utils.ApkUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

*/
/**
 * Created with GaoShan.
 * Create Time: 2019/10/03 14:57
 * Description: app管理
 *//*

@RestController
@Log4j2
@Api(tags = {"app管理操作接口2"})
public class ApiAppManagerController extends BaseController {

    @Resource
    private AppManagerService appManagerService;

    @ApiOperation("android-App-查询最新版本")
    @PostMapping("/api_p/AppManager/android/select")
    public ResponseVo<AppManagerModel> selectList_Android_AppManager() {
        AppManagerModel appManagerModel = appManagerService.selectNewest();
        return ResponseVo.getSelectOneResultMap(appManagerModel);
    }

    @ApiOperation("android-App-上传")
    @PostMapping(value = "/api/AppManager/uploadApk")
    public ResponseVo uploadApk(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile != null) {
            String basePath =
                    request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/";
            String videoPath = "apk/" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".apk";
            String path = BaseUtils.getDownloadPath() + videoPath;
            File newFile = new File(path);
            //判断路径是否存在，如果不存在就创建一个SysFileService
            if (!newFile.getParentFile().exists()) {
                if (!newFile.getParentFile().mkdirs()) {
                    log.error("上传路径创建失败,请联系管理员");
                    return ResponseVo.getResultMapMsg("上传路径创建失败,请联系管理员");
                }
            }
            //添加访问前缀
            //http://localhost:8080/projeptName/upload/image/20170905/文件名
            String urlPath = basePath + videoPath;
            //将上传文件保存到一个目标文件当中
            try {
                multipartFile.transferTo(newFile);
                AppManagerModel appManagerModel = new AppManagerModel();
                BaseModelUtils.getInitBaseModel(appManagerModel);
                appManagerModel.setDown_url(urlPath);
                ApkUtils.functionDo00(newFile.getAbsolutePath(), appManagerModel);
                Integer insert = appManagerService.insert(appManagerModel);
                return ResponseVo.getResultMap(insert);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseVo.getResultMapMsg(e.getMessage());
            }
        } else {
            return ResponseVo.getResultMapMsg("未找到文件，请核对参数名：[file]");
        }
    }
}
*/
