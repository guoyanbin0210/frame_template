package com.gyb.body.constant;

public enum DirEnum {

    /**
     * bd_data 表示 资讯类型的分类
     */
    NEWS_TYPE("tb_em_news-news_type","资讯类型"),
    NEWS_TYPE_SECOND("tb_em_news-news_type_second","资讯类型-2级"),

    THIRD_PART_TYPE("tb_ott_services-ser_type","第三方服务类型"),

    USER_JOB_TYPE("tb_user_main-job_type","用户职业"),
    WENXZHENG_QUE_TYPE("tb_wen_zheng_advise-question_type","问政问题分类"),

    INTEGRAL_RULE("virtual-INTEGRAL_RULE","积分规则"),
    ;

    private String code ;
    private String des;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    DirEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }
}
