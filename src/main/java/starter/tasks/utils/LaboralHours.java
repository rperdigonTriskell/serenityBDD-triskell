package starter.tasks.utils;

import java.time.*;

/**
 * Calculates working hours (8 hours/day, Mon-Fri) over a specified number of weeks.
 */
public class LaboralHours {

    /**
     * Returns total working hours from the start of the current month over a set number of weeks.
     *
     * @return Total working hours
     */
    public static String getLaboralHours() {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        int weeksToConsider = 4;
        int calculatedHours = LaboralHours.calculateLaboralHours(currentYear, currentMonth, weeksToConsider);

        return calculatedHours + ".00 h";
    }

    /**
     * Returns total working hours from the start of a given month/year over a set number of weeks.
     *
     * @param year  Year (e.g., 2024)
     * @param month Month (1 for Jan, 12 for Dec)
     * @param weeks Number of weeks to include
     * @return Total working hours
     */
    public static int calculateLaboralHours(int year, int month, int weeks) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusWeeks(weeks - 1);
        while (end.getDayOfWeek() != DayOfWeek.FRIDAY) end = end.minusDays(1);

        int hours = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (isLaboralDay(date)) hours += 8;
        }
        return hours;
    }

    /**
     * Checks if a date is a working day (Mon-Fri).
     *
     * @param date Date to check
     * @return True if working day, false otherwise
     */
    public static boolean isLaboralDay(LocalDate date) {
        return date.getDayOfWeek().getValue() <= 5;
    }
}