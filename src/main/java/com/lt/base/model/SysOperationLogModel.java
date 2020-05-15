package com.lt.base.model;

import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

/**
 * Created with GaoShan.
 * Description:
 * Create Time: 2018-12-27 09:41
 */
@Component
public class SysOperationLogModel extends BaseModel {

    /**
     * 登录id
     */
    @GsExcelProperty(index = 0, description = "登录id")
    private String sol_login_id;

    /**
     * 描述
     */
    @GsExcelProperty(index = 1, description = "描述")
    private String sol_des;

    public void setSol_login_id(String sol_login_id) {
        this.sol_login_id = sol_login_id;
    }

    public String getSol_login_id() {
        return sol_login_id;
    }

    public void setSol_des(String sol_des) {
        this.sol_des = sol_des;
    }

    public String getSol_des() {
        return sol_des;
    }

}
