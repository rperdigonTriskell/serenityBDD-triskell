package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

import java.util.List;

import static starter.tasks.GenericTasks.*;
import static starter.tasks.WaitElement.*;
import static starter.tasks.security.CredentialManager.*;

public class SendTextTo {
    /**
     * Input the given text into the specified element.
     *
     * @param text    the text to input
     * @param element the element to input the text into
     */
    public static void input(String text, String element) {
        Target target = WaitElement.getVisibleTargetWithWait(element);
        performAttemptsTo(
                "clear {1} and input text",
                Clear.field(target),
                Enter.theValue(text).into(target)
        );
    }

    /**
     * Input the given enter into the specified element.
     *
     * @param element the element to input the text into
     */
    public static void inputAndEnter(String text, String element) {
        Target target = WaitElement.getVisibleTargetWithWait(element);
        performAttemptsTo(
                "clear {1}, input text and hit enter",
                Clear.field(target),
                Enter.theValue(text).into(target).thenHit(Keys.ENTER)
        );
    }

    /**
     * Input the given text into the specified element.
     *
     * @param text    the text to input
     * @param element the element to input the text into
     */
    public static void input(String text, WebElementFacade element) {
        element = getVisibleWebelementFacadeWithWait(element);
        performAttemptsTo(
                "clear {1} and input text",
                Clear.field(element),
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
        input(getCredential(text, false), element);
    }

    /**
     * Iterates through the rows of a DataTable and calls the input method with the elements of each row.
     *
     * @param table the DataTable to iterate through
     */
    public static void table(DataTable table) {
        for (List<String> row : table.asLists(String.class)) {
            input(row.get(1), row.get(0));
        }
    }
}