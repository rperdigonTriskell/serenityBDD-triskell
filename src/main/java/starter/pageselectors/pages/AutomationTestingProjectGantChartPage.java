package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

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
        mapSelectors.put(TOOLBAR_CONTEXT + "Create", By.xpath("//button[.//label[text()='Create']]"));
        mapSelectors.put(TOOLBAR_CONTEXT + "Save", By.cssSelector("button.b-widget.b-button.b-icon-align-start.b-blue.b-text"));
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
        mapSelectors.put("Name 3rt Assigned Resources", By.xpath("(.//div[@data-column='assignments'])[4]"));
        mapSelectors.put("Name 3rt Assigned Resources filled", By.xpath("(//div[@data-column='assignments'])[4]//li"));
        mapSelectors.put("Name 3rt Assigned Resources down arrow", By.cssSelector("div.b-icon-down[data-ref='expand']"));
        mapSelectors.put("select resource project", By.cssSelector("div.b-vbox.b-box-center.b-panel-body-wrap.b-assignmentpicker-body-wrap"));
        mapSelectors.put("System Administrator checkbox resource project", By.cssSelector("div[class='b-grid-cell b-check-cell b-widget-cell b-checkbox-selection b-grid-cell-align-center']"));
        mapSelectors.put("Save select resource", By.cssSelector("button[class='b-widget b-button b-green b-text b-box-item b-first-visible-child']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + SIDEBAR_CONTEXT + "Main Menu", By.cssSelector("a[data-qtip='Main menu']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT  + TIMESHEET, By.cssSelector("a[data-qtip='Timesheet']"));
        mapSelectors.put(AUTOMATION_TESTING_PROJECT_CONTEXT + SIDEBAR_CONTEXT + TIMESHEET, By.cssSelector("a[data-qtip='Timesheet']"));
        mapSelectors.put("time bar 3rt row", By.xpath("(//div[@data-task-bar-feature='percentBar'])[3]"));
        mapSelectors.put("time bar 3rt row modal duration", By.cssSelector("div[class='b-hbox b-box-center b-auto-container-panel b-panel-body-wrap b-tooltip-body-wrap'] tbody>tr:nth-child(3)>td:nth-child(2)"));
        mapSelectors.put("day", By.xpath("(//div[@data-tick-index='0'])[4]"));
        return mapSelectors;
    }
}