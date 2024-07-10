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
        mapSelectors.put("LOGINI", By.name("login-button"));
        mapSelectors.put("useri", By.id("user-name"));
        mapSelectors.put("passi", By.id("password"));

        return mapSelectors;
    }
}
