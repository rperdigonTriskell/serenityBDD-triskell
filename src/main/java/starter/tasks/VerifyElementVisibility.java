package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;

import static starter.Constants.*;
import static starter.tasks.GenericTasks.*;

public class VerifyElementVisibility implements Task {
    private String element;
    private String visibility;

    public VerifyElementVisibility(String element, String visibility) {
        this.element = element;
        this.visibility = visibility;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        Target locator = getTarget(element);
        String visibilityState = visibility.toLowerCase();

        // wait until the element parameter visibility state
        actor.attemptsTo(WaitFor.waitUntil(locator, visibilityState));

        if (visibilityState.equals(STATES.VISIBLE.getState())) { // visible
            performShouldSeeThat("{0} verifies that the element {1} is visible",
                    actor2 -> WebElementQuestion.stateOf(locator).answeredBy(actor2),
                    WebElementStateMatchers.isVisible());
        } else if (visibilityState.equals(STATES.INVISIBLE.getState())) { // invisible
            performShouldSeeThat("{0} verifies that the element {1} is not visible",
                    actor2 -> WebElementQuestion.stateOf(locator).answeredBy(actor2),
                    WebElementStateMatchers.isNotVisible());
        } else if (visibilityState.equals(STATES.PRESENT.getState())) { // present
            performShouldSeeThat("{0} verifies that the element {1} is present",
                    actor2 -> WebElementQuestion.stateOf(locator).answeredBy(actor2),
                    WebElementStateMatchers.isPresent());
        } else if (visibilityState.equals(STATES.NOT_PRESENT.getState())) { // not present
            performShouldSeeThat("{0} verifies that the element {1} is not present",
                    actor2 -> WebElementQuestion.stateOf(locator).answeredBy(actor2),
                    WebElementStateMatchers.isNotPresent());
        } else {
            throw new IllegalArgumentException("Unknown visibility state: " + visibility);
        }
    }

    public static Task verifyElementIsVisible(String element) {
        return new VerifyElementVisibility(element, STATES.VISIBLE.getState());
    }

    public static Task verifyElementIsNotVisible(String element) {
        return new VerifyElementVisibility(element, STATES.INVISIBLE.getState());
    }

    public static Task verifyElementIsPresent(String element) {
        return new VerifyElementVisibility(element, STATES.PRESENT.getState());
    }

    public static Task verifyElementIsNotPresent(String element) {
        return new VerifyElementVisibility(element, STATES.NOT_PRESENT.getState());
    }
}
