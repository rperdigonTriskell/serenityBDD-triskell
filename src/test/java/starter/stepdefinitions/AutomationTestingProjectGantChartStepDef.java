package starter.stepdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

import static starter.Constants.*;
import static starter.stepdefinitions.GenericStepDef.*;
import static starter.tasks.ElementInteraction.*;
import static starter.tasks.WaitElement.*;
import static starter.tasks.utils.LaboralHours.*;

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
        for (int i = currentDay; i < daysInCalendar.size(); i++) {
            int dayNumber = Integer.parseInt(daysInCalendar.get(i).getText());
            if (dayNumber == targetDay) {
                clickOnTarget(daysInCalendar.get(i));
                return;
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