package com.lt.base.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created with GaoShan.
 * Create Time: 2019/9/6 17:06
 * Description:
 */
@RestControllerAdvice
@Log4j2
public aspect ExceptionControllerAdvice extends ResponseEntityExceptionHandler {


}
