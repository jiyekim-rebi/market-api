package com.rebianne.shopapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rebianne.shopapi.constant.Level;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;

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
@JsonInclude(JsonInclude.Include.NON_NULL) //NULL이 아닌 개체만 return 시킬 예정
public class User extends Common implements UserDetails {

    @Id
    @Column(name="user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Size(min=2, message="Name은 2글자 이상 입력해주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요.")
    private String name;

    @Column(nullable = false, length = 100)
    @ApiModelProperty(notes = "사용자 패스워드를 입력해주세요.")
    private String password;

    @ApiModelProperty(notes = "사용자 주소를 입력해주세요.")
    private String address;

    @ApiModelProperty(notes = "사용자 상세 주소를 입력해주세요.")
    private String detail_address;

    @Column(nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "사용자의 등급을 설정해주세요.")
    private Level level;

    //기본 권한 세팅
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority(level.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() { return this.password; }

    //계정만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //잠긴계정
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(isCredentialsNonExpired() && isAccountNonExpired() && isAccountNonLocked()){
            return true;
        }
        return false;
    }


}
