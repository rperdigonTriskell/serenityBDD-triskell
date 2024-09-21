package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import starter.selectors.factory.PageFactory;

import java.util.List;

import static starter.Constants.CHECKBOX;
import static starter.selectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitInteractions.*;


public class ElementInteraction {
    /**
     * Clicks on the given target element.
     *
     * @param target The target element to click on.
     */
    public static void clickOnTarget(Object target) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                createClickActionFor(target)
        );
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
                .located(getCurrentPage().getSelector(repeatedText))
                .resolveAllFor(OnStage.theActorInTheSpotlight()); //resolveAllFor uses implicit wait
    }

    /**
     * Creates a ClickInteraction object based on the type of the given target.
     *
     * @param target The target to create the ClickInteraction for.
     * @return The ClickInteraction object.
     */
    public static Task createClickActionFor(Object target) {
        if (target instanceof By) {
            return Task.where("{0} waits for and clicks on By locator",
                    WaitInteractions.waitVisible((By) target),
                    WaitInteractions.waitClickable((By) target),
                    Click.on((By) target)
            );
        }
        if (target instanceof WebElementFacade) {
            ((WebElementFacade) target).waitUntilVisible();
            ((WebElementFacade) target).waitUntilClickable();
            return Task.where("{0} waits for and clicks on WebElementFacade",
                    Click.on((WebElementFacade) target)
            );
        }
        if (target instanceof Target) {
            return Task.where("{0} waits for and clicks on Target",
                    WaitInteractions.waitVisible((Target) target),
                    WaitInteractions.waitClickable((Target) target),
                    Click.on((Target) target)
            );
        }
        if (target instanceof String) {
            Target targetElement = getTarget((String) target);
            return Task.where("{0} waits for and clicks on selector",
                    WaitInteractions.waitVisible(targetElement),
                    WaitInteractions.waitClickable(targetElement),
                    Click.on(targetElement)
            );
        }
        throw new IllegalArgumentException("Invalid target type: " + target.getClass().getSimpleName());
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
        Target table = getTarget(board);

        performAttemptsTo("{0} waits for table to be visible", waitVisible(table));


        // Find the table rows on the web
        List<WebElementFacade> rows = getTableRows(table);

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
