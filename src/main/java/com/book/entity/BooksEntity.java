package com.book.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@Builder
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
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pushTime;
	/**
	 * 打印时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 搜索关键字
	 */
	@TableField(exist = false)
	private String searchName;
	/**
	 * 出版时间
	 */
	@TableField(exist = false)
	private String searchPushTime;
	/**
	 * 作者
	 */
	@TableField(exist = false)
	private String searchAuthor;
	/**
	 * 出版社
	 */
	@TableField(exist = false)
	private String searchPress;
}
