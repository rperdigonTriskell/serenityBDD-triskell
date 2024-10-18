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
    private final int horizontalSteps;  // Número de tramos en horizontal

    // Constructor que recibe el origen y el número de tramos
    public DragAndDropByCoordinates(Target source, int horizontalSteps) {
        this.source = source;
        this.horizontalSteps = horizontalSteps;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Resolver el elemento source como WebElementFacade
        WebElementFacade sourceElement = source.resolveFor(actor);

        // Obtener el tamaño del elemento source
        int sourceWidth = sourceElement.getSize().getWidth();
        int sourceHeight = sourceElement.getSize().getHeight();

        // Definir el tamaño de cada tramo/paso (por ejemplo, 100 píxeles por tramo)
        int stepSize = 100; // Ajusta este valor según el tramo que quieras mover.

        // Calcular la distancia total a mover
        int distanceToMove = stepSize * horizontalSteps;

        // Mover desde la esquina superior derecha del elemento hacia la derecha
        new Actions(getStaticDriver())
                .moveToElement(sourceElement, sourceWidth / 2, -sourceHeight / 2)  // Mueve a la esquina superior derecha del source
                .clickAndHold()
                .moveByOffset(distanceToMove, 0)  // Mueve hacia la derecha por el número de tramos especificado
                .release()
                .perform();
    }

    // Método estático para inicializar la tarea
    public static DragAndDropByCoordinates from(Target source) {
        return instrumented(DragAndDropByCoordinates.class, source, 0);  // Por defecto, 0 tramos
    }

    // Método para especificar el número de tramos en horizontal
    public DragAndDropByCoordinates bySteps(int steps) {
        return new DragAndDropByCoordinates(this.source, steps);
    }
}