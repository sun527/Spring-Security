package com.shangma.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
 * @since 2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_admin_role")
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole implements Serializable {
    private Long roleId;

    private Long adminId;
}
