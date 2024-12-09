package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
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
    public static Actor getActor(){
        return OnStage.theActorInTheSpotlight();
    }

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
        return target.resolveFor(getActor());
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
    public static Target getWaitPresentTarget(String element) {
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
    public static Target getWaitClicableTarget(String element) {
        Target target = getWaitVisibleTarget(element);
        performAttemptsTo(
                "{0} waits for table to be visible",
                waitUntil(element, Constants.STATES.CLICKABLE.getState())
        );
        return target;
    }

    /**
     * Creates a Performable that waits for a WebElementFacade to be visible.
     *
     * @param element The WebElementFacade to wait for.
     * @return A Performable to execute the wait.
     */
    public static Performable toBeVisible(WebElementFacade element) {
        return Task.where("{0} waits for the WebElementFacade to be visible",
                actor -> element.waitUntilVisible()
        );
    }

    /**
     * Retrieves the WebElementFacade for the given element by name.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getWaitWebelementFacadeVisible(WebElementFacade element) {
        performAttemptsTo(
                "{0} waits for table to be visible",
                toBeVisible(element)
        );
        return element;
    }

    /**
     * Retrieves the WebElementFacade for the given element by name.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getWaitVisiWebelementFacadeVisible(String element) {
        return getWaitVisibleTarget(element).resolveFor(getActor());
    }

    /**
     * Retrieves the WebElementFacade for a given Target, with a wait until it is visible.
     *
     * @param target The Target to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given Target.
     */
    public static WebElementFacade getWaitVisibleWebelementFacadeFromTarget(Target target) {
        getWaitVisibleTarget(target);
        return target.resolveFor(getActor());
    }

    /**
     * Retrieves a list of WebElementFacade instances for elements matching the given selector.
     *
     * @param selector The By selector to locate the elements.
     * @return A list of WebElementFacade instances for the elements matching the selector.
     */
    public static List<WebElementFacade> getWaitWebElementsFacadeBySelector(By selector) {
        Target target = Target.the("target elements").located(selector);
        List<WebElementFacade> elements = target.resolveAllFor(getActor());
        elements.forEach(WebElementFacade::waitUntilVisible);
        return elements;
    }
}
