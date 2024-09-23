package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static starter.Constants.WAIT_DURATION;
import static starter.selectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.GenericTasks.getWebelementFacade;

public class WaitInteractions {

    public static Task waitPresent(By locator) {
        return Task.where("{0} waits for element to be present",
                WaitUntil.the(locator, isPresent()).forNoMoreThan(WAIT_DURATION));
    }

    public static Task waitPresent(Target target) {
        return Task.where("{0} waits for element to be present",
                WaitUntil.the(target, isPresent()).forNoMoreThan(WAIT_DURATION));
    }

    public static Task waitVisible(By locator) {
        return Task.where("{0} waits for element to be visible",
                WaitUntil.the(locator, isVisible()).forNoMoreThan(WAIT_DURATION));
    }

    public static Task waitVisible(Target target) {
        return Task.where("{0} waits for target to be visible",
                WaitUntil.the(target, isVisible()).forNoMoreThan(WAIT_DURATION));
    }

    public static Task waitNotVisible(Target target) {
        return Task.where("{0} waits for target to be visible",
                WaitUntil.the(target, isNotVisible()).forNoMoreThan(WAIT_DURATION));
    }

    public static WebElementFacade waitNotVisible(WebElementFacade target) {
        target.waitForCondition().until(webDriver -> !target.isVisible());
        return target;
    }

    public static Task waitNotPresent(Target target) {
        return Task.where("{0} waits for target to be visible",
                WaitUntil.the(target, isNotPresent()).forNoMoreThan(WAIT_DURATION));
    }

    public static Task waitClickable(By locator) {
        return Task.where("{0} waits for element to be clickable",
                WaitUntil.the(locator, isClickable()).forNoMoreThan(WAIT_DURATION));
    }

    public static Task waitClickable(Target target) {
        return Task.where("{0} waits for target to be clickable",
                WaitUntil.the(target, isClickable()).forNoMoreThan(WAIT_DURATION));
    }
}
