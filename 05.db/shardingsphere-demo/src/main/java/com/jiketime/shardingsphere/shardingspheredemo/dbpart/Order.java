package com.jiketime.shardingsphere.shardingspheredemo.dbpart;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by liaock on 2021/6/27
 **/

@Data
@Builder
@Setter
@Getter
public class Order {

    private int orderId;

    private String orderNo;

    private String createName;

    private BigDecimal price;

}
