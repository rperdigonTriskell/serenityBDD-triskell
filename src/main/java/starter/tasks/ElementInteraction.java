package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.RightClick;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import starter.Constants;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static starter.Constants.CHECKBOX;
import static starter.pageselectors.factory.PageFactory.getCurrentPage;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitFor.*;


public class ElementInteraction {
    /**
     * Clicks on the given target element.
     *
     * @param target The target element to click on.
     */
    public static void clickOnTarget(Object target) {
        waitFor(target, Constants.STATES.VISIBLE.getState());
        waitFor(target, Constants.STATES.CLICKABLE.getState());
        performAttemptsTo("{0} attempts to click on target", createClickActionFor(target));
    }

    /**
     * Creates a ClickInteraction object based on the type of the given target.
     *
     * @param target The target to create the ClickInteraction for.
     * @return The ClickInteraction object.
     */
    public static Task createClickActionFor(Object target) {
        if (target instanceof By) {
            waitForVisibility((By) target);
            WaitFor.waitForClickable((By) target);
            return Task.where("{0} waits for and clicks on By locator",
                    WaitFor.waitUntil((By) target, Constants.STATES.VISIBLE.getState()),
                    WaitFor.waitUntil((By) target, Constants.STATES.CLICKABLE.getState()),
                    Click.on((By) target)
            );
        }
        if (target instanceof WebElementFacade) {
            waitForVisibility((WebElementFacade) target);
            WaitFor.waitForClickable((WebElementFacade) target);
            WebElementFacade element = (WebElementFacade) target;
            return Task.where("{0} waits for and clicks on WebElementFacade",
                    ((WebElementFacade) target).waitUntilVisible(),
                    ((WebElementFacade) target).waitUntilClickable(),
                    Click.on(element)
            );
        }
        if (target instanceof Target) {
            waitForVisibility((Target) target);
            WaitFor.waitForClickable((Target) target);
            Target targetElement = (Target) target;
            return Task.where("{0} waits for and clicks on Target",
                    WaitFor.waitUntil(targetElement, Constants.STATES.VISIBLE.getState()),
                    WaitFor.waitUntil(targetElement, Constants.STATES.CLICKABLE.getState()),
                    Click.on(targetElement)
            );
        }
        if (target instanceof String) {
            waitForVisibility(getTarget((String) target));
            waitForClickable(getTarget((String) target));
            Target targetElement = getTarget((String) target);
            return Task.where("{0} waits for and clicks on selector",
                    WaitFor.waitUntil(targetElement, Constants.STATES.VISIBLE.getState()),
                    WaitFor.waitUntil(targetElement, Constants.STATES.CLICKABLE.getState()),
                    Click.on(targetElement)
            );
        }
        throw new IllegalArgumentException("Invalid target type: " + target.getClass().getSimpleName());
    }

    /**
     * Performs a right-click on the given target element.
     *
     * @param target The target element to right-click on.
     */
    public static void rightClickOnTarget(String target) {
        performAttemptsTo("{0} attempts to right-click on target", createRightClickActionFor(target));
    }

    /**
     * Creates a ClickInteraction object based on the type of the given target for a right-click action.
     *
     * @param target The target to create the ClickInteraction for.
     * @return The ClickInteraction object.
     */
    public static Task createRightClickActionFor(String target) {
        waitForVisibility(target);
        waitForClickable(target);

        Target targetElement = getTarget(target);

        return Task.where("{0} waits for and right-clicks on selector",
                WaitFor.waitUntil(targetElement, Constants.STATES.VISIBLE.getState()),
                WaitFor.waitUntil(targetElement, Constants.STATES.CLICKABLE.getState()),
                RightClick.on(targetElement) // Right-click action
        );
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
                .resolveAllFor(theActorInTheSpotlight()); //resolveAllFor uses implicit wait
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

        performAttemptsTo("{0} waits for table to be visible", WaitFor.waitUntil(board,Constants.STATES.VISIBLE.getState()));

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

    /**
     * Performs a hover (mouse over) action on the given target element.
     *
     * @param target The target element to hover over.
     */
    public static void hoverOverTarget(String target) {
        Target targetElement = getTarget(target);
        waitFor(targetElement, Constants.STATES.PRESENT.getState());
        waitFor(targetElement, Constants.STATES.VISIBLE.getState());
        waitFor(targetElement, Constants.STATES.CLICKABLE.getState());
        performAttemptsTo("{0} attempts to hover over target", Task.where("{0} hovers over target", MoveMouse.to(targetElement)));
    }

}
