package com.blueharvest.assignment.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is global exception handler to decorate the exceptions consistently. It
 * logs the stacktrace and returns the message to upstream.
 * 
 * @author nbhutada
 *
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleException(HttpServletRequest req, Exception e) {
		LOGGER.error("***************************************************");
		LOGGER.error("Request " + req.getRequestURI() + " raised error", e);
		LOGGER.error("***************************************************");
		Map<String, Object> ret = new LinkedHashMap<>(5);
		ret.put("timestamp", Instant.now());
		Integer statusCode = 500;
		ret.put("status", statusCode);
		ret.put("error", HttpStatus.valueOf(statusCode));
		ret.put("message", e.getMessage());
		ret.put("path", req.getRequestURI());
		return ResponseEntity.status(statusCode).body(ret);
	}
}
