package com.shangma.cn.transfer.base;

import com.shangma.cn.dto.BrandDTO;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 15:26
 * 文件说明： slf4j+logback
 */
@Slf4j
public class BaseTransfer<DTO, Entity> {

    /**
     * entity转DTO
     */
    public DTO toDTO(Entity entity) {
        try {
//            Class superClassGenricType = ReflectionUtils.getSuperClassGenricType(BaseTransfer.class, 0);
            Class<DTO> entityClass = (Class<DTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            DTO o = entityClass.newInstance();
            BeanUtils.copyProperties(entity, o);
            return o;
        } catch (Exception e) {
            log.error("实体类转换DTO错误"+e.getMessage());
            return null;
        }
    }

    /**
     * entity转DTO
     */
    public List<DTO> toDTO(List<Entity> list) {
        List<DTO> dtos = new ArrayList<>();
        list.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    /**
     * DTO转Entity
     *
     * @param dto
     * @return
     */
    public Entity toEntity(DTO dto) {
        try {
            Class<Entity> entityClass = (Class<Entity>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Entity entity = entityClass.newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            log.error("DTO转换实体类错误"+e.getMessage());
        }
        return null;
    }


    /**
     * DTO转Entity
     */
    public List<Entity> toEntity(List<DTO> list) {
        List<Entity> entities = new ArrayList<>();
        list.forEach(dto -> entities.add(toEntity(dto)));
        return  entities;
    }



}
