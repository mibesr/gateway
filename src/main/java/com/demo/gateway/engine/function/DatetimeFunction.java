package com.demo.gateway.engine.function;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间函数
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class DatetimeFunction extends BaseFunction {

    public String formatDateString(long timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    @Override
    public String getName() {
        return "Datetime";
    }
}
