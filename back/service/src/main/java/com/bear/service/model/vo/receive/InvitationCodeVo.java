package com.bear.service.model.vo.receive;

import com.bear.util.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 邀请码
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCodeVo {

    /**
     * 对应的code
     */
    @NotBlank
    private String code;

    /**
     * 有效期截止的时间
     */
    @JsonFormat(pattern=Common.DATE_TIME_FORMAT)
    private LocalDateTime validTime;
}
