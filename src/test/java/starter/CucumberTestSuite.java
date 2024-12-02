package starter;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        features = {"src/test/resources/features"},
//        tags = "@PROD or @AWS",
        tags = "@toDo",
        glue = "starter.stepdefinitions"
)
public class CucumberTestSuite {
}
