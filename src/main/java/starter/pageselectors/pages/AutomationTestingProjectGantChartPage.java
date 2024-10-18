package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.AUTOMATION_TESTING_PROJECT_GANTT_CHART;

public class AutomationTestingProjectGantChartPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_GANTT_CHART, By.id("b-gantt-1"));
        mapSelectors.put("Gantt Chart", By.id("b-gantt-1"));
        mapSelectors.put("Selected item: Automation Testing Project", By.cssSelector("div[id*='b-popup-'][class*='b-widget']"));
        mapSelectors.put("Selected item: Automation Testing Project name", By.cssSelector("div[id*='b-popup-'][class*='b-widget'] > header>div"));
        mapSelectors.put("Selected item: Automation Testing Task", By.cssSelector("div[id*='b-popup-'][class*='b-widget']"));
        mapSelectors.put("Selected item: Automation Testing Task name", By.cssSelector("div[id*='b-popup-'][class*='b-widget'] > header>div"));
        mapSelectors.put("Name", By.cssSelector("input[name='name']"));
        mapSelectors.put("Create", By.xpath("//button[.//label[text()='Create']]"));
        mapSelectors.put("Save", By.xpath("(//button[.//label[text()='Save']])[2]"));
        mapSelectors.put("Task", By.xpath(".//div[@data-column='objectName' and text()='Task']"));
        mapSelectors.put("Name 1rt row", By.xpath("(//div[@data-column='name'])[2]"));
        mapSelectors.put("Name 2rt row", By.xpath("(//div[@data-column='name'])[3]"));
        mapSelectors.put("Name 3rt row", By.xpath("(//div[@data-column='name'])[4]"));
        mapSelectors.put("Name 3rt row date", By.cssSelector("div.b-gantt-task.b-sch-event-resizable-true"));
        mapSelectors.put("task options", By.cssSelector("div[data-owner-cmp='b-menu-1'][role='menu']"));
        mapSelectors.put("Edit", By.cssSelector("div[data-ref='editTask']"));
        mapSelectors.put("Copy", By.cssSelector("div[data-ref='copy']"));
        mapSelectors.put("Cut", By.cssSelector("div[data-ref='cut']"));
        mapSelectors.put("Paste", By.cssSelector("div[data-ref='paste']"));
        mapSelectors.put("Filter", By.cssSelector("div[data-ref='filterMenu']"));
        mapSelectors.put("Indent", By.cssSelector("div[data-ref='indent']"));
        mapSelectors.put("Outdent", By.cssSelector("div[data-ref='outdent']"));
        mapSelectors.put("Add dependencies", By.cssSelector("div[data-ref='linkTasks']"));
        mapSelectors.put("Remove dependencies", By.cssSelector("div[data-ref='unlinkTasks']"));
        mapSelectors.put("Add item...", By.cssSelector("div[data-ref='addItem']"));
        mapSelectors.put("Remove", By.cssSelector("div[data-ref='delete']"));
        mapSelectors.put("Sub Item", By.xpath("(.//input[@name='itemType'])[2]"));
        return mapSelectors;
    }
}