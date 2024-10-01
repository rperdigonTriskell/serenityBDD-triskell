package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class TimesheetPage extends AbstractPage {
    /**
     * Returns a Timesheet map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(TIMESHEET, By.xpath("//div[contains(@id, 'tabbar')]//a[.//span[text()='Timesheet']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "My Timesheet", By.cssSelector("a[data-qtip='My Timesheet']"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Project Manager Approval", By.cssSelector("a[data-qtip='Project Manager Approval']"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Administrator Approval", By.cssSelector("a[data-qtip='Administrator Approval']"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Timesheet Summary", By.xpath("//a[.//span[text()='Timesheet Summary']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + TIMESHEET, By.xpath("//div[contains(@id, 'tabbar')]//a[.//span[text()='Timesheet']]"));
        //board summary
        mapSelectors.put(TIMESHEET_SUMMARY + BOARD_SUFFIX + " title", By.xpath("//div[span[contains(text(),'Timesheet Summary from')]]"));
        mapSelectors.put(TIMESHEET_SUMMARY + BOARD_SUFFIX + " Supersede a User", By.cssSelector("div[id*='toolbar'] > a[data-qtip='Supersede a User']"));
        mapSelectors.put(TIMESHEET_SUMMARY + BOARD_SUFFIX + " Save as Default View", By.xpath("//a[.//span[text()='Save as Default View']]"));
        //boards inside
        mapSelectors.put(TIMESHEET_BOARD + "activity board", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[1]//tbody"));
        mapSelectors.put(TIMESHEET_BOARD + "activity board empty", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[1]"));
        mapSelectors.put(TIMESHEET_BOARD + "time board", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[2]//tbody"));
        mapSelectors.put(TIMESHEET_BOARD + "time board empty", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[2]"));
        //board timesheet
        mapSelectors.put(TIMESHEET_BOARD + "title", By.xpath("//div[span[contains(text(),'My Timesheet from') and (@class ='x-panel-header-text-container-default timeTitleDefault')]]"));
        mapSelectors.put(TIMESHEET_BOARD + "Add Activities", By.cssSelector("a[data-qtip='Add activities']"));
        mapSelectors.put(TIMESHEET_BOARD + "Delete", By.cssSelector("a[data-qtip='Remove from Timesheet']"));
        mapSelectors.put(TIMESHEET_BOARD + "Generate", By.cssSelector("a[data-qtip='Generate']"));
        mapSelectors.put(TIMESHEET_BOARD + "Comment", By.cssSelector("a[data-qtip='History & Comments']"));
        mapSelectors.put(TIMESHEET_BOARD + "Supersede a User", By.cssSelector("div[id*='toolbar'] > a[data-qtip='Supersede a User']"));
        mapSelectors.put(TIMESHEET_BOARD + "Path Breakdown Structure", By.xpath("//div[contains(@id, 'toolbar')]//a[@data-qtip='Supersede a User']/following-sibling::a[1]"));
        mapSelectors.put(TIMESHEET_BOARD + "Save as Default View", By.xpath("//a[.//span[text()='Save as Default View']]"));
        mapSelectors.put(TIMESHEET_BOARD + "Manage grid density", By.cssSelector("a[data-qtip='Manage grid density']"));
        mapSelectors.put(TIMESHEET_BOARD + "Legend", By.cssSelector("a[data-qtip='Timesheet legend']"));
        mapSelectors.put(TIMESHEET_BOARD + "Configuration", By.cssSelector("a[data-qtip='Change Configuration']"));
        mapSelectors.put(TIMESHEET_BOARD + "Submit Timesheet", By.xpath("//a[.//span[text()='Submit Timesheet']]"));
        //Add activity timesheet
        mapSelectors.put("Add Object To Timesheet", By.xpath("(//div[span[text()='Add object to timesheet']])[1]"));
        mapSelectors.put("Search", By.name("search"));
        mapSelectors.put("Search icon", By.xpath("//a[.//span[@class='x-btn-icon-el fas fa-search ']]"));
        mapSelectors.put("MAPRE Portfolio Automation Test Task Checkbox", By.xpath("(//input[@class=' x-tree-checkbox'])[1]"));
        mapSelectors.put("Integration_Triskell Task 2 Checkbox", By.xpath("(//span[contains(text(), 'Task 2')]/preceding-sibling::input)[1]"));
        mapSelectors.put("Integration_Triskell Task 6 Checkbox", By.xpath("(//span[contains(text(), 'Task 6')]/preceding-sibling::input)[1]"));
        mapSelectors.put("Add & Close", By.xpath("(//a[.//span[text()='Add & Close']])[1]"));
        //options
        mapSelectors.put(TIMESHEET_CONTEXT + "Yes", By.xpath("//a[.//span[text()='Yes']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "OK", By.xpath("//a[.//span[text()='OK']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Close", By.xpath("(//a[.//span[text()='Close']])[1]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "all activities checkbox", By.xpath("//div[@class='x-column-header-inner x-column-header-inner-empty']"));
        mapSelectors.put("Failed message", By.cssSelector("div[role='textbox'][id*='messagebox']"));
        mapSelectors.put("Question message", By.cssSelector("div[role='textbox'][id*='messagebox']"));
        mapSelectors.put("Warning message", By.cssSelector("div[role='textbox'][id*='messagebox']"));
        //Timesheet Submit
        mapSelectors.put(TIMESHEET_CONTEXT + TIMESHEET_CONTEXT + "Submit", By.xpath("(//div[contains(@id, 'tkBodyWindow')])[1]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Submit", By.xpath("//a[.//span[text()='Submit']]"));

        mapSelectors.put("loading", By.id("loading-container"));

        return mapSelectors;
    }
}