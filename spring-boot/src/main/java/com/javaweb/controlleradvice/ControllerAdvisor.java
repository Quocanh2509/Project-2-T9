package com.javaweb.controlleradvice;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;
import com.javaweb.Beans.*;
import com.javaweb.customexception.FileRequireException;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(FileRequireException.class)
	public ResponseEntity<Object> handleCityNotFoundException(FileRequireException ex) {

		ErrorReponseDTO errorDTO=new ErrorReponseDTO();
		errorDTO.setError(ex.getMessage());
		List<String> detail = new ArrayList<>();
		detail.add("dữ liệu là null hoặc xâu rỗng!");
		
		errorDTO.setDetail(detail);
		return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
