package leeue.controller;

import leeue.entity.Users;
import leeue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liyue
 * @date 2019-08-20 16:55
 */
@RestController
public class TestController {
    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/get")
    public String getUser(@RequestParam("id") Long id) {
        List<Users> users = userService.getUser(id);
        if (null == users || users.isEmpty()) {
            return null;
        }
        return users.get(0).getAge() + "";
    }









    /***
     * 清除缓存
     */
    @GetMapping("removeCache")
    public void removeCache() {
        cacheManager.getCache("userCache").clear();
    }

}
