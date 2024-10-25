package starter.stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.AfterClass;
import starter.Constants;
import starter.tasks.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static starter.Constants.*;
import static starter.stepdefinitions.TimesheetTasks.*;
import static starter.tasks.ElementDataVerifier.*;
import static starter.tasks.ElementInteraction.*;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.GenericTasks.performAttemptsTo;
import static starter.tasks.IsLoad.*;
import static starter.tasks.NavigateTo.*;
import static starter.pageselectors.factory.PageFactory.*;
import static starter.tasks.SendTextTo.*;
import static starter.tasks.WaitFor.*;
import static starter.tasks.security.CredentialManager.*;
import static starter.tasks.security.EnvironmentManager.*;

public class GenericStepDef {
    /**
     * Other utilities
     */
    // Actor for scenario
    String actor = getCredential("username", false);
    String baseUrl;

    /**
     * Sets the stage before each scenario.
     */
    @Before
    public void setTheStage(Scenario scenario) {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(actor);
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
     * Sets the stage after each scenario.
     */
    @AfterClass
    public static void finish() {
        try {
            if (getStaticDriver() != null) {
                getStaticDriver().quit();
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

    /**
     * Generic steps definitions
     * */

    /**
     * Navigates to a web page.
     *
     * @param url the URL of the web page
     */
    @Given("go to web {string}")
    public void goToWeb(String url) {
        performAttemptsTo("{0} attempts navigate to ",theWebSite(url));
    }

    /**
     * Navigates to system environment web page.
     */
    @Given("go to web Triskell")
    public void goToTriskell() {
        performAttemptsTo("{0} attempts navigate to ",theWebSite(baseUrl));
    }


    /**
     * Navigates to a web page.
     *
     * @param url the URL of the web page
     */
    @Given("go to wrong web {string}")
    public void goToWrongWeb(String url) {
        performAttemptsTo("{0} attempts navigate to ",theWrongWebSite(url));
    }

    /**
     * Navigates to a web page with a domain.
     *
     * @param url    the URL of the web page
     * @param domain the domain to append to the URL
     */
    @Given("go to web {string} with domain {string}")
    public void goToWebWithDomain(String url, String domain) {
        performAttemptsTo("{0} attempts navigate to ",theWebSite(url + getCredential(domain, false)));
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
        performAttemptsTo("{0} attempts navigate to ",theWrongWebSite(url));
    }

    /**
     * Navigates to a web page with a XSS Atack.
     *
     * @param url the URL of the web page
     */
    @Given("go to wrong web {string} with XSS atack")
    public void goToWrongWebWithXSSAtack(String url) {
        performAttemptsTo("{0} attempts navigate to ",theWrongWebSite(url + "<script>alert('XSS')</script>"));
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
        performAttemptsTo("{0} attempts navigate to ",theWrongWebSite(url + "<script>alert('XSS')</script>"));
    }

    /**
     * Checks if a page has loaded.
     *
     * @param page the name of the page to check
     */
    @Then("check to {string} has loaded")
    public static void checkToHasLoaded(String page) {
        isLoadPage(page);
    }

    /**
     * Verifies the visibility of an element.
     *
     * @param element the element to verify
     * @param visibility the expected visibility of the element
     */
    @Then("verify the element {string} are {string}")
    public static void verifyTheElementAre(String element, String visibility) {
        waitFor(element,visibility);
        performAttemptsTo("{0} verify the element {1} are {2}", new VerifyElementVisibility(element,visibility));
    }

    /**
     * Checks if elements in the given context are visible or invisible.
     *
     * @param context   the context in which to check the elements
     * @param dataTable the DataTable containing the elements to check
     */
    @Then("check to the following {string} elements are:")
    public void checkFollowingElementsAre(String context, DataTable dataTable) {
        performAttemptsTo("{0} check to the following {1} elements are:",VerifyTableElementsVisibility.verifyElementsVisibility(context,dataTable));
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
     * Verifies the text of the specified elements matches the expected values.
     *
     * @param dataTable the DataTable containing the elements and expected values
     */
    @Then("verify that the text of the specified elements matches the expected values:")
    public void verifyThatTheTextOfTheSpecifiedElementsMatchesTheExpectedValues(DataTable dataTable) {
        verifyDatatableElementsTextIs(dataTable);
    }

    /**
     * Verifies that the elements on the specified context match the expected data.
     *
     * @param  context   the context in which to verify the elements
     * @param  dataTable the DataTable containing the expected data for the elements
     */
    @Then("verify the following elements on the {string} should match the expected data:")
    public static void verifyFollowingElementsOnTheShouldMatchTheExpectedData(String context, DataTable dataTable) {
        // Process the DataTable into a list of maps
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        WebElementFacade element = getWebelementFacade(context);
        element.waitForCondition().until(driver -> element.isVisible());

        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitFor.waitUntil(context, Constants.STATES.VISIBLE.getState()),
                 new VerifyTableElements(context, expectedData)
        );
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in {string}")
    public static void clickIn(String element) {
        clickOnTarget(element);
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("left click in {string}")
    public static void leftClickIn(String element) {
        rightClickOnTarget(element);
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("click in sidebar {string}")
    public static void clickInSidebar(String element) {
        clickOnTarget(SIDEBAR_CONTEXT + element);
    }

    /**
     * Sends text to an element.
     *
     * @param text    the text to send
     * @param element the element to send the text to
     */
    @When("send text {string} to element {string}")
    public static void sendTextToElement(String text, String element) {
        input(text, element);
    }


    /**
     * Sends text to an element.
     *
     * @param element the element to send the text to
     */
    @When("send text and enter {string} to element {string}")
    public static void sendTextAndEnterToElement(String text, String element) {
        inputAndEnter(text, element);
    }


    /**
     * Sends a credential to an element.
     *
     * @param text    the credential to send
     * @param element the element to send the credential to
     */
    @When("send credential {string} to element {string}")
    public static void sendCredentialToElement(String text, String element) {
        credential(text, element);
    }

    /**
     * Sends text to a table.
     *
     * @param table the table to send the text to
     */
    @When("send text to:")
    public static void sentTextTo(DataTable table) {
        table(table);
    }

    /**
     * Sends text to a table.
     *
     * @param webTable  the name of the table to send the text to
     * @param dataTable the data table containing the text to send
     */
    @When("send text to table {string}:")
    public static void sentTextToTable(String webTable, DataTable dataTable) {
        fillTimesheetTableWithValues(webTable, dataTable);
    }

    /**
     * Waits for an loadig.
     */
    @Then("wait for loading")
    public static void waitLoading() {
        WebElementFacade element = getWebelementFacade("loading");
        element.waitForCondition().until(driver -> !element.isVisible());
        performAttemptsTo("{0} wait for loading", WaitFor.waitUntil("loading", STATES.INVISIBLE.getState()));
    }


    @When("drag and drop {string} to {string}")
    public static void dragAndDropTo(String target, String space) {
        Target source = getTarget(target);
        Target destination = getTarget(space);

        performAttemptsTo(
                "{0} drag and drop {1} to {2}",
                DragAndDropToElements.from(source).to(destination)
        );
    }

    /**
     * Clicks on an element.
     *
     * @param element the element to click on
     */
    @When("moves the cursor over the element {string}")
    public static void movesTheCursorOverTheElement(String element) {
        hoverOverTarget(element);
    }
}
