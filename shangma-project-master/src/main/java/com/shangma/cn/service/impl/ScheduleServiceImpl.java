package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.BrandDTO;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.entity.ScheduleBean;
import com.shangma.cn.mapper.BrandMapper;
import com.shangma.cn.mapper.ScheduleMapper;
import com.shangma.cn.query.BrandQuery;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.ScheduleService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.BrandTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:58
 * 文件说明：
 * 2个实体类的属性值互换
 * BeanUtils jar包
 * spring提供了BeanUtils
 */
@Service
@Transactional
public class ScheduleServiceImpl extends BaseServiceImpl<ScheduleBean> implements ScheduleService {


    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public int addSchedule(ScheduleBean scheduleBean) {
        return scheduleMapper.insert(scheduleBean);
    }

    @Override
    public int updateSchedule(ScheduleBean scheduleBean) {
        return scheduleMapper.updateById(scheduleBean);
    }
}
