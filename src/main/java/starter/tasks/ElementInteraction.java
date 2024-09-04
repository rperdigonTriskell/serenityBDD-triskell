package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.ClickInteraction;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import starter.selectors.factory.PageFactory;

import java.util.List;

import static starter.Constants.CHECKBOX;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitInteractions.*;


public class ElementInteraction {
    /**
     * Clicks on the given target element.
     *
     * @param target The target element to click on.
     */
    public static void clickOnTarget(Object target) {
        OnStage.theActorInTheSpotlight().attemptsTo(createClickActionFor(target));
    }

    /**
     * Clicks on the first item in the list whose title contains the given text.
     *
     * @param repeatedText     The text to search for in the list items.
     * @param listElement      The list to iterate for results.
     * @param elementWithTitle The title text to match in the list items.
     */
    public static void clickOnElementInListWithTitleContaining(String repeatedText, String listElement, String elementWithTitle) {
        List<WebElementFacade> webElements = getWebElementsWithTitleContaining(listElement);
        for (WebElementFacade element : webElements) {
            if (element.getText().contains(elementWithTitle)) {
                clickOnTarget(repeatedText);
                break;
            }
        }
    }

    /**
     * Gets a list of web elements whose selector contains the given text.
     *
     * @param repeatedText The text to search for in the selector.
     * @return The list of matching web elements.
     */
    public static List<WebElementFacade> getWebElementsWithTitleContaining(String repeatedText) {
        return Target.the("option with title field")
                .located(PageFactory.getCurrentPage().getSelector(repeatedText))
                .resolveAllFor(OnStage.theActorInTheSpotlight()); //resolveAllFor uses implicit wait
    }

    /**
     * Creates a ClickInteraction object based on the type of the given target.
     *
     * @param target The target to create the ClickInteraction for.
     * @return The ClickInteraction object.
     */
    private static ClickInteraction createClickActionFor(Object target) {
        int attempts = 0;
        while (attempts < 3) { // Try the action up to 3 times
            try {
                if (target instanceof By) {
                    return createClickOnByAction((By) target);
                }
                if (target instanceof WebElementFacade) {
                    return createClickOnWebElementFacadeAction((WebElementFacade) target);
                }
                if (target instanceof String) {
                    return createClickOnStringAction((String) target);
                }
                // Throw an IllegalArgumentException if the target type is invalid
                throw new IllegalArgumentException("Invalid target type: " + target.getClass().getSimpleName());
            } catch (StaleElementReferenceException e) {
                // If a StaleElementReferenceException is caught, increment the attempts counter and try again
                attempts++;
            }
        }
        // If the action still fails after 3 attempts, throw a StaleElementReferenceException
        throw new StaleElementReferenceException("Element is stale after 3 attempts.");
    }

    /**
     * Creates a ClickInteraction object for a By locator.
     *
     * @param locator The By locator to click on.
     * @return The ClickInteraction object.
     */
    private static ClickInteraction createClickOnByAction(By locator) {
        return Click.on(locator);
    }

    /**
     * Creates a ClickInteraction object for a WebElementFacade.
     *
     * @param elementFacade The WebElementFacade to click on.
     * @return The ClickInteraction object.
     */
    private static ClickInteraction createClickOnWebElementFacadeAction(WebElementFacade elementFacade) {
        return Click.on(elementFacade);
    }

    /**
     * Creates a ClickInteraction object for a string selector.
     *
     * @param selector The string selector to click on.
     * @return The ClickInteraction object.
     */
    private static ClickInteraction createClickOnStringAction(String selector) {
        return Click.on(PageFactory.getCurrentPage().getSelector(selector));
    }

    /**
     * Clicks on a specific board within a given context based on the target text.
     *
     * @param board   the selector for the board web element
     * @param context the context of the board
     * @param target  the text to search for in the board rows
     */
    public static void clickOnBard(String board, String context, String target) {
        // Find the web table based on the provided context
        WebElementFacade table = waitElementVisible(getWebelementFacade(board), true);

        // Find the table rows on the web
        List<WebElementFacade> rows = waitElementsVisible(getTableRows(table));

        // Iterate through each row to find the one containing the target text
        for (WebElementFacade row : rows) {
            if (row.getText().contains(target)) {
                // If the context is 'CHECKBOX', click on the first column (assumed to be the checkbox)
                if (context.equals(CHECKBOX)) {
                    getTableColumns(row).get(0).click();
                } else {
                    throw new AssertionError("Context not recognized: " + context);
                }
                return; // Exit the loop once the desired row is found and clicked
            }
        }

        // If no row with the target text is found, throw an exception
        throw new AssertionError("Target text '" + target + "' not found in any row.");
    }
}
