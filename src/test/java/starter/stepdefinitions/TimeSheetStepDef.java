package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

import static starter.Constants.*;
import static starter.selectors.factory.PageFactory.getCurrentPage;
import static starter.stepdefinitions.GenericStepDef.clickIn;
import static starter.stepdefinitions.GenericStepDef.sendTextToElement;
import static starter.tasks.ElementDataVerifier.verifyElementTextIs;
import static starter.tasks.ElementInteraction.clickOnBard;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.ElementVisibilityVerifier.verifyElementVisibility;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitInteractions.*;

public class TimeSheetStepDef {
    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in timesheet {string}")
    public void clickInTimesheet(String element) {
        clickOnTarget(TIMESHEET_CONTEXT + element);
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in timesheet board {string}")
    public void clickInTimesheetBoard(String element) {
        clickOnTarget(TIMESHEET + BOARD_SUFFIX + " " + element);
    }

    /**
     * Verifies the text of an element.
     *
     * @param element the element to verify
     * @param text    the expected text of the element
     */
    @Then("verify the text element timesheet {string} is {string}")
    public void verifyTheTextElementTimesheetIs(String element, String text) {
        verifyElementTextIs(TIMESHEET_CONTEXT + element, text);
    }

    /**
     * Verifies the text of an element.
     *
     * @param element the element to verify
     * @param text    the expected text of the element
     */
    @Then("verify the text element timesheet board {string} is {string}")
    public void verifyTheTextElementIs(String element, String text) {
        verifyElementTextIs(TIMESHEET + BOARD_SUFFIX + " " + element, text);
    }

    /**
     * Handles the table state based on the given table state and action.
     *
     * @param tableState the state of the table ("empty" or "not empty")
     * @param action     the action to perform ("add" or "delete")
     */
    @When("if the table is {string}, {string} an activity")
    public void handleTableState(String tableState, String action) {
        WebElementFacade table = waitElementPresent(getCurrentPage().$(getCurrentPage().getSelector(TIMESHEET + BOARD_SUFFIX + " " + ACTIVITY + BOARD_SUFFIX)), true);
        List<WebElementFacade> rows = getTableRows(table);
        WebElementFacade addActivities = waitElementPresent(getCurrentPage().$(getCurrentPage().getSelector(TIMESHEET + BOARD_SUFFIX + " Add Activities")), true);
        if ("empty".equals(tableState)) {
            if (rows.isEmpty() && "add".equals(action)) {
                clickIn("Add Activities");
                waitElementVisible(getWebelementFacade("Add Object To Timesheet"), true);
                sendTextToElement("Task 1", "Search");
                clickInTimesheet("Search icon");
                waitElementVisible(getWebelementFacade("MAPRE Portfolio Task 1 Checkbox"), true);
                clickInTimesheet("MAPRE Portfolio Task 1 Checkbox");
                clickInTimesheet("Add & Close");
                waitElementVisible(getWebelementFacade("Add Object To Timesheet"), false);
            }
        } else if ("not empty".equals(tableState)) {
            if (!rows.isEmpty() && "delete".equals(action)) {
                clickInTimesheet("all activities checkbox");
                clickInTimesheet("Delete");
                clickInTimesheet("Yes");
                waitElementVisible(addActivities, true);
                waitElementVisible(table, false);
            }
        }
    }

    /**
     * Verifies the visibility of an element on the timesheet board.
     *
     * @param  element  the element to verify the visibility of
     * @param  visibility  the expected visibility of the element (either "visible" or "not visible")
     */
    @Then("verify the element timesheet board {string} are {string}")
    public static void verifyTheElementAre(String element, String visibility) {
        verifyElementVisibility(TIMESHEET + BOARD_SUFFIX + " " + element, visibility);
    }

    /**
     * Clicks on a checkbox element in the activity board.
     *
     * @param  element  the name of the checkbox element to click on
     */
    @When("click in activity board checkbox {string}")
    public void clickInActivityBoard(String element) {
        clickOnBard(TIMESHEET + BOARD_SUFFIX + " " + ACTIVITY_BOARD, CHECKBOX , element);
    }
}
