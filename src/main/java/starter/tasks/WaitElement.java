package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import starter.Constants;

import java.util.List;

import static starter.pageselectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitForTask.*;

public class WaitElement {

    /**
     * Retrieves the actor in the spotlight.
     *
     * @return The actor in the spotlight.
     */
    public static Actor getCurrentActor() {
        return OnStage.theActorInTheSpotlight();
    }

    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static By getVisibleSelectorWithWait(String element) {
        By by = getCurrentPage().getSelector(element);
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(by, Constants.STATES.VISIBLE.getState())
        );
        return by;
    }

    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static By getVisibleByWithWait(By element) {
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(element, Constants.STATES.VISIBLE.getState())
        );
        return element;
    }

    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getVisibleSelectorWithWait(By element) {
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(element, Constants.STATES.VISIBLE.getState())
        );
        return Target.the("target element").located(element);
    }

    /**
     * Retrieves the WebElementFacade for the specified Target, waiting until it becomes visible.
     *
     * @param target The Target to locate and wait for.
     * @return The WebElementFacade for the specified Target once it is visible.
     */
    public static Target getVisibleTargetWithWait(Target target) {
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(target, Constants.STATES.VISIBLE.getState())
        );
        return target;
    }


    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getVisibleTargetWithWait(String element) {
        return getVisibleTargetWithWait(getTarget(element));
    }

    /**
     * Retrieves the WebElementFacade for the specified Target, waiting until it becomes visible.
     *
     * @param target The Target to locate and wait for.
     * @return The WebElementFacade for the specified Target once it is visible.
     */
    public static WebElementFacade getVisibleWebElementFacadeWithWait(Target target) {
        return getVisibleTargetWithWait(target).resolveFor(getCurrentActor());
    }

    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getPresentTargetWithWait(String element) {
        Target target = getTarget(element);
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(element, Constants.STATES.PRESENT.getState())
        );
        return target;
    }

    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getClicableTargetWithWait(String element) {
        Target target = getVisibleTargetWithWait(element);
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(element, Constants.STATES.CLICKABLE.getState())
        );
        return target;
    }

    /**
     * Retrieves the WebElementFacade for the given element by name.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getVisibleWebelementFacadeWithWait(WebElementFacade element) {
        waitForVisibility(element);
        return element;
    }

    /**
     * Retrieves the WebElementFacade for the given element by name.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getVisibleWebelementFacadeWithWait(String element) {
        return getVisibleTargetWithWait(element).resolveFor(getCurrentActor());
    }

    /**
     * Retrieves a list of WebElementFacade instances for elements matching the given selector.
     *
     * @param selector The By selector to locate the elements.
     * @return A list of WebElementFacade instances for the elements matching the selector.
     */
    public static List<WebElementFacade> getWebElementsFacadeBySelectorWithWait(By selector) {
        Target target = Target.the("target elements").located(selector);
        List<WebElementFacade> elements = target.resolveAllFor(getCurrentActor());
        elements.forEach(WebElementFacade::waitUntilVisible);
        return elements;
    }
}
