package com.leeue.config;

import com.leeue.entity.Permission;
import com.leeue.handler.MyAuthenticationFailureHandler;
import com.leeue.handler.MyAuthenticationSuccessHandler;
import com.leeue.mapper.PermissionMapper;
import com.leeue.security.MyUserDetailService;
import com.leeue.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Spring Security 配置
 * * @EnableWebSecurity 开启Security配置
 *
 * @author liyue
 * @date 2019/12/18 14:48
 */
@Component

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 重写 configure方法
     * 配置认证用户信息和授权
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*
        //配置账号权限 admin账号
        auth.inMemoryAuthentication()
                .passwordEncoder(this.passwordEncoder())
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                //配置路由
                .authorities("addOrder");

        //添加 test账号只有增加权限
        auth.inMemoryAuthentication()
                .passwordEncoder(this.passwordEncoder())
                .withUser("test")
                .password(passwordEncoder().encode("test"))
                //配置路由
                .authorities("showOrder", "addOrder");

        //如果想实现动态账号，与数据关联，在该地方查询数据库*/

        auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {

            //验证密码 加密密码和数据库密码进行比对
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String rawPass = MD5Util.encode((String) rawPassword);
                boolean result = rawPass.equals(encodedPassword);
                return result;
            }

            // 对表单密码进行加密
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }
        });
    }

    /**
     * 重写 configure方法
     * <p>
     * 配置拦截请求资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置拦截请求资源 使用httpBasic模式
        // http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();

        //配置拦截请求资源，使用 formLogin模式
        /*http.authorizeRequests()
                //不拦截登录请求
                .antMatchers("/login").permitAll()
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                .antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
                //配置验证成功
                .successHandler(successHandler)
                //配置验证失败
                .failureHandler(failureHandler)
                //这个要把禁止掉，否则就需要在表单传递token
                .and().csrf().disable();*/

        /**
         *  如何权限控制，给每个请求路径分配一个权限名称，让以后账号只要关联该名称，就可以有访问权限
         *  .antMatchers("/showOrder").hasAnyAuthority("showOrder")
         *  第一个参数是请求路径，第二参数是请求权限名称
         */

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequest = http.authorizeRequests();

        List<Permission> permissionList = permissionMapper.findAllPermission();

        permissionList.forEach(permission -> {

            authorizeRequest.antMatchers(permission.getUrl())
                    .hasAnyAuthority(permission.getPermTag());
        });

        //将登录页面设置为全部可以访问的
        authorizeRequest.antMatchers("/login")
                .permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and().csrf().disable();
    }
}
