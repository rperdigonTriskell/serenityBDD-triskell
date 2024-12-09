package starter.tasks;


import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import starter.Constants;
import starter.pageselectors.factory.PageFactory;

import static starter.Constants.PROJECT_CONTEXT;
import static starter.tasks.GenericTasks.performAttemptsTo;


public class IsLoad extends PageObject {
    /**
     * A description of the entire Java function.
     *
     * @param  pageName	description of parameter
     * @return         	description of return value
     */
    public static void isLoadPage(String pageName) {
        PageFactory.setCurrentPage(pageName);
        performAttemptsTo("visible page: ",VerifyElementVisibility.verifyElementIsVisible(pageName));
    }

    /**
     * Verifies that the given page is not loaded.
     *
     * @param pageName description of parameter
     * @return description of return value
     */
    public static void isNotLoadPage(String pageName) {
        PageFactory.setCurrentPage(pageName);
        Serenity.recordReportData().withTitle("not visible page: ").andContents(pageName);
        VerifyElementVisibility.verifyElementIsNotVisible(pageName);
    }
}
