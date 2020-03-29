package com.example.cxfdemo.service;

import com.example.cxfdemo.entity.Jsyd;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.math.BigDecimal;

@WebService
public interface DemoService2 {
    /**
     * 测试
     * @param name
     * @return
     */
    String sayHello(@WebParam(name = "name") String name);

    BigDecimal testDecimal(@WebParam(name = "nyd") BigDecimal nyd);

    Jsyd testObj(@WebParam(name = "jsyd") Jsyd jsyd);

}
