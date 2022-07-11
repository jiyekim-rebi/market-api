package com.rebianne.shopapi.entity.order;

import com.rebianne.shopapi.entity.Common;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * [2022.07.02] Order Product entity
 * key: product_id
 */
@Entity
@Table(name="TB_ORDER_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "주문 데이터 내 상품 데이터를 위한 도메인 객체")
public class Order_Product extends Common {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ApiModelProperty(notes = "상품명을 입력해주세요")
    private String name;
    
    @ApiModelProperty(notes = "상품금액을 입력해주세요")
    private int amount;

    @ApiModelProperty(notes = "상품의 구매 수량을 입력해주세요")
    private int stock;

}
