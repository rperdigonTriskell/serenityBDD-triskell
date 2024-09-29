package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static starter.tasks.GenericTasks.getTarget;
import static starter.tasks.GenericTasks.getWebelementFacade;


public class VerifyTableElements implements Task {

    private final String tableName;
    private final DataTable dataTable;

    public VerifyTableElements(String tableName, DataTable dataTable) {
        this.tableName = tableName;
        this.dataTable = dataTable;
    }

    public static VerifyTableElements forTable(String tableName, DataTable dataTable) {
        return new VerifyTableElements(tableName, dataTable);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Obtener el selector para la tabla
        Target tableTarget = getTarget(tableName);

        // Encuentra la tabla
        List<WebElementFacade> rows = getTableRows(actor, tableTarget);

        // Procesar el DataTable
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        for (int i = 1; i < expectedData.size(); i++) { // Comienza desde 1 para ignorar el encabezado
            Map<String, String> rowData = expectedData.get(i);
            // Comprobar cada campo
            verifyRowData(actor, rowData, rows.get(i - 1));
        }
    }

    private List<WebElementFacade> getTableRows(Actor actor, Target tableTarget) {
        // Obtenemos la tabla como WebElementFacade
        WebElementFacade table = getWebelementFacade(tableName);

        // Verifica si hay un table dentro
        if (!table.findElements(By.xpath(".//table")).isEmpty()) {
            return table.thenFindAll(By.xpath(".//table//tbody//tr"));
        } else {
            return table.thenFindAll(By.xpath(".//tr"));
        }
    }

    private <T extends Actor> void verifyRowData(T actor, Map<String, String> rowData, WebElementFacade row) {
        // Comparar cada celda con los valores esperados
        for (String key : rowData.keySet()) {
            String expectedValue = rowData.get(key);

            // Crear un Target para la celda
            Target cellTarget = Target.the("cell with key: " + key)
                    .located(By.xpath(".//td[contains(@class, '" + key + "')]"));

            // Obtener el valor actual de la celda
            String actualValue = WebElementQuestion.the(cellTarget).answeredBy(actor).getText();

            // Lógica de comparación
            if (!actualValue.equals(expectedValue)) {
                actor.remember("verificationError", "Mismatch for " + key + ": expected " + expectedValue + " but found " + actualValue);
            }
        }
    }
}
