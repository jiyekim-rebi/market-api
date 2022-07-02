package com.rebianne.shopapi.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

//테이블별 공통 필드
@EntityListeners(value= {AuditingEntityListener.class})
@Data
public class Common {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regist_date;

    @LastModifiedDate
    private LocalDateTime update_date;

}
