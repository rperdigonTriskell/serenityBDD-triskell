package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static starter.Constants.WAIT_DURATION;

public class WaitInteractions {

    public static Performable waitPresent(By locator) {
        return WaitUntil.the(locator, isPresent()).forNoMoreThan(WAIT_DURATION);
    }

    public static Performable waitPresent(Target target) {
        return WaitUntil.the(target, isPresent()).forNoMoreThan(WAIT_DURATION);
    }

    public static Performable waitVisible(By locator) {
        return WaitUntil.the(locator, isVisible()).forNoMoreThan(WAIT_DURATION);
    }

    public static Performable waitVisible(Target target) {
        return WaitUntil.the(target, isPresent()).forNoMoreThan(WAIT_DURATION)
                .then(WaitUntil.the(target, isVisible()).forNoMoreThan(WAIT_DURATION));
    }

    public static Performable waitNotVisible(Target target) {
        return WaitUntil.the(target, isNotVisible()).forNoMoreThan(WAIT_DURATION);
    }

    public static WebElementFacade waitNotVisible(WebElementFacade target) {
        target.waitForCondition().until(webDriver -> !target.isVisible());
        return target;
    }

    public static Performable waitNotPresent(Target target) {
        return WaitUntil.the(target, isNotPresent()).forNoMoreThan(WAIT_DURATION);
    }

    public static Performable waitClickable(By locator) {
        return WaitUntil.the(locator, isClickable()).forNoMoreThan(WAIT_DURATION);
    }

    public static Performable waitClickable(Target target) {
        return WaitUntil.the(target, isClickable()).forNoMoreThan(WAIT_DURATION);
    }
}