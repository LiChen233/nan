package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.dao.PressDao;
import com.book.entity.PressEntity;
import com.book.service.PressService;
import org.springframework.stereotype.Service;


@Service("pressService")
public class PressServiceImpl extends ServiceImpl<PressDao, PressEntity> implements PressService {
}
