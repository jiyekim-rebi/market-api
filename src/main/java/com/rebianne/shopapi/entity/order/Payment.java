package com.rebianne.shopapi.entity.order;

import com.rebianne.shopapi.entity.Common;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_ORDER_PAYMENT")
public class Payment extends Common {

    @Id
    @Column(name="payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
