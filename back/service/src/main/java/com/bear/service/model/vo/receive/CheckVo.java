package com.bear.service.model.vo.receive;

import com.bear.model.SimplePerson;
import com.bear.service.model.bo.CheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 打卡类
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckVo {
    @NotNull
    private Integer type;
    @NotNull
    private String url;
    @NotNull
    @Length(max = 200, message = "说明的最大长度为200")
    private String comment;
}
