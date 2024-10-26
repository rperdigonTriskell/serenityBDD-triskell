Feature: Dashboard parts

  Background:
    Given go to web Triskell
    Then check to "Login" has loaded
    When send credential "username" to element "Username"
    And send credential "password" to element "Password"
    And click in "Validate"
    Then check to "Dashboard" has loaded
    When click in "Sidebar Timesheet"
    Then check to "Timesheet" has loaded

  @AWS
  Scenario: See All components Timesheet
    When click in "Timesheet"
    Then check to the following "Timesheet" elements are:
      | element                  | visibility |
      | My Timesheet             | visible    |
      | Project Manager Approval | visible    |
      | Administrator Approval   | visible    |
      | Timesheet Summary        | visible    |
      | Timesheet                | visible    |

    And check to the following "Timesheet board" elements are:
      | element                  | visibility |
      | title                    | visible    |
      | activity board           | present    |
      | time board               | present    |
      | Add Activities           | visible    |
      | Delete                   | visible    |
      | Generate                 | visible    |
      | Comment                  | visible    |
      | Path Breakdown Structure | visible    |
      | Supersede a User         | visible    |
      | Save as Default View     | visible    |
      | Manage grid density      | visible    |
      | Legend                   | visible    |
      | Configuration            | visible    |
