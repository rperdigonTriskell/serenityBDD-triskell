package starter.selectors.factory;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import starter.selectors.pages.*;

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
        if (element == null) {
            throw new IllegalArgumentException("WebElementFacade cannot be null.");
        }

        // Obtener la representación en string del WebElementFacade
        String elementToString = element.toString();
        // Extraer el tipo y el valor del selector del string
        String[] parts = elementToString.split(": ", 2);

        if (parts.length == 2) {
            String selectorType = parts[0].trim().toLowerCase(); // Convertir a minúsculas para la comparación
            String selectorValue = parts[1].trim();

            // Eliminar el prefijo "by." si está presente
            if (selectorType.startsWith("by.")) {
                selectorType = selectorType.substring(3); // Eliminar "by."
            }

            Function<String, By> byFunction = LOCATOR_MAP.get(selectorType);
            if (byFunction != null) {
                return byFunction.apply(selectorValue);
            } else {
                throw new IllegalArgumentException("Unsupported locator type: " + selectorType);
            }
        }

        throw new IllegalArgumentException("Unrecognized format of WebElementFacade.toString(): " + elementToString);
    }
    public static String getWebElementSelectorValue(WebElementFacade element) {
        if (element == null) {
            throw new IllegalArgumentException("WebElementFacade cannot be null.");
        }

        // Obtener la representación en string del WebElementFacade
        String elementToString = element.toString();
        // Extraer el tipo y el valor del selector del string
        String[] parts = elementToString.split(": ", 2);

        if (parts.length == 2) {
            return parts[1].trim();
        }

        throw new IllegalArgumentException("Unrecognized format of WebElementFacade.toString(): " + elementToString);
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