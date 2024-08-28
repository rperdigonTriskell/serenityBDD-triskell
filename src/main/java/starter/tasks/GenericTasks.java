package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static starter.tasks.WaitInteractions.*;

public class GenericTasks {

    /**
     * Executes a task using the given actor and action function.
     *
     * @param description      A description of the task for reporting purposes.
     * @param questionFunction The function that generates the question to ask the actor.
     * @param matcher          The matcher to validate the question result.
     * @param <T>              The type of the question result.
     */
    public static  <T> void performShouldSeeThat(String description, Function<Actor, T> questionFunction, Matcher<T> matcher) {
        // Espera a que la condición sea adecuada usando WaitInteractions
        waitPerformShouldSeeThat(questionFunction, matcher);

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
        List<WebElement> elements;

        // Verifica si el elemento table contiene otra tabla
        if (table.findElements(By.cssSelector("table")).size() > 0) {
            elements = table.findElements(By.cssSelector("tr table > tbody > tr"));
        } else {
            elements = table.findElements(By.cssSelector("tr"));
        }

        // Convierte la lista de WebElements a WebElementFacade
        return elements.stream()
                .map(element -> (WebElementFacade) element) // Necesario para Serenity BDD
                .collect(Collectors.toList());
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
