package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;

import static starter.tasks.GenericTasks.performAttemptsTo;

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
}
