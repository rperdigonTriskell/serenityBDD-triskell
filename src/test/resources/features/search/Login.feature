#@Login
#Feature: Login to the application
#
##//////////////////////////////////////////////////////////////////////////////
# #basic scenarios
#
#
#  @PROD
#  Scenario Outline: Successful login
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "username" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    Then check to "Dashboard" has loaded
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Failed login - Empty user
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User is required"
#
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Failed login - Incomplete domain
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "incomplete_username" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Incorrect Username. Domain name must be compound of a Name + Dot + Domain Extension."
#
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Failed login - Wrong name
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "jhon doe" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong Username Format. A Domain is required"
#
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Failed login - Wrong name domain and wrong password
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "1@1.1" to element "Username"
#    And send credential "111" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Authentication rejected, contact with administrator"
#
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Failed login - Empty password
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "1@1.1" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Password is required"
#
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Failed login - Wrong pasword
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "username" to element "Username"
#    And send credential "123456" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong user / password"
#
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: OK/close error - Empty user
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User is required"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                        | quit_error |
##      | https://intaws.triskellsoftware.com/triskell/  | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/  | OK         |
#      #      | https://intnr.triskellsoftware.com/triskell/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell | OK         |
##      | https://intaws.triskellsoftware.com/triskell/  | X          |
#      #      | https://intdev.triskellsoftware.com/triskell/  | X          |
#      #      | https://intnr.triskellsoftware.com/triskell/   | X          |
#      | https://ondemand.triskellsoftware.com/triskell | X          |
#
#
#  @PROD
#  Scenario Outline: OK/close error - Incomplete domain
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "incomplete_username" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Incorrect Username. Domain name must be compound of a Name + Dot + Domain Extension."
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                         | quit_error |
##      | https://intaws.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intnr.triskellsoftware.com/triskell/    | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/ | OK         |
##      | https://intaws.triskellsoftware.com/triskell/   | X          |
#      #      | https://intdev.triskellsoftware.com/triskell/   | X          |
#      #      | https://intnr.triskellsoftware.com/triskell/    | X          |
#      | https://ondemand.triskellsoftware.com/triskell/ | X          |
#
#
#  @PROD
#  Scenario Outline: OK/close error - Wrong name
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "jhon doe" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong Username Format. A Domain is required"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                         | quit_error |
##      | https://intaws.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intnr.triskellsoftware.com/triskell/    | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/ | OK         |
##      | https://intaws.triskellsoftware.com/triskell/   | X          |
#      #      | https://intdev.triskellsoftware.com/triskell/   | X          |
#      #      | https://intnr.triskellsoftware.com/triskell/    | X          |
#      | https://ondemand.triskellsoftware.com/triskell/ | X          |
#
#
#  @PROD
#  Scenario Outline: OK/close error - Wrong name domain and wrong password
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "1@1.1" to element "Username"
#    And send credential "111" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Authentication rejected, contact with administrator"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                         | quit_error |
##      | https://intaws.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intnr.triskellsoftware.com/triskell/    | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/ | OK         |
##      | https://intaws.triskellsoftware.com/triskell/   | X          |
#      #      | https://intdev.triskellsoftware.com/triskell/   | X          |
#      #      | https://intnr.triskellsoftware.com/triskell/    | X          |
#      | https://ondemand.triskellsoftware.com/triskell/ | X          |
#
#
#
#  @PROD
#  Scenario Outline: OK/close error - Empty password
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "1@1.1" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Password is required"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                         | quit_error |
##      | https://intaws.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/   | OK         |
#      #      | https://intnr.triskellsoftware.com/triskell/    | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/ | OK         |
##      | https://intaws.triskellsoftware.com/triskell/   | X          |
#      #      | https://intdev.triskellsoftware.com/triskell/   | X          |
#      #      | https://intnr.triskellsoftware.com/triskell/    | X          |
#      | https://ondemand.triskellsoftware.com/triskell/ | X          |
#
#
#  @PROD
#  Scenario Outline: OK/close error - Wrong password
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "username" to element "Username"
#    And send credential "123456" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong user / password"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                        | quit_error |
##      | https://intaws.triskellsoftware.com/triskell/  | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/  | OK         |
#      #      | https://intnr.triskellsoftware.com/triskell/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell | OK         |
##      | https://intaws.triskellsoftware.com/triskell/  | X          |
#      #      | https://intdev.triskellsoftware.com/triskell/  | X          |
#      #      | https://intnr.triskellsoftware.com/triskell/   | X          |
#      | https://ondemand.triskellsoftware.com/triskell | X          |
#
# #//////////////////////////////////////////////////////////////////////////////
# # Field Validation Tests
#
#
#  @PROD
#  Scenario Outline: Username and Password Length
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "short_credential" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username" to element "Username"
#    And send credential "short_credential" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "long_credential" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username" to element "Username"
#    And send credential "long_credential" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Allowed Characters
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "character_error_credential" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username" to element "Username"
#    And send credential "character_error_credential" to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: Spaced Blank User Pass
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "spaced_username" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username" to element "Username"
#    And send credential "spaced_password" to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      #      | https://intdev.triskellsoftware.com/triskell/  |
#      #      | https://intnr.triskellsoftware.com/triskell/   |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: SQL Injection
#    Given go to wrong web "<webside>"
#    Then check to "Login" hasn't loaded
#
#    Examples:
#      | webside                                                            |
##      | https://intaws.triskellsoftware.com/triskell/'admin' OR '1'='1'    |
#      #      | https://intdev.triskellsoftware.com/triskell/'admin' OR '1'='1'    |
#      #      | https://intnr.triskellsoftware.com/triskell/'admin' OR '1'='1'     |
##      | https://intaws.triskellsoftware.com/triskell/'password' OR '1'='1' |
#      #      | https://intdev.triskellsoftware.com/triskell/'password' OR '1'='1' |
#      #      | https://intnr.triskellsoftware.com/triskell/'password' OR '1'='1'  |
##      | https://intaws.triskellsoftware.com/triskell/‘ or 1=1;–.           |
#      #      | https://intdev.triskellsoftware.com/triskell/‘ or 1=1;–.           |
#      #      | https://intnr.triskellsoftware.com/triskell/‘ or 1=1;–.            |
#      | https://ondemand.triskellsoftware.com/triskell/‘ or 1=1;–.         |
#
#
#  @PROD
#  Scenario Outline: SQL Injection login
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "'admin' OR '1'='1'" to element "Username"
#    And send credential "'password' OR '1'='1'" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "‘ or 1=1;–." to element "Username"
#    And send credential "‘ or 1=1;–." to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                         |
##      | https://intaws.triskellsoftware.com/triskell/   |
#      #      | https://intdev.triskellsoftware.com/triskell/   |
#      #      | https://intnr.triskellsoftware.com/triskell/    |
#      | https://ondemand.triskellsoftware.com/triskell/ |
#
##
##  @PROD
##  Scenario Outline: XSS Injection
##    Given go to wrong web "<webside>" with XSS atack
##    Then check to "Login" hasn't loaded
##    Examples:
##      | webside                                       |
####      | https://intaws.triskellsoftware.com/triskell/ |
###      #      | https://intdev.triskellsoftware.com/triskell/ |
###      #      | https://intnr.triskellsoftware.com/triskell/  |
##      | https://ondemand.triskellsoftware.com/triskell  |
#
##
##  @PROD
##  Scenario Outline: XSS Injection login
##    Given go to web "<webside>"
##    Then check to "Login" has loaded
##    When send credential "xss" to element "Username"
##    And send credential "xss" to element "Password"
##    And click in "Validate"
##    And click in "X"
##    And send credential "username" to element "Username"
##    And send credential "spaced_password" to element "Password"
##    And click in "Validate"
##    Examples:
##      | webside                                       |
###      | https://intaws.triskellsoftware.com/triskell/ |
##      #      | https://intdev.triskellsoftware.com/triskell/ |
##      #      | https://intnr.triskellsoftware.com/triskell/  |
##      | https://ondemand.triskellsoftware.com/triskell  |
#
#
#  @PROD
#  Scenario Outline: Force Attack
#    Given go to web "<webside>"
#    Then check to "Login" has loaded
#    When send credential "" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User is required"
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
##      | https://intaws.triskellsoftware.com/triskell/  |
#      | https://ondemand.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: bad url
#    Given go to wrong web "<webside>"
#    Then check to "bad url" has loaded
#    Examples:
#      | webside                                         |
#      | https://intawss.triskellsoftware.com/triskell/  |
#      | https://intdevs.triskellsoftware.com/triskell/  |
#      | https://intnrs.triskellsoftware.com/triskell/   |
#      | https://intaws.triskellsoftwaree.com/triskell/  |
#      | https://intdev.triskellsoftwaree.com/triskell/  |
#      | https://intnr.triskellsoftwareee.com/triskell/  |
#      | https://intaws.triskellsoftware.como/triskell/  |
#      | https://intdev.triskellsoftware.como/triskell/  |
#      | https://intnr.triskellsoftware.como/triskell/   |
#      | https://ondemandr.triskellsoftware.com/triskell |
#
#
#  @PROD
#  Scenario Outline: bad url folder
#    Given go to web "<webside>"
#    Then check to "bad url folder" has loaded
#    And click in "retry"
#    Then check to "Login" has loaded
#    Examples:
#      | webside                                        |
##      | https://intaws.triskellsoftware.com/truskell/  |
##      | https://intdev.triskellsoftware.com/truskell/  |
##      | https://intnr.triskellsoftware.com/truskell/   |
#      | https://ondemand.triskellsoftware.com/truskell |