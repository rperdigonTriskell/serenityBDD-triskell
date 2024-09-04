#Feature: Add Activity to Timesheet
#
#  As a user, I want to add an activity to my timesheet so that I can log my work hours.
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
#    When if the table is "not empty", "delete" an activity
#    Then verify the text element timesheet board "activity board" is ""


#  @PROD
#  Scenario: Add a New Activity
#    When click in timesheet board "Add Activities"
#    Then verify the element "Add Object To Timesheet" are "visible"
#    When send text "Task 1" to element "Search"
#    And click in "Search icon"
#    Then verify the element "MAPRE Portfolio Task 1 Checkbox" are "visible"
#    And click in "MAPRE Portfolio Task 1 Checkbox"
#    And click in "Add & Close"
#    Then verify the element "Add Object To Timesheet" are "not present"
#    And verify the element timesheet board "activity board" are "visible"
#    And verify the following elements on the "Timesheet board activity board" should match the expected data:
#      | Check | WORK APP | RES. APP | PATH                                                | PARENT           | OBJECT | OBJECT TYPE | PLANNED | TOTAL |
#      |       |          |          | Project Management/MAPRE Portfolio/Development Plan | Development Plan | Task 1 | Task        |         |       |
#    And verify the following elements on the "Timesheet board time board" should match the expected data:
#      | MON   | TUE   | WED   | THU   | FRI   | SAT   | SUN   |
#      | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h |
#
#  @PROD
#  Scenario: Adding Multiple Activities
#    When click in timesheet board "Add Activities"
#    Then verify the element "Add Object To Timesheet" are "visible"
#    When send text "Task 1" to element "Search"
#    And click in "Search icon"
#    Then verify the element "MAPRE Portfolio Task 1 Checkbox" are "visible"
#    And click in "MAPRE Portfolio Task 1 Checkbox"
#    And click in "Add & Close"
#    Then verify the element "Add Object To Timesheet" are "not present"
#    And verify the element timesheet board "activity board" are "visible"
#    And verify the following elements on the "Timesheet board activity board" should match the expected data:
#      | Check | WORK APP | RES. APP | PATH                                                | PARENT           | OBJECT | OBJECT TYPE | PLANNED | TOTAL |
#      |       |          |          | Project Management/MAPRE Portfolio/Development Plan | Development Plan | Task 1 | Task        |         |       |
#    When click in timesheet board "Add Activities"
#    Then verify the element "Add Object To Timesheet" are "visible"
#    When send text "Task 2" to element "Search"
#    And click in "Search icon"
#    Then verify the element "Integration_Triskell Task 2 Checkbox" are "visible"
#    And click in "Integration_Triskell Task 2 Checkbox"
#    And click in "Add & Close"
#    Then verify the element "Add Object To Timesheet" are "not present"
#    And verify the element timesheet board "activity board" are "visible"
#    And verify the following elements on the "Timesheet board activity board" should match the expected data:
#      | Check | WORK APP | RES. APP | PATH                                                       | PARENT               | OBJECT | OBJECT TYPE | PLANNED | TOTAL |
#      |       |          |          | Project Management/MAPRE Portfolio/Development Plan        | Development Plan     | Task 1 | Task        |         |       |
#      |       |          |          | Project Management/NN Group Portfolio/Integration_Triskell | Integration_Triskell | Task 2 | Task        |         |       |
#    When click in timesheet board "Add Activities"
#    Then verify the element "Add Object To Timesheet" are "visible"
#    When send text "Task 6" to element "Search"
#    And click in "Search icon"
#    Then verify the element "Integration_Triskell Task 6 Checkbox" are "visible"
#    And click in "Integration_Triskell Task 6 Checkbox"
#    And click in "Add & Close"
#    Then verify the element "Add Object To Timesheet" are "not present"
#    And verify the element timesheet board "activity board" are "visible"
#    And verify the following elements on the "Timesheet board activity board" should match the expected data:
#      | Check | WORK APP | RES. APP | PATH                                                       | PARENT               | OBJECT | OBJECT TYPE | PLANNED | TOTAL |
#      |       |          |          | Project Management/MAPRE Portfolio/Development Plan        | Development Plan     | Task 1 | Task        |         |       |
#      |       |          |          | Project Management/NN Group Portfolio/Integration_Triskell | Integration_Triskell | Task 2 | Task        |         |       |
#      |       |          |          | Project Management/NN Group Portfolio/Integration_Triskell | Integration_Triskell | Task 6 | Task        | 48.00h  |       |
#    And verify the following elements on the "Timesheet board time board" should match the expected data:
#      | MON   | TUE   | WED   | THU   | FRI   | SAT   | SUN   |
#      | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h |
#      | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h |
#      | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h |

#  @PROD
#  Scenario: Attempting to add an activity without providing details
#    When click in timesheet board "Add Activities"
#    Then verify the element "Add Object To Timesheet" are "visible"
#    When click in "Add & Close"
#    Then verify the text element "Failed message" is "Select one or more items from list"
#    When click in "OK"
#    When click in "Close"
#    Then verify the element "Add Object To Timesheet" are "not present"
#    And verify the text element timesheet board "activity board" is ""
#
