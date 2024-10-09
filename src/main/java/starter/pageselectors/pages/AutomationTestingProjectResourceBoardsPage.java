package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class AutomationTestingProjectResourceBoardsPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_RESOURCE_BOARDS, By.xpath(linkXpathText("Resources")));
        mapSelectors.put("Resources", By.xpath(linkXpathText("Resources")));
        mapSelectors.put("Requeriments", By.xpath(linkXpathText("Requirements")));

        //2 boards to Assigment
        mapSelectors.put("Resources Board Assignments name", By.xpath("((//div[contains(@id, 'schedulergrid')])[3]/div/div[1]//td[1]/div/div[1])[1]"));
        mapSelectors.put("Resources board Assignments data", By.xpath("(//div[contains(@id, 'schedulergrid')])[3]/div/div[2]"));
        //2 boards to Requirements
        mapSelectors.put("Resources Board Requirements name", By.cssSelector("table[id*='gridview-'] tr > td:nth-of-type(2) span"));
        mapSelectors.put("Resources board Requirements data", By.xpath("(//table[contains(@id, 'schedulergrid')]//tr/td)[2]"));
        return mapSelectors;
    }
}