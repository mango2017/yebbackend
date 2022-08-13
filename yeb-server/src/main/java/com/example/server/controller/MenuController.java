package com.example.server.controller;


import com.example.server.pojo.Menu;
import com.example.server.service.IAdminService;
import com.example.server.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mango
 * @since 2022-08-13
 */
@RestController
@RequestMapping("/system/cfg")
@Api(tags = "菜单管理")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }
}
