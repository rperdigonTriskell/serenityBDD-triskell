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
    Then verify the element "System Administrator checkbox" are "visible"

