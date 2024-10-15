package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.hamcrest.Matchers;
import starter.Constants;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static starter.tasks.GenericTasks.*;

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
        Target target = getTarget(element);
        WaitFor.waitForVisibility(target);
        performAttemptsTo(
                "wait until the element " + element + " text is: " + expectedText,
                WaitFor.waitUntil(element, Constants.STATES.VISIBLE.getState())
        );
        performShouldSeeThat(
                "check that the element" + element + " text is: " + expectedText,
                actor -> Text.of(target).answeredBy(actor),
                equalTo(expectedText)
        );
    }

    /**
     * Verifies the text of multiple elements using a DataTable.
     * The DataTable should have two columns: "element" and "value".
     *
     * @param dataTable The DataTable containing elements and expected values.
     */
    public static void verifyDatatableElementsTextIs(DataTable dataTable) {
        // Convert DataTable to a list of maps (each row is a map with keys: "element" and "value")
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        // Iterate through each row
        for (Map<String, String> row : rows) {
            String element = row.get("element");
            String expectedValue = row.get("value");

            // Reuse the existing method to verify each element's text
            verifyElementTextIs(element, expectedValue);
        }
    }
}
