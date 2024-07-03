package starter.selectors.factory;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPage implements SelectorFactory {
    public Map<String, By> mapSelectors = new HashMap<String, By>();

    public abstract Map<String, By> mapSelectors();

    public By getSelector(String selector) {
        return mapSelectors().get(selector.toLowerCase());
    }
}
