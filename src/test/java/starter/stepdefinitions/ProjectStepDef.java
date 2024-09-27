package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static starter.Constants.*;
import static starter.stepdefinitions.GenericStepDef.*;
import static starter.tasks.ElementDataVerifier.*;
import static starter.tasks.ElementInteraction.*;
import static starter.tasks.ElementVisibilityVerifier.*;
import static starter.tasks.SendTextTo.*;

public class ProjectStepDef {
    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in Project {string}")
    public static void clickInProject(String element) {
        clickOnTarget(PROJECT_CONTEXT + element);
    }


    /**
     * Verifies the visibility of an element on the timesheet board.
     *
     * @param element    the element to verify the visibility of
     * @param visibility the expected visibility of the element (either "visible" or "not visible")
     */
    @Then("verify the element Project {string} are {string}")
    public static void verifyTheElementProjectAre(String element, String visibility) {
        verifyElementVisibility(PROJECT_CONTEXT + element, visibility);
    }

    /**
     * Sends text to an element.
     *
     * @param text    the text to send
     * @param element the element to send the text to
     */
    @When("send text Project {string} to element {string}")
    public static void sendTextToProjectElement(String text, String element) {
        sendTextToElement(text, PROJECT_CONTEXT + element);
    }

    /**
     * Sends text to an element.
     *
     * @param element the element to send the text to
     */
    @When("send text and enter Project {string} to element {string}")
    public static void sendTextAndEnterToProjectElement(String text, String element) {
        inputAndEnter(text, PROJECT_CONTEXT + element);
    }

    /**
     * Verifies the text of an element.
     *
     * @param element the element to verify
     * @param text    the expected text of the element
     */
    @Then("verify the text element Project board {string} is {string}")
    public void verifyTheTextElementIs(String element, String text) {
        verifyElementTextIs(PROJECT_CONTEXT + element, text);
    }

    /**
     * Verifies that the elements on the specified context match the expected data.
     *
     * @param context   the context in which to verify the elements
     * @param dataTable the DataTable containing the expected data for the elements
     */
    @Then("verify the following Project elements on the {string} should match the expected data:")
    public void verifyFollowingProjectElementsOnTheShouldMatchTheExpectedData(String context, DataTable dataTable) {
        verifyFollowingElementsOnTheShouldMatchTheExpectedData(PROJECT_CONTEXT + context, dataTable);
    }
}