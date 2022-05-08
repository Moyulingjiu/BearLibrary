package com.bear.service.model.vo.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 打卡类
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAdminVo {

    /**
     * 得到的点数
     * 对于学习进步目标，对应自律值
     * 其他对应荣誉值
     */
    @NotNull(message = "得到的点数不能为空")
    private Long point;

    /**
     * 得到的经验值
     */
    @NotNull(message = "得到的经验值不能为空")
    private Long exp;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空")
    private Boolean pass;

    /**
     * 管理员备注
     */
    @NotNull
    @Length(max = 200, message = "说明的最大长度为200")
    private String comment;
}
