package starter.selectors.factory;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPage extends PageObject implements SelectorFactory {

    /**
     * Map to hold selector names and their corresponding By objects.
     */
    public Map<String, By> mapSelectors = new HashMap<String, By>();

    /**
     * Returns a map of selector mappings.
     * @return Map of selector mappings.
     */
    public abstract Map<String, By> mapSelectors();

    /**
     * Retrieves the By object for the given selector name.
     * @param selector the name of the selector.
     * @return the By object corresponding to the selector name.
     */
    public By getSelector(String selector) {
        return mapSelectors().get(selector);
    }


}
