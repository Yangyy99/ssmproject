package com.ityang.ssm.controller;

import com.ityang.ssm.domain.Permission;
import com.ityang.ssm.domain.Role;
import com.ityang.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();

        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;

    }

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(
            @RequestParam(name = "id",required = true) String id){
        ModelAndView mv=new ModelAndView();
       Role role= roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");

        return mv;
    }
    /**
     * 新建
     */
    @RequestMapping("/add.do")
    public String addRole(Role role){

        roleService.add(role);

        return "redirect:findAll.do";
    }
    /**
     * 删除角色
     */
    @RequestMapping("/delete.do")
    public String delete(@RequestParam(name = "id") String id){
        roleService.delete(id);
        return "redirect:findAll.do";
    }

    /**
     *  查询出 该角色和该角色可以添加的权限
     * @param roleId
     * @return mv
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String id){
        ModelAndView mv=new ModelAndView();
        Role role = roleService.findById(id);

        List<Permission> permissionList=roleService.findPermissionByRoleId(id);

        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");

        return mv;
    }

    /**
     * 为角色添加权限
     * @param roleId 目标角色的id
     * @param permissionIds 所要添加的权限id的数组
     * @return
     */
    @RequestMapping("/addRoleToPermission.do")
    public String addRoleToPermission(@RequestParam(name = "roleId") String roleId,
                                      @RequestParam(name = "ids") String[] permissionIds){

        roleService.addPermissionToRole(roleId,permissionIds);

        return "redirect:findAll.do";
    }

    /**
     * 减少角色的权限
     * @param id
     * @return
     */
    @RequestMapping("/findRoleByIdAndOtherPermission.do")
    public ModelAndView findRoleByIdAndOtherPermission(@RequestParam("id") String id){
        ModelAndView mv=new ModelAndView();

        Role role = roleService.findById(id);
        List<Permission> permissions=roleService.findRoleByIdAndOtherPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-delete");
        return mv;
    }

    @RequestMapping("/deleteRoleToPermission.do")
    public String deleteRoleToPermission(@RequestParam("ids") String[] permissionIds){
        roleService.deletePermissionToRole(permissionIds);
        return "redirect:findAll.do";
    }






}
