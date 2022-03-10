package com.shangma.cn.query;

import com.shangma.cn.query.base.BaseQuery;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/29 9:58
 * 文件说明：
 */
@Data
public class MenuQuery extends BaseQuery {

    private String menuTitle;

    private Integer menuType;


    /**
     * 表示是否是查询
     * @return
     */
    public boolean isQuery() {
        //
        return !(StringUtils.isEmpty(menuTitle) && Objects.isNull(menuType) && Objects.isNull(getStartTime()) && Objects.isNull(getEndTime()));
    }
}
