Feature: Login to the application

  Scenario: Successful login
    #AWS
    Given go to web "https://intaws.triskellsoftware.com/triskell/"
    #Prod
#    Given go to web "https://ondemand.triskellsoftware.com/triskell/"
      Then check to "login" has loaded
      When send credential "username" to element "username"
      And send credential "password" to element "password"
      And click in "validate"
      Then check to "home page" has loaded

#    Given a valid user is on the login page
#    When the user enters a valid username and password
#    And the user clicks on the login button
#    Then the user should be redirected to the home page
#
#  Scenario: Failed login
#    Given a user is on the login page
#    When the user enters an invalid username and password
#    And the user clicks on the login button
#    Then an error message should be displayed indicating the credentials are incorrect
#
#  Scenario: Required fields for login
#    Given a user is on the login page
#    When the user leaves one or both fields empty
#    And the user clicks on the login button
#    Then an error message should be displayed indicating both fields are required