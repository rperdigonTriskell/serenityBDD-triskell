package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.AUTOMATION_TESTING_PROJECT_GANT_CHART;
import static starter.Constants.AUTOMATION_TESTING_PROJECT_RESOURCE_BOARDS;

public class AutomationTestingProjectGantChartPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_GANT_CHART, By.id("b-gantt-1"));
        mapSelectors.put("Selected item: Automation Testing Project", By.id("b-popup-1"));
        mapSelectors.put("Name", By.id("b-popup-1"));
        mapSelectors.put("Save", By.id("b-button-22"));
        mapSelectors.put("Name 1rt row", By.xpath("(//div[@data-column='name'])[2]"));
        mapSelectors.put("Name 2rt row", By.xpath("(//div[@data-column='name'])[3]"));
        return mapSelectors;
    }
}