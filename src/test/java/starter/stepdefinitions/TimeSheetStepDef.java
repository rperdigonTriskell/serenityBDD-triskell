package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static starter.Constants.*;
import static starter.selectors.factory.PageFactory.getCurrentPage;
import static starter.tasks.ElementDataVerifier.verifyElementTextIs;
import static starter.tasks.ElementDataVerifier.verifyRowData;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.GenericTasks.getTableRows;
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

    @When("if there are activities created they are deleted")
    public void ifThereAreActivitiesCreatedTheyAreDeleted() {
        WebElementFacade table = waitElementPresent(getCurrentPage().$(getCurrentPage().getSelector(TIMESHEET + BOARD_SUFFIX + " " + ACTIVITY + BOARD_SUFFIX)), true);
        List<WebElementFacade> rows = getTableRows(table);
        WebElementFacade addActivities = waitElementPresent(getCurrentPage().$(getCurrentPage().getSelector(TIMESHEET + BOARD_SUFFIX + " Add Activities")), true);
        if (!rows.isEmpty()) {
            clickInTimesheet("all activities checkbox");
            clickInTimesheet("Delete");
            clickInTimesheet("Yes");
            waitElementVisible(addActivities,true);
            waitElementVisible(table,false);
        }
    }

//    if there are activities created they are deleted
//
//    Given go to web Triskell
//    Then check to "Login" has loaded
//    When send credential "username" to element "Username"
//    And send credential "password" to element "Password"
//    And click in "Validate"
//    Then check to "Dashboard" has loaded
//    When click in sidebar "Timesheet"
//    Then check to "Timesheet" has loaded
//    When click in timesheet "Timesheet"
//    Then verify the element "Timesheet board Add Activities" are "visible"
//    When if there are activities created they are deleted
//    Then verify the text element timesheet board "activity board" is ""
//
//
//    @PROD
//    Scenario: Add a New Activity
//    When click in timesheet board "Add Activities"
//    Then verify the element "Add Object To Timesheet" are "visible"
//    When send text "Task 1" to element "Search"
//    And click in "Search icon"
//    And click in "MAPRE Portfolio Task 1 Checkbox"
//    And click in "Add & Close"
//    Then verify the element "Add Object To Timesheet" are "not present"
//    And verify the element "Timesheet board activity board" are "visible"
//    And verify the following elements on the "Timesheet board activity board" should match the expected data:
//            | Check | WORK APP | RES. APP | PATH                                                | PARENT           | OBJECT | OBJECT TYPE | PLANNED | TOTAL |
//            |       |          |          | Project Management/MAPRE Portfolio/Development Plan | Development Plan | Task 1 | Task        |         |       |

}
