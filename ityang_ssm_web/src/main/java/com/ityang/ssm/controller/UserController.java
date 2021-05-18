package com.ityang.ssm.controller;

import com.ityang.ssm.domain.Role;
import com.ityang.ssm.domain.UserInfo;
import com.ityang.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("userList", users);
        mv.setViewName("user-list");
        return mv;

    }

    /**
     * 添加用户
     */
    @RequestMapping("/save.do")
    public String addUser(UserInfo userInfo) {

        userService.add(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 显示用户详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(required = false, name = "id") String id) {
        ModelAndView mv = new ModelAndView();

        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;

    }

    /**
     * 为用户查询未添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String id) {
        ModelAndView mv = new ModelAndView();

        UserInfo user = userService.findById(id);

        List<Role> roles = userService.findRolesByUserId(id);

        mv.addObject("user", user);
        mv.addObject("roleList", roles);

        mv.setViewName("user-role-add");


        return mv;
    }

    /**
     * 给用户添加角色
     *
     * @param userId,roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,
                                @RequestParam(name = "ids") String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 为用户减少角色，显示为用户减少的角色
     *
     * @return
     */
    @RequestMapping("/findUserByIdAndOtherRole.do")
    public ModelAndView findUserByIdAndOtherRole(@RequestParam(name = "id") String id) {

        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        List<Role> roles = userService.findUserByIdAndOtherRole(id);

        mv.addObject("user", user);
        mv.addObject("roleList", roles);
        mv.setViewName("user-role-delete");
        return mv;
    }

    @RequestMapping("/deleteRoleToUser.do")
    public String deleteRoleToUser(@RequestParam(name = "ids") String[] roleIds) {

        userService.deleteRoleToUser(roleIds);
        return "redirect:findAll.do";

    }

}
