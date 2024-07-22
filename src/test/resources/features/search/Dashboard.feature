Feature: Dashboard parts

  Scenario Outline: Successful login
    Given go to web "<webside>"
    Then check to "login" has loaded
    When send credential "username" to element "username"
    And send credential "password" to element "password"
    And click in "validate"
    Then check to "dashboard" has loaded
    Examples:
      | webside                                        |
#      | https://intaws.triskellsoftware.com/triskell/  |
#      | https://intdev.triskellsoftware.com/triskell/  |
#      | https://intnr.triskellsoftware.com/triskell/   |
      | https://ondemand.triskellsoftware.com/triskell |
