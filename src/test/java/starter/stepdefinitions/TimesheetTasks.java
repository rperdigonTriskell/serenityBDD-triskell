package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import starter.Constants;
import starter.tasks.FillTableWithValues;
import starter.tasks.WaitElement;
import starter.tasks.WaitForTask;

import java.util.*;

import static starter.Constants.*;
import static starter.pageselectors.factory.PageFactory.*;
import static starter.tasks.ElementInteraction.*;
import static starter.tasks.ElementInteraction.waitLoadingInteraction;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.IsLoad.isLoadPage;
import static starter.tasks.SendTextTo.*;
import static starter.tasks.WaitElement.*;

public class TimesheetTasks {

    /**
     * Handles the timesheet table based on the given table state and action.
     *
     * @param tableState the state of the table ("empty" or "not empty")
     * @param action     the action to perform ("add" or "delete")
     */
    public static void manageTimesheetTable(String tableState, String action) {
        Target targetTable = WaitElement.getVisibleTargetWithWait(TIMESHEET_BOARD + ACTIVITY + BOARD_SUFFIX + " empty");

        ifBlueColorThenEmptyTimesheetTimeTable();

        performAttemptsTo(
                "{0} waits for table to be visible",
                WaitForTask.waitUntil(targetTable, Constants.STATES.VISIBLE.getState())
        );

        List<WebElementFacade> rows = getTableRows(targetTable);

        if (tableState.equals(EMPTY)) {
            if (rows.isEmpty() && ADD.equals(action)) {
                addTimesheetActivity();
            } else {
                deleteAllTimesheetActivities();
                addTimesheetActivity();
            }
        } else if (tableState.equals(NOT_EMPTY)) {
            if (!rows.isEmpty() && DELETE.equals(action)) {
                deleteAllTimesheetActivities();
            }
        }
    }


    /**
     * Deletes all activities by clicking on the "all activities checkbox", "Delete" button, and "Yes" button.
     */
    public static void deleteAllTimesheetActivities() {
        clickOnTarget(TIMESHEET_CONTEXT + "all activities checkbox");
        clickOnTarget(TIMESHEET_BOARD + "Delete");
        clickOnTarget(TIMESHEET_CONTEXT + "Yes");

        ifCanNotDeleteTimesheet();
        
        performAttemptsTo("{0} waits for Yes button to disappear", WaitForTask.waitUntil(TIMESHEET_CONTEXT + "Yes", STATES.INVISIBLE.getState()));
    }

    /**
     * Adds all activities by clicking on the "Add Activities" button, entering a search term,
     * selecting a checkbox, clicking "Add & Close", and waiting for the "Add Object To Timesheet"
     * element to disappear.
     */
    public static void addTimesheetActivity() {
        performAttemptsTo("{0}", WaitForTask.waitUntil(TIMESHEET_BOARD + "Add Activities", STATES.VISIBLE.getState()));
        clickOnTarget(TIMESHEET_BOARD + "Add Activities");
        performAttemptsTo("{0}", WaitForTask.waitUntil("Search", STATES.VISIBLE.getState()));
        input("Automation Test Task", "Search");
        performAttemptsTo("{0}", WaitForTask.waitUntil("Search icon", STATES.VISIBLE.getState()));
        clickOnTarget("Search icon");
        performAttemptsTo("{0}", WaitForTask.waitUntil("first search result", STATES.VISIBLE.getState()));
        clickOnTarget("first search result");
        performAttemptsTo("{0}", WaitForTask.waitUntil("Add & Close", STATES.VISIBLE.getState()));
        clickOnTarget("Add & Close");
        performAttemptsTo("{0}", WaitForTask.waitUntil("Add & Close", STATES.INVISIBLE.getState()));
    }


    /**
     * Fills the web table with values provided in the DataTable.
     *
     * @param tableName the context used to find the web table
     * @param dataTable the DataTable containing the values to fill the table
     */
    public static void fillTimesheetTableWithValues(String tableName, DataTable dataTable) {
        // Convert the DataTable from Gherkin to a list of maps
        List<Map<String, String>> rowsData = getListFromDatatable(dataTable);

        // Get the table's selector
        By tableSelector = getVisibleSelectorWithWait(tableName);

        // Fill the table with the provided values
        performAttemptsTo("{0} fills the table", FillTableWithValues.inTable(tableSelector, rowsData));
    }

    /**
     * Fills the web table identified by the given By selector with values provided in the DataTable.
     *
     * @param tableSelector the By selector used to find the web table
     * @param rowsData      the DataTable containing the values to fill the table
     */
    public static void fillTableWithValues(By tableSelector, List<Map<String, String>> rowsData) {
        // wait for the table to be visible
        performAttemptsTo("{0} waits for table to be visible", WaitForTask.waitUntil(tableSelector, Constants.STATES.VISIBLE.getState()));
        for (int rowIndex = 0; rowIndex < rowsData.size(); rowIndex++) {
            Map<String, String> rowData = rowsData.get(rowIndex);

            // iterate through each column in the row
            int colIndex = 0;
            for (String columnName : rowData.keySet()) {

                String cellValue = rowData.get(columnName);

                //  wait and re ubicate the table before interacting with the cell
                Target updatedTable = getTarget(tableSelector);
                performAttemptsTo("{0} waits for table to be visible", WaitForTask.waitUntil(updatedTable, Constants.STATES.VISIBLE.getState()));

                // locate the cell
                WebElementFacade cell = getTimesheetTableCell(updatedTable, rowIndex, colIndex);

                // enter text into the cell
                enterTimesheetTextInCell(cell, cellValue);

                colIndex++;
            }
        }
    }


    /**
     * Retrieves the WebElementFacade for a cell in a table based on the given target,
     * row index, and column index.
     *
     * @param target   the target used to find the table
     * @param rowIndex the row index of the cell
     * @param colIndex the column index of the cell
     * @return the WebElementFacade for the cell
     */
    public static WebElementFacade getTimesheetTableCell(Target target, int rowIndex, int colIndex) {
        List<WebElementFacade> rows = getTableRows(target);
        WebElementFacade row = rows.get(rowIndex);
        List<WebElementFacade> columns = getTableColumns(row);
        return columns.get(colIndex);
    }

    /**
     * Enters text into the input field within a table cell.
     *
     * @param cell the WebElementFacade representing the table cell
     * @param text the text to enter in the table cell
     */
    public static void enterTimesheetTextInCell(WebElementFacade cell, String text) {
        // click on the cell to refresh the DOM
        clickOnTarget(cell);

        performAttemptsTo("{0} waits for loading", WaitForTask.waitUntil("loading", STATES.INVISIBLE.getState()));

        // wait for the input to be visible
        List<WebElementFacade> inputField = getWebElementsFacadeBySelector(By.cssSelector("input[name*='PERIODID_']"));

        // input the text into the input field
        input(text, inputField.get(inputField.size() - 1));

    }

    /**
     * Checks if the color of the first cell in the first row of the timesheet table is blue. If it is,
     * fills the table with default values.
     */
    public static void ifBlueColorThenEmptyTimesheetTimeTable() {
        Target timetable = getPresentTargetWithWait(TIMESHEET_BOARD + TIME + BOARD_SUFFIX + " empty");

        List<Map<String, String>> rowsData = new ArrayList<>();
        Map<String, String> rowData = new HashMap<>();
        rowData.put("MON", "");
        rowData.put("TUE", "");
        rowData.put("WED", "");
        rowData.put("THU", "");
        rowData.put("FRI", "");
        rowsData.add(rowData);

        if (!getTableRows(timetable).isEmpty()) {
            String timerows = getTableRows(timetable).get(0).thenFindAll(By.cssSelector("td > div")).get(0).getCssValue("color");
            if (timerows.equals("rgba(33, 150, 243, 1)")) {
                performAttemptsTo(
                        "{0} fills the table with default values",
                        FillTableWithValues.inTable(getCurrentPage().getSelector(TIMESHEET_BOARD + TIME + BOARD_SUFFIX),
                                rowsData)
                );
                Target submit = WaitElement.getVisibleTargetWithWait(TIMESHEET_BOARD + "Submit Timesheet");
                clickOnTarget(submit);
                submit = WaitElement.getVisibleTargetWithWait(TIMESHEET_CONTEXT + "Submit");
                clickOnTarget(submit);
                clickOnTarget(TIMESHEET_BOARD + "Delete");
                clickOnTarget(TIMESHEET_CONTEXT + "Yes");
                performAttemptsTo("{0} waits for Yes button to disappear", WaitForTask.waitUntil(TIMESHEET_CONTEXT + "Yes", STATES.INVISIBLE.getState()));
            }
        }
    }

    /**
     * Checks if the user can not delete the timesheet.
     */
    public static void ifCanNotDeleteTimesheet() {
        Target message = WaitElement.getVisibleTargetWithWait(TIMESHEET_CONTEXT + "message can not delete");
        if (message.isVisibleFor(getCurrentActor())) {
            clickOnTarget(WaitElement.getVisibleTargetWithWait(TIMESHEET_CONTEXT + "ok"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait(SIDEBAR_CONTEXT + "Project"));
            isLoadPage(PROJECT);
            inputAndEnter(PROJECT_CONTEXT + "search parent", MANUAL_TESTING_PROJECT);
            waitLoadingInteraction();
            clickOnTarget(WaitElement.getVisibleTargetWithWait(PROJECT_CONTEXT + "arrow"));
            ///
            isLoadPage(MANUAL_TESTING_PROJECT);
            clickOnTarget(WaitElement.getVisibleTargetWithWait("Gantt Chart"));
            isLoadPage(MANUAL_TESTING_PROJECT_GANTT_CHART);
            clickOnTarget(WaitElement.getVisibleTargetWithWait("OK"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait("Automation Test Task"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait("Remove"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait("OK"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait("Create"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait("Task"));
            input("Automation Test Task", WaitElement.getVisibleWebelementFacadeWithWait("Name"));
            clickOnTarget(WaitElement.getVisibleTargetWithWait("Save"));
        }
    }
}
