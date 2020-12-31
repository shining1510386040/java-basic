package com.demo.lambadaDemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description jdk8中的日期处理
 * @date 2020/12/22 10:58
 * @see
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        // 获取当前时间点
        LocalDate now = LocalDate.now();
        System.out.println("==============》》》当前日期是：" + now);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("===========>>>>当前的日期时间是：" + localDateTime);
        LocalDate localDate = LocalDate.of(2020, 12, 22);


        // 日期格式化
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // parttern 不对格式化会报错
        String nowStr = dateformatter.format(now);
        String localDateTimeStr = timeformatter.format(localDateTime);
        System.out.println("格式化日期：" + nowStr);
        System.out.println("格式化日期时间：" + localDateTimeStr);

        // parse 解析字符串为日期，parttern不对格式化会报错
        LocalDate parse = LocalDate.parse("2021-12-29", dateformatter);
        System.out.println("===========>>parse:" + parse);

        // 日期调整
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfMonth();

        String firstDay = localDate.with(temporalAdjuster).format(dateformatter);
        System.out.println("当月的第一天为：" + firstDay);

        // 获取当前的13位毫秒数
        long timeMillis = System.currentTimeMillis();
        // 东8区
        long toEpochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));

        System.out.println(timeMillis);
        System.out.println(toEpochSecond);


    }

}
