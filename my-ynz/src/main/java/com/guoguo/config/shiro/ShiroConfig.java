package com.guoguo.config.shiro;

import cn.hutool.core.util.ObjectUtil;
import com.guoguo.ynz.dao.user.UserDao;
import com.guoguo.ynz.entity.user.UserRolePermission;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 分割符
     */
    private String separator = "\n";

    @Autowired
    private UserDao userDao;

    @Bean
    public Realm realm() {
        // 查询数据库用户、权限、角色
        List<UserRolePermission> userRolePermissionList = userDao.getUserRolePermissionList();
        Map<String, String> processData = processData(userRolePermissionList);

        TextConfigurationRealm realm = new TextConfigurationRealm();
        // 定义用户、用户密码、用户角色
        realm.setUserDefinitions(processData.get("user"));
        // 定义角色
        realm.setRoleDefinitions(processData.get("role"));
        // 使用缓存
        realm.setCachingEnabled(true);
        return realm;
    }

    private Map<String, String> processData(List<UserRolePermission> userRolePermissionList) {
        Map<String, String> dataMap = new HashMap<>();
        if (ObjectUtil.isNull(userRolePermissionList)) {
            return dataMap;
        }

        Map<String, List<String>> userRoleMap = new HashMap<>();
        Map<String, List<String>> rolePermissionMap = new HashMap<>();
        for (UserRolePermission userRolePermission : userRolePermissionList) {
            String userRoleMapKey = userRolePermission.getMobile() + "=" + userRolePermission.getPassword();
            if (userRoleMap.containsKey(userRoleMapKey)) {
                if (!userRoleMap.get(userRoleMapKey).contains(userRolePermission.getRoleName())) {
                    userRoleMap.get(userRoleMapKey).add(userRolePermission.getRoleName());
                }
            } else {
                List<String> list = new ArrayList<>();
                list.add(userRolePermission.getRoleName());
                userRoleMap.put(userRoleMapKey, list);
            }

            if (rolePermissionMap.containsKey(userRolePermission.getRoleName())) {
                rolePermissionMap.get(userRolePermission.getRoleName()).add(userRolePermission.getPermissionName());
            } else {
                List<String> list = new ArrayList<>();
                list.add(userRolePermission.getPermissionName());
                rolePermissionMap.put(userRolePermission.getRoleName(), list);
            }
        }
        StringBuffer user = new StringBuffer();
        StringBuffer role = new StringBuffer();
        for (Map.Entry<String, List<String>> entry : userRoleMap.entrySet()) {
            StringBuffer roleStr = new StringBuffer();
            for (String roleName : entry.getValue()) {
                roleStr.append(",");
                roleStr.append(roleName);
                StringBuffer permissionStr = new StringBuffer();
                for (String permissionName : rolePermissionMap.get(roleName)) {
                    permissionStr.append(permissionName);
                    permissionStr.append(",");
                }
                permissionStr.deleteCharAt(permissionStr.length() - 1);
                role.append(roleName + "=" + permissionStr.toString());
                role.append(separator);
            }
            user.append(entry.getKey() + roleStr.toString());
            role.append(separator);
        }
        dataMap.put("user", user.toString());
        dataMap.put("role", role.toString());
        return dataMap;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "authc"); // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/", "authc");
        chainDefinition.addPathDefinition("/ynz/**", "authc");
        return chainDefinition;
    }

    /**
     * 配置会话存储
     * 这意味着您的会话数据可以驻留在内存中，文件系统上，关系数据库或NoSQL数据存储区中，或您需要的任何其他位置。您可以控制持久性行为。
     *
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
//        enterpriseCacheSessionDAO.setActiveSessionsCacheName("");
        return enterpriseCacheSessionDAO;
    }

    /**
     * 配置EhCache会话管理器
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }
}
