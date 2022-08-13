package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.AdminMapper;
import com.example.server.mapper.MenuMapper;
import com.example.server.pojo.Admin;
import com.example.server.pojo.Menu;
import com.example.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mango
 * @since 2022-08-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenusByAdminId() {
        return menuMapper.getMenusByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
