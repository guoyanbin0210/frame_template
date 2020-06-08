package com.lt.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * DateUtils.getDateAfter(Calendar.MINUTE,5); //表示5分钟之后
     *
     * @param field  Calendar.MINUTE, Calendar.SECOND ...
     * @param amount
     * @return
     */

    public static Date getDateAfter(int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 日期转化为String
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return date string
     */
    public static String fmtDateMin_ss(Date date) {
        if (null == date) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期转化为String
     * yyyy-MM-dd HH:mm
     *
     * @param date
     * @return date string
     */
    public static String fmtDateMin_mm(Date date) {
        if (null == date) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期转化为String
     * yyyy-MM-dd
     *
     * @param date
     * @return date string
     */
    public static String fmtDateMin_dd(Date date) {
        if (null == date) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化string为Date
     *
     * @param dateStr
     * @return date
     */
    public static Date parseDate(String dateStr) {
        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }
        try {
            String fmtstr;
            if (dateStr.indexOf(':') > 0) {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 距今
     * 2019-05-09
     * 5小时前
     * 5分钟前
     * 刚刚
     *
     * @param date
     * @return
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;//单位秒
        long d_MINUTE = 60;//分钟- 秒
        long d_HOUR = 60 * d_MINUTE;//小时- 秒
        long d_DAY = 24 * d_HOUR;//天 - 秒
        if (ago > d_DAY) {
            return fmtDateMin_dd(date);
        } else if (ago > d_HOUR) {
            return ago / d_HOUR + "小时前";
        } else if (ago > d_MINUTE) {
            return ago / d_MINUTE + "分钟前";
        }else if(ago<d_MINUTE){
            return "刚刚";
        }
        return String.valueOf(ago);
    }

    /**
     * 距离截止日期还有多长时间
     *
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long remain = deadline - now;

        return null;
    }


}
