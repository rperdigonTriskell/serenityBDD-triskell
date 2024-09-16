package starter.tasks;

import net.serenitybdd.core.pages.*;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static starter.Constants.WAIT_DURATION;
import static starter.selectors.factory.PageFactory.*;

public class WaitInteractions extends PageObject {

    /**
     * Waits for the given WebElementFacade to become present or not present, depending on the value of the 'present' parameter.
     *
     * @param element the WebElementFacade to wait for
     * @param present a boolean indicating whether the element should be present or not present
     * @return the WebElementFacade if it is present and 'present' is true, or null if it is not present and 'present' is true, or null if it is present and 'present' is false, or the WebElementFacade if it is not present and 'present' is false
     * @throws TimeoutException if the element does not become present within the specified timeout duration
     */
    public static WebElementFacade waitElementPresent(WebElementFacade element, boolean present) {
        if (present) {
            return element.withTimeoutOf(WAIT_DURATION).waitUntilPresent();
        } else {
            return waitUntilElementNotPresent(element);
        }
    }

    /**
     * Waits until the given WebElementFacade is no longer present on the page.
     *
     * @param element the WebElementFacade to wait for
     * @throws TimeoutException if the element does not become not present within the specified timeout duration
     */
    public static WebElementFacade waitUntilElementNotPresent(WebElementFacade element) {
        try {
            new WebDriverWait(getStaticDriver(), WAIT_DURATION)
                    .until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(getWebElementSelector(element))));
            return null; // Element is not present, return null
        } catch (TimeoutException e) {
            return element;
        }
    }



    /**
     * Waits for a WebElement to be visible on the page (using Serenity's WebElementFacade).
     *
     * @param element the WebElementFacade to wait for
     * @return the WebElementFacade that was found
     */
    public static WebElementFacade waitElementVisible(WebElementFacade element, boolean visibility) {
        if (visibility) {
            return element.withTimeoutOf(WAIT_DURATION).waitUntilVisible();
        } else {
            return element.withTimeoutOf(WAIT_DURATION).waitUntilNotVisible();
        }
    }

    /**
     * Waits for all elements in the given list to be visible on the page (using Serenity's WebElementFacade).
     *
     * @param elements the list of WebElementFacades to wait for
     * @return the list of WebElementFacades that were found and are visible
     */
    public static List<WebElementFacade> waitElementsVisible(List<WebElementFacade> elements) {
        elements.forEach(WebElementFacade::waitUntilVisible);
        return elements;
    }

    /**
     * Waits for all elements in the given list to be visible on the page (using Serenity's WebElementFacade).
     *
     * @param elements the list of WebElementFacades to wait for
     * @return the list of WebElementFacades that were found and are visible
     */
    public static List<WebElementFacade> waitElementsPresent(List<WebElementFacade> elements) {
        elements.forEach(WebElementFacade::waitUntilPresent);
        return elements;
    }

    /**
     * Waits for the given target to be visible on the page.
     *
     * @param target the target element to wait for
     */
    public static void waitForClickVisibility(Object target) {
        if (target instanceof By) {
            By locator = (By) target;
            WaitUntil.the(locator, WebElementStateMatchers.isVisible()).forNoMoreThan(WAIT_DURATION);
            WaitUntil.the(locator, WebElementStateMatchers.isClickable()).forNoMoreThan(WAIT_DURATION);
        } else if (target instanceof WebElementFacade) {
            WebElementFacade elementFacade = (WebElementFacade) target;
            elementFacade.withTimeoutOf(WAIT_DURATION).waitUntilVisible();
            elementFacade.withTimeoutOf(WAIT_DURATION).waitUntilClickable();
        } else if (target instanceof String) {
            By locator = getCurrentPage().getSelector((String) target);
            WaitUntil.the(locator, WebElementStateMatchers.isVisible()).forNoMoreThan(WAIT_DURATION);
            WaitUntil.the(locator, WebElementStateMatchers.isClickable()).forNoMoreThan(WAIT_DURATION);
        } else {
            throw new IllegalArgumentException("Unsupported target type: " + target.getClass().getSimpleName());
        }
    }

}
