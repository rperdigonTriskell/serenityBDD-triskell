package starter.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static starter.pageselectors.factory.PageFactory.getStaticDriver;
import static starter.tasks.GenericTasks.getWebelementFacadeFromTarget;

public class DragAndDropByCoordinates implements Task {

    // Target elements for drag and drop operation
    private final Target source;     // The element to be dragged
    private final Target reference;   // The reference element to validate size
    private final int horizontalSteps; // Number of steps to move horizontally

    // Constructor to initialize the source, reference, and horizontal steps
    public DragAndDropByCoordinates(Target source, Target reference, int horizontalSteps) {
        this.source = source;             // Initialize source element
        this.reference = reference;       // Initialize reference element
        this.horizontalSteps = horizontalSteps; // Initialize horizontal movement steps
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Get the source element as a WebElementFacade
        WebElementFacade sourceElement = getWebelementFacadeFromTarget(source);
        int sourceWidth = sourceElement.getSize().getWidth();   // Get width of source element
        int sourceHeight = sourceElement.getSize().getHeight(); // Get height of source element

        // Get the reference element as a WebElementFacade
        WebElementFacade referenceElement = getWebelementFacadeFromTarget(reference);
        int referenceWidth = referenceElement.getSize().getWidth(); // Get width of reference element
        int referenceHeight = referenceElement.getSize().getHeight(); // Get height of reference element

        // Validate size of reference element (optional logic here)
        if (referenceWidth <= 0 || referenceHeight <= 0) {
            throw new IllegalStateException("Reference element has invalid dimensions.");
        }

        // Calculate the total distance to move horizontally
        int stepSize = referenceWidth; // Define step size based on reference width
        int distanceToMove = stepSize * horizontalSteps; // Calculate total distance to move horizontally

        // Perform the drag-and-drop action using Actions class
        new Actions(getStaticDriver())
                .moveToElement(sourceElement, sourceWidth / 2, sourceHeight / 2) // Move to the center of source element
                .clickAndHold() // Click and hold the source element
                .moveByOffset(distanceToMove, 0) // Move the element by the calculated distance
                .release() // Release the dragged element
                .perform(); // Execute the actions
    }

    // Static method to instantiate the task with default horizontal steps of 0
    public static DragAndDropByCoordinates from(Target source, Target reference) {
        return instrumented(DragAndDropByCoordinates.class, source, reference, 0);
    }

    // Method to set the number of horizontal steps for the drag-and-drop
    public DragAndDropByCoordinates bySteps(int steps) {
        return new DragAndDropByCoordinates(this.source, this.reference, steps); // Create a new instance with updated steps
    }
}
