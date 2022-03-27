package com.bear.service.dao;

import com.bear.service.mapper.UserPoMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户dao层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
public class UserDao {
    @Autowired
    private UserPoMapper userPoMapper;
}
