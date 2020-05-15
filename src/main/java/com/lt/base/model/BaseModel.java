package com.lt.base.model;

import com.lt.base.util.BaseUtils;
import com.lt.base.util.BeanRefUtil;
import com.lt.body.user.utils.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-11-26
 * Time: 17:10
 */
@Component
@Getter
@Setter
public class BaseModel implements Serializable {
    private String id;
    private String create_by;
    private Date create_time;
    private String modify_by;
    private Date modify_time;
    private Integer base_status;
    private Integer base_available;
    private Integer base_sort;

    /**
     * 初始化对象
     *
     * @param baseModel
     */
    public void init(BaseModel baseModel) {

        baseModel.setId(BaseUtils.getUUID());
        baseModel.setCreate_by(getUserName());
        baseModel.setCreate_time(new Date());

        baseModel.setBase_status(1);//数据默认状态
        baseModel.setBase_available(1);//数据默认可见
        baseModel.setBase_sort(1);//默认排序1 值相同按照时间排序

    }

    private String getUserName() {
        String username = "sys";
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (UnavailableSecurityManagerException e) {
            username = "api用户";
        }
        if (subject != null) {
            if (subject.getPrincipal() != null) {
                SysLoginModel sysLoginModel = (SysLoginModel) subject.getPrincipal();
                username = sysLoginModel.getUser_name();
            }
        }
        return username;
    }

    private String getUserName(HttpServletRequest request) {
        String username = "sys";
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (UnavailableSecurityManagerException e) {
            username = "api用户";
        }
        if (subject != null) {
            if (subject.getPrincipal() != null) {
                SysLoginModel sysLoginModel = (SysLoginModel) subject.getPrincipal();
                username = sysLoginModel.getId();
            }
        } else {
            String token = request.getHeader("Authentication");
            if (token != null) {
                username = JwtUtil.verifyToken(token);
            }
        }

        return username;
    }

    /**
     * 插入
     *
     * @param request
     * @param baseModel
     */
    public void getInstanceForInsert(HttpServletRequest request, BaseModel baseModel) {
        init(baseModel);
        BeanRefUtil.getInstanceByRequest(request, baseModel, true);
    }


    /**
     * 更新
     *
     * @param request
     * @param baseModel
     */
    public void getInstanceForUpdate(HttpServletRequest request, BaseModel baseModel) {
        baseModel.setModify_by(getUserName(request));
        baseModel.setModify_time(new Date());
        BeanRefUtil.getInstanceByRequest(request, baseModel, true);
    }

    public void getInstanceForSelect(HttpServletRequest request, BaseModel baseModel) {
        BeanRefUtil.getInstanceByRequest(request, baseModel, false);
    }






}

