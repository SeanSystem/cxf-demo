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
@WebService(endpointInterface = "com.example.cxfdemo.service.DemoService", serviceName = "demoService")
@AutoPublish(value = "/demoService")
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }
}
