package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class DashboardPage extends AbstractPage {
    /**
     * Returns a Dashboard map of selectors for different elements on the page.
     *
     * @return a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(DASHBOARD, By.id("APP_HOME_BUTTON"));
        //sidebar
        mapSelectors.put(SIDEBAR_CONTEXT + HOME_PAGE, By.id("APP_HOME_BUTTON"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Solutions", By.cssSelector("a[data-qtip='Solutions']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Portfolio", By.cssSelector("a[data-qtip='Portfolio']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Project", By.cssSelector("a[data-qtip='Project']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Agile Task", By.cssSelector("a[data-qtip='Agile Task']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Request", By.cssSelector("a[data-qtip='Request']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Product", By.cssSelector("a[data-qtip='Product']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Sprint", By.cssSelector("a[data-qtip='Sprint']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Issue", By.cssSelector("a[data-qtip='Issue']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Department", By.cssSelector("a[data-qtip='Department']"));
        mapSelectors.put(SIDEBAR_CONTEXT + TIMESHEET, By.cssSelector("a[data-qtip='Timesheet']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Reports", By.cssSelector("a[data-qtip='Reports']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Strategic Plan", By.cssSelector("a[data-qtip='Strategic Plan']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Strategic Objective", By.cssSelector("a[data-qtip='Strategic Objective']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Risk", By.cssSelector("a[data-qtip='Risk']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Task", By.cssSelector("a[data-qtip='Task']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Testing Comp", By.cssSelector("a[data-qtip='Testing Comp']"));
        mapSelectors.put(SIDEBAR_CONTEXT + "Favorites", By.xpath("//div/span"+xpathText("Favorites")));
        mapSelectors.put(SIDEBAR_CONTEXT + "IT (Team 1)", By.xpath("//span"+xpathText("IT (Team 1)")));
        //header
        mapSelectors.put(HEADING_CONTEXT + "Sidebar icon", By.id("toggle-menu-btn"));
        mapSelectors.put(HEADING_CONTEXT + "Search", By.name("searchParameter"));
        mapSelectors.put(HEADING_CONTEXT + "Create", By.xpath("//span[text()='Create ...']/ancestor::a"));
        mapSelectors.put(HEADING_CONTEXT + "Configuration icon", By.cssSelector("a[data-qtip='Go to Configuration Environment']"));
        mapSelectors.put(HEADING_CONTEXT + "Supersede icon", By.cssSelector("a[data-qtip='Supersede a User']"));
        mapSelectors.put(HEADING_CONTEXT + "Notifications icon", By.cssSelector("a[data-qtip='Supersede a User'] ~ a:has(svg)"));
        mapSelectors.put(HEADING_CONTEXT + "User icon", By.cssSelector("a:has(div.avatar.user-image)"));
        mapSelectors.put(HEADING_CONTEXT + HOME_PAGE, By.xpath("//a[.//span[contains(@class, 'x-tab-inner x-tab-inner-center') and text()='Home page']]"));
        mapSelectors.put(HEADING_CONTEXT + "Activity Feed", By.xpath(linkXpathText("Activity Feed")));
        mapSelectors.put(HEADING_CONTEXT + "Resource Costs Budget", By.xpath("//a[.//span[text()='Resource Costs Budget']]"));
        mapSelectors.put(HEADING_CONTEXT + "Dashboard 1", By.xpath("//a[.//span[text()='Dashboard 1']]"));
        mapSelectors.put(HEADING_CONTEXT + "+", By.cssSelector("a[data-qtip='Add Dashboard']"));
        //dashboard
        mapSelectors.put(DASHBOARD_CONTEXT + "My Items", By.cssSelector("div.x-panel.homepage-mmyitems-panel.x-panel-default-framed.x-box-item"));
        mapSelectors.put(DASHBOARD_CONTEXT + "Favorites", By.xpath("//span[text()='Favorites']/ancestor::a[contains(@id, 'tab')]"));
        mapSelectors.put(DASHBOARD_CONTEXT + "Last Modified", By.xpath("//span[text()='Last Modified']/ancestor::a"));
        mapSelectors.put(DASHBOARD_CONTEXT + "Subscriptions", By.xpath("//span[text()='Subscriptions']/ancestor::a"));
        mapSelectors.put(DASHBOARD_CONTEXT + FAVORITES + BOARD_SUFFIX, By.xpath("//table[.//div[text()='Department (1 Item)']]"));
        mapSelectors.put(DASHBOARD_CONTEXT + LAST_MODIFIED + BOARD_SUFFIX, By.xpath("//a[.//span[text()='Last Week']]"));
        mapSelectors.put(DASHBOARD_CONTEXT + SUBSCRIPTIONS + BOARD_SUFFIX, By.cssSelector("div[class='x-panel centering-icons x-fit-item x-panel-default x-grid']"));
        mapSelectors.put(DASHBOARD_CONTEXT + "My Alerts and Notifications", By.cssSelector("div[class='x-panel homepage-alerts-panel x-panel-default-framed x-box-item']"));
        mapSelectors.put(DASHBOARD_CONTEXT + "My Calendar View", By.cssSelector("div[class='x-panel homepage-calendar-panel x-panel-default-framed x-box-item']"));
        return mapSelectors;
    }
}