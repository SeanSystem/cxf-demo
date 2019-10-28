package com.example.cxfdemo.config;

import com.example.cxfdemo.service.impl.DemoServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Value("${cxf.path}")
    private String path;

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        // 发布服务名称 localhost:8080/cxf
        return new ServletRegistrationBean(new CXFServlet(), path);

    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

/*    @Bean
    public Endpoint syncEmpOrgImpl() {
        // 绑定要发布的服务实现类
        EndpointImpl endpoint = new EndpointImpl(springBus(), new DemoServiceImpl());
        // 接口访问地址
        endpoint.publish("/hello");
        return endpoint;
    }*/

}