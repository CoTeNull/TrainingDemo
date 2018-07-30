package cn.cote.controller;

import cn.cote.dao.CustomUserMapper;
import cn.cote.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class HelloWorld {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String sayHello(){
      return "hello";
    }


    @Autowired
    CustomUserMapper customUserMapper;
    @RequestMapping("/say")
    public String getPasswordByUserName(String userName) {
        User user = new User();
        user.setUserName("admin");
        String password =customUserMapper.findPassWordByName(user);
        System.out.println("----------"+password);
        return password;
    }

    @RequestMapping(value = "/toLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        if(subject.hasRole("user")){
            return "拥有user";
        }else{
            return "不是用户";
        }
//        if(subject.isPermitted("admin:add")){
//            return "user:add";
//        }else{
//            return "no:user";
//        }
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
