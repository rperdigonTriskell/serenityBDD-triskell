package starter;


import java.time.Duration;

public class Constants {
    public final static String LOGIN = "Login";
    public final static String BAD_URL = "bad url";
    public final static String BAD_URL_FOLDER = "bad url folder";
    public final static String DASHBOARD = "Dashboard";
    public final static String TIMESHEET = "Timesheet";
    public final static String TIMESHEET_SUMMARY = "Timesheet Summary";
    public final static String HOME_PAGE = "Home Page";
    public final static String SIDEBAR_CONTEXT = "Sidebar ";
    public final static String HEADING_CONTEXT = "Heading ";
    public final static String DASHBOARD_CONTEXT = DASHBOARD + " ";
    public final static String TIMESHEET_CONTEXT = TIMESHEET + " ";
    public final static String ACTIVITY = "activity";
    public final static String BOARD_SUFFIX = " board";
    public final static String ACTIVITY_BOARD = ACTIVITY + BOARD_SUFFIX;
    public final static String TIMESHEET_BOARD = TIMESHEET + BOARD_SUFFIX;
    public final static String FAVORITES = "Favorites";
    public final static String LAST_MODIFIED = "Last Modified";
    public final static String SUBSCRIPTIONS = "Subscriptions";
    public final static String ELEMENT = "element";
    public final static String VISIBILITY = "visibility";
    public final static String CHECKBOX = "checkbox";
    public final static String[] STATES = {
            "visible",
            "invisible",
            "enabled",
            "disabled",
            "present",
            "not present",
            "selected",
            "deselected",
    };
    public static final Duration WAIT_DURATION = Duration.ofSeconds(2);
}
