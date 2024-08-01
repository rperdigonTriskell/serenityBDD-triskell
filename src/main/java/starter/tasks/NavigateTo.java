package starter.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import org.openqa.selenium.WebDriverException;

import static starter.tasks.security.CredentialManager.getCredential;

public class NavigateTo {

    /**
     * Navigates the specified actor to the given website URL.
     *
     * @param url   the URL of the website to navigate to
     * @param actor the name of the actor performing the navigation
     */
    public static void theWebSite(String url, String actor) {
        OnStage.theActorCalled(actor).attemptsTo(Open.url(getCredential(url, false)));
    }


    /**
     * Attempts to navigate the specified actor to an invalid URL to trigger an error.
     *
     * @param url   the invalid URL to navigate to
     * @param actor the name of the actor performing the navigation
     */
    public static void theWrongWebSite(String url, String actor) {
        try {
            OnStage.theActor(actor);
            Serenity.getDriver().get(url);
        } catch (WebDriverException e) {
            if (e.getMessage().contains("ERR_NAME_NOT_RESOLVED")) {
                Serenity.recordReportData()
                        .asEvidence()
                        .withTitle("Expected Behavior")
                        .andContents("Attempted to navigate to an invalid URL: " + url
                                + ". Expected behavior: Name not resolved error.");
            } else {
                // For other exceptions, re-throw to ensure they are recorded as errors
                throw e;
            }
        }
    }
}
