package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.tasks.VerifyElementVisibility;

import static starter.Constants.*;
import static starter.tasks.ElementDataVerifier.verifyElementTextIs;
import static starter.tasks.ElementInteraction.clickOnBard;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.stepdefinitions.TimesheetTasks.*;
import static starter.tasks.GenericTasks.performAttemptsTo;

public class TimeSheetStepDef {
    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in timesheet {string}")
    public static void clickInTimesheet(String element) {
        clickOnTarget(TIMESHEET_CONTEXT + element);
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in timesheet board {string}")
    public void clickInTimesheetBoard(String element) {
        clickOnTarget(TIMESHEET_BOARD + element);
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
        verifyElementTextIs(TIMESHEET_BOARD + element, text);
    }

    /**
     * Handles the table state based on the given table state and action.
     *
     * @param tableState the state of the table ("empty" or "not empty")
     * @param action     the action to perform ("add" or "delete")
     */
    @When("if the table is {string}, {string} an activity")
    public void ifTheTableIsAnActivity(String tableState, String action) {
        manageTimesheetTable(tableState, action);
    }

    /**
     * Verifies the visibility of an element on the timesheet board.
     *
     * @param  element  the element to verify the visibility of
     * @param  visibility  the expected visibility of the element (either "visible" or "not visible")
     */
    @Then("verify the element timesheet {string} are {string}")
    public static void verifyTheElementTimesheetAre(String element, String visibility) {
        performAttemptsTo("{0} verify the element timesheet {1} are {2}",new VerifyElementVisibility(TIMESHEET_CONTEXT + element,visibility));
    }

    /**
     * Verifies the visibility of an element on the timesheet board.
     *
     * @param  element  the element to verify the visibility of
     * @param  visibility  the expected visibility of the element (either "visible" or "not visible")
     */
    @Then("verify the element timesheet board {string} are {string}")
    public static void verifyTheElementTimesheetBoardAre(String element, String visibility) {
        performAttemptsTo("{0} verify the element timesheet board {1} are {2}",new VerifyElementVisibility(TIMESHEET_BOARD + element,visibility));
    }

    /**
     * Clicks on a checkbox element in the activity board.
     *
     * @param  element  the name of the checkbox element to click on
     */
    @When("click in activity board checkbox {string}")
    public void clickInActivityBoard(String element) {
        clickOnBard(TIMESHEET_BOARD + ACTIVITY_BOARD, CHECKBOX , element);
    }

}
