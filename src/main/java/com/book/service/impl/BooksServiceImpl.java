package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.dao.BooksDao;
import com.book.entity.BooksEntity;
import com.book.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("booksService")
public class BooksServiceImpl extends ServiceImpl<BooksDao, BooksEntity> implements BooksService {

    @Autowired
    BooksDao booksDao;

    @Override
    public void borrowBook(String id) {
        booksDao.borrowBook(id);
    }

    @Override
    public void returnBook(String id) {
        booksDao.returnBook(id);
    }
}
