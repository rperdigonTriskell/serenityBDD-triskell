#Feature: Add Comments to an Activity
#
#  As a user, I want to add comments to an activity so that I can provide additional details or context.
#
#  Scenario: Add a Comment to an Activity
#    Given the user has added activities to the timesheet
#    When the user selects an activity and clicks on the comment icon (comic bubble)
#    Then a form should appear for entering the comment
#    And after submission, the comment should be saved and associated with the activity
#
#  Scenario: Attempting to add a comment without selecting an activity
#    Given the user is on the timesheet page
#    When the user attempts to add a comment without selecting an activity
#    Then the system should display an error message indicating that an activity must be selected before adding a comment
#    And no comments should be added