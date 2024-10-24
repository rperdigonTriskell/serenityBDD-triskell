package starter.stepdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.targets.Target;
import starter.tasks.DragAndDropByCoordinates;

import static starter.Constants.TOOLBAR_CONTEXT;
import static starter.stepdefinitions.GenericStepDef.clickIn;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitFor.*;

public class AutomationTestingProjectGantChartStepDef {
    @When("drag and drop {string} to {int} days")
    public static void dragAndDropTo(String target, int space) {
        Target reference = getTarget("day");
        Target source = getTarget(target);
        waitForVisibility(source);
        performAttemptsTo("{0} hover the element",
                MoveMouse.to(source)
        );
        performAttemptsTo(
                "{0} drag and drop {1} to {2}",
                DragAndDropByCoordinates.from(source,reference).bySteps(space)
        );
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in toolbar {string}")
    public static void clickInToolbar(String element) {
        clickIn(TOOLBAR_CONTEXT + element);
    }
}