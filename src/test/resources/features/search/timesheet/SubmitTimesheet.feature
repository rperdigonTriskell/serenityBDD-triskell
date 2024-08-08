Feature: Submit Timesheet

  As a user, I want to submit my timesheet so that my logged hours are sent for approval.

  Scenario: Submit Timesheet
    Given the user has logged activities in the timesheet
    When the user clicks on "Submit Timesheet"
    Then a confirmation dialog should appear
    And after confirming, the timesheet should be marked as submitted and become non-editable

  Scenario: Attempting to submit an empty timesheet
    Given the user is on the timesheet page
    When the user clicks "Submit Timesheet" without having added any activities
    Then the system should display an error message indicating that an empty timesheet cannot be submitted
    And the timesheet should not be marked as submitted

  Scenario: Attempting to export an empty timesheet
    Given the user is on the timesheet page
    When the user clicks "Export" without having added any activities
    Then the system should display an error message indicating that an empty timesheet cannot be exported
    And the export file should not be generated
