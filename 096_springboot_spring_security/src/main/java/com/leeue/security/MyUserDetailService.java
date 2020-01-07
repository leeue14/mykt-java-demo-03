package com.leeue.security;

import com.leeue.entity.Permission;
import com.leeue.entity.User;
import com.leeue.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 设置动态用户信息
 *
 * @author liyue
 * @date 2019/12/18 17:03
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //1.根据用户名称查询 用户信息
        User user = userMapper.findByUsername(username);

        //2.底层会根据数据库查询用户信息，判断密码是否正确  自动判断不需要再自己去实现了

        //3.给用户授权
        List<Permission> permissionList = userMapper.findPermissionByUsername(username);

        List<GrantedAuthority> authorityList = permissionList.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermTag())).collect(Collectors.toList());

        user.setAuthorities(authorityList);

        return user;
    }
}
