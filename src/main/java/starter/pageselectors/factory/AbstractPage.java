package starter.pageselectors.factory;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPage extends PageObject implements SelectorFactory {

    /**
     * Map to hold selector names and their corresponding By objects.
     */
    protected Map<String, By> mapSelectors = new HashMap<String, By>();

    /**
     * Constructor to initialize common selectors.
     */
    public AbstractPage() {
        mapSelectors.put("loading", By.id("loading-container"));
        mapSelectors.put("Saving", By.xpath(".//div[text()='Saving...']"));
    }

    /**
     * Returns a map of selector mappings.
     * @return Map of selector mappings.
     */
    public abstract Map<String, By> mapSelectors();

    /**
     * Retrieves the By object for the given selector name.
     * Throws an exception if the selector is not found.
     * @param selector the name of the selector.
     * @return the By object corresponding to the selector name.
     */
    public By getSelector(String selector) {
        By foundSelector = mapSelectors().get(selector);
        if (foundSelector == null) {
            throw new NoSuchElementException("Selector '" + selector + "' not found.");
        }
        return foundSelector;
    }

    public static String xpathText(String xpathSearch) {
        return String.format("[text()='%s']", xpathSearch);
    }

    public static String xpathContainsText(String xpathSearch) {
        return String.format("[contains(text(), '%s')]", xpathSearch);
    }

    public static String linkXpathText(String xpathSearch) {
        return String.format("//a[.//span%s]", xpathText(xpathSearch));
    }
}
