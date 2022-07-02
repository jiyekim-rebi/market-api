package com.rebianne.shopapi.entity;

import com.rebianne.shopapi.constant.Level;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * [2022.07.01] user entity
 * key: user_id, email
 */
@Entity
@Table(name="TB_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User extends Common {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //PK
    @Column(unique = true)
    private String email;

    @Size(min=2, message="Name은 2글자 이상 입력해주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요.")
    private String name;

    @ApiModelProperty(notes = "사용자 패스워드를 입력해주세요.")
    private String password;

    @ApiModelProperty(notes = "사용자 주소를 입력해주세요.")
    private String address;

    @ApiModelProperty(notes = "사용자 상세 주소를 입력해주세요.")
    private String detail_address;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "사용자의 등급을 설정해주세요.")
    private Level level;


}
