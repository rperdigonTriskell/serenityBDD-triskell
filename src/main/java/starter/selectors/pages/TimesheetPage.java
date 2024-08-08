package starter.selectors.pages;

import org.openqa.selenium.By;
import starter.selectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class TimesheetPage extends AbstractPage {
    /**
     * Returns a Home map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(TIMESHEET_CONTEXT, By.cssSelector("//div[contains(@id, 'tabbar')]//a[.//span[text()='Timesheet']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + "My Timesheet", By.cssSelector("a[data-qtip='My Timesheet']"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Project Manager Approval", By.cssSelector("a[data-qtip='Project Manager Approval']"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Administrator Approval", By.cssSelector("a[data-qtip='Administrator Approval']"));
        mapSelectors.put(TIMESHEET_CONTEXT + "Timesheet Summary", By.xpath("//a[.//span[text()='Timesheet Summary']]"));
        mapSelectors.put(TIMESHEET_CONTEXT + TIMESHEET, By.xpath("//div[contains(@id, 'tabbar')]//a[.//span[text()='Timesheet']]"));
        //board
        mapSelectors.put(TIMESHEET_SUMMARY + BOARD_SUFFIX + " title", By.xpath("//div[span[contains(text(),'Timesheet Summary from')]]"));
        mapSelectors.put(TIMESHEET_SUMMARY + BOARD_SUFFIX + " Supersede a User", By.cssSelector("div[id*='toolbar'] > a[data-qtip='Supersede a User']"));
        mapSelectors.put(TIMESHEET_SUMMARY + BOARD_SUFFIX + " Save as Default View", By.xpath("//a[.//span[text()='Save as Default View']]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " title", By.xpath("//div[span[contains(text(),'My Timesheet from') and (@class ='x-panel-header-text-container-default timeTitleDefault')]]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " activity board", By.xpath("(//div[@class='x-grid-view x-fit-item x-grid-view-default x-unselectable'])[1]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " time board", By.xpath("(//div[@class='x-grid-view x-fit-item x-grid-view-default x-unselectable'])[2]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Add Activities", By.xpath("a[data-qtip='Add activities']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Delete", By.xpath("a[data-qtip='Remove from Timesheet']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Generate", By.xpath("a[data-qtip='Generate']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Comment", By.xpath("a[data-qtip='History & Comments']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Supersede a User", By.xpath("div[id*='toolbar'] > a[data-qtip='Supersede a User']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Path Breakdown Structure", By.xpath("//div[contains(@id, 'toolbar')]//a[@data-qtip='Supersede a User']/following-sibling::a[1]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Save as Default View", By.xpath("//a[.//span[text()='Save as Default View']]"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Manage grid density", By.xpath("a[data-qtip='Manage grid density']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Legend", By.xpath("a[data-qtip='Timesheet legend']"));
        mapSelectors.put(TIMESHEET + BOARD_SUFFIX + " Configuration", By.xpath("a[data-qtip='Change Configuration']"));
        return mapSelectors;
    }
}