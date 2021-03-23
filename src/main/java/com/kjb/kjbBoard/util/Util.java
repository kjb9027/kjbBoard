package com.kjb.kjbBoard.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format1.format(time);
	}
	public static Map<String, Object> mapOf(Object... args) {
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
		}
		int size = args.length / 2;
		Map<String,Object> map = new LinkedHashMap<>();
		
		for (int i = 0; i < size; i++) {
			int keyIdx = i * 2;
			int valueIdx = keyIdx + 1;
			String key;
			Object value;
			try {
				key = (String) args[keyIdx];
			} catch (Exception e) {
				throw new IllegalArgumentException("키는 String으로 입력해야됩니다." + e.getMessage());
			}
			value = args[valueIdx];
			map.put(key, value);
		}
		return map;
	}
}
