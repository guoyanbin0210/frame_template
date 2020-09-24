package com.gyb.body.user.model;

import com.gyb.base.poi.annotation.GsExcelProperty;
import com.gyb.base.model.BaseModel;
import org.springframework.stereotype.Component;

/**
 * Description:积分管理
 * Create Time: 2019-05-20 04:58
 */
@Component
public class UserIntegralModel extends BaseModel {
    //变化分数:ui_change,类型：整型

    //类型:ui_type,类型：字符串

    //关联用户:user_id,类型：字符串

    /**
     * 变化分数
     */
    @GsExcelProperty(index = 0, description = "变化分数")
    private Integer ui_change;

    /**
     * 类型
     */
    @GsExcelProperty(index = 1, description = "类型")
    private String ui_type;

    /**
     * 关联用户
     */
    @GsExcelProperty(index = 2, description = "关联用户")
    private String user_id;

    public void setUi_change(Integer ui_change) {
        this.ui_change = ui_change;
    }

    public Integer getUi_change() {
        return ui_change;
    }

    public void setUi_type(String ui_type) {
        this.ui_type = ui_type;
    }

    public String getUi_type() {
        return ui_type;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

}
