package com.rebianne.shopapi.exception;

import com.rebianne.shopapi.common.SystemCode;
import com.rebianne.shopapi.entity.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.ws.Response;
import java.util.Calendar;

//https://ugo04.tistory.com/88?category=816744
@RestControllerAdvice
public class ExceptionAdvice {

    //공통 Exception 관련된 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(String code, Exception e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setTimestamp(Calendar.getInstance().getTime());
        exceptionResponse.setCode(code);
        exceptionResponse.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    //user를 찾을 수 없을때
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setTimestamp(Calendar.getInstance().getTime());
        exceptionResponse.setCode(SystemCode.CODE_USER_NOT_FOUND);
        exceptionResponse.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    //유저찾기 관련
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> userNotCreateException(UserException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setTimestamp(Calendar.getInstance().getTime());
        exceptionResponse.setCode(SystemCode.CODE_USER_NOT_CREATE);
        exceptionResponse.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}
