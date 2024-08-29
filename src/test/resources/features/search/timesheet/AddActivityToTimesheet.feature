Feature: Add Activity to Timesheet

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


  @PROD
  Scenario: Add a New Activity
    When click in timesheet "Timesheet"
    Then verify the element "Timesheet board Add Activities" are "visible"
    Then verify the text element timesheet board "activity board" is ""
    When click in timesheet board "Add Activities"
    Then verify the element "Add Object To Timesheet" are "visible"
    When send text "Task 1" to element "Search"
    And click in "Search icon"
    And click in "MAPRE Portfolio Task 1 Checkbox"
    And click in "Add & Close"
    Then verify the element "Add Object To Timesheet" are "not present"
    Then verify the element "Timesheet board activity board" are "visible"
    Then verify the following elements on the "Timesheet board activity board" should match the expected data:
      | Check | WORK APP | RES. APP | PATH                                                | PARENT           | OBJECT | OBJECT TYPE | PLANNED | TOTAL |
      |       |          |          | Project Management/MAPRE Portfolio/Development Plan | Development Plan | Task 1 | Task        |         |       |
#
##    Then a form should appear for entering the activity details
##    And after submission, the new activity should be displayed in the timesheet grid
#
##  Scenario Outline: Adding Multiple Activities
##    Given the user is on the timesheet page
##    When the user clicks on "Add Activities" and enters the following details:
##      | Activity   | Hours | Date       |
##      | <activity> | <hours> | <date>    |
##    Then the activities should be displayed in the timesheet grid with the correct details
##
##    Examples:
##      | activity  | hours | date       |
##      | CR1805-009 | 2.0   | 07/08/2024 |
##      | CR1805-010 | 1.5   | 08/08/2024 |
##      | CR1805-011 | 3.0   | 09/08/2024 |
##
##  Scenario: Attempting to add an activity without providing details
##    Given that the user is on the timesheet page
##    When the user clicks on "Add Activities" and does not provide any details (leaves all fields empty)
##    Then the system should display an error message stating that all required fields must be filled
##    And the activity should not be added to the grid
##
##  Scenario: Attempting to add an activity with invalid hours
##    Given that the user is on the timesheet page
##    When the user clicks on "Add Activities" and enters an invalid value for hours (e.g. letters instead of numbers)
##    Then the system should display an error message stating that hours must be a valid number
##    And the activity should not be added to the grid
##
##  Scenario: Attempting to add an activity on a date outside the selected range
##    Given the user has selected a specific date range in the timesheet
##    When the user attempts to add an activity with a date outside that range
##    Then the system should display an error message indicating that the date is outside the selected range
##    And the activity should not be added to the grid
