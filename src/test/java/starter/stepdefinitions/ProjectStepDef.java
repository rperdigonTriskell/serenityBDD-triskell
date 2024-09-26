package starter.stepdefinitions;

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
    @When("click in project {string}")
    public static void clickInProject(String element) {
        clickOnTarget(PROJECT_CONTEXT + element);
    }


    /**
     * Verifies the visibility of an element on the timesheet board.
     *
     * @param element    the element to verify the visibility of
     * @param visibility the expected visibility of the element (either "visible" or "not visible")
     */
    @Then("verify the element project {string} are {string}")
    public static void verifyTheElementProjectAre(String element, String visibility) {
        verifyElementVisibility(PROJECT_CONTEXT + element, visibility);
    }

    /**
     * Sends text to an element.
     *
     * @param text    the text to send
     * @param element the element to send the text to
     */
    @When("send text project {string} to element {string}")
    public static void sendTextToProjectElement(String text, String element) {
        sendTextToElement(text, PROJECT_CONTEXT + element);
    }

    /**
     * Sends text to an element.
     *
     * @param element the element to send the text to
     */
    @When("send enter project to element {string}")
    public static void sendEnterToProjectElement(String element) {
        sendEnterToElement(PROJECT_CONTEXT + element);
    }

    /**
     * Verifies the text of an element.
     *
     * @param element the element to verify
     * @param text    the expected text of the element
     */
    @Then("verify the text element project board {string} is {string}")
    public void verifyTheTextElementIs(String element, String text) {
        verifyElementTextIs(PROJECT_CONTEXT + element, text);
    }


}