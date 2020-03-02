/**
 * Created by SAM on 12/10/2019.
 */
public class CalendarUtils {

    private CalendarUtils() {
    }

    public static int getFirstWeekDayOfMonth(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = utilCalendar.get(Calendar.DAY_OF_WEEK) - utilCalendar.getFirstDayOfWeek();
        return dayOfWeek < 0 ? 7 + dayOfWeek : dayOfWeek;
    }

    public static int getFirstWeekOfMonth(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return utilCalendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date getYesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    public static int getQuarter(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) / 3) + 1;
    }

    public static int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getNumberOfDaysInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getNumberOfWeekInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public static Calendar getTodayCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar;
    }

    public static boolean isSameMonth(Calendar calendar, Calendar calendar2) {
        return getYear(calendar) == getYear(calendar2) && getMonth(calendar) == getMonth(calendar2);
    }

    public static long getNextSixMonthData() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 6);
        return calendar.getTimeInMillis();
    }


    public static void setNextDay(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }

    public static void setPreviousDay(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
    }

    public static void setNextWeek(Calendar calendar) {
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
    }

    public static void setPreviousWeek(Calendar calendar) {
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
    }

    public static void setNextMonth(Calendar calendar) {
        calendar.add(Calendar.MONTH, 1);
    }

    public static void setPreviousMonth(Calendar calendar) {
        calendar.add(Calendar.MONTH, -1);
    }

    public static void setNextYear(Calendar calendar) {
        calendar.add(Calendar.YEAR, 1);
    }

    public static void setPreviousYear(Calendar calendar) {
        calendar.add(Calendar.YEAR, -1);
    }

    public static void setNextQuarter(Calendar calendar) {
        calendar.add(Calendar.MONTH, +3);
    }

    public static void setPreviousQuarter(Calendar calendar) {
        calendar.add(Calendar.MONTH, -3);
    }


    public static long getMillis_startOfDay(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.HOUR_OF_DAY, 0);
        utilCalendar.set(Calendar.MINUTE, 0);
        utilCalendar.set(Calendar.SECOND, 0);
        utilCalendar.set(Calendar.MILLISECOND, 0);

        return utilCalendar.getTimeInMillis();
    }

    public static long getMillis_endOfDay(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        utilCalendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        utilCalendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        utilCalendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return utilCalendar.getTimeInMillis();
    }


    public static long getMillis_startOfWeek(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_WEEK, 1);
        utilCalendar.set(Calendar.HOUR_OF_DAY, 0);
        utilCalendar.set(Calendar.MINUTE, 0);
        utilCalendar.set(Calendar.SECOND, 0);
        utilCalendar.set(Calendar.MILLISECOND, 0);

        return utilCalendar.getTimeInMillis();
    }

    public static long getMillis_endOfWeek(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_WEEK, 7);
        utilCalendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        utilCalendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        utilCalendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        utilCalendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return utilCalendar.getTimeInMillis();
    }


    public static long getMillis_startOfQuarter(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();

        utilCalendar.set(Calendar.MONTH, utilCalendar.get(Calendar.MONTH) / 3 * 3);

        utilCalendar.set(Calendar.DAY_OF_MONTH, 1);
        utilCalendar.set(Calendar.HOUR_OF_DAY, 0);
        utilCalendar.set(Calendar.MINUTE, 0);
        utilCalendar.set(Calendar.SECOND, 0);
        utilCalendar.set(Calendar.MILLISECOND, 0);

        return utilCalendar.getTimeInMillis();
    }

    public static long getMillis_endOfQuarter(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();

        utilCalendar.set(Calendar.MONTH, utilCalendar.get(Calendar.MONTH) / 3 * 3 + 2);

        utilCalendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        utilCalendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        utilCalendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        utilCalendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        utilCalendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return utilCalendar.getTimeInMillis();
    }


    public static long getMillis_firstDayOfMonth(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_MONTH, 1);
        utilCalendar.set(Calendar.HOUR_OF_DAY, 0);
        utilCalendar.set(Calendar.MINUTE, 0);
        utilCalendar.set(Calendar.SECOND, 0);
        utilCalendar.set(Calendar.MILLISECOND, 0);

        return utilCalendar.getTimeInMillis();
    }

    public static long getMillis_lastDayOfMonth(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        utilCalendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        utilCalendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        utilCalendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        utilCalendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return utilCalendar.getTimeInMillis();
    }


    public static long getMillis_firstDayOfYear(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_YEAR, 1);
        utilCalendar.set(Calendar.HOUR_OF_DAY, 0);
        utilCalendar.set(Calendar.MINUTE, 0);
        utilCalendar.set(Calendar.SECOND, 0);
        utilCalendar.set(Calendar.MILLISECOND, 0);

        return utilCalendar.getTimeInMillis();
    }

    public static long getMillis_lastDayOfYear(Calendar calendar) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        utilCalendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        utilCalendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        utilCalendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        utilCalendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return utilCalendar.getTimeInMillis();
    }

    @SuppressLint("SimpleDateFormat")
    public static String getFirstDayOfMonth(Calendar calendar, String format) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.setTimeInMillis(getMillis_firstDayOfMonth(utilCalendar));
        return new SimpleDateFormat(format).format(utilCalendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getLastDayOfMonth(Calendar calendar, String format) {
        Calendar utilCalendar = (Calendar) calendar.clone();
        utilCalendar.setTimeInMillis(getMillis_lastDayOfMonth(utilCalendar));
        return new SimpleDateFormat(format).format(utilCalendar.getTime());
    }
}
