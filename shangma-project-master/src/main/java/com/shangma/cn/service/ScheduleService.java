package com.shangma.cn.service;

import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.BrandDTO;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.entity.ScheduleBean;
import com.shangma.cn.query.BrandQuery;
import com.shangma.cn.service.base.BaseService;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:55
 * 文件说明：
 */
public interface ScheduleService extends BaseService<ScheduleBean> {


    int addSchedule(ScheduleBean scheduleBean);

    int updateSchedule(ScheduleBean scheduleBean);


}
