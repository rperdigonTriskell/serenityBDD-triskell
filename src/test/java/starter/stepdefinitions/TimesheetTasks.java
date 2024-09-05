package starter.stepdefinitions;

import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

import static starter.Constants.*;
import static starter.selectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.GenericTasks.getTableRows;
import static starter.tasks.GenericTasks.getWebelementFacade;
import static starter.tasks.SendTextTo.input;
import static starter.tasks.WaitInteractions.waitElementPresent;
import static starter.tasks.WaitInteractions.waitElementVisible;

public class TimesheetTasks {
    /**
     * Handles the timesheet table based on the given table state and action.
     *
     * @param tableState the state of the table ("empty" or "not empty")
     * @param action     the action to perform ("add" or "delete")
     */
    public static void handleTimesheetTable(String tableState, String action) {
        WebElementFacade table = waitElementPresent(getWebelementFacade(TIMESHEET + BOARD_SUFFIX + " " + ACTIVITY + BOARD_SUFFIX), true);
        List<WebElementFacade> rows = getTableRows(table);
        if ("empty".equals(tableState)) {
            if (rows.isEmpty() && "add".equals(action)) {
                addActivity();
            } else {
                deleteAllActivities(table);
                addActivity();
            }
        } else if ("not empty".equals(tableState)) {
            if (!rows.isEmpty() && "delete".equals(action)) {
                deleteAllActivities(table);
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes all activities by clicking on the "all activities checkbox", "Delete" button, and "Yes" button.
     *
     * @param table the WebElementFacade representing the table to wait for invisibility
     */
    public static void deleteAllActivities(WebElementFacade table) {
        WebElementFacade addActivities = waitElementPresent(getCurrentPage().$(getCurrentPage().getSelector(TIMESHEET + BOARD_SUFFIX + " Add Activities")), true);
        clickOnTarget(TIMESHEET_CONTEXT + "all activities checkbox");
        clickOnTarget(TIMESHEET_CONTEXT + "Delete");
        clickOnTarget(TIMESHEET_CONTEXT + "Yes");
        waitElementVisible(addActivities, true);
        waitElementVisible(table, false);
    }

    /**
     * Adds all activities by clicking on the "Add Activities" button, entering a search term,
     * selecting a checkbox, clicking "Add & Close", and waiting for the "Add Object To Timesheet"
     * element to disappear.
     */
    public static void addActivity() {
        clickOnTarget(TIMESHEET_BOARD + " Add Activities");
        waitElementVisible(getWebelementFacade("Add Object To Timesheet"), true);
        input("Task 1", "Search");
        clickOnTarget("Search icon");
        waitElementVisible(getWebelementFacade("MAPRE Portfolio Task 1 Checkbox"), true);
        clickOnTarget("MAPRE Portfolio Task 1 Checkbox");
        clickOnTarget("Add & Close");
        waitElementVisible(getWebelementFacade("Add Object To Timesheet"), false);
        waitElementVisible(getWebelementFacade(TIMESHEET_BOARD + " Add Activities"), true);
        waitElementVisible(getWebelementFacade(TIMESHEET + BOARD_SUFFIX + " " + ACTIVITY_BOARD), true);
    }
}
