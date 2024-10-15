Feature: Project parts

  As a user, I want to add an project item to my project table so and can edit or delete it.

  Background:
    Given go to web Triskell
    Then check to "Login" has loaded
    When send credential "username" to element "Username"
    And send credential "password" to element "Password"
    And click in "Validate"
    Then check to "Dashboard" has loaded
    When click in sidebar "Project"
    Then check to "Project" has loaded
    When if the table Project "empty", "add" an Project

  @PROD
  Scenario: assign resources
    Then check to "Automation Testing Project" has loaded
    When click in Automation Testing Project "Resource Boards"
    Then check to "Automation Testing Project Resource Boards" has loaded
    When click in "Resources"
    And check to "Add Resources" has loaded
    When send text and enter "System Administrator" to element "search"
    Then wait for loading
    Then verify the element "System Administrator checkbox" are "visible"
    When click in "System Administrator checkbox"
    And click in "Add Resource"
    Then wait for loading
    And verify the text element "empty resource board" is ""
    When click in "Close"
    Then check to "Automation Testing Project Resource Boards" has loaded
    And verify the text element "Resources Board Assignments name" is "SYSTEM ADMINISTRATOR"
    When click in "Requeriments"
    And verify the element "Resources Board Requirements name" are "visible"
    And verify the text element "Resources Board Requirements name" is "System Administrator"
    When drag and drop Requirements date System Administrator data to "3" weeks
    Then verify the element "Automation Testing Project period modal" are "visible"
    When click in "Save"
    Then verify the element "Automation Testing Project period modal" are "invisible"
    And verify the element "Resources Board Requirements name" are "visible"
    And verify the text element "System Administrator h/day" is "8.00 h/Day"
    And verify the text element "System Administrator hour" is "80.00 h"
    When click in "Gantt Chart"
    Then check to "Gantt Chart" has loaded
    When click in "Create"
    Then verify the element "Selected item: Automation Testing Project" are "visible"
    When click in "Task"
    And send text "Automation test task" to element "Name"
    And click in "Save"
    Then verify the element "Gantt Chart" are "visible"
    And verify that the text of the specified elements matches the expected values:
      | element      | value                      |
      | Name 1rt row | Automation Testing Project |
      | Name 2rt row | Automation Testing Task    |
