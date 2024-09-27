package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.function.Consumer;

import static starter.Constants.*;
import static starter.pageselectors.factory.PageFactory.*;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitInteractions.*;

public class ElementVisibilityVerifier {

    /**
     * Verifies if the given web element is visible or not.
     *
     * @param element    The element to be verified.
     * @param visibility A boolean that determines if the element should be visible or not.
     */
    public static void verifyElementVisibility(String element, String visibility) {
        if (visibility.equalsIgnoreCase(STATES[0])) {
            verifyElementIsVisible(element);
        } else if (visibility.equalsIgnoreCase(STATES[1])) {
            verifyElementIsNotVisible(element);
        } else if (visibility.equalsIgnoreCase(STATES[4])) {
            verifyElementIsPresent(element);
        } else if (visibility.equalsIgnoreCase(STATES[5])) {
            verifyElementIsNotPresent(element);
        } else {
            throw new IllegalArgumentException("Unknown visibility state: " + visibility);
        }
    }

    /**
     * Verifies if the given web element is visible or not.
     *
     * @param element   The element to be verified.
     * @param isVisible A boolean that determines if the element should be visible or not.
     */
    public static void verifyElementVisibility(String element, boolean isVisible) {
        Target locator = getTarget(element);
        if (isVisible) {
            performAttemptsTo("{0} waits for element to be visible", waitVisible(locator));
        } else {
            performAttemptsTo("{0} waits for element to be present", waitNotVisible(locator));
        }
        performShouldSeeThat(
                "check that the element is present: " + element,
                actor -> WebElementQuestion.stateOf(getTarget(element)).answeredBy(actor),
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
     * Verifies if the elements in the DataTable are visible according to their specified visibility.
     *
     * @param dataTable The DataTable containing elements and their visibility state.
     */
    public static void dataTableAreVisible(String context, DataTable dataTable) {
        Consumer<Map<String, String>> elementProcessor = element -> {
            String elementName = element.get(ELEMENT);
            String visibility = element.get(VISIBILITY);
            verifyElementVisibility(context + elementName, visibility);
        };

        processDataTable(dataTable, elementProcessor);
    }

    /**
     * Verifies if the given web element is visible.
     *
     * @param element The element to be verified.
     */
    public static void verifyElementIsPresent(String element) {
        verifyElementPresence(element, true);
    }

    /**
     * Verifies that the given element is not visible on the current page.
     *
     * @param element The element to be verified.
     */
    public static void verifyElementIsNotPresent(String element) {
        verifyElementPresence(element, false);
    }

    /**
     * Wait verifies if the given web element is present in the DOM or not.
     *
     * @param element   The element to be verified.
     * @param isPresent A boolean that determines if the element should be present or not.
     */
    public static void verifyElementPresence(String element, boolean isPresent) {
       if (isPresent){
           getWebelementFacade(element).waitForCondition().until(driver -> getWebelementFacade(element).isPresent());
           performAttemptsTo("{0} waits to present", waitPresent(getTarget(element)));
       }else{
           getWebelementFacade(element).waitForCondition().until(driver -> !getWebelementFacade(element).isPresent());
           performAttemptsTo("{0} waits to not present", waitNotPresent(getTarget(element)));
       }

        performShouldSeeThat(
                "check that the element is present: " + element,
                actor -> WebElementQuestion.stateOf(getTarget(element)).answeredBy(actor),
                isPresent ? WebElementStateMatchers.isPresent() : WebElementStateMatchers.isNotPresent()
        );
    }
}