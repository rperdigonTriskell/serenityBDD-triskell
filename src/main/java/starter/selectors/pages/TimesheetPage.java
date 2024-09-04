package starter.selectors.pages;

import org.openqa.selenium.By;
import starter.selectors.factory.AbstractPage;

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
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " activity board", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[1]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " time board", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[2]"));
        //board timesheet
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " title", By.xpath("//div[span[contains(text(),'My Timesheet from') and (@class ='x-panel-header-text-container-default timeTitleDefault')]]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Add Activities", By.cssSelector("a[data-qtip='Add activities']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Delete", By.cssSelector("a[data-qtip='Remove from Timesheet']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Generate", By.cssSelector("a[data-qtip='Generate']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Comment", By.cssSelector("a[data-qtip='History & Comments']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Supersede a User", By.cssSelector("div[id*='toolbar'] > a[data-qtip='Supersede a User']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Path Breakdown Structure", By.xpath("//div[contains(@id, 'toolbar')]//a[@data-qtip='Supersede a User']/following-sibling::a[1]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Save as Default View", By.xpath("//a[.//span[text()='Save as Default View']]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Manage grid density", By.cssSelector("a[data-qtip='Manage grid density']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Legend", By.cssSelector("a[data-qtip='Timesheet legend']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Configuration", By.cssSelector("a[data-qtip='Change Configuration']"));
        mapSelectors.put("row 1", By.cssSelector("a[data-qtip='Change Configuration']"));
        //board timesheet
        mapSelectors.put("Add Object To Timesheet", By.xpath("(//div[span[text()='Add object to timesheet']])[1]"));
        mapSelectors.put("Search", By.name("search"));
        mapSelectors.put("Search icon", By.xpath("//a[.//span[@class='x-btn-icon-el fas fa-search ']]"));
        mapSelectors.put("MAPRE Portfolio Task 1 Checkbox", By.xpath("(//span[contains(text(), 'Task 1')]/preceding-sibling::input)[1]"));
        mapSelectors.put("Integration_Triskell Task 2 Checkbox", By.xpath("(//span[contains(text(), 'Task 2')]/preceding-sibling::input)[1]"));
        mapSelectors.put("Integration_Triskell Task 6 Checkbox", By.xpath("(//span[contains(text(), 'Task 6')]/preceding-sibling::input)[1]"));
        mapSelectors.put("Add & Close", By.xpath("(//a[.//span[text()='Add & Close']])[1]"));
        mapSelectors.put("Close", By.xpath("(//a[.//span[text()='Close']])[1]"));
        //options
        mapSelectors.put(TIMESHEET_CONTEXT + "Yes", By.xpath("//a[.//span[text()='Yes']]"));
        mapSelectors.put("OK", By.xpath("//a[.//span[text()='OK']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "all activities checkbox", By.xpath("//div[contains(@id, 'gridcolumn-') and contains(@id, '-titleEl') and @role='presentation' and contains(@class, 'x-column-header-inner') and contains(@class, 'x-column-header-inner-empty')]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Delete", By.cssSelector("a[data-qtip='Remove from Timesheet']"));
        mapSelectors.put("Failed message", By.cssSelector("div[role='textbox'][id*='messagebox']"));

        return mapSelectors;
    }
}