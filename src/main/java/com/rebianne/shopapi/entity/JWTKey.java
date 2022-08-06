package com.rebianne.shopapi.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

//jwt 토큰방식으로 변경: entity
@Data
public class JWTKey {

    String value = "";

    public String getValue(){
        if(!StringUtils.isEmpty(value))  return value;

        return "";
    }

}
