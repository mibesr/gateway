package com.demo.gateway.engine.function;


import com.demo.gateway.common.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class MoneyFunction extends BaseFunction {
    @Override
    public String getName() {
        return "Money";
    }

    public static Money of(long amount, String currency) {
        return new Money(amount, currency);
    }
}
