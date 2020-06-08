package com.lt.body.user.model;

import com.lt.base.model.BaseModel;
import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;


@Component
public class IntegralRuleModel extends BaseModel {


    /**
     * 标题
     */
    @GsExcelProperty(index = 0, description = "标题")
    private String ir_title;

    /**
     * 分值
     */
    @GsExcelProperty(index = 1, description = "分值")
    private Integer ir_val;

    /**
     * 日上限
     */
    @GsExcelProperty(index = 2, description = "日上限")
    private Integer ir_day_max;

    /**
     * 备注
     */
    @GsExcelProperty(index = 3, description = "备注")
    private String ir_des;

    /**
     * SINGLE 1次
     * CIRCULATION 循环
     */
    @GsExcelProperty(index = 4, description = "类型")
    private String ir_type;

    @GsExcelProperty(index = 5, description = "次数")
    private Integer ir_day_count;

    public Integer getIr_day_count() {
        return ir_day_count;
    }

    public void setIr_day_count(Integer ir_day_count) {
        this.ir_day_count = ir_day_count;
    }

    public String getIr_type() {
        return ir_type;
    }

    public void setIr_type(String ir_type) {
        this.ir_type = ir_type;
    }

    public void setIr_title(String ir_title) {
        this.ir_title = ir_title;
    }

    public String getIr_title() {
        return ir_title;
    }

    public void setIr_val(Integer ir_val) {
        this.ir_val = ir_val;
    }

    public Integer getIr_val() {
        return ir_val;
    }

    public void setIr_day_max(Integer ir_day_max) {
        this.ir_day_max = ir_day_max;
    }

    public Integer getIr_day_max() {
        return ir_day_max;
    }

    public void setIr_des(String ir_des) {
        this.ir_des = ir_des;
    }

    public String getIr_des() {
        return ir_des;
    }

}
