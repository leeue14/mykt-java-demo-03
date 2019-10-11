package leeue.api.controller;

import leeue.fegin.MemberApiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/10/11 10:50
 */
@RestController
public class FeignController {

    @Autowired
    private MemberApiFeign memberApiFeign;

    @GetMapping("/feignMember")
    public String feignMember(){
        return  memberApiFeign.getMember();
    }
}
