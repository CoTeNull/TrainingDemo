package cn.cote.controller;

import cn.cote.pojo.TUser;
import cn.cote.pojo.User;
import cn.cote.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "begin Hello!";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public String name(){
        TUser tUser;
        tUser=userService.findUserByName("cote");
        return tUser.getUserPassword();
    }

    @RequestMapping(value = "/toLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        if (subject.hasRole("user")) {
            return "拥有user";
        } else {
            return "不是用户";
        }
    }
    @RequiresRoles("admin")
    @RequestMapping(value = "/admin" ,method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String admin(){
        return "没错你有admin的权限";
    }

    @RequiresPermissions("admin:add")
    @RequestMapping(value = "/adminAdd" ,method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String user(){
        return "没错你是管理员，拥有管理员权限";
    }

}
