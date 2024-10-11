package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;

public class DragAndDropByCoordinates implements Task {

    private final Target source;
    private final Target destination;

    // Constructor que recibe el origen y el destino
    public DragAndDropByCoordinates(Target source, Target destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Resolver los elementos como WebElementFacade
        WebElementFacade sourceElement = source.resolveFor(actor);
        WebElementFacade destinationElement = destination.resolveFor(actor);

        // Obtener el tamaño y posición de los elementos
        int sourceCenterX = sourceElement.getLocation().getX() + (sourceElement.getSize().getWidth() / 2);
        int destinationCenterX = destinationElement.getLocation().getX() + (destinationElement.getSize().getWidth() / 2);

        // Mover de izquierda a derecha, desde el centro izquierdo del source al centro derecho del destination
        new Actions(getStaticDriver())
                .moveToElement(sourceElement, -sourceElement.getSize().getWidth() / 2, 0)  // Mueve al centro-izquierda del source
                .clickAndHold()
                .moveByOffset(destinationCenterX - sourceCenterX, 0)  // Arrastra horizontalmente hasta el centro del destino
                .release()
                .perform();
    }

    // Método estático para inicializar la tarea
    public static DragAndDropByCoordinates from(Target source) {
        return instrumented(DragAndDropByCoordinates.class, source, null);
    }

    // Método para especificar el destino del drag and drop
    public DragAndDropByCoordinates to(Target destination) {
        return new DragAndDropByCoordinates(this.source, destination);
    }
}
