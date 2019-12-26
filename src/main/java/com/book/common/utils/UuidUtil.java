package com.book.common.utils;

import java.util.UUID;

public class UuidUtil {

	/**
	 * 返回UUID
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}

