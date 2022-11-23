package com.te.lmsproject.lmsexceptionhandler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.lmsproject.lmscustomexception.CustomException;
import com.te.lmsproject.lmsresponce.Responce;
import com.te.lmsproject.lmsresponce.ValidationResponce;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Responce> customExceptionHandler(CustomException exception) {
		return new ResponseEntity<Responce>(new Responce(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationResponce> handleInvalidArgument(MethodArgumentNotValidException ex) {
		List<String> list = ex.getBindingResult().getAllErrors().stream().map(t -> t.getDefaultMessage()).toList();
		return new ResponseEntity<ValidationResponce>(new ValidationResponce(true, list, null), HttpStatus.BAD_REQUEST);
	}
}
