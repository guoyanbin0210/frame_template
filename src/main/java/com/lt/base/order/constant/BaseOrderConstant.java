package com.lt.base.order.constant;

/**
 * Description: 订单模块常量
 * Date: 2018-11-27
 * Time: 13:41
 */
public class BaseOrderConstant {

    /**
     * 订单状态
     */
    public enum OrderStatus {
        ORDER_STATUS_CREATE(0, "创建"),
        ORDER_STATUS_COMPLETE(1, "完成"),
        ORDER_STATUS_DOING(2, "进行中"),
        ORDER_STATUS_CANCEL(3, "取消"),
        ORDER_STATUS_EXCEPTION_1(401, "异常"),

        ;
        private int code;
        private String dri;

        OrderStatus(int code, String dri) {
            this.code = code;
            this.dri = dri;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDri() {
            return dri;
        }

        public void setDri(String dri) {
            this.dri = dri;
        }
    }

    /**
     * 订单支付状态
     * 0. 未支付
     * 1. 支付成功
     * 2. 退款成功
     * 21.退款中
     */
    public enum PayOrderStatus {
        PAY_ORDER_STATUS_CREATE(0, "未支付"),
        PAY_ORDER_STATUS_COMPLETE(1, "支付成功"),
        PAY_ORDER_STATUS_REFUND(2, "退款成功"),
        PAY_ORDER_STATUS_REFUND_ING(21, "退款中"),
        ;
        private int code;
        private String dri;

        PayOrderStatus(int code, String dri) {
            this.code = code;
            this.dri = dri;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDri() {
            return dri;
        }

        public void setDri(String dri) {
            this.dri = dri;
        }
    }


    /**
     * 发货状态
     * 0. 未发货
     * 1. 待收货
     * 2. 已退货
     * 21. 退货中
     */
    public enum DeliverOrderStatus {
        DELIVER_ORDER_DEFAULT(0, "未发货"),
        DELIVER_ORDER_PAID(1, "待收货"),
        DELIVER_ORDER_REFUND(2, "已退货"),
        ;

        private int code;
        private String dir;

        DeliverOrderStatus(int code, String dir) {
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


    }
}
