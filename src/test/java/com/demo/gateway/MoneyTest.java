package com.demo.gateway;

import com.demo.gateway.common.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author 隐墨星辰
 */
public class MoneyTest {

    @Test
    public void test1() {
        Money money = Money.of(100, "CNY");
        assertEquals(money.getAmount().doubleValue(), BigDecimal.valueOf(1.00).doubleValue());
        assertEquals(money.getCent(), 100);

        Money money2 = Money.of(BigDecimal.valueOf(100), "CNY");
        assertEquals(money2.getAmount().doubleValue(), BigDecimal.valueOf(100.00).doubleValue());
        assertEquals(money2.getCent(), 10000);

        Money money3 = Money.of(BigDecimal.valueOf(1), "CNY");
        assertEquals(money3.getAmount().doubleValue(), BigDecimal.valueOf(1.00).doubleValue());
        assertEquals(money3.getCent(), 100);


        assertNotEquals(money, money2);
        assertEquals(money, money3);
        assertTrue(money.compareTo(money2) < 0);
        assertEquals(0, money.compareTo(money3));

        assertEquals(money.add(money2).getAmount().doubleValue(), BigDecimal.valueOf(101.00).doubleValue());

        assertEquals(money.divide(3).getAmount().doubleValue(), BigDecimal.valueOf(0.33).doubleValue());
        assertEquals(money2.divide(3).getAmount().doubleValue(), BigDecimal.valueOf(33.33).doubleValue());
        assertEquals(money2.divide(3, RoundingMode.UP).getAmount().doubleValue(), BigDecimal.valueOf(33.34).doubleValue());
        assertEquals(money2.divide(3, RoundingMode.DOWN).getAmount().doubleValue(), BigDecimal.valueOf(33.33).doubleValue());

        assertEquals(money.getCentFactor(), 2);
    }

    @Test
    public void test2() {

        Money money = Money.of(100, "JPY");
        assertEquals(money.getAmount().doubleValue(), BigDecimal.valueOf(100.00).doubleValue());
        assertEquals(money.getCent(), 100);

        Money money2 = Money.of(BigDecimal.valueOf(100), "JPY");
        assertEquals(money2.getAmount().doubleValue(), BigDecimal.valueOf(100.00).doubleValue());
        assertEquals(money2.getCent(), 100);

        Money money3 = Money.of(BigDecimal.valueOf(1), "JPY");
        assertEquals(money3.getAmount().doubleValue(), BigDecimal.valueOf(1.00).doubleValue());
        assertEquals(money3.getCent(), 1);



        assertEquals(money, money2);
        assertNotEquals(money, money3);
        assertTrue(money.compareTo(money3) > 0);
        assertEquals(0, money.compareTo(money2));

        assertEquals(money.add(money2).getAmount().doubleValue(), BigDecimal.valueOf(200.00).doubleValue());

        assertEquals(money.divide(3).getAmount().doubleValue(), BigDecimal.valueOf(33.00).doubleValue());
        assertEquals(money2.divide(3).getAmount().doubleValue(), BigDecimal.valueOf(33.00).doubleValue());
        assertEquals(money2.divide(3, RoundingMode.UP).getAmount().doubleValue(), BigDecimal.valueOf(34.00).doubleValue());
        assertEquals(money2.divide(3, RoundingMode.DOWN).getAmount().doubleValue(), BigDecimal.valueOf(33.00).doubleValue());

        assertEquals(money.getCentFactor(), 0);
    }

}
