package starter.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static starter.Constants.*;
import static starter.tasks.ElementDataVerifier.*;
import static starter.tasks.WaitElement.getWaitWebelementFacadeVisible;

public class VerifyTableElements implements Task {

    private final String tableName;
    private final List<Map<String, String>> expectedData;

    public VerifyTableElements(String tableName, List<Map<String, String>> expectedData) {
        this.tableName = tableName;
        this.expectedData = expectedData;
    }

    /**
     * Returns the current date in the format "dd/MM/yyyy".
     *
     * @return a string representing the current date.
     */
    public static String getCurrentDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.now().format(dateFormatter);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Locate the table
        WebElementFacade table = WaitElement.getWaitWebelementFacadeVisible(tableName);
        List<WebElementFacade> rows = table.thenFindAll(By.xpath("tr"));

        // Iterate over each row of the expected data
        for (int i = 0; i < expectedData.size(); i++) {
            Map<String, String> expectedRow = expectedData.get(i);
            WebElementFacade currentRow = rows.get(i).findBy(By.xpath(".//table//tbody//tr"));

            // Get the actual columns in the row
            List<WebElementFacade> columns = currentRow.thenFindAll(By.xpath(".//td"));

            // Validate each field in the row based on the expected data
            int columnIndex = 0; // Start from the first column
            for (Map.Entry<String, String> entry : expectedRow.entrySet()) {
                // Check if the expected value is "[empty]" or "[actual date]"
                String expectedValue = entry.getValue();
                if (expectedValue.equals(EMPTY_DATA)) {
                    expectedValue = ""; // Set expected value to empty string
                } else if (expectedValue.equals(ACTUAL_DATE)) {
                    expectedValue = getCurrentDate(); // Set expected value to current date
                }

                // Get the actual value from the table cell
                String actualValue = columns.get(columnIndex).getText().trim();

                // Perform the comparison
                verifyTextsAreEqual(actualValue, expectedValue);
                columnIndex++; // Move to the next column for the next entry
            }
        }
    }
}