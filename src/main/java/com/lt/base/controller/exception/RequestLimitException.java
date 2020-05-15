package com.lt.base.controller.exception;
/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-5-13
 * Time: 17:08
 */
public class RequestLimitException extends Exception {
    private static final long serialVersionUID = 1364225358754654702L;  
  
    public RequestLimitException() {  
        super("请求超出设定的限制");
    }  
  
    public RequestLimitException(String message) {  
        super(message);  
    }  
  
}  