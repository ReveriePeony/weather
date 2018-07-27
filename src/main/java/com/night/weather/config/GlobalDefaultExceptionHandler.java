package com.night.weather.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.night.weather.common.dto.ResponseDTO;
import com.night.weather.common.enums.ErrorCodeMessage;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月25日
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @SuppressWarnings("rawtypes")
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseDTO defaultErrorHandler(HttpServletRequest request, Exception e) {
        return ResponseDTO.createErrorMsg(ErrorCodeMessage.SYSTEM_ERROR);
    }

}
