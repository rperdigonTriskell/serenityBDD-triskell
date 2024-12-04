package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import starter.Constants;

import java.util.List;
import java.util.Map;

import static starter.tasks.GenericTasks.getWebelementFacade;
import static starter.tasks.GenericTasks.performAttemptsTo;
import static starter.tasks.WaitElement.getWaitWebelementFacadeVisible;
import static starter.tasks.WaitFor.waitFor;
import static starter.tasks.WaitFor.waitUntil;

public class ElementDataVerifier {

    /**
     * Verifies if the actual text matches the expected text.
     *
     * @param actualText   the actual text to compare
     * @param expectedText the expected text to match
     */
    public static void verifyTextsAreEqual(String actualText, String expectedText) {
        performAttemptsTo("verify that actual text: {0} matches expected text: {1}",VerifyTextsAreEqual.between(actualText, expectedText));
    }

    /**
     * Verifies if the element text matches the expected text.
     *
     * @param element      The element to verify.
     * @param expectedText The expected text to match.
     */
    public static void verifyElementTextIs(String element, String expectedText) {
        performAttemptsTo("{0} wait for {1}", waitUntil(element, Constants.STATES.VISIBLE.getState()));
        performAttemptsTo("verify that element: {0} text matches expected text: {1}", VerifyElementTextIs.forElement(element, expectedText));
    }

    /**
     * Verifies the text of multiple elements using a DataTable.
     * The DataTable should have two columns: "element" and "value".
     *
     * @param dataTable The DataTable containing elements and expected values.
     */
    public static void verifyDatatableElementsTextIs(DataTable dataTable) {
        performAttemptsTo("verify that the text of the specified elements matches the expected values", VerifyDatatableElementsTextIs.from(dataTable));
    }

    /**
     * Verifies that the elements on the specified context match the expected data.
     *
     * @param  context   the context in which to verify the elements
     * @param  dataTable the DataTable containing the expected data for the elements
     */
    public static void verifyElementsMatchData(String context, DataTable dataTable) {
        // Process the DataTable into a list of maps
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        performAttemptsTo(
                "verify the following elements on the {0} should match the expected data",
                new VerifyTableElements(context, expectedData)
        );
    }
}
