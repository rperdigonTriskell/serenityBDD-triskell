Feature: Add Activity to Timesheet

  As a user, I want to add an activity to my timesheet so that I can log my work hours.

  Scenario: Add a New Activity
    Given the user is on the timesheet page
    When the user clicks on "Add Activities"
    Then a form should appear for entering the activity details
    And after submission, the new activity should be displayed in the timesheet grid

  Scenario Outline: Adding Multiple Activities
    Given the user is on the timesheet page
    When the user clicks on "Add Activities" and enters the following details:
      | Activity   | Hours | Date       |
      | <activity> | <hours> | <date>    |
    Then the activities should be displayed in the timesheet grid with the correct details

    Examples:
      | activity  | hours | date       |
      | CR1805-009 | 2.0   | 07/08/2024 |
      | CR1805-010 | 1.5   | 08/08/2024 |
      | CR1805-011 | 3.0   | 09/08/2024 |

  Scenario: Attempting to add an activity without providing details
    Given that the user is on the timesheet page
    When the user clicks on "Add Activities" and does not provide any details (leaves all fields empty)
    Then the system should display an error message stating that all required fields must be filled
    And the activity should not be added to the grid

  Scenario: Attempting to add an activity with invalid hours
    Given that the user is on the timesheet page
    When the user clicks on "Add Activities" and enters an invalid value for hours (e.g. letters instead of numbers)
    Then the system should display an error message stating that hours must be a valid number
    And the activity should not be added to the grid

  Scenario: Attempting to add an activity on a date outside the selected range
    Given the user has selected a specific date range in the timesheet
    When the user attempts to add an activity with a date outside that range
    Then the system should display an error message indicating that the date is outside the selected range
    And the activity should not be added to the grid
