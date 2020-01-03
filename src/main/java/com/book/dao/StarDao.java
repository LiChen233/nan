package com.book.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.entity.StarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-12-25 14:39:17
 */
@Mapper
@Repository
public interface StarDao extends BaseMapper<StarEntity> {
    @Select("SELECT AVG(star) FROM star WHERE book_id = #{bookId}")
	BigDecimal getAvg(String bookId);
}
