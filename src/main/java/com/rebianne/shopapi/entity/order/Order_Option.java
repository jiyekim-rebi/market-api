package com.rebianne.shopapi.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="TB_ORDER_OPTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "사용자별 주문 옵션 데이터를 위한 도메인 객체")
public class Order_Option {

    @Id
    @Column(name="option_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Order_Product product;

    @ApiModelProperty(notes = "옵션 데이터 정렬 순서 지정")
    private int sequence;

    @ApiModelProperty(notes = "옵션명을 입력해주세요.")
    private String name;

    @ApiModelProperty(notes = "옵션 금액을 입력해주세요.")
    private int amount;

    @ApiModelProperty(notes = "옵션 구매 개수를 입력해주세요.")
    private int stock;

}
