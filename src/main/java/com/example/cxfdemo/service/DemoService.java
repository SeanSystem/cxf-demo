package com.example.cxfdemo.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface DemoService {
    /**
     * 测试
     * @param name
     * @return
     */
    String sayHello(@WebParam(name = "name") String name);

}
