package com.shangma.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 辉哥
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role_menu")
@ApiModel(value="RoleMenu对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "权限id")
    private Long menuId;


}
