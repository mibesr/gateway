package com.demo.gateway.common;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Money类
 *
 * @author 隐墨星辰
 */
@ToString
@AllArgsConstructor
public class Money {
    /**
     * 币种最小单位
     */
    private long cent;

    /**
     * 币种
     */
    private Currency currency;

    /**
     * @param minorUnits
     * @param currencyCode
     * @return
     */
    public static Money of(long minorUnits, String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
        assertNotNull(currency, "currency can not be null: " + currencyCode);

        return of(minorUnits, currency);
    }

    /**
     * 创建Money对象
     *
     * @param minorUnits
     * @param currency
     * @return
     */
    public static Money of(long minorUnits, Currency currency) {
        Money money = new Money(0, currency);

        money.cent = minorUnits;

        return money;
    }

    /**
     * 获取金额数，单位为元
     * 内部系统强制使用getAmount，不能使用getCents。
     * 比如：人民币 1元，返回1，100分返回1。日元最小单位是1元，返回1
     *
     * @return
     */
    public BigDecimal getAmount() {
        return BigDecimal.valueOf(cent, currency.getDefaultFractionDigits());
    }

    /**
     * 返回币种最小单位
     * 内部系统强制使用getAmount，不能使用getCents，除非在和银行渠道对接时，需要使用getCent。
     *
     * @return
     */
    public long getCent() {
        return cent;
    }

    /**
     * 返回元与分的位数，比如人民币，返回2，日元返回0
     */
    public final int getCentFactor() {
        return currency.getDefaultFractionDigits();
    }

    /**
     * 除法
     *
     * @param divisor
     * @return
     */
    public Money divide(long divisor) {
        return divide(divisor, RoundingMode.HALF_EVEN);
    }

    /**
     * 除法
     *
     * @param divisor
     * @return
     */
    public Money divide(long divisor, RoundingMode roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(BigDecimal.valueOf(divisor), roundingMode);
        return Money.of(newCent.longValue(), this.currency);
    }

    /**
     * 加法
     *
     * @param other
     * @return
     */
    public Money add(Money other) {
        assertSameCurrency(other);
        return Money.of(this.cent + other.cent, this.currency);
    }

    /**
     * 减法
     *
     * @param other
     * @return
     */
    public Money subtract(Money other) {
        assertSameCurrency(other);
        return Money.of(this.cent - other.cent, this.currency);
    }

    /**
     * 乘法
     *
     * @param factor
     * @return
     */
    public Money multiply(long factor) {
        return Money.of(this.cent * factor, this.currency);
    }

    /**
     * 相同币种比较
     *
     * @param other
     */
    private void assertSameCurrency(Money other) {
        if (!Objects.equals(this.currency, other.currency)) {
            throw new IllegalArgumentException("Money instances must have the same currency");
        }
    }
}
