/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.constant;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The constants for date time.
 *
 * @author pdai
 */
public final class DateTimeConstants {

    /**
     * The date time format of "yyyy-MM-dd HH:mm:ss".
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * The date time format of "HH:mm MMM d yyyy".
     */
    public static final String FORMAT_HH_MM_MMM_D_YYYY = "HH:mm MMM d yyyy";

    /**
     * The date time format of "yyyy-MM-dd".
     */
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * The date time format of "yyyy-M-d".
     */
    public static final String FORMAT_YYYY_M_D = "yyyy-M-d";

    /**
     * The date time format of "M/d/yyyy".
     */
    public static final String FORMAT_M_D_YYYY = "M/d/yyyy";

    /**
     * The date time format of "HH:mm:ss".
     */
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";

    /**
     * The date time format of "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     */
    public static final String FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * The date time format of "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * The date time format of "yyyy".
     */
    public static final String FORMAT_YYYY = "yyyy";

    /**
     * The date time format of "yyyy-MM".
     */
    public static final String FORMAT_YYYY_MM = "yyyy-MM";

    /**
     * The date time format of "yyyy-MM-dd HH".
     */
    public static final String FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    /**
     * The date time format of "yyyy-MM-dd HH:mm".
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * The date time format of "yyyyMMdd".
     */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * The date time format of "yyyy-M-d".
     */
    public static final String FORMAT_MMM_DD_COMMA_YYYY = "MMM dd, yyyy";

    /**
     * The time zone ID of "Asia/Shanghai".
     */
    public static final ZoneId SHANG_HAI = ZoneId.of("Asia/Shanghai");

    /**
     * The time zone ID of "UTC".
     */
    public static final ZoneId UTC_ZONE = ZoneId.of("UTC");

    /**
     * The period keyword of a year.
     */
    public static final String PERIOD_YEAR = "year";

    /**
     * The period keyword of a month.
     */
    public static final String PERIOD_QUARTER = "quarter";

    /**
     * The period keyword of a month.
     */
    public static final String PERIOD_MONTH = "month";

    /**
     * The period keyword of a week.
     */
    public static final String PERIOD_WEEK = "week";

    /**
     * The period keyword of a day.
     */
    public static final String PERIOD_DAY = "day";

    /**
     * The period keyword of an hour.
     */
    public static final String PERIOD_HOUR = "hour";

    /**
     * The period keyword of a minute.
     */
    public static final String PERIOD_MINUTE = "minute";

    /**
     * The period keyword of a second.
     */
    public static final String PERIOD_SECOND = "second";

    /**
     * Available period keywords.
     */
    public static final List<String> PERIOD_KEYWORDS = Collections.unmodifiableList(Arrays.asList(PERIOD_YEAR,
            PERIOD_MONTH, PERIOD_WEEK, PERIOD_DAY, PERIOD_HOUR, PERIOD_MINUTE, PERIOD_SECOND));

    /**
     * Date separator.
     */
    public static final String DATE_SEPARATOR = StringConstants.DASH;

    /**
     * TIME separator.
     */
    public static final String TIME_SEPARATOR = ":";

    /**
     * months of quarter.
     */
    public static final int MONTH_OF_QUARTER = 3;

    /**
     * Date span for 60 minute/seconds.
     */
    public static final int DATE_SPAN_60 = 60;

    /**
     * Date span for 24 hours.
     */
    public static final int DATE_SPAN_24 = 24;

    /**
     * Date span for previous 30 days.
     */
    public static final int DATE_SPAN_PREVIOUS_30_DAYS = -30;

    /**
     * Date span for previous 7 days.
     */
    public static final int DATE_SPAN_PREVIOUS_7_DAYS = -7;

    /**
     * 1000 mills.
     */
    public static final long MILLS_1000 = 1000;

    /**
     * 60 seconds.
     */
    public static final long SECONDS_60 = 60;

    /**
     * 3600 seconds.
     */
    public static final long SECONDS_3600 = 3600;

    /**
     * 86400 seconds.
     */
    public static final long SECONDS_86400 = 86400;

    /**
     * The private constructor to avoid instantiation of an constant class.
     */
    private DateTimeConstants() {
    }

}
