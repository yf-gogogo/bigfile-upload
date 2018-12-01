package com.example.login.controllor;

import com.example.login.model.LoginSuccess;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;
import java.util.Arrays;

@RestController
@CrossOrigin
public class CookieTest {

    private Cookie[] cookies;

    @RequestMapping("/login")
    public LoginSuccess login(@RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "pwd",required = false) String pwd,
                              HttpServletRequest request, HttpServletResponse response)
    {
        LoginSuccess loginSuccess = new LoginSuccess();
        cookies = request.getCookies();
        if(name==null&&pwd==null){
            loginSuccess.setStatus(100);
            loginSuccess.setMsg("账号密码不能为空");
        }else
        if(cookies != null){
            if(cookies[0].getValue().equals("张三")&&cookies[1].getValue().equals("666666")){
                loginSuccess.setStatus(300);
                loginSuccess.setMsg("已登录");
            }
        }else {
            if(name.equals("张三")&&pwd.equals("666666")){
                Cookie cookie = new Cookie("name",name);
                response.addCookie(cookie);
                cookie = new Cookie("pwd",pwd);
                response.addCookie(cookie);
                loginSuccess.setStatus(200);
                loginSuccess.setMsg("登陆成功");
            }else {
                loginSuccess.setStatus(100);
                loginSuccess.setMsg("账号或密码错误");
            }
        }
        return loginSuccess;
    }
}
