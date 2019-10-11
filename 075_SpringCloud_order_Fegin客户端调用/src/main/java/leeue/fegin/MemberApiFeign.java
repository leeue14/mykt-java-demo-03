package leeue.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liyue
 * @date 2019/10/11 10:51
 */

@FeignClient(name = "leeue-member")
public interface MemberApiFeign {

    // Fegin 书写方式以SpringMvc形式书写
    // @FeignClient调用服务接口 name 就是你要调用接口的服务名称

    // 如果要调用 member 服务的那个接口，就直接复制过来，或书写。
    // 只要有人调用这个 MemberApiFeign 客户端，底层自动使用反射机制找到这个接口，使用RPC远程调用技术
    @RequestMapping(value = "member/getMember",method = RequestMethod.GET)
    String getMember();
}
