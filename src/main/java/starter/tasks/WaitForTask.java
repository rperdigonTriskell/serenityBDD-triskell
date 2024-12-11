package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starter.Constants;

import java.util.regex.Matcher;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static starter.Constants.WAIT_DURATION;
import static starter.pageselectors.factory.PageFactory.getCurrentPage;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;
import static starter.tasks.GenericTasks.*;

public class WaitForTask implements Task {
    private final Target target;
    private final String state;

    public WaitForTask(Target target, String state) {
        this.target = target;
        this.state = state.toLowerCase();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade webElementFacade = getWebelementFacadeFromTarget(target);
        if (state.equals(Constants.STATES.VISIBLE.getState())) {
            waitForVisibility(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isCurrentlyVisible()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.INVISIBLE.getState())) {
            waitForInvisibility(webElementFacade);
            actor.attemptsTo(WaitUntil.the(target, isNotVisible()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.PRESENT.getState())) {
            waitFor(webElementFacade, Constants.STATES.PRESENT.getState());
            actor.attemptsTo(WaitUntil.the(target, isPresent()).forNoMoreThan(WAIT_DURATION));
        } else if (state.equals(Constants.STATES.NOT_PRESENT.getState())) {
            waitForElementNotPresent(webElementFacade);
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
    public static WaitForTask waitUntil(Target target, String state) {
        return new WaitForTask(target, state);
    }

    public static WaitForTask waitUntil(String target, String state) {
        return new WaitForTask(getTarget(target), state);
    }

    public static WaitForTask waitUntil(WebElementFacade element, String state) {
        return new WaitForTask(Target.the("element").locatedBy(element.getWrappedElement().toString()), state);
    }

    public static WaitForTask waitUntil(By locator, String state) {
        return new WaitForTask(Target.the("element").located(locator), state);
    }

    // Other waits methods
    private static WebDriver driver = getStaticDriver();

    public static void waitFor(Object element, String state) {
        if (element instanceof String) {
            waitFor((String) element, state);
        }
    }

    public static void waitFor(String element, String state) {
        if (state.equals(Constants.STATES.VISIBLE.getState())) {
            waitForVisibility(element);
        } else if (state.equals(Constants.STATES.INVISIBLE.getState())) {
            waitForInvisibility(element);
        } else if (state.equals(Constants.STATES.PRESENT.getState())) {
            waitForElementPresent(element);
        } else if (state.equals(Constants.STATES.NOT_PRESENT.getState())) {
            waitForElementNotPresent(element);
        } else if (state.equals(Constants.STATES.CLICKABLE.getState())) {
            waitForClickable(element);
        } else {
            throw new IllegalArgumentException("Unknown visibility state: " + state);
        }
    }

    public static void waitForVisibility(WebElementFacade element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForVisibility(Target element) {
        waitForVisibility(getWebelementFacadeFromTarget(element));
    }

    public static void waitForVisibility(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOfElementLocated(element)));
    }

    public static void waitForVisibility(String element) {
        waitForVisibility(getWebelementFacade(element));
    }

    public static void waitForInvisibility(WebElementFacade element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.invisibilityOf(element)));
    }

    public static void waitForInvisibility(String element) {
        waitForInvisibility(getWebelementFacade(element));
    }

    public static void waitForClickable(WebElementFacade element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void waitForClickable(Target element) {
        waitForClickable(getWebelementFacadeFromTarget(element));
    }

    public static void waitForClickable(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void waitForClickable(String element) {
        waitForClickable(getWebelementFacade(element));
    }

    public static void waitForElementPresent(WebElementFacade element) {
        retryOnStaleElement(() -> element.waitUntilPresent());
    }

    public static void waitForElementPresent(Target target) {
        WebElement element = target.resolveFor(getStaticDriver());
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForElementPresent(String element) {
        By by = getCurrentPage().getSelector(element);
        waitForElementPresent(by);
    }

    public static void waitForElementPresent(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.presenceOfElementLocated(element)));
    }

    public static void waitForElementNotPresent(String element) {
        By by = getCurrentPage().getSelector(element);
        waitForElementNotPresent(by);
    }

    public static void waitForElementNotPresent(WebElementFacade element) {
        retryOnStaleElement(() -> element.waitUntilNotVisible());
    }

    public static void waitForElementNotPresent(By element) {
        retryOnStaleElement(() -> new WebDriverWait(driver, WAIT_DURATION)
                .until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(element))));
    }

    // Retry logic
    public static void retryOnStaleElement(Runnable action) {
        int retries = 10; // Number of retry attempts
        int retryDelay = 1000; // Delay between retries in milliseconds (1 second)

        while (retries > 0) {
            try {
                action.run();
                return; // Exit method if successful
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                retries--;
                if (retries == 0) {
                    throw e; // Throw exception if no more retries are left
                }
                try {
                    Thread.sleep(retryDelay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}