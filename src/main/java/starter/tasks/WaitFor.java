package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
            waitForInvisibilityWithRetry(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isNotVisible()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.PRESENT.getState())) {
            waitForElementPresent(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isPresent()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.NOT_PRESENT.getState())) {
            actor.attemptsTo(WaitUntil.the(target, isNotPresent()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.CLICKABLE.getState())) {
            waitForClickable(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isClickable()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.DISABLED.getState())) {
            actor.attemptsTo(WaitUntil.the(target, isNotEnabled()).forNoMoreThan(WAIT_DURATION));
        } else {
            throw new IllegalArgumentException("Unknown visibility state: " + state);
        }
    }

    // Factory Methods
    public static WaitFor waitUntil(Target target, String state) {
        return new WaitFor(target, state);
    }

    public static WaitFor waitUntil(String target, String state) {
        return new WaitFor(getTarget(target), state);
    }

    public static WaitFor waitUntil(By locator, String state) {
        return new WaitFor(Target.the("element").located(locator), state);
    }

    //Other waits methods
    private static WebDriver driver = getStaticDriver();

    // Implementación con reintentos para visibilidad
    public static void waitForVisibility(WebElementFacade element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForVisibility(Target element) {
        WebElementFacade webElementFacade = getWebelementFacadeFromTarget(element);
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOf(webElementFacade)));
    }

    public static void waitForVisibility(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(element)));
    }

    public static void waitForInvisibilityWithRetry(WebElementFacade element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.invisibilityOf(element)));
    }


    public static void waitForClickable(Target element) {
        WebElementFacade webElementFacade = getWebelementFacadeFromTarget(element);
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(webElementFacade)));
    }

    public static void waitForClickable(WebElementFacade element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void waitForClickable(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    // Espera explícita para que el elemento esté presente usando WebElementFacade
    public static void waitForElementPresent(WebElementFacade element) {
        retryOnStaleElement(() -> element.waitUntilPresent());
    }

    public static void waitForElementPresent(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.presenceOfElementLocated(element)));
    }

    public static void waitForElementNotPresent(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(element))));
    }


    // Método de reintento en caso de StaleElementReferenceException
    private static void retryOnStaleElement(Runnable waitAction) {
        int retries = 3;  // Número de reintentos permitidos
        while (retries > 0) {
            try {
                waitAction.run();  // Ejecutar la acción de espera
                return;  // Si es exitoso, salir del método
            } catch (StaleElementReferenceException e) {
                retries--;
                if (retries == 0) {
                    throw e;  // Si se agotan los reintentos, relanzar la excepción
                }
            }
        }
    }
}