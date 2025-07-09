package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static String formatDate(Date date) {
        if (date == null) return null; // or return "N/A", or throw a custom exception
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
