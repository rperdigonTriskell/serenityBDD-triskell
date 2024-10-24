package starter.stepdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.targets.Target;
import starter.tasks.DragAndDropBetweenElements;
import starter.tasks.DragAndDropToElements;

import static starter.Constants.*;
import static starter.stepdefinitions.GenericStepDef.waitLoading;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitFor.waitForVisibility;

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

    @When("drag and drop Requirements date System Administrator data to {string} weeks")
    public static void  dragAndDropRequirementsDateToWeeks(String space) {
        Target source =
                Target.the("source")
                        .locatedBy("(//div[@class='x-grid-cell-inner '])[6]");

        waitForVisibility(source);

        Target destination =
                Target.the("source")
                        .locatedBy("(//tr[contains(@id, 'schedulergrid')]//div[contains(@class, 'x-grid-cell-inner')])[2]//div[{1}]")
                        .of(space, space);

        performAttemptsTo(
                "{0} drag and drop {1} to {2}",
                DragAndDropBetweenElements.from(source, destination)
        );

        waitLoading();
    }
}