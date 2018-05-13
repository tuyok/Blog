package com.tuyongkang.blog.config.ext;

import com.tuyongkang.blog.ext.security.*;
import com.tuyongkang.blog.service.SysPermissionService;
import com.tuyongkang.blog.service.SysRoleService;
import com.tuyongkang.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 配置spring security
 * 自定义登录认证
 * 数据库动态授权
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private MyFilterSecurityInterceptor filterSecurityInterceptor;

    @Bean
    public UserDetailsService userDetailsService() {
        MyUserDetailsService userDetailsService = new MyUserDetailsService();
        userDetailsService.setSysUserService(sysUserService);
        userDetailsService.setSysRoleService(sysRoleService);
        return userDetailsService;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        MyAccessDecisionManager accessDecisionManager = new MyAccessDecisionManager();
        return accessDecisionManager;
    }

    @Bean
    public SecurityMetadataSource securityMetadataSource(){
        MySecurityMetadataSource securityMetadataSource = new MySecurityMetadataSource();
        securityMetadataSource.setSysRoleService(sysRoleService);
        securityMetadataSource.setSysPermissionService(sysPermissionService);
        return securityMetadataSource;
    }

    @Bean
    public MyFilterSecurityInterceptor filterSecurityInterceptor(SecurityMetadataSource securityMetadataSource
            ,AccessDecisionManager accessDecisionManager){
        MyFilterSecurityInterceptor filterSecurityInterceptor = new MyFilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        return filterSecurityInterceptor;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        AuthenticationProvider authenticationProvider=new MyAuthenticationProvider(userDetailsService);
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,AuthenticationProvider authenticationProvider) throws Exception {
        /*auth
                .inMemoryAuthentication()
                .withUser("tuyongkang").password("123456").roles("user");*/
        auth.authenticationProvider(authenticationProvider); //使用自定义的身份认证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ignore url patterns
        web.ignoring().antMatchers("/lib/**","/img/**", "/html/**","/js/**","/css/**","/index.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                .formLogin()
                    .loginPage("/login.html")   //除了上面的ignore的，我们都需要进行身份认证
                    .loginProcessingUrl("/login") //会调用我们自己的权限认证提供者
                    .successForwardUrl("/loginSuccess")
                    .failureForwardUrl("/loginError")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll();
        http.addFilterBefore(filterSecurityInterceptor,FilterSecurityInterceptor.class);

    }


}
