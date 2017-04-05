//@@author A0163744B
package seedu.task.model.util;

import java.util.Calendar;

import seedu.task.commons.exceptions.IllegalValueException;

public class DateParser {
    public static final String DATE_STRING_ILLEGAL_FORMAT =
            "String must be of the form DD/MM/YYYY HHMM, or day_of_week|today|tomorrow HHMM";
    public static final String DATE_ILLEGAL_DATE = "The given date is not valid";

    private static final int DEFAULT_SECONDS = 0;
    private static final int DEFAULT_MILLISECONDS = 0;
    private static final String DATE_STRING_VALIDATION_REGEX =
            "^([A-Za-z]{3,9}|[0-3][0-9]/[0-1][0-9]/[0-9]{4}) [0-2][0-9][0-5][0-9]$";
    private static final String EMPTY_DATE_STRING = "";
    private static final int MONTH_OFFSET = 1;

    //@@author A0164103W
    private static int parsedDate[] = new int[3];
    private static final int INDEX_DAY = 0;
    private static final int INDEX_MONTH = 1;
    private static final int INDEX_YEAR = 2;
    private static final int INDEX_SUN = 1;
    private static final int INDEX_MON = 2;
    private static final int INDEX_TUE = 3;
    private static final int INDEX_WED = 4;
    private static final int INDEX_THU = 5;
    private static final int INDEX_FRI = 6;
    private static final int INDEX_SAT = 7;

    //@@author A0163744B
    public static Calendar parse(String date) throws IllegalValueException {
        if (!isValidDateString(date)) {
            throw new IllegalValueException(DATE_STRING_ILLEGAL_FORMAT);
        }
        Calendar cal = Calendar.getInstance();
        int year = getDate(date)[INDEX_YEAR];
        int month = getDate(date)[INDEX_MONTH];
        int day = getDate(date)[INDEX_DAY];
        int hour = getHour(date);
        int minute = getMinute(date);
        if (!isValidDate(year, month, day, hour, minute)) {
            throw new IllegalValueException(DATE_ILLEGAL_DATE);
        }
        cal.set(year, month, day, hour, minute, DEFAULT_SECONDS);
        cal.set(Calendar.MILLISECOND, DEFAULT_MILLISECONDS);
        return cal;
    }

    public static String toString(Calendar date) {
        String dateString;

        if (date == null) {
            return EMPTY_DATE_STRING;
        }

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);

        dateString = String.format("%02d/%02d/%4d %02d%02d", day, month + MONTH_OFFSET, year, hour, minute);

        return dateString;
    }

    public static boolean isValidDateString(String test) {
        return test.matches(DATE_STRING_VALIDATION_REGEX);
    }

    public static boolean isValidDate(int year, int month, int day, int hour, int minute) {
        if (year < 0 || month < 0 || month > 11) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        if (day < 1 || day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            return false;
        }
        return true;
    }

    //@@author A0164103W
    /**
     * Returns date in integers given string format of date
     *
     * @param date in string format
     * @return array containing day, month, and year at INDEX_DAY, INDEX_MONTH, INDEX_YEAR respectively
     * @throws IllegalValueException if illegal values are entered
     */
    private static int[] getDate(String date) throws IllegalValueException {
        Calendar calDate = Calendar.getInstance();
        int dayOfWeek = calDate.get(Calendar.DAY_OF_WEEK);

        if (date.substring(0, 1).matches("[0-9]")) {
            parsedDate[INDEX_DAY] = Integer.parseInt(date.substring(0, 2));
            parsedDate[INDEX_MONTH] = Integer.parseInt(date.substring(3, 5)) - MONTH_OFFSET;
            parsedDate[INDEX_YEAR] = Integer.parseInt(date.substring(6, 10));
        } else {
            String firstWord = date.substring(0, date.indexOf(" "));
            switch (firstWord.toLowerCase()) {
            case "today" : {
                break;
            }
            case "tomorrow" : {
                calDate.add(Calendar.DATE, 1);
                break;
            }
            case "monday" :
            case "mon": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, MON_INDEX));
                break;
            }
            case "tuesday" :
            case "tues" :
            case "tue": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, TUE_INDEX));
                break;
            }
            case "wednesday" :
            case "wed": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, WED_INDEX));
                break;
            }
            case "thursday" :
            case "thurs" :
            case "thu": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, THU_INDEX));
                break;
            }
            case "friday" :
            case "fri": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, FRI_INDEX));
                break;
            }
            case "saturday" :
            case "sat": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, SAT_INDEX));
                break;
            }
            case "sunday" :
            case "sun": {
                calDate.add(Calendar.DATE, compareDayOfWeek(dayOfWeek, SUN_INDEX));
                break;
            }
            default:
                throw new IllegalValueException(DATE_STRING_ILLEGAL_FORMAT);
            }
            parsedDate[INDEX_DAY] = calDate.get(Calendar.DAY_OF_MONTH);
            parsedDate[INDEX_MONTH] = calDate.get(Calendar.MONTH);
            parsedDate[INDEX_YEAR] = calDate.get(Calendar.YEAR);
        }

        return parsedDate;
    }

    private static int compareDayOfWeek(int day1, int day2) {
        return day2 - day1 < 0 ?
                day2 - day1 + 7 :
                day2 - day1;
    }

    //@@author A0163744B
    private static int getHour(String date) {
        String time = date.substring(date.indexOf(" ") + 1);
        return Integer.parseInt(time.substring(0, 2));
    }

    private static int getMinute(String date) {
        String time = date.substring(date.indexOf(" ") + 1);
        return Integer.parseInt(time.substring(3));
    }
}
