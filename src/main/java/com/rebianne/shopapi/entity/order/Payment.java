package com.rebianne.shopapi.entity.order;

import com.rebianne.shopapi.constant.PayStatus;
import com.rebianne.shopapi.entity.Common;
import com.rebianne.shopapi.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_ORDER_PAYMENT")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "결제 데이터를 위한 도메인 객체")
public class Payment extends Common {

    @Id
    @Column(name="payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ApiModelProperty(notes = "총 결제금액을 입력해주세요")
    private int total_amount;

    @ApiModelProperty(notes = "순이익을 입력해주세요")
    private int net_amount;

    @ApiModelProperty(notes = "할인 금액을 입력해주세요")
    private int dis_amount;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "결제상태: PAYMENT, 환불상태: CANCEL")
    private PayStatus status;

}
