package starter.selectors.pages;

import org.openqa.selenium.By;
import starter.selectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.*;

public class LoginPage extends AbstractPage {
    @Override
    public Map mapSelectors() {
        mapSelectors.put(LOGIN, By.cssSelector("fieldset[id*='fieldset']"));
        mapSelectors.put("username", By.name("username"));
        mapSelectors.put("password", By.name("password"));
        mapSelectors.put("validate", By.cssSelector("a[id*='button']"));
        return mapSelectors;
    }
}
