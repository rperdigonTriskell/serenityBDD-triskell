package starter.stepdefinitions;

import io.cucumber.java.en.*;

import static starter.Constants.*;
import static starter.tasks.ElementDataVerifier.verifyElementTextIs;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.ElementVisibilityVerifier.verifyElementVisibility;

public class AutomationTestingProjectStepDef {
    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in Automation Testing Project sidebar {string}")
    public void clickInSidebar(String element) {
        clickOnTarget(AUTOMATION_TESTING_PROJECT_CONTEXT + element);
    }

    /**
     * Verifies the visibility of an element on the timesheet board.
     *
     * @param element    the element to verify the visibility of
     * @param visibility the expected visibility of the element (either "visible" or "not visible")
     */
    @Then("verify the element Automation Testing Project {string} are {string}")
    public static void verifyTheElementProjectAre(String element, String visibility) {
        verifyElementVisibility(AUTOMATION_TESTING_PROJECT_CONTEXT + element, visibility);
    }
}