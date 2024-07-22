package starter.selectors.pages;

import org.openqa.selenium.By;
import starter.selectors.factory.AbstractPage;

import java.util.Map;

import static starter.Constants.DASHBOARD;

public class DashboardPage extends AbstractPage {
    /**
     * Returns a Home map of selectors for different elements on the page.
     *
     * @return         a map containing selectors for various elements
     */
    @Override
    public Map mapSelectors() {
        mapSelectors.put(DASHBOARD, By.id("APP_HOME_BUTTON"));
        return mapSelectors;
    }
}
