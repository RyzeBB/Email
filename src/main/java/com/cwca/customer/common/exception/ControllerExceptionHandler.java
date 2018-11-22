package com.cwca.customer.common.exception;

import com.cwca.customer.common.web.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**通过此注解声明此类为一个全局异常处理类型*/
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult handleServiceException(
			   ServiceException e){
		e.printStackTrace();
		//将异常封装到JsonResult
		return new JsonResult(e);
		//this.state=ERROR;
		//this.message=e.getMessage();
	}
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleRuntimeException(RuntimeException e) {
		System.out.println("handleRuntimeException");
		System.out.println("e:"+e.getMessage());
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("exp", e.getMessage());
		return mv;
	}
}
