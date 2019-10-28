package com.example.cxfdemo.service.impl;

import com.example.cxfdemo.annotation.AutoPublish;
import com.example.cxfdemo.service.DemoService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * 测试webservice
 *
 * @author Sean
 * 2019/10/28
 */
@WebService(endpointInterface = "com.example.cxfdemo.service.DemoService2", serviceName = "demoService2")
@AutoPublish(value = "/demoService2")
@Service
public class DemoServiceImpl2 implements DemoService {
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }
}
