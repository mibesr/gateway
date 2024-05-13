package com.demo.gateway.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Money {
    private long amount;
    private String currency;

    public static Money of(long amount, String currency) {
        return new Money(amount, currency);
    }
}
