package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.questions.Text;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static starter.selectors.factory.PageFactory.*;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitInteractions.*;

public class ElementDataVerifier extends PageObject {
    /**
     * Verifies if the actual text matches the expected text.
     *
     * @param  actualText   the actual text to compare
     * @param  expectedText the expected text to match
     * @throws AssertionError if the actual text does not match the expected text
     */
    public static void verifyTextsAreEqual(String actualText, String expectedText) {
        assertThat("The actual text does not match the expected text.",
                actualText, Matchers.equalTo(expectedText));
    }

    /**
     * Verifies if the element text matches the expected text.
     *
     * @param element      The element to verify.
     * @param expectedText The expected text to match.
     */
    public static void verifyElementTextIs(String element, String expectedText) {
        waitElementVisible(getCurrentPage().$(getCurrentPage().getSelector(element)), true);
        performShouldSeeThat(
                "check that the element" + element + " text is: " + expectedText,
                actor -> Text.of(getCurrentPage().getSelector(element)).answeredBy(actor),
                equalTo(expectedText)
        );
    }

    /**
     * Verifies if the table elements match the data in the provided DataTable.
     *
     * @param  context    the context used to find the web table
     * @param  dataTable  the DataTable containing the expected data
     */
    public static void verifyTableElementsMatchData(String context, DataTable dataTable) {
        // Find the web table based on the provided context
        WebElementFacade table = waitElementVisible(getCurrentPage().$(getCurrentPage().getSelector(context)),true);

        // Find the table rows on the web
        List<WebElementFacade> rows = waitElementsVisible(getTableRows(table));

        // Convert Gherkin DataTable to a list of maps
        List<Map<String, String>> expectedRows = dataTable.asMaps(String.class, String.class);

        // Check that the number of rows matches
        validateRowCount(rows, expectedRows.size());

        // Apply the Consumer to each row in the table using its index
        for (int i = 0; i < expectedRows.size(); i++) {
            verifyRowData(rows.get(i), expectedRows.get(i));
        }
    }

    /**
     * Verifies the data in a row of a table by comparing it with the expected data.
     *
     * @param  row         the WebElement representing the row to verify
     * @param  expectedRow a Map containing the expected data for each column in the row
     * @throws AssertionError if the number of columns in the row does not match the expected number of columns
     */
    public static void verifyRowData(WebElement row, Map<String, String> expectedRow) {
        // Find the columns within the row
        List<WebElement> columns = row.findElements(By.cssSelector("td"));

        // Verificar que el número de columnas coincida
        if (columns.size() != expectedRow.size()) {
            throw new AssertionError("The number of columns does not match the expected columns.");
        }

        // Verificar cada columna en la fila
        int columnIndex = 0;
        for (String columnName : expectedRow.keySet()) {
            String expectedValue = expectedRow.get(columnName);
            expectedValue = (expectedValue != null) ? expectedValue : "";
            String actualValue = columns.get(columnIndex).getText().trim();
            actualValue = (actualValue != null) ? actualValue : "";
            verifyTextsAreEqual(actualValue, expectedValue);
            columnIndex++;
        }
    }

}
