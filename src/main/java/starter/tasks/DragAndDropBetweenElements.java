package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;
import static starter.tasks.GenericTasks.getWebelementFacadeFromTarget;

public class DragAndDropBetweenElements implements Task {

    private final Target source;
    private final Target destination;

    // Constructor que recibe el origen y el destino
    public DragAndDropBetweenElements(Target source, Target destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Resolver los elementos source y destination como WebElementFacade
        WebElementFacade sourceElement = getWebelementFacadeFromTarget(source);
        WebElementFacade destinationElement = getWebelementFacadeFromTarget(destination);

        // Obtener el tamaño de los elementos
        int sourceWidth = sourceElement.getSize().getWidth();
        int sourceHeight = sourceElement.getSize().getHeight();
        int destinationWidth = destinationElement.getSize().getWidth();
        int destinationHeight = destinationElement.getSize().getHeight();

        // Calcular las coordenadas de inicio (centro izquierdo del source)
        int startX = -(sourceWidth / 2);
        int startY = 0;

        // Calcular las coordenadas de fin (centro derecho del destination)
        int endX = destinationWidth / 2;
        int endY = 0;

        // Realizar el drag and drop usando las coordenadas calculadas
        new Actions(getStaticDriver())
                .moveToElement(sourceElement, startX, startY)  // Mueve al centro izquierdo del source
                .clickAndHold()
                .moveToElement(destinationElement, endX, endY)  // Mueve al centro derecho del destination
                .release()
                .perform();
    }

    // Método estático para inicializar la tarea
    public static DragAndDropBetweenElements from(Target source, Target destination) {
        return instrumented(DragAndDropBetweenElements.class, source, destination);
    }
}