package starter.selectors.pages;

import org.openqa.selenium.By;
import starter.selectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.HOME_PAGE;

public class HomePage extends AbstractPage {
    @Override
    public Map mapSelectors() {
        mapSelectors.put(HOME_PAGE, By.id("APP_HOME_BUTTON"));
        return mapSelectors;
    }
}
