package com.example.emp.controller;


import com.example.emp.entity.SysUsers;
import com.example.emp.service.IRolesService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dutao
 * @since 2020-04-19
 */
@Controller
@RequestMapping("/emp/roles")
public class RolesController {

    @Resource
    private IRolesService rolesService;

    @RequestMapping("/{id}")
    @ResponseBody
    public SysUsers getRole(@PathVariable  Integer id){
        return  rolesService.getRoleByUserId(id);
    }

}
