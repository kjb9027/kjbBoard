package com.kjb.kjbBoard.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		Map<String, Object> map = new LinkedHashMap<>();

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

	public static int getAsInt(Object object, int defaultValue) {
		if (object instanceof BigInteger) {
			return ((BigInteger) object).intValue();
		} else if (object instanceof Double) {
			return (int) Math.floor((double) object);
		} else if (object instanceof Float) {
			return (int) Math.floor((float) object);
		} else if (object instanceof Long) {
			return (int) object;
		} else if (object instanceof Integer) {
			return (int) object;
		} else if (object instanceof String) {
			return Integer.parseInt((String) object);
		}

		return defaultValue;
	}

	public static String msgAndBack(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}

	public static String msgAndReplace(String msg, String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("location.replace('" + url + "');");
		sb.append("</script>");
		return sb.toString();
	}

	public static Map<String, Object> getParamMap(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<>();

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			Object paramValue = request.getParameter(paramName);

			param.put(paramName, paramValue);
		}

		return param;
	}

	public static String toJsonStr(Map<String, Object> param) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(param);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String getUrlEncoded(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static Object ifNull(Object data, Object defaultValue) {
		return data != null ? data : defaultValue;
	}

	public static Object reqAttr(HttpServletRequest request, String attrName, Object defaultValue) {
		return ifNull(request.getAttribute(attrName), defaultValue);
	}

	public static boolean isEmpty(Object data) {
		if(data == null) {
			return true;
		}
		
		if(data instanceof String) {
			String strData = (String)data;
			if(strData.trim().length()==0) {
				return true;
			}
		}
		else if(data instanceof Integer) {
			Integer integerData = (Integer)data;
			return integerData != 0;
		}
		else if(data instanceof List) {
			List listData = (List)data;
			return listData.size() == 0;
		}
		else if(data instanceof Map) {
			Map mapData = (Map)data;
			return mapData.size() == 0;
		}
		return false;
	}

	public static Object ifEmpty(Object data, Object defaultValue) {
		if(isEmpty(data)) {
			return defaultValue;
		}
		return data;
	}
}
