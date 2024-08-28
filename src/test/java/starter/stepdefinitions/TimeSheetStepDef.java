package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static starter.Constants.*;
import static starter.tasks.ElementDataVerifier.verifyElementTextIs;
import static starter.tasks.ElementInteraction.clickOnTarget;

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
}
