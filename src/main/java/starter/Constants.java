package starter;


import java.time.Duration;

public class Constants {
    public final static String LOGIN = "Login";
    public final static String BAD_URL = "bad url";
    public final static String BAD_URL_FOLDER = "bad url folder";
    public final static String DASHBOARD = "Dashboard";
    public final static String TIMESHEET = "Timesheet";
    public final static String PROJECT = "Project";
    public final static String TIMESHEET_SUMMARY = "Timesheet Summary";
    public final static String HOME_PAGE = "Home Page";
    public final static String AUTOMATION_TESTING_PROJECT = "Automation Testing Project";
    public final static String SIDEBAR_CONTEXT = "Sidebar ";
    public final static String HEADING_CONTEXT = "Heading ";
    public final static String DASHBOARD_CONTEXT = DASHBOARD + " ";
    public final static String TIMESHEET_CONTEXT = TIMESHEET + " ";
    public final static String PROJECT_CONTEXT = PROJECT + " ";
    public final static String AUTOMATION_TESTING_PROJECT_CONTEXT = AUTOMATION_TESTING_PROJECT + " ";
    public final static String ACTIVITY = "activity";
    public final static String TIME = "time";
    public final static String BOARD_SUFFIX = " board";
    public final static String ACTIVITY_BOARD = ACTIVITY + BOARD_SUFFIX;
    public final static String TIME_BOARD = TIME + BOARD_SUFFIX;
    public final static String TIMESHEET_BOARD = TIMESHEET + BOARD_SUFFIX + " ";
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
    public static final String EMPTY_DATA = "[empty]";
    public static final String  ACTUAL_DATE = "[actual date]";
    public static final String  EMPTY = "empty";
    public static final String  ADD = "add";
    public static final String  NOT_EMPTY = "not empty";
    public static final String  DELETE = "delete";
    //wait time
    public static final Duration WAIT_DURATION = Duration.ofSeconds(10);
}
