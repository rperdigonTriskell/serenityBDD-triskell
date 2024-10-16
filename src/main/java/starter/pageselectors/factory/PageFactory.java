package starter.pageselectors.factory;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import starter.pageselectors.pages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.openqa.selenium.support.PageFactory.initElements;
import static starter.Constants.*;

public class PageFactory extends PageObject {

    /**
     * Map associating page names with their corresponding instances.
     */
    private static final Map<String, AbstractPage> map = new HashMap<String, AbstractPage>() {{
        put(LOGIN, initElements(getStaticDriver(), LoginPage.class));
        put(BAD_URL, initElements(getStaticDriver(), LoginPage.class));
        put(BAD_URL_FOLDER, initElements(getStaticDriver(), LoginPage.class));
        put(DASHBOARD, initElements(getStaticDriver(), DashboardPage.class));
        put(TIMESHEET, initElements(getStaticDriver(), TimesheetPage.class));
        put(PROJECT, initElements(getStaticDriver(), ProjectPage.class));
        put(AUTOMATION_TESTING_PROJECT, initElements(getStaticDriver(), AutomationTestingProjectPage.class));
        put(AUTOMATION_TESTING_PROJECT_RESOURCE_BOARDS, initElements(getStaticDriver(), AutomationTestingProjectResourceBoardsPage.class));
        put(AUTOMATION_TESTING_PROJECT_GANTT_CHART, initElements(getStaticDriver(), AutomationTestingProjectGantChartPage.class));
        put(ADD_RESOURCES, initElements(getStaticDriver(), AddResourcesPage.class));
    }};

    /**
     * The currently selected page.
     */
    private static AbstractPage currentPage;

    /**
     * Gets the current page that is selected.
     *
     * @return The currently selected page.
     */
    public static AbstractPage getCurrentPage() {
        return currentPage;
    }

    /**
     * Gets the name of the current page that is selected.
     *
     * @return The name of the currently selected page.
     */
    public static String getCurrentPageName() {
        for (Map.Entry<String, AbstractPage> entry : map.entrySet()) {
            if (entry.getValue().equals(currentPage)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Sets the current page based on the provided page name.
     *
     * @param page Name of the page to set as the current page.
     */
    public static void setCurrentPage(String page) {
        currentPage = map.get(page);
    }

    /**
     * Gets a static instance of the WebDriver.
     *
     * @return Static instance of WebDriver.
     */
    public static WebDriver getStaticDriver() {
        return new PageFactory().getDriver();
    }

    /**
     * Gets a By object from a WebElement using the information from its toString().
     *
     * @param element WebElement from which to obtain the By selector.
     * @return By object corresponding to the WebElement's selector.
     * @throws IllegalArgumentException If the toString() format is not recognized.
     */
    public static By getWebElementSelector(WebElementFacade element) {
        String elementToString = element.toString();

        // Sample toString() output: "By.cssSelector: div.classname"
        if (elementToString.contains("By.cssSelector")) {
            String selector = extractSelector(elementToString, "By.cssSelector:");
            return By.cssSelector(selector);
        }
        // Sample toString() output: "By.id: elementId"
        else if (elementToString.contains("By.id")) {
            String selector = extractSelector(elementToString, "By.id:");
            return By.id(selector);
        }
        // Sample toString() output: "By.xpath: //div[@class='classname']"
        else if (elementToString.contains("By.xpath")) {
            String selector = extractSelector(elementToString, "By.xpath:");
            return By.xpath(selector);
        }
        // Add more cases for other locator strategies as needed
        else {
            throw new IllegalArgumentException("Unrecognized WebElementFacade toString() format: " + elementToString);
        }
    }

    /**
     * Extracts the selector part from the toString() output of a WebElementFacade.
     *
     * @param toStringOutput The toString() output of a WebElementFacade.
     * @param prefix The prefix identifying the locator strategy.
     * @return The extracted selector string.
     */
    private static String extractSelector(String toStringOutput, String prefix) {
        int startIndex = toStringOutput.indexOf(prefix) + prefix.length();
        return toStringOutput.substring(startIndex).trim();
    }

    /**
     * A map that associates locator types with functions that take a string parameter and return a By object.
     *
     * @see By
     */
    public static final Map<String, Function<String, By>> LOCATOR_MAP = new HashMap<String, Function<String, By>>() {{
        put("id", By::id);
        put("xpath", By::xpath);
        put("css selector", By::cssSelector);
        put("tag name", By::tagName);
        put("name", By::name);
        put("class name", By::className);
        put("link text", By::linkText);
        put("partial link text", By::partialLinkText);
    }};
}