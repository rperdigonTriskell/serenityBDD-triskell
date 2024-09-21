Feature: Generate Report
  As a user, I want to add an activity to my timesheet so that I can log my work hours.

  Background:
    Given go to web Triskell
    Then check to "Login" has loaded
    When send credential "username" to element "Username"
    And send credential "password" to element "Password"
    And click in "Validate"
    Then check to "Dashboard" has loaded
    When click in sidebar "Timesheet"
    Then check to "Timesheet" has loaded
    When click in timesheet "Timesheet"
    Then verify the element timesheet board "Add Activities" are "visible"
    When if the table is "empty", "add" an activity
    And verify the following elements on the "Timesheet board activity board" should match the expected data:
      | Check | WORK APP | RES. APP | OBJECT               | DESCRIPTION | PLANNED | TOTAL |
      |       |          |          | Automation Test Task |             |         |       |
    And verify the following elements on the "Timesheet board time board" should match the expected data:
      | MON | TUE | WED | THU | FRI | SAT | SUN |
      |     |     |     |     |     |     |     |

  @PROD
  Scenario: Generating a Report with All Correct Data
#  te comento un metodo serenity bdd screenplay que implementar
    When send text to table "Timesheet board time board":
      | MON | TUE | WED | THU | FRI |
      | 8   | 8   | 8   | 8   | 8   |
#  "Timesheet board time board" es un target y su selector lo recoje este metodo:
#  /**
#    * Retrieves the Target for the given element on the current page.
#  */
#  public static Target getTarget(String element) {
#  return Target.the(element).located(getCurrentPage().getSelector(element));
#  }
#    El selector de "Timesheet board time board"  es este:
#  mapSelectors.put(TIMESHEET_BOARD + "time board", By.xpath("(//*[contains(@id, 'gridview-') and contains(@id, '-table')])[2]"));
#
#    Tambien he de aclarar que esta tabla es un table dentro de otro table:
#  if (!table.findElements(By.xpath(".//table")).isEmpty()) {
#  rows = table.thenFindAll(By.xpath(".//table//tbody//tr"));
#  } else {
#  rows = table.thenFindAll(By.xpath(".//tr"));
#  }
#
#    este es un condicional mal escrito ya que quiero un enfoque screenplay.
#
#    tambien añadir esta mecanica de rellenar:
#  // click on the cell to activate DOM event to create imput to inmput text
#
#  // wait for the input to be visible, DOM refresh and need update
#
#  // input the text into the input field

    And click in timesheet board "Submit Timesheet"
    Then verify the element timesheet "Timesheet Submit" are "visible"
    When click in timesheet "Submit"
    Then verify the element timesheet "Submit" are "invisible"
    And verify the following elements on the "Timesheet board time board" should match the expected data:
      | MON   | TUE   | WED   | THU   | FRI   |
      | 8.00h | 8.00h | 8.00h | 8.00h | 8.00h |

#    durante la eliminación de actividades hay un error que impide eliminar bien la actividad y es un posible bug

#
#  @PROD
#  Scenario: Generating a Report with Data Replacement
#    When send text to table "Timesheet board time board":
#      | MON | TUE | WED | THU | FRI |
#      | 8   | 8   | 8   | 8   | 8   |
#  And click in timesheet board "Submit Timesheet"
#  Then verify the element timesheet "Timesheet Submit" are "visible"
#  When click in timesheet "Submit"
#  Then verify the element timesheet "Submit" are "invisible"
#  And verify the following elements on the "Timesheet board time board" should match the expected data:
#    | MON   | TUE   | WED   | THU   | FRI   |
#    | 8.00h | 8.00h | 8.00h | 8.00h | 8.00h |
#  When send text to table "Timesheet board time board":
#    | MON | TUE | WED | THU | FRI |
#    | 5   | 5   | 5   | 5   | 5   |
#  And click in timesheet board "Submit Timesheet"
#  Then verify the element timesheet "Timesheet Submit" are "visible"
#  When click in timesheet "Submit"
#  Then verify the element timesheet "Submit" are "invisible"
#  And verify the following elements on the "Timesheet board time board" should match the expected data:
#    | MON   | TUE   | WED   | THU   | FRI   |
#    | 8.00h | 8.00h | 8.00h | 8.00h | 8.00h |


#    Given that the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And selects "Planned"
#    And enters a valid value in the "Units" field
#    And selects a valid date range in "From period" and "To period"
#    And activates the "Replace data" option
#    And clicks "Generate"
#    Then the system should replace the existing data and generate the new report, displaying a success message
#
#  Scenario: Cancel Report Generation
#    Given the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And in the modal, clicks "Cancel"
#    Then the system should close the modal without performing any further action
#
#  Scenario: Attempting to Generate a Report without Entering Data in the "Units" Field
#    Given the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And leaves the "Units" field empty
#    And tries to click "Generate"
#    Then the system should display an error message stating that the "Units" field is required
#
#  Scenario: Attempting to Generate a Report with an Invalid Date Range
#    Given the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And selects "Units"
#    And enters a valid value in the "Units" field
#    And selects an invalid date range (e.g. "From period" is after "To period")
#    And attempts to click "Generate"
#    Then the system should display an error message indicating that the date range is invalid
#
#  Scenario: Attempting to Generate a Report without Selecting a Radio Option (Units or Planned)
#    Given the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And does not select any radio option (neither "Units" nor "Planned")
#    And attempts to click "Generate"
#    Then the system should display an error message indicating that one of the available options must be selected
#
#  Scenario: Attempting to Generate a Report with Incomplete Data
#    Given the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And selects "Planned"
#    And does not enter a value in the "Units" field
#    And selects a valid date range
#    And attempts to click "Generate"
#    Then the system should display an error message indicating that all required fields must be filled out
#
#  Scenario: Attempting to Generate a Report with Incorrect Date Format
#    Given the user is on the timesheet page
#    When the user clicks the "Generate" button
#    And selects "Units"
#    And enters a valid value in the "Units" field
#    And enters a date in the incorrect format (for example, dd/mm/yyyy instead of mm/dd/yyyy)
#    And attempts to click "Generate"
#    Then the system should display an error message indicating that the date format is incorrect
