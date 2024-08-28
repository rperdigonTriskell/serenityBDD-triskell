#Feature: Delete an Activity
#
#  As a user, I want to delete an activity from my timesheet so that I can remove incorrect entries.
#
#  Scenario: Delete an Activity
#    Given the user has added activities to the timesheet
#    When the user selects an activity and clicks on the delete icon (trash can)
#    Then a confirmation dialog should appear
#    And after confirming, the activity should be removed from the grid
#
#  Scenario: Attempting to delete an activity that does not exist
#    Given the user is on the timesheet page
#    When the user attempts to delete an activity that is not listed in the grid
#    Then the system should display an error message indicating that the selected activity cannot be found
#    And no activities should be removed from the grid
