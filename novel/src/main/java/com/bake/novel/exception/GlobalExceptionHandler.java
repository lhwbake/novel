package com.bake.novel.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bake.novel.dto.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		
		logger.error("错误信息->{}", e.getMessage());
		logger.error("错误信息->{}", e.getCause());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURI());
		mav.setViewName("error");
		return mav;
	}
	
	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ErrorInfo<String> error = new ErrorInfo<String>();
		error.setMessage(e.getMessage());
		error.setCode(ErrorInfo.ERROR);
		error.setData("");
		error.setUrl(req.getRequestURI());
		return error;
	}

}
