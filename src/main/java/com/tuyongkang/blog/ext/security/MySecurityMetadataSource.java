package com.tuyongkang.blog.ext.security;

import com.tuyongkang.blog.model.entity.SysPermissionEntity;
import com.tuyongkang.blog.model.entity.SysRoleEntity;
import com.tuyongkang.blog.service.SysPermissionService;
import com.tuyongkang.blog.service.SysRoleService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private SysPermissionService sysPermissionService;

    private SysRoleService sysRoleService;

    private HashMap<String, Collection<ConfigAttribute>> map =null;

    public void setSysPermissionService(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public MySecurityMetadataSource(){

    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 AccessDecisionManager 的 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map == null) loadPermissions();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 每个权限对应的可以访问的角色列表
     */
    private void loadPermissions(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<SysPermissionEntity> sysPermissionEntityList = sysPermissionService.findAll();
        for(SysPermissionEntity sysPermissionEntity : sysPermissionEntityList){
            array = new ArrayList<>();
            List<SysRoleEntity> sysRoleEntities = sysRoleService.findPermissionRoles(sysPermissionEntity.getId());
            for(SysRoleEntity sysRoleEntity : sysRoleEntities){
                cfg = new SecurityConfig(sysRoleEntity.getRoleName());
                array.add(cfg);
            }
            map.put(sysPermissionEntity.getUrl(),array);
        }
    }

}
