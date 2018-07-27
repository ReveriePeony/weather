package com.night.weather.common.enums;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月25日
 */
public enum ErrorCodeMessage {
	
	NOT_FOUND(404, "请求不存在"),
	SYSTEM_ERROR(500, "系统错误"),
	FIND_ERROR(10000, "查询失败"),
	INSERT_ERROR(10001, "新增失败"),
	DEL_ERROR(10002, "删除失败"),
	
	;

	private Integer code;
	private String msg;
	
	private ErrorCodeMessage(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
