package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author mango
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu_role")
@ApiModel(value="MenuRole对象", description="")
public class MenuRole extends Model<MenuRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id")
    private Integer mid;

    @ApiModelProperty(value = "权限id")
    private Integer rid;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
