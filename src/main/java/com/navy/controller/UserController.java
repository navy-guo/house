package com.navy.controller;

import com.github.pagehelper.PageInfo;
import com.navy.entity.User;
import com.navy.service.UserService;
import com.navy.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询所有
     */
    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public ModelAndView common(ModelAndView modelAndView, User user) {
        //列表
        List<User> users = userService.findPage(user, user.getPage(), user.getRows());
        modelAndView.addObject("pageInfo", new PageInfo<User>(users));
        modelAndView.setViewName("admin/user/list");
        return modelAndView;
    }
    /**
     * 到编辑页面
     */
    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView toedit(ModelAndView modelAndView, User user) {
        if (null != user.getId()) {
            user = userService.findOne(user.getId());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("admin/user/edit");
        } else {
            modelAndView.setViewName("admin/user/add");
        }
        return modelAndView;
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView edit(User user) {
        if (null == user.getId()) {
            userService.save(user);
        } else {
            userService.update(user);
        }
        return new ModelAndView("redirect:/user/admin/list");
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/admin/del", method = RequestMethod.POST)
    public Object del(User user) {
        try {
            userService.delete(user.getId());
        } catch (Exception e) {
            return new JsonResult(JsonResult.STATUS_FAIL);
        }
        return new JsonResult(JsonResult.STATUS_SUCCESS);
    }

}
