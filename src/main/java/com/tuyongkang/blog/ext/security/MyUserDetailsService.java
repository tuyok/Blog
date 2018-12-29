package com.tuyongkang.blog.ext.security;

import com.tuyongkang.blog.model.entity.SysRoleEntity;
import com.tuyongkang.blog.model.entity.SysUserEntity;
import com.tuyongkang.blog.service.SysRoleService;
import com.tuyongkang.blog.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 在 spring security 配置中进行实例化
 */
public class MyUserDetailsService implements UserDetailsService{

    private SysUserService sysUserService;

    private SysRoleService sysRoleService;

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public MyUserDetailsService(){

    }

    /**
     * 没有实现自定义的UserDetails,只是使用User封装了账号信息和权限信息
     * 后面可以根据自己的需要自行实现UserDetails接口，存储更多的信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUserEntity sysUserEntity = sysUserService.findByUserName(username);
        if(sysUserEntity != null){
            List<SysRoleEntity> permissionEntityList = sysRoleService.findUserRoles(sysUserEntity.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for(SysRoleEntity sysRoleEntity : permissionEntityList){
                if(sysRoleEntity != null && sysRoleEntity.getRoleName() !=null){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysRoleEntity.getRoleName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(sysUserEntity.getUserName(),sysUserEntity.getPassword(),grantedAuthorities);
        }else{
            return null;
        }
    }

}
