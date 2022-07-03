package com.rebianne.shopapi.entity.product;

import com.rebianne.shopapi.constant.ProductStatus;
import com.rebianne.shopapi.entity.Common;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * [2022.07.03] Product Option entity
 * 상품 옵션등록 관련 엔티티
 */

@Entity
@Data
@Table(name="TB_PRODUCT_OPTION")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "상품 옵션 데이터를 위한 도메인 객체")
public class Option extends Common {

    @Id
    @Column(name="option_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ApiModelProperty(notes = "옵션 정렬순서를 지정해주세요")
    private int sequence;

    @ApiModelProperty(notes = "옵션명을 입력해주세요")
    private String name;

    @ApiModelProperty(notes = "옵션금액을 입력해주세요")
    private int amount;

    @ApiModelProperty(notes = "옵션의 바코드를 입력해주세요")
    private String barcode;

    @ApiModelProperty(notes = "옵션의 재고을 입력해주세요")
    private int stock;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes="옵션 상품의 필수 유무(Y/N)")
    private ProductStatus is_required;

    @ApiModelProperty(notes = "옵션 판매 여부(Y/N)")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}
