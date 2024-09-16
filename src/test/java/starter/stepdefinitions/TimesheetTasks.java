package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.*;

import static starter.Constants.*;
import static starter.selectors.factory.PageFactory.getWebElementSelector;
import static starter.tasks.ElementInteraction.*;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.SendTextTo.*;
import static starter.tasks.WaitInteractions.*;

public class TimesheetTasks {

    /**
     * Handles the timesheet table based on the given table state and action.
     *
     * @param tableState the state of the table ("empty" or "not empty")
     * @param action     the action to perform ("add" or "delete")
     */
    public static void manageTimesheetTable(String tableState, String action) {
        WebElementFacade table = waitElementPresent(getWebelementFacade(TIMESHEET_BOARD + ACTIVITY + BOARD_SUFFIX), true);
        List<WebElementFacade> rows = getTableRows(table);

        ifBlueColorThenEmptyTimesheetTimeTable();

        if ("empty".equals(tableState)) {
            if (rows.isEmpty() && "add".equals(action)) {
                addTimesheetActivity();
            } else {
                deleteAllTimesheetActivities();
                addTimesheetActivity();
            }
        } else if ("not empty".equals(tableState)) {
            if (!rows.isEmpty() && "delete".equals(action)) {
                deleteAllTimesheetActivities();
            }
        }
    }


    /**
     * Deletes all activities by clicking on the "all activities checkbox", "Delete" button, and "Yes" button.
     *
     */
    public static void deleteAllTimesheetActivities() {
        clickOnTarget(TIMESHEET_CONTEXT + "all activities checkbox");
        clickOnTarget(TIMESHEET_BOARD + "Delete");
        clickOnTarget(TIMESHEET_CONTEXT + "Yes");
        waitElementVisible(getWebelementFacade(TIMESHEET_CONTEXT + "Yes"), false);
    }

    /**
     * Adds all activities by clicking on the "Add Activities" button, entering a search term,
     * selecting a checkbox, clicking "Add & Close", and waiting for the "Add Object To Timesheet"
     * element to disappear.
     */
    public static void addTimesheetActivity() {
        clickOnTarget(TIMESHEET_BOARD + "Add Activities");
        waitElementVisible(getWebelementFacade("Add Object To Timesheet"), true);
        input("Task 1", "Search");
        clickOnTarget("Search icon");
        waitElementVisible(getWebelementFacade("MAPRE Portfolio Task 1 Checkbox"), true);
        clickOnTarget("MAPRE Portfolio Task 1 Checkbox");
        clickOnTarget("Add & Close");
        waitElementPresent(getWebelementFacade("Add Object To Timesheet"), false);
    }


    /**
     * Fills the web table with values provided in the DataTable.
     *
     * @param tableName the context used to find the web table
     * @param dataTable the DataTable containing the values to fill the table
     */
    public static void fillTimesheetTableWithValues(String tableName, DataTable dataTable) {
        // Convertir el DataTable de Gherkin a una lista de mapas
        List<Map<String, String>> rowsData = getExpectedRows(dataTable);

        // Obtener la tabla usando getWebelementFacade y esperar a que sea visible
        WebElementFacade table = waitElementVisible(getWebelementFacade(tableName), true);

        // Iterar sobre las filas del DataTable y sus datos correspondientes
        fillTableWithValues(table, rowsData);
    }

    public static void fillTableWithValues(WebElementFacade table, List<Map<String, String>> rowsData) {
        By tableSelector = getWebElementSelector(table);
        for (int rowIndex = 0; rowIndex < rowsData.size(); rowIndex++) {
            Map<String, String> rowData = rowsData.get(rowIndex);

            // Iterar sobre las columnas del DataTable
            int colIndex = 0;
            for (String columnName : rowData.keySet()) {
                String cellValue = rowData.get(columnName);

                // Reubicar la tabla antes de interactuar con la celda
                WebElementFacade updatedTable = waitElementVisible(getWebElementFacadeBySelector(tableSelector), true);
//                WebElementFacade updatedTable = waitElementVisible(table, true);

                // Localizar la celda correspondiente
                WebElementFacade cell = waitElementVisible(getTimesheetTableCell(updatedTable, rowIndex, colIndex),true);

                // Ingresar el texto en la celda correspondiente
                enterTimesheetTextInCell(cell, cellValue);

                colIndex++;
            }
        }
    }


    /**
     * Gets the table cell at the specified row and column index.
     *
     * @param table    the WebElementFacade representing the web table
     * @param rowIndex the index of the row where the cell is located
     * @param colIndex the index of the column where the cell is located
     * @return the WebElementFacade representing the table cell
     */
    public static WebElementFacade getTimesheetTableCell(WebElementFacade table, int rowIndex, int colIndex) {
        // Localiza la fila y columna basadas en los índices
        List<WebElementFacade> rows = getTableRows(table);
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
        // Hacer clic en la celda para disparar el evento y refrescar el DOM
        clickOnTarget(cell);

        // Esperar a que el input dentro del div esté disponible antes de interactuar
        List<WebElementFacade> inputField = waitElementsPresent(getWebElementsFacadeBySelector(By.cssSelector("input[name*='PERIODID_']")));

        // Ingresar el texto en el input correspondiente
        input(text, inputField.get(inputField.size() -1));
    }

    /**
     * Checks if the color of the first cell in the first row of the timesheet table is blue. If it is,
     * fills the table with default values.
     */
    public static void ifBlueColorThenEmptyTimesheetTimeTable() {
        WebElementFacade timetable = waitElementPresent(getWebelementFacade(TIMESHEET_BOARD + TIME + BOARD_SUFFIX), true);
        List<Map<String, String>> rowsData = new ArrayList<>();
        Map<String, String> rowData = new HashMap<>();
        rowData.put("MON", "0");
        rowData.put("TUE", "0");
        rowData.put("WED", "0");
        rowData.put("THU", "0");
        rowData.put("FRI", "0");
        rowsData.add(rowData);
        if (!getTableRows(timetable).isEmpty()) {
            waitElementVisible(getTableRows(timetable).get(0), true);
            String timerows = getTableRows(timetable).get(0).thenFindAll(By.cssSelector("td > div")).get(0).getCssValue("color");
            if (timerows.equals("rgba(33, 150, 243, 1)")) {
                fillTableWithValues(timetable, rowsData);
                clickOnTarget(TIMESHEET_BOARD + "Submit Timesheet");
                waitElementVisible(getWebelementFacade(TIMESHEET_CONTEXT + "Timesheet Submit"), true);
                clickOnTarget(TIMESHEET_CONTEXT + "Submit");
            }
        }
    }

}
