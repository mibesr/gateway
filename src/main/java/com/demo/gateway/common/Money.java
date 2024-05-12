package com.demo.gateway.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Money类
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@ToString
@AllArgsConstructor
public class Money {
    private long amount;
    private String currency;

    // TODO 需要增加更多操作

    public static Money of(long amount, String currency) {
        return new Money(amount, currency);
    }
}
