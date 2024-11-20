package starter.stepdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

import static starter.Constants.TOOLBAR_CONTEXT;
import static starter.stepdefinitions.GenericStepDef.clickIn;
import static starter.tasks.WaitElement.*;
import static starter.tasks.utils.LaboralHours.calculateLaboralDaysFromStartDay;

public class AutomationTestingProjectGantChartStepDef {
    @When("click in {int} days more")
    public static void clickOnDayAfterLaboralDays(int daysToMove) {
        // Get the calendar and active day elements
        Target calendar = getWaitVisibleTarget("calendar");
        Target activeDay = getWaitVisibleTarget("active day");

        // Get the current day as an integer
        int currentDay = Integer.parseInt(activeDay.resolveFor(getActor()).getText());

        // Calculate the target business day
        int targetDay = calculateLaboralDaysFromStartDay(currentDay, daysToMove);

        // Retrieve the list of calendar days
        List<WebElementFacade> daysInCalendar = calendar.resolveFor(getActor())
                .thenFindAll(getWaitVisibleSelector("days calendar"));

        // Find and click the target day
        for (WebElementFacade dayElement : daysInCalendar) {
            int dayNumber = Integer.parseInt(dayElement.getText());
            if (dayNumber == targetDay) {
                dayElement.click();
                return; // Stop after clicking
            }
        }

        // If the target day is not found, throw an error
        throw new RuntimeException("The target day was not found in the calendar");
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