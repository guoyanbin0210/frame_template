package com.lt.body.constant;



public enum ConcernTypeEnum {
    EmNews_GOOD("EmNews_GOOD", "点赞"),
    EmNews_SHARE("EmNews_SHARE", "分享"),

    /**
     * main_id = news_id
     * son_id = user_id
     */
    EmNews_REPLY("EmNews_REPLY", "评论"),
    EmNews_ADMIN_REPLY("EmNews_ADMIN_REPLY", "管理员回复评论"),
    EmNews_ATTENTION("EmNews_ATTENTION", "关注"),
    EmNews_COLLECT("EmNews_COLLECT", "收藏"),
    EmNews_BROWSE_LOG("EmNews_BROWSE_LOG", "浏览记录"),


    //问政模块
    /**
     * 我向区长说句话
     */
    WENZHENG_ASK_REPLY("WENZHENG_ASK_REPLY", "我向区长说句话的回复"),
    WENZHENG_ADVICE_REPLY("WENZHENG_ADVICE_REPLY", "意见建议的回复"),

    WENZHENG_PEOPLE_CONGRESS_REPLY("WENZHENG_PEOPLE_CONGRESS_REPLY", "人代会留言"),
    WENZHENG_PEOPLE_CONGRESS_ISSUE("WENZHENG_PEOPLE_CONGRESS_ISSUE", "人代会议题"),

    /**
     * main_id = news_id
     * son_id = user_id
     */
    EmNews_JOIN_ACTIVITY("EmNews_JOIN_ACTIVITY", "参与的活动"),
    EmNews_CANCLE_JOIN_ACTIVITY("EmNews_CANCLE_JOIN_ACTIVITY", "取消参与的活动"),

    EmNews_REPLY_NOTIFI("EmNews_REPLY_NOTIFI", "评论回复通知"),

    EmNews_AUDIT_REFUSE("EmNews_AUDIT_REFUSE", "文章审核为通过原因"),

    ;

    ConcernTypeEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }

    private String code;
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
}