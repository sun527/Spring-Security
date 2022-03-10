package com.shangma.cn.transfer;

import com.baomidou.mybatisplus.extension.api.R;
import com.shangma.cn.dto.RoleDTO;
import com.shangma.cn.entity.Role;
import com.shangma.cn.transfer.base.BaseTransfer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/25 16:08
 * 文件说明：
 */
@Component
public class RoleTransfer  extends BaseTransfer<RoleDTO, Role> {
}
