package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;

public class DragAndDropToElements implements Task {

    private final Target source;
    private final Target destination;

    // Constructor que recibe el elemento de origen y destino
    public DragAndDropToElements(Target source, Target destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Usa Actions de Selenium para realizar el drag and drop
        new Actions(getStaticDriver())
                .dragAndDrop(source.resolveFor(actor), destination.resolveFor(actor))
                .perform();
    }

    // Método estático para crear la tarea con el elemento fuente
    public static DragAndDropToElements from(Target source) {
        return instrumented(DragAndDropToElements.class, source, null);
    }

    // Método para especificar el destino del drag and drop
    public DragAndDropToElements to(Target destination) {
        return new DragAndDropToElements(this.source, destination);
    }
}
