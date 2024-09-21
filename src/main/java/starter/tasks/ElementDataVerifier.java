package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static starter.Constants.*;
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
        performAttemptsTo(
                "wait until the element " + element + " text is: " + expectedText,
                waitVisible(getCurrentPage().getSelector(element))
        );
        performShouldSeeThat(
                "check that the element" + element + " text is: " + expectedText,
                actor -> Text.of(getCurrentPage().getSelector(element)).answeredBy(actor),
                equalTo(expectedText)
        );
    }
}
