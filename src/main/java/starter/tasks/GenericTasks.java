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
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static starter.selectors.factory.PageFactory.*;
import static starter.tasks.WaitInteractions.*;

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
     * Retrieves the WebElementFacade for the given selector on the current page.
     *
     * @param selector The By selector to locate the WebElementFacade.
     * @return The WebElementFacade for the given selector.
     */
    public static WebElementFacade getWebElementFacadeBySelector(By selector) {
        return getCurrentPage().$(selector);
    }

    /**
     * Retrieves a list of WebElementFacade for elements matching the given selector on the current page.
     *
     * @param selector The By selector to locate the elements.
     * @return A list of WebElementFacade for the elements matching the selector.
     */
    public static List<WebElementFacade> getWebElementsFacadeBySelector(By selector) {
        return getCurrentPage().$$(selector);
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
        List<Map<String, String>> elements = getExpectedRows(dataTable);

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
        if (!table.findElements(By.xpath(".//table")).isEmpty()) {
            elements = table.thenFindAll(By.xpath(".//table//tbody//tr"));
        } else {
            elements = table.thenFindAll(By.xpath(".//tr"));
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
        return table.thenFindAll(By.xpath(".//td"));
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

    /**
     * Retrieves the expected rows from the given DataTable as a list of maps with String keys and values.
     *
     * @param  dataTable  the DataTable containing the expected rows
     * @return            a list of maps representing the expected rows, with String keys and values
     */
    public static List<Map<String, String>> getExpectedRows(DataTable dataTable) {
        return dataTable.asMaps(String.class, String.class);
    }


    /**
     * Applies the given action to each element in the web table based on the provided context.
     *
     * @param  webTable           the context of the web table
     * @param  dataTable          the DataTable containing the expected rows
     * @param  action             the BiConsumer that will be applied to each element in the table
     * @throws AssertionError     if the number of rows in the table does not match the number of expected rows
     */
    public static void applyActionToTableElements(String webTable, DataTable dataTable, BiConsumer<WebElementFacade, Map<String, String>> action) {
        // Find the web table based on the provided context
        WebElementFacade table = waitElementVisible(getWebelementFacade(webTable),true);

        // Find the table rows on the web
        List<WebElementFacade> rows = waitElementsVisible(getTableRows(table));

        // Convert Gherkin DataTable to a list of maps
        List<Map<String, String>> expectedRows = getExpectedRows(dataTable);

        // Check that the number of rows matches
        validateRowCount(rows, expectedRows.size());

        // Apply the Consumer to each row in the table using its index
        for (int i = 0; i < expectedRows.size(); i++) {
            action.accept(rows.get(i), expectedRows.get(i));
        }
    }
}
