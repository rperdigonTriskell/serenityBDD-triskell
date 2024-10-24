package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;
import starter.tasks.VerifyElementVisibility;
import starter.tasks.WaitFor;

import java.util.List;

import static starter.Constants.*;
import static starter.stepdefinitions.AutomationTestingProjectStepDef.*;
import static starter.stepdefinitions.GenericStepDef.*;
import static starter.tasks.ElementDataVerifier.*;
import static starter.tasks.ElementInteraction.*;
import static starter.tasks.GenericTasks.*;
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
        verifyTheElementAre(PROJECT_CONTEXT + element, visibility);
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
    public static void verifyFollowingProjectElementsOnTheShouldMatchTheExpectedData(String context, DataTable dataTable) {
        verifyFollowingElementsOnTheShouldMatchTheExpectedData(PROJECT_CONTEXT + context, dataTable);
    }

    /**
     * Handles the table state based on the given table state and action.
     *
     * @param tableState the state of the table ("empty" or "not empty")
     * @param action     the action to perform ("add" or "delete")
     */
    @When("if the table Project {string}, {string} an Project")
    public void ifTheTableIsAnProject(String tableState, String action) {
        inputAndEnter("Automation Testing Project", PROJECT_CONTEXT + "search parent");

        waitLoading();

        Target projectTable = getTarget(PROJECT_CONTEXT + "empty project board");
        performAttemptsTo("{0}", WaitFor.waitUntil(projectTable, STATES.PRESENT.getState()));

        List<WebElementFacade> rows = getTableRows(projectTable);

        if (tableState.equals(EMPTY)) {
            if (rows.isEmpty() && ADD.equals(action)) {
                addProject();
            } else {
                deleteAllProject();
                addProject();
            }
        } else if (tableState.equals(NOT_EMPTY)) {
            if (!rows.isEmpty() && DELETE.equals(action)) {
                deleteAllProject();
            }
        }
    }

    public static void addProject() {
        clickInProject("add Project");
        verifyTheElementProjectAre("Create New Project", "visible");
        sendTextAndEnterToProjectElement("Automation Testing Project", "Name input");
        clickInProject("Choose Parent button");
        verifyTheElementProjectAre("Add Object as New Parent", "visible");
        sendTextAndEnterToProjectElement("testing", "search parent");
        clickInProject("testing");
        verifyTheElementProjectAre("Create New Project", "visible");
        clickInProject("save");
        checkToHasLoaded("Automation Testing Project");
    }

    public static void deleteAllProject() {
        clickInProject("Automation Testing Project checkbox");
        clickInProject("delete");
        verifyTheElementProjectAre("delete anwser", "visible");
        clickInProject("yes");
    }
}