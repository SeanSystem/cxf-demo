package com.example.cxfdemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author Sean
 * @date 2019/11/21
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler(NullPointerException.class)
    public void test(Exception e){
        log.error("空指针异常:",e);
    }
}
