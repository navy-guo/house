package com.navy.controller;

import com.github.pagehelper.PageInfo;
import com.navy.entity.House;
import com.navy.entity.User;
import com.navy.service.HouseService;
import com.navy.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Resource
    private HouseService houseService;

    /**
     *   查询所有
     */
    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public ModelAndView common(ModelAndView modelAndView, House house) {
        //列表
        List<House> houses = houseService.findPage(house,house.getPage(),house.getRows());
        modelAndView.addObject("pageInfo", new PageInfo<House>(houses));
        modelAndView.setViewName("admin/house/list");
        return modelAndView;
    }

    /**
     * 到编辑页面
     */
    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView toedit(ModelAndView modelAndView,House house) {
        if (null != house.getId()) {
            house = houseService.findOne(house.getId());
            modelAndView.addObject("house", house);
            modelAndView.setViewName("admin/house/edit");
        } else
            modelAndView.setViewName("admin/house/add");
        return modelAndView;
    }
    /**
     * 编辑
     */
    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpSession httpSession, House house) {
        if (null == house.getId()) {
            User user = (User) httpSession.getAttribute("user");
            if (null != user) {
                house.setUserId(user.getId());
                house.setUserName(user.getName());
            }
            house.setFollowDate(new Date());
            house.setCreateDate(new Date());
            houseService.save(house);
        } else {
            houseService.update(house);
        }
        return new ModelAndView("redirect:/house/admin/list");
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/admin/del", method = RequestMethod.POST)
    public Object del(House house) {
        try {
            houseService.delete(house.getId());
        } catch (Exception e) {
            return new JsonResult(JsonResult.STATUS_FAIL);
        }
        return new JsonResult(JsonResult.STATUS_SUCCESS);
    }

    /**
     * 跟进
     */
    @ResponseBody
    @RequestMapping(value = "/admin/editFollow", method = RequestMethod.POST)
    @Transactional
    public Object editFollow(HttpSession httpSession, House house) {
        if (null != house.getId()) {
            User user = (User) httpSession.getAttribute("user");
            if (null != user) {
                house.setUserId(user.getId());
                house.setUserName(user.getName());
            }
            house.setFollowDate(new Date());
            houseService.update(house);
        } else {
            return new JsonResult(JsonResult.STATUS_FAIL);
        }
        return new JsonResult(JsonResult.STATUS_SUCCESS);
    }

}
