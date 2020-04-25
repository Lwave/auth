package com.example.sys.shiro;


import com.example.emp.entity.SysUsers;
import com.example.emp.service.IRolesService;
import com.example.emp.service.impl.SysUsersServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;


public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUsersServiceImpl sysUsersService;

    @Resource
    private IRolesService rolesService;

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //登录认证的方法需要先执行,需要用他来判断登录的用户信息是否合法
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 1.获取用户输入的用户名
        String username = token.getUsername();
        token.isRememberMe();
        SysUsers user = null;
        try {
            // 2.获取用户输入的密码
            user = sysUsersService.getUserByUserName(username);
            //取出盐并编码
            ByteSource saltSource = ByteSource.Util.bytes(user.getSalt());
            System.out.println("saltSource" + saltSource);
            // 创建简单认证信息对象
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(
                            user.getUsername(),//用户身份
                            user.getPassword(),//已加密的密码
                            saltSource,//盐值对应的byteSource
                            getName() //realm的名字
                    );
            //存储用户信息
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            return info;
        } catch (AuthenticationException e) {
            e.getMessage();
            throw new AuthenticationException("该用户名称不存在");
        }


    }


    /**
     * 授权检查
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUsers user = (SysUsers) SecurityUtils.getSubject().getSession().getAttribute("user");
        //根据用户信息获取用户权限
        SysUsers role = rolesService.getRoleByUserId(user.getId());
        String name = role.getRoles().getName();
        // 简单授权信息对象，对象中包含用户的角色和权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(name);
        // info.addStringPermission("cms:news:view");
        // info.addStringPermission("cms:news:list");
        System.out.println("授权完成....");
        return info;


    }
}
