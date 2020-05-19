package com.lt.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@Component
@Getter
@Setter
public class BaseModel implements Serializable {
    private String id;
    private String create_by;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    private String modify_by;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modify_time;
    private Integer base_status;
    private Integer base_available;
    private Integer base_sort;


    public String getId() {
        return id;
    }

    public String getCreate_by() {
        return create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public String getModify_by() {
        return modify_by;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public Integer getBase_status() {
        return base_status;
    }

    public Integer getBase_available() {
        return base_available;
    }

    public Integer getBase_sort() {
        return base_sort;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setModify_by(String modify_by) {
        this.modify_by = modify_by;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public void setBase_status(Integer base_status) {
        this.base_status = base_status;
    }

    public void setBase_available(Integer base_available) {
        this.base_available = base_available;
    }

    public void setBase_sort(Integer base_sort) {
        this.base_sort = base_sort;
    }

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

