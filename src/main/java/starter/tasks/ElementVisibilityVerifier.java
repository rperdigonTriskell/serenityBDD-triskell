package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Map;
import java.util.function.Consumer;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static starter.Constants.*;
import static starter.selectors.factory.PageFactory.getCurrentPage;
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
        WebElementFacade elementFacade = getWebelementFacade(element);
//        waitElementVisible(elementFacade, isVisible);
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(element, isVisible ? isVisible() : isNotVisible()).forNoMoreThan(WAIT_DURATION)
        );
        performShouldSeeThat(
                "check that the element visibility is: " + isVisible + ", for element: " + element,
                actor -> elementFacade.withTimeoutOf(WAIT_DURATION).waitUntilVisible(),
                isVisible ? WebElementStateMatchers.isVisible() : isNotVisible()
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

        dataTableUtil(dataTable, elementProcessor);
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
     * Verifies if the given web element is present in the DOM or not.
     *
     * @param element   The element to be verified.
     * @param isPresent A boolean that determines if the element should be present or not.
     */
    public static void verifyElementPresence(String element, boolean isPresent) {
        waitElementPresent(getWebelementFacade(element), isPresent);
        performShouldSeeThat(
                "check that the element is present: " + element,
                actor -> WebElementQuestion.stateOf(getCurrentPage().getSelector(element)).answeredBy(actor),
                isPresent ? WebElementStateMatchers.isPresent() : WebElementStateMatchers.isNotPresent()
        );
    }
}