package com.demo.gateway;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 隐墨星辰
 */
public class Money2Test {

    @Test
    public void test1() {
        Money money = Money.of(100, "CNY");
        assertEquals(money.getNumber().doubleValue(), BigDecimal.valueOf(100.00).doubleValue());

        Money money2 = Money.of(BigDecimal.valueOf(100), "CNY");
        assertEquals(money2.getNumber().doubleValue(), BigDecimal.valueOf(100.00).doubleValue());

        Money money3 = Money.of(BigDecimal.valueOf(1), "CNY");
        assertEquals(money3.getNumber().doubleValue(), BigDecimal.valueOf(1.00).doubleValue());


        assertEquals(money, money2);
        assertNotEquals(money, money3);
        assertTrue(money.compareTo(money3) > 0);
        assertEquals(0, money.compareTo(money2));

        assertEquals(money.add(money2).getNumber().doubleValue(), BigDecimal.valueOf(200.00).doubleValue());

//        assertEquals(money.divide(3).getNumber().doubleValue(), BigDecimal.valueOf(33.33).doubleValue());
//        assertEquals(money2.divide(3).getNumber().doubleValue(), BigDecimal.valueOf(33.33).doubleValue());
//        assertEquals(money3.divide(3.00).getNumber().doubleValue(), BigDecimal.valueOf(0.33).doubleValue());
//
//        assertEquals(money.getCurrency().getDefaultFractionDigits(), 2);
    }

}
