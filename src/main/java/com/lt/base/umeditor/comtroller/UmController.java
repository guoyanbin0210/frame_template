package com.lt.base.umeditor.comtroller;

import com.alibaba.fastjson.JSON;
import com.lt.base.umeditor.um.Uploader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: MyController
 * Description:
 * date: 2019/5/24 19:32
 *
 * @author GaoShan
 */
@Controller
@ApiIgnore
public class UmController {




    @RequestMapping("/api_p/um/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("upfile") MultipartFile upfile) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Uploader up = new Uploader(request);
        String[] fileType = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        up.upload(upfile);

        String callback = request.getParameter("callback");
        String result = JSON.toJSONString(up);
        if (callback == null) {
            response.getWriter().print(result);
        } else {
            response.getWriter().print("<script>" + callback + "(" + result + ")</script>");
        }

    }
}
