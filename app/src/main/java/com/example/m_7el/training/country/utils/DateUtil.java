package com.example.m_7el.training.country.utils;


import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Calendar getToday() {
        return Calendar.getInstance();
    }

    public static Calendar getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar;
    }

}
