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
        mapSelectors.put(PROJECT, By.xpath("("+linkXpathText("Project")+")[2]"));
        mapSelectors.put(PROJECT_CONTEXT + "add Project", By.xpath("("+linkXpathText("Project")+")[2]"));
        mapSelectors.put(PROJECT_CONTEXT + "Create New Project", By.xpath("(//div[contains(@id, 'tkBodyWindow')])[1]"));
        //
        mapSelectors.put(PROJECT_CONTEXT + "Name input", By.name("ppname"));
        mapSelectors.put(PROJECT_CONTEXT + "parent imput", By.name("pp_par"));
        mapSelectors.put(PROJECT_CONTEXT + "Choose Parent button", By.xpath("//a[.//span[contains(text(), 'Choose parent ...')]]"));
        mapSelectors.put(PROJECT_CONTEXT + "Add Object as New Parent", By.xpath(".//span[text()='Add Object as New Parent']"));
        mapSelectors.put(PROJECT_CONTEXT + "search parent", By.name("search"));
        mapSelectors.put(PROJECT_CONTEXT + "testing", By.xpath("//td[.//div[text()='testing']]"));
        mapSelectors.put(PROJECT_CONTEXT + "save", By.xpath(linkXpathText("Save")));
        mapSelectors.put(PROJECT_CONTEXT + "Automation Testing Project", By.xpath(".//span[text()=' Automation Testing Project' and @class='tk-object-title']"));
        //
        mapSelectors.put(PROJECT_CONTEXT + "Search in Grid", By.name("search"));

        mapSelectors.put(PROJECT_CONTEXT + "project board", By.xpath("(//table[.//strong[contains(text(), 'Automation Testing Project')]])[1]//tbody"));
        mapSelectors.put(PROJECT_CONTEXT + "empty project board", By.xpath("(//div[contains(@id, 'grid-')])[2]"));

        //
        mapSelectors.put(PROJECT_CONTEXT + "all activities checkbox", By.xpath("(//span[contains(@id, 'gridcolumn')])[1]"));
        mapSelectors.put(PROJECT_CONTEXT + "Automation Testing Project checkbox", By.xpath("(//tr[.//strong[text()='Automation Testing Project']])[2]//td[1]//div//div"));
        mapSelectors.put(PROJECT_CONTEXT + "delete", By.cssSelector("a[data-qtip='Remove']"));
        mapSelectors.put(PROJECT_CONTEXT + "delete anwser", By.xpath("(//div[contains(@id, 'messagebox')])[1]"));
        mapSelectors.put(PROJECT_CONTEXT + "yes", By.xpath("//a[.//span[text()='Yes']]"));

        //
        mapSelectors.put(PROJECT_CONTEXT + "arrow", By.cssSelector("span.fa-arrow-right"));

        return mapSelectors;
    }
}