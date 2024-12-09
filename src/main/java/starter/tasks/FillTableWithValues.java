package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.ElementInteraction.waitLoadingInteraction;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.SendTextTo.input;
import static starter.tasks.WaitElement.*;
import static starter.tasks.WaitElement.getWaitWebelementFacadeVisible;

import java.util.List;
import java.util.Map;

public class FillTableWithValues implements Task {

    private final By tableSelector;
    private final List<Map<String, String>> rowsData;

    public FillTableWithValues(By tableSelector, List<Map<String, String>> rowsData) {
        this.tableSelector = tableSelector;
        this.rowsData = rowsData;
    }

    public static FillTableWithValues inTable(By tableSelector, List<Map<String, String>> rowsData) {
        return new FillTableWithValues(tableSelector, rowsData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int rowIndex = 0; rowIndex < rowsData.size(); rowIndex++) {
            Map<String, String> rowData = rowsData.get(rowIndex);
            int colIndex = 0;

            for (String columnName : rowData.keySet()) {
                String cellValue = rowData.get(columnName);

                // Re-locate the table and wait for it to be visible
                Target updatedTable = getWaitVisibleSelector(tableSelector);

                // Locate the cell
                WebElementFacade cell = getTimesheetTableCell(updatedTable, rowIndex, colIndex);

                // Enter text into the cell
                enterTimesheetTextInCell(cell, cellValue);

                colIndex++;
            }
        }
    }

    /**
     * Helper method to retrieve the WebElementFacade for a cell in the table.
     */
    private WebElementFacade getTimesheetTableCell(Target target, int rowIndex, int colIndex) {
        List<WebElementFacade> rows = getTableRows(target);
        WebElementFacade row = rows.get(rowIndex);
        List<WebElementFacade> columns = getTableColumns(row);
        return columns.get(colIndex);
    }

    /**
     * Enters text into the input field within a table cell.
     */
    private void enterTimesheetTextInCell(WebElementFacade cell, String text) {
        clickOnTarget(getWaitWebelementFacadeVisible(cell));
        waitLoadingInteraction();
        List<WebElementFacade> inputField = getWebElementsFacadeBySelector(By.cssSelector("input[name*='PERIODID_']"));
        input(text, inputField.get(inputField.size() - 1));
    }

    /**
     * Retrieve table rows as WebElementFacade.
     */
    private List<WebElementFacade> getTableRows(Target target) {
        WebElementFacade table = getWebelementFacadeFromTarget(target);
        List<WebElementFacade> rows;
        if (!table.findElements(By.xpath(".//table")).isEmpty()) {
            rows = table.thenFindAll(By.xpath(".//table//tbody//tr"));
        } else {
            rows = table.thenFindAll(By.xpath(".//tr"));
        }
        return rows;
    }

    /**
     * Retrieve table columns from a row.
     */
    public static List<WebElementFacade> getTableColumns(WebElementFacade table) {
        return table.thenFindAll(By.xpath(".//td"));
    }

    /**
     * Utility method to get WebElementFacade list by a selector.
     */
    public static List<WebElementFacade> getWebElementsFacadeBySelector(By selector) {
        Target targetElements = Target.the("elements list").located(selector);
        return targetElements.resolveAllFor(getActor());
    }
}
