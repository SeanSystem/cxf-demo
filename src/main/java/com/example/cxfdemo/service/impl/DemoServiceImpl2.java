package com.example.cxfdemo.service.impl;

import com.example.cxfdemo.annotation.AutoPublish;
import com.example.cxfdemo.entity.Jsyd;
import com.example.cxfdemo.service.DemoService;
import com.example.cxfdemo.service.DemoService2;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.math.BigDecimal;

/**
 * 测试webservice
 *
 * @author Sean
 * 2019/10/28
 */
@WebService(endpointInterface = "com.example.cxfdemo.service.DemoService2", serviceName = "demoService2")
@AutoPublish(value = "/demoService2")
@Service
public class DemoServiceImpl2 implements DemoService2 {
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }

    @Override
    public BigDecimal testDecimal(BigDecimal nyd) {
        return nyd;
    }

    @Override
    public Jsyd testObj(Jsyd jsyd) {
        return jsyd;
    }
}
