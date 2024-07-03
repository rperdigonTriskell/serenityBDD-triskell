package starter.selectors.pages;

import org.openqa.selenium.By;
import starter.selectors.factory.AbstractPage;

import static starter.Constants.*;
import java.util.Map;

public class SaucedemoYourCartPage extends AbstractPage {
    @Override
    public Map mapSelectors() {
        mapSelectors.put(YOURCART, By.id("cart_contents_container"));
        mapSelectors.put("checkout", By.id("checkout"));
        return mapSelectors;
    }
}
