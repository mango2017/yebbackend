package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mango
 * @since 2022-08-13
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);
}
