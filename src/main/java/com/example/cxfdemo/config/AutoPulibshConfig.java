
package com.example.cxfdemo.config;

import com.example.cxfdemo.annotation.AutoPublish;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import java.util.Map;
import java.util.Set;

/**
 * 自动发布注解
 *
 * @author Sean
 * 2019/10/28
 */

@Configuration
public class AutoPulibshConfig implements ApplicationRunner {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    SpringBus springBus;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Object> beansWithAnnotation = webApplicationContext.getBeansWithAnnotation(AutoPublish.class);
        Set<String> names = beansWithAnnotation.keySet();
        for (String name : names) {
            Object o = beansWithAnnotation.get(name);
            // 绑定要发布的服务实现类
            EndpointImpl endpoint = new EndpointImpl(springBus, o);
            Class<?> aClass = o.getClass();
            AutoPublish annotation = aClass.getAnnotation(AutoPublish.class);
            String value = annotation.value();
            // 接口访问地址
            endpoint.publish(value);
            System.out.println("发布服务地址为：" + value);
        }
    }


}
