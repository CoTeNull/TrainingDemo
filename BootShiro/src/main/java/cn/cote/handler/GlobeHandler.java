package cn.cote.handler;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobeHandler {
    @ExceptionHandler(value = UnauthorizedException.class)
//    @ResponseBody
    public String login(Exception e){
        return "403.html";
    }
}
