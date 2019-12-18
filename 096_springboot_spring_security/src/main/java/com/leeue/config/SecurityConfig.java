package com.leeue.config;

import com.leeue.handler.MyAuthenticationFailureHandler;
import com.leeue.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    /**
     * 重写 configure方法
     * 配置认证用户信息和授权
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

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

        //如果想实现动态账号，与数据关联，在该地方查询数据库
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
        http.authorizeRequests()
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
                .and().csrf().disable();

        /**
         *  如何权限控制，给每个请求路径分配一个权限名称，让以后账号只要关联该名称，就可以有访问权限
         *  .antMatchers("/showOrder").hasAnyAuthority("showOrder")
         *  第一个参数是请求路径，第二参数是请求权限名称
         */


    }

    /**
     * Security 认证的时候必须要对密码进行加密才能进行访问，否则会报错
     *
     * @return
     */
    private PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
