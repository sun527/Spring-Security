package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.components.EmailService;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.dto.ScheduleBeanDTO;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.ScheduleBean;
import com.shangma.cn.mapper.AdminMapper;
import com.shangma.cn.service.ScheduleService;
import com.shangma.cn.transfer.ScheduleBeanTransfer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:00
 * 文件说明：
 */
@RestController
@RequestMapping("schedule")
@Api(tags = "定时管理", description = "定时管理")
public class ScheduleController extends BaseController {


    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleBeanTransfer scheduleBeanTransfer;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private Map<Long, ScheduledFuture> map = new HashMap<>();
    @Autowired
    private AdminMapper adminMapper;


    @Autowired
    private EmailService emailService;


    @PostConstruct
    public void initSchedule() {
        System.out.println("PostConstruct方法执行了");
        List<ScheduleBean> list = scheduleService.list();
        list.forEach(scheduleBean -> {
            String cronExpress = scheduleBean.getCronExpress();
            CronTrigger cronTrigger = new CronTrigger(cronExpress);
            ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    List<Admin> admins = adminMapper.selectList(null);
                    admins.forEach(admin -> emailService.sendMail(admin));
                }
            }, cronTrigger);
            map.put(scheduleBean.getCronId(), scheduledFuture);

        });

    }


    @ApiOperation(value = "查询所有")
    @GetMapping
    public AxiosResult<List<ScheduleBeanDTO>> list() {
        return AxiosResult.success(scheduleBeanTransfer.toDTO(scheduleService.list()));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "id查询")
    public AxiosResult<ScheduleBeanDTO> findById(@PathVariable Long id) {
        return AxiosResult.success(scheduleBeanTransfer.toDTO(scheduleService.findById(id)));
    }

    @PostMapping
    @ApiOperation(value = "添加品牌")

    public AxiosResult add(@RequestBody ScheduleBean ScheduleBean) {
        return toAxios(scheduleService.addSchedule(ScheduleBean));
    }

    @PutMapping
    @ApiOperation(value = "修改品牌")
    public AxiosResult<Void> update(@RequestBody ScheduleBean ScheduleBean) {
        //判断表单校验有没有成功
        Long cronId = ScheduleBean.getCronId();
        ScheduledFuture scheduledFuture = map.get(cronId);
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }
        map.remove(cronId);
        int row = scheduleService.updateSchedule(ScheduleBean);
        ScheduledFuture scheduledFuture1 = threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                List<Admin> admins = adminMapper.selectList(null);
                admins.forEach(admin -> emailService.sendMail(admin));
            }
        }, new CronTrigger(ScheduleBean.getCronExpress()));

        map.put(cronId, scheduledFuture1);

        return toAxios(row);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "通过id删除")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        int i = scheduleService.deleteById(id);
        ScheduledFuture scheduledFuture = map.get(id);
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
        map.remove(id);
        return toAxios(i);
    }


    @PutMapping("{id}")
    @ApiOperation(value = "暂停")
    public AxiosResult<Void> pause(@PathVariable Long id) {
        ScheduledFuture scheduledFuture = map.get(id);
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            //停掉
            scheduledFuture.cancel(false);
            //删掉
            map.remove(id);
        }
        return AxiosResult.success();

    }


    @PostMapping("{id}")
    @ApiOperation(value = "重启")
    public AxiosResult<Void> resume(@PathVariable Long id) {
        ScheduleBean byId = scheduleService.findById(id);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                List<Admin> admins = adminMapper.selectList(null);
                admins.forEach(admin -> emailService.sendMail(admin));
            }
        }, new CronTrigger(byId.getCronExpress()));
        map.put(byId.getCronId(), scheduledFuture);
        return AxiosResult.success();
    }


}
