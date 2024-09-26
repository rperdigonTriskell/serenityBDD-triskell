package starter.pageselectors.pages;

import org.openqa.selenium.By;
import starter.pageselectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class LoginPage extends AbstractPage {
    /**
     * Returns a Login map of selectors for different elements on the page.
     *
     * @return         a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(LOGIN, By.cssSelector("fieldset[id*='fieldset']"));
        mapSelectors.put("bad url", By.id("main-frame-error"));
        mapSelectors.put("bad url folder", By.id("article"));
        mapSelectors.put("retry", By.cssSelector("#article > div > p:nth-child(2) > a"));
        mapSelectors.put("Username", By.name("username"));
        mapSelectors.put("Password", By.name("password"));
        mapSelectors.put("Validate", By.cssSelector("a[id*='button']"));
        mapSelectors.put("Error", By.cssSelector("table[id*='messagebox']:first-of-type"));
        mapSelectors.put("OK", By.cssSelector("div[id*='messagebox'] :first-of-type > a:first-of-type"));
        mapSelectors.put("X", By.cssSelector("div[id*='messagebox'] :first-of-type > div[id*='tool']:first-of-type"));
        return mapSelectors;
    }
}
