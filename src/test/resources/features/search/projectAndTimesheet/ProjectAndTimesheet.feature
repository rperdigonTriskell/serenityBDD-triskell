#Feature: Manage Project and Timesheet
#
#  As a user, I want to add an project item to my project table so and can edit or delete it.
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
#    Then verify the text element timesheet board "activity board empty" is ""
#    When click in sidebar "Project"
#    Then check to "Project" has loaded
#    When if the table Project "empty", "add" an Project
#
#  @PROD @AWS
#  Scenario: assign project and timesheet
#    Then check to "Automation Testing Project" has loaded
#    When click in Automation Testing Project "Resource Boards"
#    Then check to "Automation Testing Project Resource Boards" has loaded
#    When click in "Resources"
#    And check to "Add Resources" has loaded
#    When send text and enter "System Administrator" to element "search"
#    Then wait for loading
#    Then verify the element "System Administrator checkbox" are "visible"
#    When click in "System Administrator checkbox"
#    And click in "Add Resource"
#    Then wait for loading
#    And verify the text element "empty resource board" is ""
#    When click in "Close"
#    Then check to "Automation Testing Project Resource Boards" has loaded
#    And verify the text element "Resources Board Assignments name" is "SYSTEM ADMINISTRATOR"
#    When click in "Requeriments"
#    Then wait for loading
#    And verify the element "Resources Board Requirements name" are "visible"
#    And verify the text element "Resources Board Requirements name" is "System Administrator"
#    When drag and drop Requirements date System Administrator data to "4" weeks
#    Then verify the element "Automation Testing Project period modal" are "visible"
#    When send text "8" to element "hours/Day"
#    When click in "Save"
#    Then verify the element "Automation Testing Project period modal" are "invisible"
#    And verify the element "Resources Board Requirements name" are "visible"
#    And verify the text element "System Administrator h/day" is "8.00 h/Day"
#    And verify the text element "System Administrator hour" is calculated in this month by 8 hour by day
#    When click in "Gantt Chart"
#    Then check to "Automation Testing Project Gantt Chart" has loaded
#    #And verify the element "Automatic progress" are "visible"
#    #When click in "OK"
#    When click in toolbar "Create"
#    Then verify the element "Selected item: Automation Testing Project" are "visible"
#    And verify the text element "Selected item: Automation Testing Project name" is "Selected item: Automation Testing Project"
#    When click in "Task"
#    And send text "Automation Testing Task" to element "Name"
#    And click in "Save"
#    Then verify the element "Selected item: Automation Testing Project" are "invisible"
#    Then verify the element "Gantt Chart" are "visible"
#    And verify that the text of the specified elements matches the expected values:
#      | element      | value                      |
#      | Name 1rt row | Automation Testing Project |
#      | Name 2rt row | Automation Testing Task    |
#    And left click in "Name 2rt row"
#    Then verify the element "task options" are "visible"
#    And click in "Add item..."
#    Then verify the element "Selected item: Automation Testing Task" are "visible"
#    And verify the text element "Selected item: Automation Testing Task name" is "Selected item: Automation Testing Task"
#    When click in "Sub Item"
#    And click in "Task"
#    And send text "Automation Testing Task Child" to element "Name"
#    And click in "Save"
#    Then verify the element "Selected item: Automation Testing Project" are "invisible"
#    And verify the element "Gantt Chart" are "visible"
#    And verify that the text of the specified elements matches the expected values:
#      | element      | value                         |
#      | Name 1rt row | Automation Testing Project    |
#      | Name 2rt row | Automation Testing Task       |
#      | Name 3rt row | Automation Testing Task Child |
#    When double click in "day percentage bar"
#    Then verify the element "Information" are "visible"
#    When click in "General"
#    And click in "Finish calendar icon"
#    And click in 3 days more
#    And click in "OK"
#    Then verify the element "Information" are "invisible"
#    And click in toolbar "Save"
#    And moves the cursor over the element "time bar 3rt row"
#    Then verify the element "time bar 3rt row modal duration" are "visible"
#    Then verify the text element "time bar 3rt row modal duration" is "4 days"
#    And click in "Name 3rt Assigned Resources"
#    Then verify the element "Name 3rt Assigned Resources down arrow" are "visible"
#    And click in "Name 3rt Assigned Resources down arrow"
#    Then verify the element "select resource project" are "visible"
#    When click in "System Administrator checkbox resource project"
#    And click in "Save select resource"
#    Then verify the element "select resource project" are "invisible"
#    And verify the text element "Name 3rt Assigned Resources filled" is "System Administrator 100%"
#    And click in toolbar "Save"
#    When click in Automation Testing Project sidebar "Main Menu"
#    Then verify the element Automation Testing Project "Timesheet" are "visible"
#    When click in Automation Testing Project sidebar "Timesheet"
#    Then check to "Timesheet" has loaded
#    When click in timesheet "Timesheet"
#    Then verify the element timesheet board "Add Activities" are "visible"
#    When click in timesheet board "Add Activities"
#    Then verify the element "Add Object To Timesheet" are "visible"
#    When send text "Automation Testing Task" to element "Search"
#    And click in "Search icon"
#    Then verify the element "first search result" are "visible"
#    And click in "first search result"
#    And click in "Add & Close"
#    Then verify the element "Add Object To Timesheet" are "invisible"
#    And verify the element timesheet board "activity board" are "visible"
#    And verify the following elements on the "Timesheet board activity board" should match the expected data:
#      | Check   | WORK APP | RES. APP | OBJECT                        | DESCRIPTION | PLANNED | TOTAL   |
#      | [empty] | [empty]  | [empty]  | Automation Testing Task Child | [empty]     | 32.00h | [empty] |
#    And verify the following elements on the "Timesheet board time board" should match the expected data:
#      | MON   | TUE   | WED   | THU   | FRI   | SAT   | SUN   |
#      | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h | 0.00h |
#    When send text to table "Timesheet board time board":
#      | MON | TUE | WED | THU | FRI |
#      | 8   | 8   | 8   | 8   | 8   |
#    And click in timesheet board "Submit Timesheet"
#    Then verify the element timesheet "Timesheet Submit" are "visible"
#    When click in timesheet "Submit"
#    Then verify the element timesheet "Submit" are "invisible"
#    And verify the following elements on the "Timesheet board time board" should match the expected data:
#      | MON   | TUE   | WED   | THU   | FRI   |
#      | 8.00h | 8.00h | 8.00h | 8.00h | 8.00h |