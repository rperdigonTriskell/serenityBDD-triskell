#Feature: Delete an Activity
#
#  As a user, I want to delete an activity from my timesheet so that I can remove incorrect entries.
#
#  Background:
#    Given go to web Triskell
#    Then check to "Login" has loaded
#    When send credential "username" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    Then check to "Dashboard" has loaded
#    When click in sidebar "Timesheet"
#    Then check to "Timesheet" has loaded
#    When click in timesheet "Timesheet"
#    Then verify the element timesheet board "Add Activities" are "visible"
#    When if the table is "empty", "add" an activity
#
#  @PROD
#  Scenario: Delete an Activity
#    When click in activity board checkbox "Automation Test"
#    And click in timesheet board "Delete"
#    Then verify the text element "Question message" is "Are you sure remove these items?"
#    When click in timesheet "Yes"
#    Then verify the element "Question message" are "invisible"
#    Then verify the text element timesheet board "activity board empty" is ""
#
#  @PROD
#  Scenario: Attempting to delete an activity that does not exist
#    When click in timesheet board "Delete"
#    Then verify the text element "Question message" is "Are you sure remove these items?"
#    When click in timesheet "Yes"
#    Then verify the text element "Warning message" is "No selected record"
#    When click in timesheet "OK"
