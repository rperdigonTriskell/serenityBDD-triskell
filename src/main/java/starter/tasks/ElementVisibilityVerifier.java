package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Map;
import java.util.function.Consumer;

import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.equalTo;
import static starter.Constants.*;
import static starter.selectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.GenericTasks.*;

public class ElementVisibilityVerifier {

    /**
     * Verifies if the given web element is visible or not.
     *
     * @param element   The element to be verified.
     * @param isVisible A boolean that determines if the element should be visible or not.
     */
    public static void verifyElementVisibility(String element, boolean isVisible) {
        performShouldSeeThat(
                "check that the element visibility is: " + isVisible + ", for element: " + element,
                actor -> the(getCurrentPage().getSelector(element)).answeredBy(actor),
                isVisible ? WebElementStateMatchers.isVisible() : WebElementStateMatchers.isNotVisible()
        );
    }

    /**
     * Verifies if the given web element is visible.
     *
     * @param element The element to be verified.
     */
    public static void verifyElementIsVisible(String element) {
        verifyElementVisibility(element, true);
    }

    /**
     * Verifies that the given element is not visible on the current page.
     *
     * @param element The element to be verified.
     */
    public static void verifyElementIsNotVisible(String element) {
        verifyElementVisibility(element, false);
    }

    /**
     * Verifies if the element text matches the expected text.
     *
     * @param element      The element to verify.
     * @param expectedText The expected text to match.
     */
    public static void verifyElementTextIs(String element, String expectedText) {
        performShouldSeeThat(
                "check that the element text matches the expected text",
                actor -> Text.of(getCurrentPage().getSelector(element)).answeredBy(actor),
                equalTo(expectedText)
        );
    }

    /**
     * Verifies if the elements in the DataTable are visible according to their specified visibility.
     *
     * @param dataTable The DataTable containing elements and their visibility state.
     */
    public static void dataTableAreVisible(String context, DataTable dataTable) {
        Consumer<Map<String, String>> elementProcessor = element -> {
            String elementName = element.get(ELEMENT);
            String visibility = element.get(VISIBILITY);
            if (visibility.equalsIgnoreCase(STATES[0])) {
                verifyElementIsVisible(context + elementName);
            } else if (STATES[1].equalsIgnoreCase(visibility)) {
                verifyElementIsNotVisible(context + elementName);
            } else {
                throw new IllegalArgumentException("Unknown visibility state: " + visibility);
            }
        };

        dataTableUtil(dataTable, elementProcessor);
    }
}
