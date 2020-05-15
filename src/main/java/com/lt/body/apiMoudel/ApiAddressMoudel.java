package com.lt.body.apiMoudel;

import lombok.Data;

@Data
public class ApiAddressMoudel {

    private String id;

    private String address;

    /**
     * 邮政编码
     */
    private String postal_code;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * logo路径
     */
    private String logo_url;

    /**
     * 照片路径
     */
    private String picture_url;


}
