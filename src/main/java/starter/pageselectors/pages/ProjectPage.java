package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class ProjectPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(PROJECT, By.cssSelector("table[id*='gridview']"));
        mapSelectors.put(PROJECT_CONTEXT + "add Project", By.xpath("(//a[.//span[text()='Project']])[2]"));
        mapSelectors.put(PROJECT_CONTEXT + "Create New Project", By.xpath("(//div[contains(@id, 'tkBodyWindow')])[1]"));
       //
        mapSelectors.put(PROJECT_CONTEXT + "Name input", By.name("ppname"));
        mapSelectors.put(PROJECT_CONTEXT + "parent imput", By.name("pp_par"));
        mapSelectors.put(PROJECT_CONTEXT + "Choose Parent button", By.xpath("//a[.//span[contains(text(), 'Choose parent ...')]]"));
        mapSelectors.put(PROJECT_CONTEXT + "Add Object as New Parent", By.xpath(".//span[text()='Add Object as New Parent']"));
        mapSelectors.put(PROJECT_CONTEXT + "search parent", By.name("search"));
        mapSelectors.put(PROJECT_CONTEXT + "testing", By.xpath("//td[.//div[text()='testing']]"));
        mapSelectors.put(PROJECT_CONTEXT + "save", By.xpath("//a[.//span[text()='Save']]"));
        mapSelectors.put(PROJECT_CONTEXT + "Automation Testing Project", By.xpath(".//span[text()=' Automation Testing Project' and @class='tk-object-title']"));
        //
        mapSelectors.put(PROJECT_CONTEXT + "Search in Grid", By.name("search"));
        return mapSelectors;
    }
}