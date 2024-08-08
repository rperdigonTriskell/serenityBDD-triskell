package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static starter.Constants.*;
import static starter.tasks.ElementInteraction.clickOnTarget;
import static starter.tasks.ElementVisibilityVerifier.dataTableAreVisible;
import static starter.tasks.ElementVisibilityVerifier.verifyElementTextIs;
import static starter.tasks.IsLoad.isLoadPage;
import static starter.tasks.IsLoad.isNotLoadPage;
import static starter.tasks.NavigateTo.*;
import static starter.selectors.factory.PageFactory.getDriverStatic;
import static starter.tasks.SendTextTo.*;
import static starter.tasks.security.CredentialManager.getCredential;
import static starter.tasks.security.EnvironmentManager.getBaseUrl;

public class GenericStepDef {
    // Actor for scenario
    String actor = "user not logged";
    String baseUrl;

    /**
     * Sets the stage before each scenario.
     */
    @Before
    public void setTheStage(Scenario scenario) {
        OnStage.setTheStage(new OnlineCast());
        Set<String> scenarioTags = new HashSet<>(scenario.getSourceTagNames());
        for (String tag : scenarioTags) {
            try {
                baseUrl = getBaseUrl(tag);
                if (baseUrl != null) {
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println("No URL found for tag: " + tag + ", checking next tag.");
            }
        }

        if (baseUrl == null) {
            throw new RuntimeException("No matching environment URL found for scenario tags.");
        }
    }

    /**
     * Navigates to a web page.
     *
     * @param url the URL of the web page
     */
    @Given("go to web {string}")
    public void goToWeb(String url) {
        theWebSite(url, actor);
    }

    /**
     * Navigates to system environment web page.
     */
    @Given("go to web Triskell")
    public void goToTriskell() {
        theWebSite(baseUrl, actor);
    }


    /**
     * Navigates to a web page.
     *
     * @param url the URL of the web page
     */
    @Given("go to wrong web {string}")
    public void goToWrongWeb(String url) {
        theWrongWebSite(url, actor);
    }

    /**
     * Navigates to a web page with a domain.
     *
     * @param url    the URL of the web page
     * @param domain the domain to append to the URL
     */
    @Given("go to web {string} with domain {string}")
    public void goToWebWithDomain(String url, String domain) {
        theWebSite(url + getCredential(domain, false), actor);
    }

    /**
     * Navigates to a web page with a domain.
     *
     * @param url    the URL of the web page
     * @param domain the domain to append to the URL
     */
    @Given("go to wrong web {string} with domain {string}")
    public void goToWrongWebWithDomain(String url, String domain) {
        url = url.replace("domain", getCredential(domain, false));
        theWrongWebSite(url, actor);
    }

    /**
     * Navigates to a web page with a XSS Atack.
     *
     * @param url the URL of the web page
     */
    @Given("go to wrong web {string} with XSS atack")
    public void goToWrongWebWithXSSAtack(String url) {
        theWrongWebSite(url + "<script>alert('XSS')</script>", actor);
    }

    /**
     * Navigates to a web page with a domain.
     *
     * @param url    the URL of the web page
     * @param domain the domain to append to the URL
     */
    @Given("go to wrong web {string} with domain {string} and XSS atack")
    public void goToWrongWebWithDomainAndXSSAtack(String url, String domain) {
        url = url.replace("domain", getCredential(domain, false));
        theWrongWebSite(url + "<script>alert('XSS')</script>", actor);
    }

    /**
     * Checks if a page has loaded.
     *
     * @param page the name of the page to check
     */
    @Then("check to {string} has loaded")
    public void checkToHasLoaded(String page) {
        isLoadPage(page);
    }

    /**
     * Checks if elements in the given context are visible or invisible.
     *
     * @param context   the context in which to check the elements
     * @param dataTable the DataTable containing the elements to check
     */
    @Then("check to the following {string} elements are:")
    public void checkFollowingElementsAre(String context, DataTable dataTable) {
        dataTableAreVisible(context + " ", dataTable);
    }


    /**
     * Checks if a page hasn't loaded.
     *
     * @param page the name of the page to check
     */
    @Then("check to {string} hasn't loaded")
    public void checkToHasntLoaded(String page) {
        isNotLoadPage(page);
    }

    /**
     * Verifies the text of an element.
     *
     * @param element the element to verify
     * @param text    the expected text of the element
     */
    @Then("verify the text element {string} is {string}")
    public void verifyTheTextElementIs(String element, String text) {
        verifyElementTextIs(element, text);
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in {string}")
    public void clickIn(String element) {
        clickOnTarget(element);
    }

    /**
     * Sends text to an element.
     *
     * @param text    the text to send
     * @param element the element to send the text to
     */
    @When("send text {string} to element {string}")
    public void sendTextToElement(String text, String element) {
        input(text, element);
    }

    /**
     * Sends a credential to an element.
     *
     * @param text    the credential to send
     * @param element the element to send the credential to
     */
    @When("send credential {string} to element {string}")
    public void sendCredentialToElement(String text, String element) {
        credential(text, element);
    }

    /**
     * Sends text to a table.
     *
     * @param table the table to send the text to
     */
    @When("send text to:")
    public void sentTextToTable(DataTable table) {
        table(table);
    }

    /**
     * Sets the stage after each scenario.
     */
    @After
    public void finish() {
        try {
            if (Serenity.getDriver() != null) {
                getDriverStatic().quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Serenity.getWebdriverManager().closeAllDrivers();
            try {
                // Close chromedriver process
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe"); // Windows
                // Close chrome process
                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe"); // Windows
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
