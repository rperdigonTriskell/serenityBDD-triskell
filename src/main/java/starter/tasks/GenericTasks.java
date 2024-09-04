package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static starter.selectors.factory.PageFactory.getCurrentPage;

public class GenericTasks {

    /**
     * Retrieves the WebElementFacade for the given element on the current page.
     *
     * @param element The name of the element to retrieve the WebElementFacade for.
     * @return The WebElementFacade for the given element.
     */
    public static WebElementFacade getWebelementFacade(String element) {
        return getCurrentPage().$(getCurrentPage().getSelector(element));
    }

    /**
     * Executes a task using the given actor and action function.
     *
     * @param description      A description of the task for reporting purposes.
     * @param questionFunction The function that generates the question to ask the actor.
     * @param matcher          The matcher to validate the question result.
     * @param <T>              The type of the question result.
     */
    public static  <T> void performShouldSeeThat(String description, Function<Actor, T> questionFunction, Matcher<T> matcher) {
        // Luego se realiza la verificación usando el matcher después de la espera
        OnStage.theActorInTheSpotlight().should(
                seeThat(description, questionFunction::apply, matcher)
        );
    }

    /**
     * Processes each row of a DataTable using the provided element processor.
     *
     * @param dataTable        The DataTable containing rows of data.
     * @param elementProcessor A consumer function to process each row of data as a map of key-value pairs.
     */
    public static void dataTableUtil(DataTable dataTable, Consumer<Map<String, String>> elementProcessor) {
        // Convert the DataTable into a list of maps, where each map represents a row of data.
        List<Map<String, String>> elements = dataTable.asMaps(String.class, String.class);

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
     * @param  table  the WebElement representing the table
     * @return        a list of WebElements representing the table rows
     */
    public static List<WebElementFacade> getTableRows(WebElementFacade table) {
        List<WebElementFacade> elements;
        if (!table.findElements(By.cssSelector("table")).isEmpty()) {
            elements = table.thenFindAll(By.cssSelector("table table tbody tr"));
        } else {
            elements = table.thenFindAll(By.cssSelector("tr"));
        }
        return elements;
    }

    /**
     * Retrieves the list of table rows from the given WebElement table.
     *
     * @param  table  the WebElement representing the table
     * @return        a list of WebElements representing the table rows
     */
    public static List<WebElementFacade> getTableColumns(WebElementFacade table) {
        return table.thenFindAll(By.cssSelector("td"));
    }

    /**
     * Validates the row count of a list of WebElements representing table rows against an expected row count.
     *
     * @param  rows               the list of WebElements representing table rows
     * @param  expectedRowCount   the expected number of rows in the table
     * @throws AssertionError     if the actual row count does not match the expected row count
     */
    public static void validateRowCount(List<WebElementFacade> rows, int expectedRowCount) {
        if (rows.size() != expectedRowCount) {
            throw new AssertionError("The number of rows in the web table does not match the expected rows.");
        }
    }

}
