package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static starter.pageselectors.factory.PageFactory.*;
import static starter.tasks.WaitElement.getWaitVisibleWebelementFacadeFromTarget;

public class GenericTasks {
    /**
     * Retrieves the WebElementFacade for the given element by name.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getWebelementFacade(String element) {
        return resolveFacade(getTarget(element));
    }

    /**
     * Retrieves the WebElementFacade for the given element by selector.
     *
     * @param element The selector of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getWebelementFacade(By element) {
        return resolveFacade(getTarget(element));
    }

    /**
     * Retrieves the Target for the given element by name.
     *
     * @param element The name of the element.
     * @return The Target for the given element.
     */
    public static Target getTarget(String element) {
        return Target.the(element).located(getCurrentPage().getSelector(element));
    }

    /**
     * Retrieves the Target for the given element by selector.
     *
     * @param element The selector of the element.
     * @return The Target for the given element.
     */
    public static Target getTarget(By element) {
        return Target.the(element.toString()).located(element);
    }

    /**
     * Retrieves the WebElementFacade for a given Target.
     *
     * @param element The Target to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given Target.
     */
    public static WebElementFacade getWebelementFacadeFromTarget(Target element) {
        return resolveFacade(element);
    }

    /**
     * Retrieves a list of WebElementFacade instances for a given Target.
     *
     * @param element The Target to retrieve the WebElementFacades for.
     * @return A list of WebElementFacade instances for the given Target.
     */
    public static List<WebElementFacade> getWebelementsFacadeFromTarget(Target element) {
        return resolveAllFacade(element);
    }

    /**
     * Retrieves the WebElementFacade for the given selector.
     *
     * @param selector The By selector to locate the WebElementFacade.
     * @return The WebElementFacade for the given selector.
     */
    public static WebElementFacade getWebElementFacadeFromBySelector(By selector) {
        return resolveFacade(getTarget(selector));
    }

    /**
     * Retrieves a list of WebElementFacade instances for elements matching the given selector.
     *
     * @param selector The By selector to locate the elements.
     * @return A list of WebElementFacade instances for the elements matching the selector.
     */
    public static List<WebElementFacade> getWebElementsFacadeBySelector(By selector) {
        return resolveAllFacade(getTarget(selector));
    }

    /**
     * Resolves a single WebElementFacade for a given Target.
     *
     * @param target The Target to resolve.
     * @return The WebElementFacade for the Target.
     */
    private static WebElementFacade resolveFacade(Target target) {
        Actor actor = OnStage.theActorInTheSpotlight();
        return target.resolveFor(actor);
    }

    /**
     * Resolves a list of WebElementFacade instances for a given Target.
     *
     * @param target The Target to resolve.
     * @return A list of WebElementFacade instances for the Target.
     */
    private static List<WebElementFacade> resolveAllFacade(Target target) {
        Actor actor = OnStage.theActorInTheSpotlight();
        return target.resolveAllFor(actor);
    }

    /**
     * Executes a task using the given actor and action function.
     *
     * @param description      A description of the task for reporting purposes.
     * @param questionFunction The function that generates the question to ask the actor.
     * @param matcher          The matcher to validate the question result.
     * @param <T>              The type of the question result.
     */
    public static <T> void performShouldSeeThat(String description, Function<Actor, T> questionFunction, Matcher<T> matcher) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(description, questionFunction::apply, matcher)
        );
    }

    /**
     * Executes one or more tasks as the actor in the spotlight.
     *
     * @param description a description of the actions being executed
     * @param actions     the tasks or actions to be executed
     */
    public static void performAttemptsTo(String description, Performable... actions) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Task.where(description, actions)
        );
    }

    /**
     * Processes each row of a DataTable using the provided element processor.
     *
     * @param dataTable        The DataTable containing rows of data.
     * @param elementProcessor A consumer function to process each row of data as a map of key-value pairs.
     */
    public static void processDataTable(DataTable dataTable, Consumer<Map<String, String>> elementProcessor) {
        // Convert the DataTable into a list of maps, where each map represents a row of data.
        List<Map<String, String>> elements = getListFromDatatable(dataTable);

        // Iterate over each map (representing a row) and apply the elementProcessor function to it.
        for (Map<String, String> element : elements) {
            // Create a mutable copy of the element map
            Map<String, String> mutableElement = new HashMap<>(element);

            // Iterate through the entries of the map and replace null values with empty strings
            mutableElement.replaceAll((k, v) -> v == null ? "" : v);

            // Apply the elementProcessor function to the modified map
            elementProcessor.accept(mutableElement);
        }
    }

    /**
     * Retrieves the list of table rows from the given WebElement table.
     *
     * @param targetTable the WebElement representing the table
     * @return a list of WebElements representing the table rows
     */
    public static List<WebElementFacade> getTableRows(Target targetTable) {
        WebElementFacade table = getWaitVisibleWebelementFacadeFromTarget(targetTable);
        List<WebElementFacade> rows;
        if (!table.findElements(By.xpath(".//table")).isEmpty()) {
            rows = table.thenFindAll(By.xpath(".//table//tbody//tr"));
        } else {
            rows = table.thenFindAll(By.xpath(".//tr"));
        }
        return rows;
    }

    /**
     * Retrieves the list of table rows from the given WebElement table.
     *
     * @param table the WebElement representing the table
     * @return a list of WebElements representing the table rows
     */
    public static List<WebElementFacade> getTableColumns(WebElementFacade table) {
        return table.thenFindAll(By.xpath(".//td"));
    }


    /**
     * Retrieves the expected rows from the given DataTable as a list of maps with String keys and values.
     *
     * @param dataTable the DataTable containing the expected rows
     * @return a list of maps representing the expected rows, with String keys and values
     */
    public static List<Map<String, String>> getListFromDatatable(DataTable dataTable) {
        return dataTable.asMaps(String.class, String.class);
    }

    public static void verifyFileNamw(String fileName) {
        performAttemptsTo("verify that the file name is {0}", VerifyFileDownloaded.withNameInRemotePath(fileName));
    }
}
