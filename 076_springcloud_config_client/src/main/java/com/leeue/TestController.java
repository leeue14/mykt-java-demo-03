package com.leeue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/10/15 19:44
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${leeue}")
    private String value;
    @RequestMapping("/getValue")
    public String getValue(){
        return value;
    }
}
