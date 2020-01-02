package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.dao.TypesDao;
import com.book.entity.TypesEntity;
import com.book.service.TypesService;
import org.springframework.stereotype.Service;


@Service("typesService")
public class TypesServiceImpl extends ServiceImpl<TypesDao, TypesEntity> implements TypesService {

}
