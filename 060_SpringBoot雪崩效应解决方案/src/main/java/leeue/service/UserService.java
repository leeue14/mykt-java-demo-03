package leeue.service;

import leeue.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liyue
 * @date 2019-08-22 16:30
 */
@Service
public class UserService {

    //定义要查询的缓存名称
    private String cacheName = "userCache";

    public List<Users> getUser(Long id) {

        //1.查询一级缓存
        //2.查询二级缓存
        //3.查询数据库db
        return null;

    }
}
