/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.siemens.pcf.common.constant.DateTimeConstants;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * date time util.
 *
 * @author pdai
 */
public final class DateTimeUtil {

    /**
     * period to date key map format key.
     */
    private static final Map<String, String> PERIOD_DATE_KEY_MAP;

    static {
        PERIOD_DATE_KEY_MAP = new LinkedHashMap<>();
        PERIOD_DATE_KEY_MAP.put(DateTimeConstants.PERIOD_YEAR, DateTimeConstants.FORMAT_YYYY);
        PERIOD_DATE_KEY_MAP.put(DateTimeConstants.PERIOD_MONTH, DateTimeConstants.FORMAT_YYYY_MM);
        PERIOD_DATE_KEY_MAP.put(DateTimeConstants.PERIOD_DAY, DateTimeConstants.FORMAT_YYYY_MM_DD);
        PERIOD_DATE_KEY_MAP.put(DateTimeConstants.PERIOD_HOUR, DateTimeConstants.FORMAT_YYYY_MM_DD_HH);
        PERIOD_DATE_KEY_MAP.put(DateTimeConstants.PERIOD_MINUTE, DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM);
        PERIOD_DATE_KEY_MAP.put(DateTimeConstants.PERIOD_SECOND, DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * no instance.
     */
    private DateTimeUtil() {
    }

    /**
     * get current unix timestamp.
     *
     * @return long
     */
    public static long currentTime() {
        return Instant.now().toEpochMilli();
    }

    /**
     * convert to millis which date time is from system default zone.
     *
     * @param localDateTime
     *            local date time
     * @return epoch millis
     */
    public static long convert2EpochMilli(LocalDateTime localDateTime) {
        return convert2EpochMilli(localDateTime, getSystemDefaultZoneId());
    }

    /**
     * convert to millis which date time is from system default zone.
     *
     * @param localDateTime
     *            local date time
     * @return epoch millis
     */
    public static long convert2EpochMilliUTC(LocalDateTime localDateTime) {
        return convert2EpochMilli(
                convertDateFromOneZone2Another(localDateTime, getSystemDefaultZoneId(), DateTimeConstants.UTC_ZONE),
                getSystemDefaultZoneId());
    }

    /**
     * convert millis which LocalDateTime from 'zoneId'.
     *
     * @param localDateTime
     *            local date time
     * @param zoneId
     *            zoneId
     * @return epochMilli
     */
    public static long convert2EpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * convert epoch second to local date time.
     *
     * @param epochSecond
     *            epoch second
     * @return local date time
     */
    public static LocalDateTime convertFromEpochSecond(Long epochSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), getSystemDefaultZoneId());
    }

    /**
     * get system default zone id.
     *
     * @return zone id
     */
    public static ZoneId getSystemDefaultZoneId() {
        return ZoneId.systemDefault();
    }

    /**
     * Parse local date time from date time string by "yyyy-MM-dd HH:mm:ss.SSS" pattern.
     *
     * @param dateTimeStr
     *            local date time string
     * @return local date time instance
     */
    public static LocalDateTime parseDateTimeYYYYMMDDHHMMSSSSS(String dateTimeStr) {
        if (dateTimeStr.length() == DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.length()) {
            return parseDateTime(dateTimeStr, DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS_SSS);
        } else {
            StringBuilder s = new StringBuilder(dateTimeStr);
            int l = DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS_SSS.length() - dateTimeStr.length();
            for (int i = 0; i < l; i++) {
                s.append(0);
            }
            return parseDateTime(s.toString(), DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS_SSS);
        }
    }

    /**
     * Parse local date time from date time string by "yyyy-MM-dd HH:mm:ss" pattern.
     *
     * @param dateTimeStr
     *            local date time string
     * @return local date time instance
     */
    public static LocalDateTime parseDateTimeYYYYMMDDHHMMSS(String dateTimeStr) {
        return parseDateTime(dateTimeStr, DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * Parse local date time from date time string by pattern key.
     *
     * @param dateTimeStr
     *            local date time string
     * @param patternKey
     *            date time formatter key
     * @return local date time instance
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String patternKey) {
        return parseDateTime(dateTimeStr, DateTimeFormatter.ofPattern(patternKey));
    }

    /**
     * Parse local date time from date time string by pattern.
     *
     * @param dateTimeStr
     *            local date time string
     * @param pattern
     *            date time formatter
     * @return local date time instance
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter pattern) {
        return LocalDateTime.parse(dateTimeStr, pattern);
    }

    /**
     * Format local date time to label.
     *
     * @param localDateTime
     *            local date time.
     * @return date label formatted by pattern
     */
    public static String formatDateTimeYYYYMMDDHHMMSS(LocalDateTime localDateTime) {
        return formatDateTime(localDateTime, DateTimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * Format local date time to label.
     *
     * @param localDateTime
     *            local date time.
     * @return date label formatted by pattern
     */
    public static String formatDateTimeYYYYMMDDHH(LocalDateTime localDateTime) {
        return formatDateTime(localDateTime, DateTimeConstants.FORMAT_YYYY_MM_DD_HH);
    }

    /**
     * Format local date time to label.
     *
     * @param localDateTime
     *            local date time.
     * @param pattern
     *            date time formatter.
     * @return date label formatted by pattern
     */
    public static String formatDateTime(LocalDateTime localDateTime, DateTimeFormatter pattern) {
        return localDateTime.format(pattern);
    }

    /**
     * Format local date time to label.
     *
     * @param localDateTime
     *            local date time.
     * @param patternKey
     *            date time formatter key for pattern
     * @return date label formatted by pattern
     */
    public static String formatDateTime(LocalDateTime localDateTime, String patternKey) {
        return formatDateTime(localDateTime, DateTimeFormatter.ofPattern(patternKey));
    }

    /**
     * Split duration by hour.
     *
     * @param totalDuration
     *            total duration
     * @param splitDurationHours
     *            split hour
     * @return duration list
     */
    public static List<LocalDateTime[]> splitDurationByHour(LocalDateTime[] totalDuration, int splitDurationHours) {
        List<LocalDateTime[]> resultList = new ArrayList<>();

        int min = totalDuration[0].getHour() / splitDurationHours;
        LocalDateTime startDateTime =
                totalDuration[0].minusHours(totalDuration[0].getHour() - (long) min * splitDurationHours).withMinute(0)
                        .withSecond(0).withNano(0);
        LocalDateTime endDateTime;
        do {
            endDateTime = startDateTime.plusHours(splitDurationHours);
            if (totalDuration[1].isAfter(endDateTime.minusNanos(1))) {
                resultList.add(new LocalDateTime[] { startDateTime.plusNanos(0), endDateTime.minusNanos(1) });
            }
            startDateTime = endDateTime;
        } while (totalDuration[1].isAfter(endDateTime));

        return resultList;
    }

    /**
     * get finical year by date time.
     *
     * @param dateTime
     *            date time
     * @return FY key
     */
    public static String getLastFinicalYearKey(LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        int year = date.getYear() - 1;
        LocalDate splitDate = LocalDate.of(year, Month.OCTOBER.getValue(), 1);
        return date.isBefore(splitDate) ? String.valueOf(year - 1) : String.valueOf(year);
    }

    /**
     * get date keys by period.
     *
     * @param year
     *            year
     * @param frequency
     *            frequency
     * @return list
     */
    public static List<String> getPeriodKeyListByFrequency(int year, String frequency) {
        LocalDateTime[] duration = getYearDuration(year);
        return getPeriodKeyListByFrequency(duration[0], duration[1], frequency);
    }

    /**
     * get date keys by period.
     *
     * @param period
     *            period
     * @param startDate
     *            startDate
     * @param endDate
     *            endDate
     * @return linkedList
     */
    public static List<String> getPeriodKeyListByFrequency(LocalDateTime startDate, LocalDateTime endDate,
            String period) {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        LocalDateTime startDateTime = startDate;
        while (startDateTime.isBefore(endDate)) {
            linkedHashSet.add(getPeriodKeyByFrequency(startDateTime, period));
            switch (period) {
            case DateTimeConstants.PERIOD_YEAR:
                startDateTime = startDateTime.plusYears(1);
                break;
            case DateTimeConstants.PERIOD_QUARTER:
                startDateTime = startDateTime.plusMonths(DateTimeConstants.MONTH_OF_QUARTER);
                break;
            case DateTimeConstants.PERIOD_MONTH:
                startDateTime = startDateTime.plusMonths(1);
                break;
            case DateTimeConstants.PERIOD_DAY:
                startDateTime = startDateTime.plusDays(1);
                break;
            case DateTimeConstants.PERIOD_WEEK:
                startDateTime = startDateTime.plusWeeks(1);
                break;
            case DateTimeConstants.PERIOD_HOUR:
                startDateTime = startDateTime.plusHours(1);
                break;
            case DateTimeConstants.PERIOD_MINUTE:
                startDateTime = startDateTime.plusMinutes(1);
                break;
            case DateTimeConstants.PERIOD_SECOND:
                startDateTime = startDateTime.plusSeconds(1);
                break;
            default:
                return new LinkedList<>();
            }
        }
        linkedHashSet.add(getPeriodKeyByFrequency(endDate, period));
        return new LinkedList<>(linkedHashSet);
    }

    /**
     * Format local date time to period key based on SystemDefaultZone.
     * <p>
     * localDateTime:2020-01-01 12:00:00<br/>
     * period year -> 2020<br/>
     * period quarter -> 2020-Q1<br/>
     * period month -> 2020-01<br/>
     * period day -> 2020-01-01<br/>
     * period hour -> 2020-01-01 12<br/>
     * period minute -> 2020-01-01 12:00<br/>
     * period second -> 2020-01-01 12:00:00<br/>
     * period week -> 2020-1<br/>
     *
     * @param dateTime
     *            local date time
     * @param period
     *            year, month, week, day, hour, minute, second
     * @return date key formatted by period based on SystemDefaultZone
     */
    public static String getPeriodKeyByFrequency(LocalDateTime dateTime, String period) {
        if (DateTimeConstants.PERIOD_MONTH.equals(period)) {
            String monthKey = formatDateTime(dateTime, PERIOD_DATE_KEY_MAP.get(DateTimeConstants.PERIOD_MONTH));
            String year = monthKey.split(DateTimeConstants.DATE_SEPARATOR)[0];
            int month = Integer.parseInt(monthKey.split(DateTimeConstants.DATE_SEPARATOR)[1]);
            return CharSequenceUtil.join(DateTimeConstants.DATE_SEPARATOR, year, Month.of(month).name());
        } else if (PERIOD_DATE_KEY_MAP.containsKey(period)) {
            return formatDateTime(dateTime, PERIOD_DATE_KEY_MAP.get(period));
        } else if (DateTimeConstants.PERIOD_QUARTER.equals(period)) {
            String monthKey = formatDateTime(dateTime, PERIOD_DATE_KEY_MAP.get(DateTimeConstants.PERIOD_MONTH));
            String year = monthKey.split(DateTimeConstants.DATE_SEPARATOR)[0];
            int month = Integer.parseInt(monthKey.split(DateTimeConstants.DATE_SEPARATOR)[1]);
            return CharSequenceUtil.join("Q", year,
                    String.valueOf(
                            month % DateTimeConstants.MONTH_OF_QUARTER == 0 ? month / DateTimeConstants.MONTH_OF_QUARTER
                                    : month / DateTimeConstants.MONTH_OF_QUARTER + 1));
        } else if (DateTimeConstants.PERIOD_WEEK.equals(period)) {
            int weekNum = getWeekNumber(dateTime);
            return CharSequenceUtil.join(DateTimeConstants.DATE_SEPARATOR,
                    Arrays.asList(String.valueOf(dateTime.getYear()), String.valueOf(weekNum)));
        }
        return null;
    }

    /**
     * get week num of local date time.
     *
     * @param dateTime
     *            dateTime
     * @return weekNumber
     */
    private static int getWeekNumber(LocalDateTime dateTime) {
        TemporalField woField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return dateTime.toLocalDate().get(woField);
    }

    /**
     * get year duration.
     *
     * @param year
     *            year
     * @return duration
     */
    public static LocalDateTime[] getYearDuration(int year) {
        LocalDateTime endDateTime = getStartDateOfYear(year + 1).minusNanos(1);
        if (LocalDateTime.now().isBefore(endDateTime)) {
            endDateTime = LocalDateTime.now();
        }
        return new LocalDateTime[] { getStartDateOfYear(year), endDateTime };
    }

    /**
     * get start date of year.
     *
     * @param year
     *            year
     * @return date
     */
    public static LocalDateTime getStartDateOfYear(int year) {
        return LocalDateTime.of(year, 1, 1, 0, 0);
    }

    /**
     * convert date time which comes from one zone to another zone.
     *
     * @param fromDateTime
     *            date time
     * @param fromZoneId
     *            date time comes form which zone
     * @param toZoneId
     *            date time comes to which zone
     * @return formatted date time from 'fromZoneId' to 'toZoneId'
     */
    public static LocalDateTime convertDateFromOneZone2Another(LocalDateTime fromDateTime, ZoneId fromZoneId,
            ZoneId toZoneId) {
        ZonedDateTime zonedDateTime = fromDateTime.atZone(fromZoneId);
        return LocalDateTime.ofInstant(Instant.from(zonedDateTime), toZoneId);
    }

    /**
     * @param dateKeyString
     *            dateKeyString
     * @param pattern
     *            pattern
     * @return str
     */
    public static String formatDateKeyFromUTC2System(String dateKeyString, String pattern) {
        LocalDateTime utcDateTime = parseDateTime(dateKeyString, pattern);
        LocalDateTime localDateTime =
                convertDateFromOneZone2Another(utcDateTime, DateTimeConstants.UTC_ZONE, getSystemDefaultZoneId());
        return formatDateTime(localDateTime, pattern);
    }

    /**
     * @param dateKeyString
     *            dateKeyString
     * @return str
     */
    public static String formatDateKeyFromUTC2SystemByYYYYMMDDHH(String dateKeyString) {
        return formatDateKeyFromUTC2System(dateKeyString, DateTimeConstants.FORMAT_YYYY_MM_DD_HH);
    }

}
