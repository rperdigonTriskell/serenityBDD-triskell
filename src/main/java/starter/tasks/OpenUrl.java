package starter.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.WebDriverException;

import static starter.pageselectors.factory.PageFactory.getStaticDriver;

public class OpenUrl implements Task {

    private final String url;
    private final boolean shouldRecord;

    public OpenUrl(String url, boolean shouldRecord) {
        this.url = url;
        this.shouldRecord = shouldRecord;
    }

    public static Performable valid(String url) {
        return Tasks.instrumented(OpenUrl.class, url, true);
    }

    public static Performable invalid(String url, boolean shouldRecord) {
        return Tasks.instrumented(OpenUrl.class, url, shouldRecord);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            getStaticDriver().get(url);
        } catch (WebDriverException e) {
            if (e.getMessage().contains("ERR_NAME_NOT_RESOLVED")) {
                if (shouldRecord) {
                    Serenity.recordReportData()
                            .asEvidence()
                            .withTitle("Expected Behavior")
                            .andContents("Attempted to navigate to an invalid URL: " + url
                                    + ". Expected behavior: Name not resolved error.");
                }
            } else {
                throw e;
            }
        }
    }
}
