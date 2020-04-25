package com.example.sys.controller;


import com.example.common.model.R;
import com.example.emp.entity.SysUsers;
import com.example.emp.service.impl.SysUsersServiceImpl;
import com.example.sys.shiro.EncryptionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@RequestMapping
public class LoginController {

    @Resource
    private SysUsersServiceImpl sysUsersService;

    /**
     * 跳转到系统登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String username, String password, boolean rememberMe) {
        System.out.println(rememberMe);
        try {
            //1.创建一Subject实例,
            // 获取当前登陆用户
            Subject subject = SecurityUtils.getSubject();
            //2.创建一个用于认证的认证token,记录用户认证的身份和凭证即用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //3.主题要进行登录,登录的时候要进行认证检查,主体提交登录请求到SecurityManager
            token.setRememberMe(rememberMe);
            subject.login(token);
            //用户认证状态
            System.out.println("用户认证状态:" + subject.isAuthenticated());
        } catch (IncorrectCredentialsException e) {
            return R.ok("账号或密码不正确");
        } catch (LockedAccountException e) {
            return R.ok("账号被锁定");
        } catch (AuthenticationException e) {
            return R.ok("账号验证失败");
        }
        return R.ok("登录成功");

    }

    /**
     * 跳转到系统初始化页面
     *
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {
        SysUsers user = (SysUsers) SecurityUtils.getSubject().getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }


    @GetMapping("/logout")
    public String logout() {
        // 销毁会话
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @GetMapping("/resetPwd")
    public String resetPwd(Model model) {
        SysUsers user = (SysUsers) SecurityUtils.getSubject().getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "pwd";
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public R resetPwd1(String password) {
        System.out.println(password);
        SysUsers user = (SysUsers) SecurityUtils.getSubject().getSession().getAttribute("user");
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = password;//密码原值
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(salt);
        System.out.println("ss" + salt);
        ByteSource byteSource = ByteSource.Util.bytes(salt);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        String password1 = new SimpleHash(hashAlgorithmName, crdentials, byteSource, hashIterations).toString();
        user.setPassword(password1);
        System.out.println(user);
        SysUsers sysUsers = sysUsersService.updateById(user);
        return R.ok("修改成功",sysUsers);
    }
}
