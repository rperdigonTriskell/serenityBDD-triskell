package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import java.util.List;
import java.util.Map;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class VerifyDatatableElementsTextIs implements Task {

    private final DataTable dataTable;

    public VerifyDatatableElementsTextIs(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public static VerifyDatatableElementsTextIs from(DataTable dataTable) {
        return instrumented(VerifyDatatableElementsTextIs.class, dataTable);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String element = row.get("element");
            String expectedValue = row.get("value");

            actor.attemptsTo(
                    VerifyElementTextIs.forElement(element, expectedValue)
            );
        }
    }
}
