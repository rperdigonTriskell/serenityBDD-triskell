#Feature: Project parts
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
#    When click in sidebar "Project"
#    Then check to "Project" has loaded
#    When if the table Project "not empty", "delete" an Project
#
#  @PROD
#  Scenario: create project
#  When click in Project "add Project"
#  Then verify the element Project "Create New Project" are "visible"
#  When send text Project "Automation Testing Project" to element "Name input"
#  And click in Project "Choose Parent button"
#  Then verify the element Project "Add Object as New Parent" are "visible"
#  When send text Project "testing" to element "search parent"
#  When click in Project "testing"
#  Then verify the element Project "Create New Project" are "visible"
#  When click in Project "save"
#  Then check to "Automation Testing Project" has loaded
#
#
#  @PROD
#  Scenario: delete project
#    When click in Project "add Project"
#    Then verify the element Project "Create New Project" are "visible"
#    When send text Project "Automation Testing Project" to element "Name input"
#    And click in Project "Choose Parent button"
#    Then verify the element Project "Add Object as New Parent" are "visible"
#    When send text Project "testing" to element "search parent"
#    When click in Project "testing"
#    Then verify the element Project "Create New Project" are "visible"
#    When click in Project "save"
#    Then check to "Automation Testing Project" has loaded
#    When click in Automation Testing Project sidebar "Main Menu"
#    Then verify the element Automation Testing Project "Project" are "visible"
#    When click in Automation Testing Project sidebar "Project"
#    Then check to "Project" has loaded
#    When send text and enter Project "Automation Testing Project" to element "search parent"
#    Then wait for loading
#    Then verify the following Project elements on the "project board" should match the expected data:
#      | Check   | [empty] | FAV     | ATT     | NAME                       | PROJECT TYPE | DESCRIPTION | HEALTH  | WORKFLOW   | START DATE | FINISH DATE | TREND  | DELAY  | %COMPLETED | DURATION | DURATION UNIT | SCORE | TECH | RISK | EST. WORK | ACT. WORK | ACT. UNIT | PROJECT MANAGER |
#      | [empty] | [empty] | [empty] | [empty] | Automation Testing Project | [empty]      | [empty]     | Correct | Initiation | [empty]    | [actual date]  | Stable | 0 days | 0 %        | 0        | [empty]       | 0     | None | None | 0 hours   | 0 Hours   | [empty]   | [empty]         |
#    Then wait for loading
#    When click in Project "all activities checkbox"
#    And click in Project "delete"
#    Then verify the element Project "delete anwser" are "visible"
#    And click in Project "yes"
#    Then wait for loading
#    When verify the text element Project board "empty project board" is ""
