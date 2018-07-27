package com.night.weather.common.dto;

import com.night.weather.common.enums.ErrorCodeMessage;

import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月25日
 * @param <T>
 */
@Data
//@ApiModel("统一响应对象")
public class ResponseDTO<T> {
	
//	@ApiModelProperty("代码")
	private Integer code;
	
//	@ApiModelProperty("描述")
	private String msg;
	
//	@ApiModelProperty("数据")
	private T data;
	
	public ResponseDTO() {}

	public ResponseDTO(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ResponseDTO(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static <T> ResponseDTO<T> createSuccessMsg(){
		return new ResponseDTO<T>(1, "success");
	}
	
	public static <T> ResponseDTO<T> createErrorMsg(){
		return new ResponseDTO<T>(0, "error");
	}

	public static <T> ResponseDTO<T> createSuccessMsg(String msg){
		return new ResponseDTO<T>(1, msg);
	}
	
	public static <T> ResponseDTO<T> createErrorMsg(String msg){
		return new ResponseDTO<T>(0, msg);
	}
	
	public static <T> ResponseDTO<T> createSuccessMsg(int code, String msg){
		return new ResponseDTO<T>(code, msg);
	}
	
	public static <T> ResponseDTO<T> createErrorMsg(int code, String msg){
		return new ResponseDTO<T>(code, msg);
	}
	
	public static <T> ResponseDTO<T> createSuccessMsg(ErrorCodeMessage e){
		return new ResponseDTO<T>(e.getCode(), e.getMsg());
	}
	
	public static <T> ResponseDTO<T> createErrorMsg(ErrorCodeMessage e){
		return new ResponseDTO<T>(e.getCode(), e.getMsg());
	}
	
	public static <T> ResponseDTO<T> createSuccess(T t){
		return new ResponseDTO<T>(1, "success", t);
	}
	
	public static <T> ResponseDTO<T> createError(T t){
		return new ResponseDTO<T>(0, "error", t);
	}
	
}
