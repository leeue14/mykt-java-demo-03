package com.leeue.zk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>实现zk选主策略</p>
 * @author liyue
 * @date 2019/9/4 14:13
 */
@RestController
public class IndexController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/getServerInfo")
    public String  getServerInfo(){
        return "端口号"+serverPort+(ElectionMaster.isSurvival ? "当前服务器为主节点" : "当前服务器为从节点");
    }

}
