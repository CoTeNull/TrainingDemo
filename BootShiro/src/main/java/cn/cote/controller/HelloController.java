package cn.cote.controller;

import cn.cote.myutils.WebData;
import cn.cote.pojo.TUser;
import cn.cote.pojo.User;
import cn.cote.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
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


    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public WebData login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
        System.out.println(userName+passWord);
        WebData webData = new WebData();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
        try {
            subject.login(token);
            String sessionId = (String) subject.getSession().getId();
            webData.setMessage("success");
            webData.setData(sessionId);
            webData.setCode(1);
        } catch (AuthenticationException e) {
            webData.setMessage(e.getMessage());
            webData.setCode(0);
        }
        return webData;
    }
    @RequiresRoles("admin")
    @RequestMapping(value = "/test" , method = RequestMethod.POST)
   public String login2(){
        return "111";
    }
}
