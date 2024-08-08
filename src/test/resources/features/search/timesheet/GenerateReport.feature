Feature: Generate Report

  Scenario: Generating a Report with All Correct Data
    Given that the user is on the timesheet page
    When the user clicks the "Generate" button
    And selects "Units"
    And enters a valid value in the "Units" field
    And selects a valid date range in "From period" and "To period"
    And clicks "Generate"
    Then the system should generate the report correctly and display a success message

  Scenario: Generating a Report with Data Replacement
    Given that the user is on the timesheet page
    When the user clicks the "Generate" button
    And selects "Planned"
    And enters a valid value in the "Units" field
    And selects a valid date range in "From period" and "To period"
    And activates the "Replace data" option
    And clicks "Generate"
    Then the system should replace the existing data and generate the new report, displaying a success message

  Scenario: Cancel Report Generation
    Given the user is on the timesheet page
    When the user clicks the "Generate" button
    And in the modal, clicks "Cancel"
    Then the system should close the modal without performing any further action

  Scenario: Attempting to Generate a Report without Entering Data in the "Units" Field
    Given the user is on the timesheet page
    When the user clicks the "Generate" button
    And leaves the "Units" field empty
    And tries to click "Generate"
    Then the system should display an error message stating that the "Units" field is required

  Scenario: Attempting to Generate a Report with an Invalid Date Range
    Given the user is on the timesheet page
    When the user clicks the "Generate" button
    And selects "Units"
    And enters a valid value in the "Units" field
    And selects an invalid date range (e.g. "From period" is after "To period")
    And attempts to click "Generate"
    Then the system should display an error message indicating that the date range is invalid

  Scenario: Attempting to Generate a Report without Selecting a Radio Option (Units or Planned)
    Given the user is on the timesheet page
    When the user clicks the "Generate" button
    And does not select any radio option (neither "Units" nor "Planned")
    And attempts to click "Generate"
    Then the system should display an error message indicating that one of the available options must be selected

  Scenario: Attempting to Generate a Report with Incomplete Data
    Given the user is on the timesheet page
    When the user clicks the "Generate" button
    And selects "Planned"
    And does not enter a value in the "Units" field
    And selects a valid date range
    And attempts to click "Generate"
    Then the system should display an error message indicating that all required fields must be filled out

  Scenario: Attempting to Generate a Report with Incorrect Date Format
    Given the user is on the timesheet page
    When the user clicks the "Generate" button
    And selects "Units"
    And enters a valid value in the "Units" field
    And enters a date in the incorrect format (for example, dd/mm/yyyy instead of mm/dd/yyyy)
    And attempts to click "Generate"
    Then the system should display an error message indicating that the date format is incorrect
