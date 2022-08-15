package com.rebianne.shopapi.exception;

import com.rebianne.shopapi.common.SystemCode;
import com.rebianne.shopapi.entity.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

    //User 정보를 찾을 수 없는 Exception일 경우
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setTimestamp(Calendar.getInstance().getTime());
        exceptionResponse.setCode(SystemCode.CODE_USER_NOT_FOUND);
        exceptionResponse.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}
