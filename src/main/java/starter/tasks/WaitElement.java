package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import starter.Constants;

import java.util.List;

import static starter.pageselectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitFor.*;

public class WaitElement {
    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static By getWaitVisibleSelector(String element) {
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
    public static Target getWaitVisibleSelector(By element) {
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
    public static WebElementFacade getWaitVisibleTarget(Target target) {
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(target, Constants.STATES.VISIBLE.getState())
        );
        return target.resolveFor(OnStage.theActorInTheSpotlight());
    }


    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getWaitVisibleTarget(String element) {
        Target target = getTarget(element);
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(element, Constants.STATES.VISIBLE.getState())
        );
        return target;
    }

    /**
     * Retrieves the Target for the given element by name, with a wait until it is visible.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getWaitClicableTarget(String element) {
        Target target = getWaitVisibleTarget(element);
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
    public static WebElementFacade getWaitWebelementFacade(WebElementFacade element) {
        performAttemptsTo(
                "{0} waits for table to be visible",
                element.waitUntilPresent(),
                element.waitUntilClickable(),
                element.waitUntilVisible()
        );
        return element;
    }

    /**
     * Retrieves the WebElementFacade for the given element by name.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getWaitWebelementFacade(String element) {
        return getWaitVisibleTarget(element).resolveFor(OnStage.theActorInTheSpotlight());
    }

    /**
     * Retrieves the WebElementFacade for a given Target, with a wait until it is visible.
     *
     * @param target The Target to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given Target.
     */
    public static WebElementFacade getWaitWebelementFacadeFromTarget(Target target) {
        getWaitVisibleTarget(target);
        return target.resolveFor(OnStage.theActorInTheSpotlight());
    }

    /**
     * Retrieves a list of WebElementFacade instances for elements matching the given selector.
     *
     * @param selector The By selector to locate the elements.
     * @return A list of WebElementFacade instances for the elements matching the selector.
     */
    public static List<WebElementFacade> getWaitWebElementsFacadeBySelector(By selector) {
        Target target = Target.the("target elements").located(selector);
        List<WebElementFacade> elements = target.resolveAllFor(OnStage.theActorInTheSpotlight());
        elements.forEach(WebElementFacade::waitUntilVisible);
        return elements;
    }
}
