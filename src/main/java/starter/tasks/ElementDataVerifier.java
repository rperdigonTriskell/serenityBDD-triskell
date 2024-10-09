package starter.tasks;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.hamcrest.Matchers;
import starter.Constants;

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
}
