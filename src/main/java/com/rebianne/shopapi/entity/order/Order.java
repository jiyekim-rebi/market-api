package com.rebianne.shopapi.entity.order;

import com.rebianne.shopapi.constant.OrderStatus;
import com.rebianne.shopapi.entity.Common;
import com.rebianne.shopapi.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * [2022.07.02] order entity
 * key: order_id
 */
@Entity
@Table(name="TB_ORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "사용자별 주문 데이터를 위한 도메인 객체")
public class Order extends Common {

    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "주문 상태값을 입력해주세요")
    private OrderStatus status;

    @ApiModelProperty(notes = "구매자의 결제번호를 입력해주세요")
    private long payment_id;

    @ApiModelProperty(notes = "구매자의 배송비를 입력해주세요")
    private long dlv_amount;

    @ApiModelProperty(notes = "구매자 주소를 입력해주세요.")
    private String address;

    @ApiModelProperty(notes = "구매자 상세주소를 입력해주세요.")
    private String detail_address;

    @ApiModelProperty(notes = "구매자명을 입력해주세요.")
    private String name;

}
