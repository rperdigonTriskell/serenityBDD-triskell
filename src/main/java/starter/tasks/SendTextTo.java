package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

import static starter.selectors.factory.PageFactory.*;
import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitInteractions.*;
import static starter.tasks.security.CredentialManager.*;

public class SendTextTo {
    /**
     * Input the given text into the specified element.
     *
     * @param text    the text to input
     * @param element the element to input the text into
     */
    public static void input(String text, String element) {
        Target target = getTarget(element);
        // Clear the specified element
        OnStage.theActorInTheSpotlight().attemptsTo(
                waitVisible(target),
                Clear.field(getCurrentPage().getSelector(element)),
                // Enter the given text into the specified element
                Enter.theValue(text).into(getCurrentPage().getSelector(element))
        );
    }

    /**
     * Input the given text into the specified element.
     *
     * @param text    the text to input
     * @param element the element to input the text into
     */
    public static void input(String text, WebElementFacade element) {
        element.waitUntilPresent();
        element.waitUntilClickable();
        element.waitUntilVisible();

        // Clear the specified element
        OnStage.theActorInTheSpotlight().attemptsTo(
                Clear.field(element),
                // Enter the given text into the specified element
                Enter.theValue(text).into(element)
        );
    }

    /**
     * Input the given credential into the specified element.
     *
     * @param text    the text to input
     * @param element the element to input the text into
     */
    public static void credential(String text, String element) {
        // Retrieve the credential value and send the credential value into the specified element
        input(getCredential(text, false), element);
    }

    /**
     * Iterates through the rows of a DataTable and calls the input method with the elements of each row.
     *
     * @param table the DataTable to iterate through
     */
    public static void table(DataTable table) {
        // Use a DataTable to iterate through the rows
        for (List<String> row : table.asLists(String.class)) {
            // Call the input method with the elements of each row
            input(row.get(1), row.get(0));
        }
    }
}