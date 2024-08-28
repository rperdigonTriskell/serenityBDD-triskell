package starter.tasks;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.Wait;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitInteractions extends PageObject {

    private static final Duration WAIT_DURATION = Duration.ofSeconds(10);

    /**
     * Waits for a WebElement to be present on the page using its selector (using Serenity's WebElementFacade).
     *
     * @param by the By selector to locate the WebElement
     * @return the WebElementFacade that was found
     */
    public static WebElementFacade waitElementIsPresent(By by) {
        return new WaitInteractions().$(by).waitUntilPresent();
    }

    /**
     * Waits for a WebElement to be visible on the page (using Serenity's WebElementFacade).
     *
     * @param element the WebElementFacade to wait for
     * @return the WebElementFacade that was found
     */
    public static WebElementFacade waitElementVisible(WebElementFacade element) {
        return element.waitUntilVisible();
    }

    /**
     * Waits for all elements in the given list to be visible on the page (using Serenity's WebElementFacade).
     *
     * @param elements the list of WebElementFacades to wait for
     * @return the list of WebElementFacades that were found and are visible
     */
    public static List<WebElementFacade> waitElementsVisible(List<WebElementFacade> elements) {
        // Recorremos cada elemento para aplicar la espera de visibilidad
        for (WebElementFacade element : elements) {
            element.waitUntilVisible();
        }
        return elements;
    }

    /**
     * Waits for the result of a function applied to the actor to match a given matcher.
     *
     * @param questionFunction a function that takes an actor and returns a value of type T
     * @param matcher          a matcher that checks if a value of type T matches a certain condition
     * @param <T>              the type of the value returned by the questionFunction
     */
    public static <T> void waitPerformShouldSeeThat(Function<Actor, T> questionFunction, Matcher<T> matcher) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Wait.until(actor -> questionFunction.apply(actor), matcher).forNoMoreThan(10).seconds()
        );
    }
}
