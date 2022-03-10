package com.shangma.cn.components;

import com.shangma.cn.entity.Admin;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/30 16:34
 * 文件说明：
 */
@Component
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Async
    public void sendMail(Admin admin) {
        System.out.println(Thread.currentThread().getName());
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("salary.ftl");
            Map<String, String> map = new HashMap<>();
            map.put("adminAccount", admin.getAdminAccount());
            map.put("adminName", admin.getAdminName());
            map.put("adminPhone", admin.getAdminPhone());
            map.put("adminSalary", admin.getAdminSalary() + "");
            StringWriter stringWriter = new StringWriter();
            template.process(map, stringWriter);
            helper.setFrom("尚马教育<zhengzhoudaxuevip@163.com>");
            helper.setTo(admin.getAdminEmail());
            helper.setSubject("工资明细表");
            helper.setText(stringWriter.toString(), true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
