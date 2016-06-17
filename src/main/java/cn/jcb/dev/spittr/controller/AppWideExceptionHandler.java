package cn.jcb.dev.spittr.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.jcb.dev.spittr.data.DuplicateSpitterException;

@ControllerAdvice
public class AppWideExceptionHandler {
	
	@ExceptionHandler(DuplicateSpitterException.class)
	public String handlerDuplicateSpitter(){
		return "error/duplicate";
	}

}
