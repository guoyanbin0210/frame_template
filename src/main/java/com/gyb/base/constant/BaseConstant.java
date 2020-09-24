package com.gyb.base.constant;


public class BaseConstant {
    /**
     * 管理员角色id 锁定在这里不可删除不可修改
     */
    public final static String ROLE_ADMIN_ID = "d8656a6e863a46be9179d45967e58d4d";
    public final static String PERMISSION_MENU_PRE = "PERMISSION_MENU_";
    public final static String ROLE_MENU_PRE = "ROLE_MENU_";

    /**
     * 增:0,删:1,改:2.4.my0.1.5,查:3(默认为查)
     */
    public enum SQL_TYPE_ENUM {
        INSERT(0),
        DELETE(1),
        UPDATE(2),
        SELECT(3);
        private int code;
        SQL_TYPE_ENUM(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
    }

    public enum ROLE_TYPE_ENUM {
        TYPE_API(1),//接口
        TYPE_MENU(2);//菜单
        private int code;
        ROLE_TYPE_ENUM(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
    }

    public enum PERMISSION_TYPE_ENUM {
        TYPE_API(1),//接口
        TYPE_MENU(2);//菜单
        private int code;
        PERMISSION_TYPE_ENUM(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
    }


    /**
     * 枚举 常量 详单与代码注释
     */
    public enum Response_MENU {

        //充电结束
        REQUEST_DO_FAILED(0, "操作失败"),//初始状态
        REQUEST_DO_SUCCESS(1, "操作成功"),//结束状态，操作流程正常执行结束
        REQUEST_SIGN_FAILED(1, "注册成功，请等待管理员审核，审核结果将以短信方式发送到您手机"),//结束状态，操作流程正常执行结束
        REQUEST_USER_PHONE_FAILED(0, "手机号已经被注册"),//异常状态
        REQUEST_USER_PHONE_NOTFOUND(0, "手机号尚未注册"),//异常状态
        REQUEST_USER_NOTFOUND(0, "用户尚未注册"),//异常状态
        REQUEST_USER_CODE_FAILED(0, "获取验证码异常"),//异常状态
        REQUEST_SYSTEM_FAILED(0, "系统发生未知异常，请联系系统管理员"),//初始状态
        REQUEST_PASSWORD_CHECK_ERROR(0, "密码错误"),//异常状态
        REQUEST_NOTECODE_CHECK_ERROR(0, "验证码错误"),//异常状态
        REQUEST_FIND_NOT_FOUND(0, "暂无数据"),//异常状态，没有数据
        REQUEST_FILE_CREATE_FAILED(0, "上传路径创建失败,请联系管理员"),//异常状态，没有数据
        REQUEST_FILE_NOT_FOUND(0, "未找到文件，请核对参数名：[file]"),//异常状态，没有数据
        REQUEST_BANNER_NOT_FOUND(0, "您已被禁言，不能发表评论"),//异常状态，没有数据
        REQUEST_OPENID_FAILED(0, "当前账号已经存在，请勿重复注册"),//异常状态，没有数据
        REQUEST_OPENID_NOTFAILED(0, "openId异常，请重试"),


        REQUEST_LOGIN_SUCCESS(101, "登录成功"),//结束状态，操作流程正常执行结束
        REQUEST_SIGN_SUCCESS(102, "注册成功"),//结束状态，操作流程正常执行结束
        REQUEST_INSERT_CONTENT(201, "文章已存在，请勿重复提交"),//异常状态
        REQUEST_SELECT_NOT_FOUND(2, "没有查询到结果"),//异常状态，没有数据
        REQUEST_INSERT_FAILED(201, "插入失败"),//异常状态
        REQUEST_INSERT_SUSSES(201, "插入成功"),//正常状态
        REQUEST_DELETE_FAILED(202, "删除失败"),//异常状态
        REQUEST_UPDATE_FAILED(203, "更新失败"),//异常状态
        REQUEST_SELECT_FAILED(204, "查询失败"),//异常状态
        REQUEST_SELECT_SUSSES(209, "查询成功"),//正常状态
        REQUEST_DOWNLOAD_FAILED(205, "下载失败"),//异常状态
        REQUEST_UPLOAD_FAILED(206, "上传失败"),//异常状态
        REQUEST_USER_NAME_FAILED(207, "用户名错误"),//异常状态
        REQUEST_PASSWORD_FAILED(208, "密码错误"),//异常状态
        //2019年5月10日 15:50:30
        REQUEST_USER_PHONE_CHECK(210, "请输入正确手机号"),//异常状态
        REQUEST_USER_PHONE_EXIST(211, "手机号已经被注册"),//异常状态

        REQUEST_CHECK_CODE_NULL(213, "未匹配到验证码"),//异常状态
        REQUEST_PHONE_NO_SIGN(213, "手机号未注册"),//结束状态，操作流程正常执行结束
        REQUEST_PASSWORD_ERROR(214, "密码错误"),//结束状态，操作流程正常执行结束

        REQUEST_IS_EXIT(215, "请勿重复提交"),//结束状态，操作流程正常执行结束

        REQUEST_NOT_JOIN_ACTIVITY(216, "您尚未报名"),//结束状态，操作流程正常执行结束

        REQUEST_PASSWORD_CODE_IS_EMPTY(217, "验证码和密码不能同时为空"),//结束状态，操作流程正常执行结束

        REQUEST_NO_RE_JONIN(218, "取消报名后不能再次报名"),//结束状态，操作流程正常执行结束
        REQUEST_ACCOUNT_PASSWORD_ERROR(219, "帐号或密码错误"),//结束状态，操作流程正常执行结束
        REQUEST_BIND_WX_OPENID_ERROR(220, "手机号已绑定微信帐号，请更换手机号"),//结束状态，操作流程正常执行结束


        REQUEST_REQUEST_CHECK(301, "请勿频繁访问"),//异常状态



        ;

        private int code;
        private String dir;

        Response_MENU(int code, String dir) {
            this.code = code;
            this.dir = dir;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public static Response_MENU getCodeDriObj(int code) {
            for (Response_MENU c : Response_MENU.values()) {
                if (c.getCode() == code)
                    return c;
            }
            return null;
        }

    }

}
