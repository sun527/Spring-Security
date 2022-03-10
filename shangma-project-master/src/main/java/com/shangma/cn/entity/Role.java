package com.shangma.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import com.shangma.cn.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 辉哥
 * @since 2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
@ApiModel(value = "Role对象", description = "")
public class Role extends BaseEntity {

    @NotBlank(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String roleDesc;


}
