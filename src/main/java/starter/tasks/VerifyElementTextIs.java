package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import starter.Constants;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static starter.tasks.GenericTasks.performAttemptsTo;
import static starter.tasks.GenericTasks.performShouldSeeThat;
import static starter.tasks.GenericTasks.getTarget;
import static org.hamcrest.Matchers.equalTo;

public class VerifyElementTextIs implements Task {

    private final String element;
    private final String expectedText;

    public VerifyElementTextIs(String element, String expectedText) {
        this.element = element;
        this.expectedText = expectedText;
    }

    public static VerifyElementTextIs forElement(String element, String expectedText) {
        return instrumented(VerifyElementTextIs.class, element, expectedText);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Target target = getTarget(element);

        performAttemptsTo(
                "wait until the element " + element + " is visible",
                WaitFor.waitUntil(element, Constants.STATES.VISIBLE.getState())
        );

        performShouldSeeThat(
                "verify the element " + element + " text is: " + expectedText,
                currentActor -> Text.of(target).answeredBy(currentActor),
                equalTo(expectedText)
        );
    }
}
