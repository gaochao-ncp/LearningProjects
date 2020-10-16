package com.gc.pattern.proxy.dbroute;

import lombok.Data;

/**
 * Created by Tom.
 */
@Data
public class Order {
    private Object orderInfo;
    //订单创建时间进行按年分库
    private Long createTime;
    private String id;

}
