package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class AddResourcesPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(ADD_RESOURCES, By.xpath("//a[.//span[text()='Resources']]"));
        mapSelectors.put("empty resource board", By.xpath("(//div[contains (@id, 'gridview-') and .//table[contains(@id, 'gridview-')]//colgroup])[2]"));
        mapSelectors.put("search", By.name("search"));
//        mapSelectors.put("Close", By.xpath("(//a[.//span[text()='Close']])[2]"));
        mapSelectors.put("Close", By.xpath("(//span[text()='Close'])[2]"));
        mapSelectors.put("System Administrator checkbox", By.xpath("//td[contains(@class, 'row-checker')]//div"));
        mapSelectors.put("Add Resource", By.xpath("//a[.//span[text()='Add Resource']]"));
        mapSelectors.put("loading", By.id("loading-container"));
        return mapSelectors;
    }
}