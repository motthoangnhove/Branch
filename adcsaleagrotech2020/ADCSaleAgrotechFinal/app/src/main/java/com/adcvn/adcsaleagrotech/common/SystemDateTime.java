package com.adcvn.adcsaleagrotech.common;

import android.os.Build;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.ChronoUnit;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class SystemDateTime {

    // get current time (2019-11-20)
    public static String getDateTimeCurrent() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String day = String.valueOf(dayOfMonth).length() == 1 ? 0 + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
        String month = String.valueOf(monthOfYear).length() == 1 ? 0 + String.valueOf(monthOfYear) : String.valueOf(monthOfYear);
        String date = year + "-" + month
                + "-" + day;
        return date;
    }

    public static int getBetweenTwoDate(String oldDate) {
        int betweenDate = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate d1 = LocalDate.parse(oldDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate d2 = LocalDate.parse(getDateTimeCurrent(), DateTimeFormatter.ISO_LOCAL_DATE);
            Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
            betweenDate = (int) diff.toDays();
        } else {
            org.threeten.bp.LocalDate d1 = org.threeten.bp.LocalDate.parse(oldDate, org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            org.threeten.bp.LocalDate d2 = org.threeten.bp.LocalDate.parse(getDateTimeCurrent(), org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            betweenDate = (int) ChronoUnit.DAYS.between(d1, d2);
        }
        return betweenDate;
    }

    public static String formatDateToServer(String date) {
        String resultDate = "";
        StringTokenizer st = new StringTokenizer(date, "/");
        List<String> dateElements = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            dateElements.add(st.nextToken());
        }
        for (int i = dateElements.size() - 1; i >= 0; i--) {
            resultDate += dateElements.get(i) + "-";
        }
        resultDate = resultDate.substring(0, resultDate.length() - 1);

        return resultDate;
    }

    public static String formatDateToClient(String date) {
        String resultDate = "";
        StringTokenizer st = new StringTokenizer(date, "-");
        List<String> dateElements = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            dateElements.add(st.nextToken());
        }
        for (int i = dateElements.size() - 1; i >= 0; i--) {
            resultDate += dateElements.get(i) + "/";
        }
        resultDate = resultDate.substring(0, resultDate.length() - 1);

        return resultDate;
    }

    // xử lý định dạng ngày time zone về ngày/tháng/năm
    public static String formatDateTimeZone(String dateTimezone) {
        int day, month, year;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime time = OffsetDateTime.parse(dateTimezone, DateTimeFormatter.ISO_DATE_TIME);
            day = time.getDayOfMonth();
            month = time.getMonthValue();
            year = time.getYear();
        } else {
            org.threeten.bp.LocalDate time = org.threeten.bp.LocalDate.parse(dateTimezone, org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            day = time.getDayOfMonth();
            month = time.getMonthValue();
            year = time.getYear();
        }
        String d = day < 10 ? "0" + day : String.valueOf(day);
        String m = month < 10 ? "0" + month : String.valueOf(month);
        return d.concat("/").concat(m).concat("/").concat(String.valueOf(year));
    }

    // xử lý định dạng ngày time zone về ngày/tháng/năm giờ:phút
    public static String formatDateTimeZoneMinute(String dateTimezone) {
        int day, month, year, hour, minute;
        String d, m, typeTime;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime time = OffsetDateTime.parse(dateTimezone, DateTimeFormatter.ISO_DATE_TIME);
            day = time.getDayOfMonth();
            month = time.getMonthValue();
            year = time.getYear();
            hour = time.getHour();
            minute = time.getMinute();
            typeTime = time.getHour() < 12 ? "AM" : "PM";
        } else {
            LocalDateTime time = LocalDateTime.parse(dateTimezone, org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            day = time.getDayOfMonth();
            month = time.getMonthValue();
            year = time.getYear();
            hour = time.getHour();
            minute = time.getMinute();
            typeTime = time.getHour() < 12 ? "AM" : "PM";
        }
        d = day < 10 ? "0" + day : String.valueOf(day);
        m = month < 10 ? "0" + month : String.valueOf(month);
        String sMinute = minute > 10 ? String.valueOf(minute) : "0" + minute;
        return d.concat("/").concat(m).concat("/").concat(String.valueOf(year)).concat(" ").concat(String.valueOf(hour)).concat(":").concat(sMinute).concat(" ").concat(typeTime);
    }
    // xử lý định dạng ngày time zone về giờ:phút
    public static String formatDateTimeZoneHourMinute(String dateTimezone){
        int day, month, year, hour, minute;
        String d, m, typeTime;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime time = OffsetDateTime.parse(dateTimezone, DateTimeFormatter.ISO_DATE_TIME);
            hour = time.getHour();
            minute = time.getMinute();
            typeTime = time.getHour()<12 ? "AM":"PM";
        }else{
            LocalDateTime time = LocalDateTime.parse(dateTimezone, org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            hour = time.getHour();
            minute = time.getMinute();
            typeTime = time.getHour() <12 ? "AM":"PM";
        }
        String sMinute = minute > 10 ? String.valueOf(minute) : "0" + minute;
        return String.valueOf(hour).concat(":").concat(sMinute).concat(" ").concat(typeTime);
    }
    // xử lý định dạng ngày time zone về ngày/tháng/năm giờ:phút
    public static String formatDateTimeZoneCaseToDay(String dateTimezone) {
        String date, hm, typeTime, dateTime;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime time = OffsetDateTime.parse(dateTimezone, DateTimeFormatter.ISO_DATE_TIME);
            int day = time.getDayOfMonth();
            int month = time.getMonthValue();
            int year = time.getYear();
            String d = day < 10 ? "0" + day : String.valueOf(day);
            String m = month < 10 ? "0" + month : String.valueOf(month);
            typeTime = time.getHour() < 12 ? "AM" : "PM";
            date = d.concat("/").concat(m).concat("/").concat(String.valueOf(year));
            String minute = time.getMinute() > 10 ? String.valueOf(time.getMinute()) : "0" + time.getMinute();
            hm = String.valueOf(time.getHour()).concat(":").concat(minute);
        } else {
            LocalDateTime time = LocalDateTime.parse(dateTimezone, org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            int day = time.getDayOfMonth();
            int month = time.getMonthValue();
            int year = time.getYear();
            String d = day < 10 ? "0" + day : String.valueOf(day);
            String m = month < 10 ? "0" + month : String.valueOf(month);
            typeTime = time.getHour() < 12 ? "AM" : "PM";
            date = d.concat("/").concat(m).concat("/").concat(String.valueOf(year));
            String minute = time.getMinute() > 10 ? String.valueOf(time.getMinute()) : "0" + time.getMinute();
            hm = String.valueOf(time.getHour()).concat(":").concat(minute);
        }
        if (date.equals(formatDateToClient(getDateTimeCurrent()))) {
            dateTime = hm.concat(" ").concat(typeTime);
        } else {
            dateTime = date;
        }
        return dateTime;
    }

    // xử lý định dạng ngày time zone về giờ:phút ngày/tháng/năm
    public static String formatDateTimeZoneCaseToTimeDay(String dateTimezone) {
        int day, month, year, hour, minute;
        String d, m, typeTime;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime time = OffsetDateTime.parse(dateTimezone, DateTimeFormatter.ISO_DATE_TIME);
            day = time.getDayOfMonth();
            month = time.getMonthValue();
            year = time.getYear();
            hour = time.getHour();
            minute = time.getMinute();
            typeTime = time.getHour() < 12 ? "AM" : "PM";
        } else {
            LocalDateTime time = LocalDateTime.parse(dateTimezone, org.threeten.bp.format.DateTimeFormatter.ISO_DATE_TIME);
            day = time.getDayOfMonth();
            month = time.getMonthValue();
            year = time.getYear();
            hour = time.getHour();
            minute = time.getMinute();
            typeTime = time.getHour() < 12 ? "AM" : "PM";
        }
        d = day < 10 ? "0" + day : String.valueOf(day);
        m = month < 10 ? "0" + month : String.valueOf(month);
        String sMinute = minute > 10 ? String.valueOf(minute) : "0" + minute;
        return String.valueOf(hour).concat(":").concat(sMinute).concat(" ").concat(typeTime).concat(" ").concat(d).concat("/").concat(m).concat("/").concat(String.valueOf(year)).concat(" ");
    }

//Xử lý định dạng ngày tháng thứ, tháng, ngày hiển thị lên lịch
    public static String convertDateToShowCalender(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd");
        return formatter.format(date);
    }

}
