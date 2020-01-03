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

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("borrow")
public class BorrowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private String bookId;
	/**
	 * 借书日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date borrowTime;
	/**
	 * 还书日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date returnTime;
	/**
	 * 还款日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date payTime;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 关联书名
	 */
	@TableField(exist = false)
	private String name;
	/**
	 * 关联作者
	 */
	@TableField(exist = false)
	private String author;
	/**
	 * 关联类型
	 */
	@TableField(exist = false)
	private String type;
	/**
	 * 关联出版社
	 */
	@TableField(exist = false)
	private String press;
	/**
	 * 关联单价
	 */
	@TableField(exist = false)
	private BigDecimal price;
	/**
	 * 关联用户名称
	 */
	@TableField(exist = false)
	private String nickName;
}
