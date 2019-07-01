package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IStuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * @author junjun
 * @date 2019/6/29 16:13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Reference
    IStuService stuService;

    @Reference
    IEmailService emailService;

    @RequestMapping("/register")
    public Object register(User user, HttpServletRequest request,Model model){
        System.out.println(user);
        String code = (String) request.getSession().getAttribute("code");
        if (user.getCode().equals(code)){
            stuService.register(user);
            model.addAttribute("msg","");
            return "login";
        }
        model.addAttribute("msg","请重新注册");
        return "register";
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public Object sendEmail(HttpServletRequest request, @RequestParam String address){
        System.out.println(address);
        Random r = new Random();
        int i = r.nextInt(9000)+ 1000;
        request.getSession().setAttribute("code",i+"");
        Email email = new Email();
        email.setFrom("f845835812@163.com");
        email.setTo(address);
        email.setDate(new Date());
        email.setTitle("验证码");
        email.setContent(i+"");
        emailService.sendEmail(email);
        return null;
    }

    @RequestMapping("/findPassword")
    public Object findPassword(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        List<User> users = stuService.getUserByName(map);
        if (users==null){
            return "findPassword";
        }
        User user = users.get(0);
        String address = user.getEmail();
        Email email = new Email();
        email.setFrom("f845835812@163.com");
        email.setTo(address);
        email.setDate(new Date());
        email.setTitle("找回密码");
        email.setContent("请点击<a href='http://192.168.56.1:8080/user/togetPassword?name="+name+"'>这里</a>找回密码");
        emailService.sendEmail(email);
        return "login";
    }

    @RequestMapping("/tofindPassword")
    public Object tofindPassword(){
        return "findPassword" ;
    }

    @RequestMapping("/togetPassword")
    public Object togetPassword(@RequestParam String name,Model model){
        model.addAttribute("name",name);
        return "updatePassword";
    }

    @RequestMapping("/updatePassword")
    public Object updatePassword(User user){
        Map<String,Object>map = new HashMap<>();
        map.put("name",user.getName());
        List<User> users = stuService.getUserByName(map);
        User user1 = users.get(0);
        user1.setPassword(user.getPassword());
        stuService.updateById(user1);
        return "login";
    }

    @RequestMapping("/toRegister")
    public Object toRegister(){
        return "register";
    }

    @RequestMapping("/toLogin")
    public Object toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public Object login(@RequestParam String name,@RequestParam String password){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        List<User> users = stuService.getUserByName(map);
        if (users!=null){
            return "ok";
        }
        return "login";
    }
}
