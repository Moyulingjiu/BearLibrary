package com.bear.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 人
 * <p>对于一个简单人对象应该使用该类，以方便cloneObject方法进行复制对象</p>
 *
 * @author moyulingjiu
 * create 2022年3月26日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplePerson implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id号
     */
    private Long id = 0L;

    /**
     * 姓名
     */
    private String name = "system";
}
