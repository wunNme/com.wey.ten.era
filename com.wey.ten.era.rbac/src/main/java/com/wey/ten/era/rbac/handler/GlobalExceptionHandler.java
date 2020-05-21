package com.wey.ten.era.rbac.handler;

import javax.security.auth.login.AccountExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.wey.ten.era.common.exception.BaseException;
import com.wey.ten.era.common.exception.CustomException;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 基础异常
	 */
	@ExceptionHandler(BaseException.class)
	public ResultObject baseException(BaseException e) {
		return ResultObject.fail(e.getMessage());
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(CustomException.class)
	public ResultObject businessException(CustomException e) {
		if (StringUtils.isNull(e.getCode())) {
			return ResultObject.fail(e.getMessage());
		}
		return ResultObject.fail(e.getCode().toString(), e.getMessage());
	}

	@ExceptionHandler(AccountExpiredException.class)
	public ResultObject handleAccountExpiredException(AccountExpiredException e) {
		log.error(e.getMessage(), e);
		return ResultObject.fail(e.getMessage());
	}

	/*
	 * @ExceptionHandler(UsernameNotFoundException.class) public ResultObject
	 * handleUsernameNotFoundException(UsernameNotFoundException e) {
	 * log.error(e.getMessage(), e); return ResultObject.fail(e.getMessage()); }
	 */

	@ExceptionHandler(Exception.class)
	public ResultObject handleException(Exception e) {
		log.error(e.getMessage(), e);
		return ResultObject.fail(e.getMessage());
	}

}
