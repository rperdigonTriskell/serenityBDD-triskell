package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class ManualTestingProjectPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(MANUAL_TESTING_PROJECT, By.xpath(".//span[text()='Manual Testing Project' and @class='tk-object-title']"));
        mapSelectors.put("Gantt Chart", By.cssSelector("a[id*=GanttButton]"));
        mapSelectors.put("OK", By.cssSelector("button.b-messagedialog-okbutton"));
        return mapSelectors;
    }
}