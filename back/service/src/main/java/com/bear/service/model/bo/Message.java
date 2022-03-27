package com.bear.service.model.bo;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 消息
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    /**
     * id号
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 消息内容
     */
    private String text;

    /**
     * 消息对应的链接url
     */
    private String link;

    /**
     * 是否阅读了
     */
    private MessageReadType read = MessageReadType.UNREAD;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate = LocalDateTime.now();

    /**
     * 创建人
     */
    private SimplePerson create;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified = LocalDateTime.now();

    /**
     * 修改人
     */
    private SimplePerson modified;
}
