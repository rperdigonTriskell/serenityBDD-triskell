package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitOnQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starter.Constants;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static starter.Constants.WAIT_DURATION;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;
import static starter.tasks.GenericTasks.*;

public class WaitFor implements Task {
    private final Target target;
    private final String state;

    public WaitFor(Target target, String state) {
        this.target = target;
        this.state = state.toLowerCase();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade webElementFacade = getWebelementFacadeFromTarget(target);
        if (state.equals(Constants.STATES.VISIBLE.getState())) {
            waitForVisibility(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isVisible()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.INVISIBLE.getState())) {
            waitForInvisibility(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isNotVisible()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.PRESENT.getState())) {
            actor.attemptsTo(WaitUntil.the(target, isPresent()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.NOT_PRESENT.getState())) {
            actor.attemptsTo(WaitUntil.the(target, isNotPresent()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.CLICKABLE.getState())) {
            waitForClickable(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isClickable()).forNoMoreThan(WAIT_DURATION));
        }else if (state.equals(Constants.STATES.DISABLED.getState())) {
            actor.attemptsTo(WaitUntil.the(target, isNotEnabled()).forNoMoreThan(WAIT_DURATION));
        } else {
            throw new IllegalArgumentException("Unknown visibility state: " + state);
        }
    }

    // Método factory para esperar por un Target
    public static WaitFor waitUntil(Target target, String state) {
        return new WaitFor(target, state);
    }

    // Método factory para esperar por un String como selector
    public static WaitFor waitUntil(String target, String state) {
        return new WaitFor(getTarget(target), state);
    }

    // Método factory para esperar por un By locator
    public static WaitFor waitUntil(By locator, String state) {
        return new WaitFor(Target.the("element").located(locator), state);
    }

    private static WebDriver driver = getStaticDriver();

    // Espera explícita para visibilidad
    public static void waitForVisibility(WebElementFacade element) {
        new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Espera explícita para invisibilidad
    public static void waitForInvisibility(WebElementFacade element) {
        new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    // Espera explícita para que el elemento sea clickeable
    public static void waitForClickable(WebElementFacade element) {
        new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
