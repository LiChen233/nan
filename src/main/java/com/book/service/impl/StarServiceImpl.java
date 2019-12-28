package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.dao.StarDao;
import com.book.entity.StarEntity;
import com.book.service.StarService;
import org.springframework.stereotype.Service;


@Service("starService")
public class StarServiceImpl extends ServiceImpl<StarDao, StarEntity> implements StarService {

}
