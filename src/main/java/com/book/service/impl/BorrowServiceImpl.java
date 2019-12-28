package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.dao.BorrowDao;
import com.book.entity.BorrowEntity;
import com.book.service.BorrowService;
import org.springframework.stereotype.Service;


@Service("borrowService")
public class BorrowServiceImpl extends ServiceImpl<BorrowDao, BorrowEntity> implements BorrowService {
}
