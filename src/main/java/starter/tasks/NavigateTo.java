package starter.tasks;

import net.serenitybdd.screenplay.Performable;
import static starter.tasks.security.CredentialManager.getCredential;

public class NavigateTo {

    /**
     * Navigates the specified actor to the given website URL.
     *
     * @param url   the URL of the website to navigate to
     */
    public static Performable theWebSite(String url) {
        return OpenUrl.valid(getCredential(url, false));
    }

    /**
     * Attempts to navigate to an invalid URL to trigger an error.
     *
     * @param url the invalid URL to navigate to
     */
    public static Performable theWrongWebSite(String url) {
        return OpenUrl.invalid(url, true);
    }

    /**
     * Attempts to navigate to an invalid URL without recording the navigation in Serenity.
     *
     * @param url the invalid URL to navigate to
     */
    public static Performable theWrongWebSiteWithoutRecording(String url) {
        return OpenUrl.invalid(url, false);
    }
}
