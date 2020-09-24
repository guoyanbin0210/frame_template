package com.gyb.base.model;

import com.gyb.base.poi.annotation.GsExcelProperty;
import com.gyb.body.constant.ConcernTypeEnum;
import org.springframework.stereotype.Component;

/**
 * Description:关系表
 * Create Time: 2019-04-18 04:52
 */
@Component
public class SysConcernModel extends BaseModel {
    /**
     * 主表id
     */
    @GsExcelProperty(index = 0, description = "主表id")
    private String sc_main_id;

    /**
     * 主表id描述
     */
    @GsExcelProperty(index = 1, description = "主表id描述")
    private String sc_main_desc;

    private String sc_main_data;


    /**
     * 子表id
     */
    @GsExcelProperty(index = 2, description = "子表id")
    private String sc_son_id;

    /**
     * 子表id描述
     */
    @GsExcelProperty(index = 3, description = "子表id描述")
    private String sc_son_desc;
    private String sc_son_data;

    /**
     * 归属类型
     */
    @GsExcelProperty(index = 4, description = "归属类型")
    private ConcernTypeEnum sc_type;


    public String getSc_main_data() {
        return sc_main_data;
    }

    public void setSc_main_data(String sc_main_data) {
        this.sc_main_data = sc_main_data;
    }

    public String getSc_son_data() {
        return sc_son_data;
    }

    public void setSc_son_data(String sc_son_data) {
        this.sc_son_data = sc_son_data;
    }

    public ConcernTypeEnum getSc_type() {
        return sc_type;
    }

    public void setSc_main_id(String sc_main_id) {
        this.sc_main_id = sc_main_id;
    }

    public String getSc_main_id() {
        return sc_main_id;
    }

    public void setSc_main_desc(String sc_main_desc) {
        this.sc_main_desc = sc_main_desc;
    }

    public String getSc_main_desc() {
        return sc_main_desc;
    }

    public void setSc_son_id(String sc_son_id) {
        this.sc_son_id = sc_son_id;
    }

    public String getSc_son_id() {
        return sc_son_id;
    }

    public void setSc_son_desc(String sc_son_desc) {
        this.sc_son_desc = sc_son_desc;
    }

    public String getSc_son_desc() {
        return sc_son_desc;
    }

    public void setSc_type(ConcernTypeEnum sc_type) {
        this.sc_type = sc_type;
    }


}
