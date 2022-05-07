package com.bear.service.dao;

import com.bear.service.mapper.PicturePoMapper;
import com.bear.service.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 图片dao层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Repository
public class PictureDao {
    @Autowired
    private PicturePoMapper picturePoMapper;
    @Autowired
    private RedisUtils redisUtils;

}
