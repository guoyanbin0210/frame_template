package com.lt.body.excelPort;

import com.lt.base.excel.ExcelColumn;
import lombok.Data;

import java.util.Date;

@Data
public class ExcelModel {
    @ExcelColumn(value = "企业名称", col = 1)
    private String company_name;

    @ExcelColumn(value = "联系人", col = 2)
    private String linkman;

    @ExcelColumn(value = "联系方式", col = 2)
    private String phone;

    @ExcelColumn(value = "所属行业", col = 3)
    private String industry;

    @ExcelColumn(value = "融资用途", col = 4)
    private String use_type;

    @ExcelColumn(value = "所选服务", col = 5)
    private String choose_type;

    @ExcelColumn(value = "银行名称", col = 6)
    private String bank_name;

    @ExcelColumn(value = "金额", col = 7)
    private String loan_money;

    @ExcelColumn(value = "是否需要无还本续贷", col = 8)
    private String loan_type;

    @ExcelColumn(value = "其他需求", col = 9)
    private String other;

    @ExcelColumn(value = "创建时间", col = 10)
    private String create_time;

    @Override
    public String toString() {
        return "ExcelModel{" +
                "company_name='" + company_name + '\'' +
                ", linkman='" + linkman + '\'' +
                ", phone='" + phone + '\'' +
                ", industry='" + industry + '\'' +
                ", use_type='" + use_type + '\'' +
                ", choose_type='" + choose_type + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", loan_money=" + loan_money +
                ", loan_type=" + loan_type +
                ", create_time=" + create_time +
                '}';
    }
}



