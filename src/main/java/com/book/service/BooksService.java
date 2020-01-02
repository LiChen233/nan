package com.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.entity.BooksEntity;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-12-25 14:39:17
 */
public interface BooksService extends IService<BooksEntity> {
    void borrowBook(String id);
    void returnBook(String id);
}

