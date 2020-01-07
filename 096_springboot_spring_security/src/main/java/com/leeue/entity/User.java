package com.leeue.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author liyue
 * @date 2019/12/18 16:57
 */
@Data
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String realname;
    private String password;
    private Date createDate;
    private Date lastLoginTime;

    /**
     * 是否可用
     */
    private boolean enabled;

    /**
     * 是否过期
     */
    private boolean accountNonExpired;

    /**
     * 是否锁定
     */
    private boolean accountNonLocked;

    /**
     * 证书是否过期
     */
    private boolean credentialsNonExpired;

    /**
     * 用户所有权限
     */
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
