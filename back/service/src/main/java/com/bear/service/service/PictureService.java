package com.bear.service.service;

import com.bear.service.dao.PictureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片service层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Service
public class PictureService {
    @Autowired
    private PictureDao pictureDao;
}
