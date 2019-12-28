package com.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-12-25 14:39:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("books")
public class BooksEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String isbn;
	/**
	 * 书名
	 */
	private String name;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 书籍详情
	 */
	private String details;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 出版社
	 */
	private String press;
	/**
	 * 出版时间
	 */
	private Date pushTime;
	/**
	 * 打印时间
	 */
	private Date printTime;
	/**
	 * 版次
	 */
	private String edition;
	/**
	 * 印次
	 */
	private String print;
	/**
	 * 规格
	 */
	private String specs;
	/**
	 * 数量
	 */
	private Integer count;
	/**
	 * 状态
	 */
	private String status;

}
