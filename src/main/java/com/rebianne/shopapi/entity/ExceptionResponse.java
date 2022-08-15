package com.rebianne.shopapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//2022.08.15 에러처리 전용 객체
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String code;
    private String message;
    private Date timestamp;

}
