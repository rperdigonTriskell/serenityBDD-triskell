package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class AutomationTestingProjectPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(AUTOMATION_TESTING_PROJECT, By.xpath(".//span[text()='Automation Testing Project' and @class='tk-object-title']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + SIDEBAR_CONTEXT + "Main Menu", By.cssSelector("a[data-qtip='Main menu']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + SIDEBAR_CONTEXT + "Project", By.cssSelector("a[data-qtip='Project']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + "Project", By.cssSelector("a[data-qtip='Project']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + "Search in Grid", By.name("search"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + "project board", By.name("search"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + "Resource Boards", By.xpath("//a[.//span[text()='Resource Boards']]"));
        mapSelectors.put("Overview table", By.xpath("(//table[contains(@class, 'x-table-layout') and contains(@id, 'ext-gen')])[1]"));
        mapSelectors.put("OK", By.xpath("//a[.//span[text()='OK']]"));
        return mapSelectors;
    }
}