package com.rebianne.shopapi.entity.product;

import com.rebianne.shopapi.constant.ProductStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * [2022.07.03] Product entity
 * 상품등록 관련 엔티티
 */
@Entity
@Table(name="TB_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "상품 데이터를 위한 도메인 객체")
public class Product {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "상품명을 입력해주세요")
    private String name;

    @ApiModelProperty(notes = "상품금액을 입력해주세요")
    private int amount;

    @ApiModelProperty(notes = "상품의 바코드를 입력해주세요")
    private String barcode;

    @ApiModelProperty(notes = "상품의 재고을 입력해주세요")
    private int stock;

    @ApiModelProperty(notes = "상품의 판매 여부(Y/N)")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}
