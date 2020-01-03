package com.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.entity.StarEntity;

import java.math.BigDecimal;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-12-25 14:39:17
 */
public interface StarService extends IService<StarEntity> {
    BigDecimal getAvg(String bookId);
}

