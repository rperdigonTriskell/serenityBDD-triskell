package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.List;
import java.util.Map;

import static starter.Constants.*;
import static starter.tasks.WaitFor.waitFor;

public class VerifyTableElementsVisibility implements Task {
    private final DataTable dataTable;
    private final String context;

    public VerifyTableElementsVisibility(String context, DataTable dataTable) {
        this.context = context;
        this.dataTable = dataTable;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String element = context + " " + row.get("element");
            String visibility = row.get("visibility");

            if (visibility.equals(STATES.VISIBLE.getState())) {
                waitFor(element,STATES.VISIBLE.getState());
                actor.attemptsTo(VerifyElementVisibility.verifyElementIsVisible(element));
            } else if (visibility.equals(STATES.INVISIBLE.getState())) {
                actor.attemptsTo(WaitFor.waitUntil(element,STATES.INVISIBLE.getState()));
                actor.attemptsTo(VerifyElementVisibility.verifyElementIsNotVisible(element));
            } else if (visibility.equals(STATES.PRESENT.getState())) {
                waitFor(element,STATES.PRESENT.getState());
                actor.attemptsTo(VerifyElementVisibility.verifyElementIsPresent(element));
            } else if (visibility.equals(STATES.NOT_PRESENT.getState())) {
                actor.attemptsTo(WaitFor.waitUntil(element,STATES.NOT_PRESENT.getState()));
                actor.attemptsTo(VerifyElementVisibility.verifyElementIsNotPresent(element));
            } else {
                throw new IllegalArgumentException("Unknown visibility state: " + visibility);
            }
        }
    }

    public static VerifyTableElementsVisibility verifyElementsVisibility(String context, DataTable dataTable) {
        return new VerifyTableElementsVisibility(context,dataTable);
    }
}