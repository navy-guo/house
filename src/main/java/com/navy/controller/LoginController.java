package com.navy.controller;

import com.navy.config.WebSecurityConfig;
import com.navy.entity.User;
import com.navy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    /**
     * 跳转到管理后台登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 跳转到管理后台登录页面
     *
     * @return
     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(HttpSession httpSession) {
//        if (httpSession.getAttribute(WebSecurityConfig.SESSION_KEY) == null) {
//            return "admin/login";
//        } else {
//            return "admin/index";
//        }
//    }

    /**
     * 用户登录判断方法
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String toLogin(User user, HttpSession httpSession) {
        String flag = "";
        List<User> user_query = userService.findAll(user);
        if (user_query.size() == 0) {
            flag = "0";
        } else {
            flag = "1";
            httpSession.setAttribute(WebSecurityConfig.SESSION_KEY, user_query.get(0));
            httpSession.setMaxInactiveInterval(-1);//永不过期
        }
        return flag;
    }
    /**
     * 用户登录退出方法
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "admin/login";
    }
}
