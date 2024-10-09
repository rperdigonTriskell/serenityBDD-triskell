package starter.stepdefinitions;

import io.cucumber.java.en.*;

import static starter.Constants.*;
import static starter.tasks.ElementInteraction.clickOnTarget;

public class AutomationTestingProjectResourceBoardsStepDef {
    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in Automation Testing Project Resource Boards {string}")
    public static void clickInAutomationTestingProject(String element) {
        clickOnTarget(AUTOMATION_TESTING_PROJECT_CONTEXT + element);
    }
}