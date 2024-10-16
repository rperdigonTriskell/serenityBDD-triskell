package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class VerifyTextsAreEqual implements Task {

    private final String actualText;
    private final String expectedText;

    public VerifyTextsAreEqual(String actualText, String expectedText) {
        this.actualText = actualText;
        this.expectedText = expectedText;
    }

    public static VerifyTextsAreEqual between(String actualText, String expectedText) {
        return instrumented(VerifyTextsAreEqual.class, actualText, expectedText);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        assertThat("The actual text does not match the expected text.",
                actualText, equalTo(expectedText));
    }
}
