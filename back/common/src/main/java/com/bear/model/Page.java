package com.bear.model;

import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 2L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页面大小
     */
    private Integer pageSize = 1;

    /**
     * 页数
     */
    private Integer total = 0;

    /**
     * 本页的行数
     */
    private Integer row = 0;

    /**
     * 是否有上一页
     */
    private Boolean hasPreviousPage = false;

    /**
     * 是否有下一页
     */
    private Boolean hasNextPage = false;

    /**
     * 数据
     */
    private List<T> list;

    public Page(List<T> list, PageInfo<?> pageInfo) {
        this.list = new ArrayList<>(list);
        this.page = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getPages();
        this.hasNextPage = pageInfo.isHasNextPage();
        this.hasPreviousPage = pageInfo.isHasPreviousPage();
        this.row = pageInfo.getSize();
    }

    public Page(List<T> list, Page<?> other) {
        this.list = new ArrayList<>(list);
        this.page = other.getPage();
        this.pageSize = other.getPageSize();
        this.total = other.getTotal();
        this.hasNextPage = other.getHasNextPage();
        this.hasPreviousPage = other.getHasPreviousPage();
        this.row = other.getRow();
    }
}
