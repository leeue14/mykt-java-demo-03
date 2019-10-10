package com.leeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liyue
 * @date 2019/10/10 18:45
 */
@RestController
public class SRController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    private AtomicInteger count = new AtomicInteger(0);

    @RequestMapping("/getMember")
    public String getMember(){
        return restTemplate.getForObject(this.getClientUrl()+"/member/getMember",String.class);
    }


    //1.获取Eureka服务器上地址列表
    //2.缓存到本地JVM
    //3.使用取模方式循环调用地址

    public String getClientUrl(){
        List<ServiceInstance> memberClientList = discoveryClient.getInstances("leeue-member");
        if(CollectionUtils.isEmpty(memberClientList)){
            return "";
        }
        //自增+1
        count.incrementAndGet();

        System.out.println(">>>>>调用次数>>>>>"+count);
        int serviceIndex = count.intValue() % memberClientList.size();

        ServiceInstance serviceInstance = memberClientList.get(serviceIndex);

       String uri =  serviceInstance.getUri().toString();

        System.out.println(">>>>>>获取调用地址为>>>>>>"+uri);

        return uri;
    }

}
