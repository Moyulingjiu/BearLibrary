package com.bear.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    private Integer page;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 页数
     */
    private Long total;

    /**
     * 数据
     */
    private List<T> list;
}
