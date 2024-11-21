#Feature: Login to the application with domain
##
###  #//////////////////////////////////////////////////////////////////////////////
###  #basic scenarios
##
##
#  @PROD
#  Scenario Outline: Successful login
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "username_without_domain" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    Then check to "Dashboard" has loaded
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: Failed login - Empty user
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User is required"
#
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
##
##
##
#  @PROD
#  Scenario Outline: Failed login - Wrong name
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "jhon doe" to element "Username"
#    And send credential "12345" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong user / password"
#
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
##
##
#  @PROD
#  Scenario Outline: Failed login - Empty password
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "username_without_domain" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Password is required"
#
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#
#  @PROD
#  Scenario Outline: Failed login - Wrong password
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "username_without_domain" to element "Username"
#    And send credential "123456" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong user / password"
#
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: Ok/close error - Empty user
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User is required"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                              | quit_error |
#      | https://intaws.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | OK         |
#      | https://intaws.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | X         |
#
#
#
#  @PROD
#  Scenario Outline: Ok/close error - Wrong name and wrong password
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "1" to element "Username"
#    And send credential "111" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong user / password"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                              | quit_error |
#      | https://intaws.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | OK         |
#      | https://intaws.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | X         |
#
#
#
#  @PROD
#  Scenario Outline: Ok/close error - Empty password
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "1" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Password is required"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                              | quit_error |
#      | https://intaws.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | OK         |
#      | https://intaws.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | X         |
#
#
#  @PROD
#  Scenario Outline: Ok/close error - Wrong password
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "username_without_domain" to element "Username"
#    And send credential "123456" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "Wrong user / password"
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                              | quit_error |
#      | https://intaws.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | OK         |
#      | https://intaws.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | X         |
#
#  #//////////////////////////////////////////////////////////////////////////////
#  # Field Validation Tests
#
#
#  @PROD
#  Scenario Outline: username_without_domain and Password Length
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "short_credential" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username_without_domain" to element "Username"
#    And send credential "short_credential" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "long_credential" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username_without_domain" to element "Username"
#    And send credential "long_credential" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: Allowed Characters
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "character_error_credential" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username_without_domain" to element "Username"
#    And send credential "character_error_credential" to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: Spaced Blank User Pass
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "spaced_username_without_domain" to element "Username"
#    And send credential "password" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username_without_domain" to element "Username"
#    And send credential "spaced_password" to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: SQL Injection
#    Given go to wrong web "<webside>" with domain "domain"
#    Then check to "Login" hasn't loaded
#
#    Examples:
#      | webside                                                                        |
#      | https://intaws.triskellsoftware.com/triskell/init/domain/'admin' OR '1'='1'    |
#      #      | https://intdev.triskellsoftware.com/triskell/init/domain/'admin' OR '1'='1'    |
#      #      | https://intnr.triskellsoftware.com/triskell/init/domain/'admin' OR '1'='1'     |
#      | https://intaws.triskellsoftware.com/triskell/init/domain/'password' OR '1'='1' |
#      #      | https://intdev.triskellsoftware.com/triskell/init/domain/'password' OR '1'='1' |
#      #      | https://intnr.triskellsoftware.com/triskell/init/domain/'password' OR '1'='1'  |
#      | https://intaws.triskellsoftware.com/triskell/init/domain/‘ or 1=1;–.           |
#      #      | https://intdev.triskellsoftware.com/triskell/init/domain/‘ or 1=1;–.           |
#      #      | https://intnr.triskellsoftware.com/triskell/init/domain/‘ or 1=1;–.            |
#      | https://ondemand.triskellsoftware.com/triskell/init/domain/‘ or 1=1;–.         |
#
#
#  @PROD
#  Scenario Outline: SQL Injection login
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "'admin' OR '1'='1'" to element "Username"
#    And send credential "'password' OR '1'='1'" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "‘ or 1=1;–." to element "Username"
#    And send credential "‘ or 1=1;–." to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   |
#      #      | https://intnr.triskellsoftware.com/triskell/init/    |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: XSS Injection
#    Given go to wrong web "<webside>" with domain "domain" and XSS atack
#    Then check to "Login" hasn't loaded
#    Examples:
#      | webside                                                     |
#      | https://intaws.triskellsoftware.com/triskell/init/domain/ |
##      #      | https://intdev.triskellsoftware.com/triskell/init/domain/ |
##      #      | https://intnr.triskellsoftware.com/triskell/init/domain/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/domain/ |
#
#
#  @PROD
#  Scenario Outline: XSS Injection login
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "xss" to element "Username"
#    And send credential "xss" to element "Password"
#    And click in "Validate"
#    And click in "X"
#    And send credential "username" to element "Username"
#    And send credential "spaced_password" to element "Password"
#    And click in "Validate"
#    Examples:
#      | webside                                            |
#      | https://intaws.triskellsoftware.com/triskell/init/ |
##      #      | https://intdev.triskellsoftware.com/triskell/init/ |
##      #      | https://intnr.triskellsoftware.com/triskell/init/  |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: Force Attack
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "" to element "Username"
#    And send credential "" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User is required"
#    Examples:
#      | webside                                              |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://intaws.triskellsoftware.com/triskell/init/   |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#      | https://ondemand.triskellsoftware.com/triskell/init/ |
#
#
#  @PROD
#  Scenario Outline: bad url
#    Given go to wrong web "<webside>" with domain "domain"
#    Then check to "bad url" has loaded
#    Examples:
#      | webside                                               |
#      | https://intawss.triskellsoftware.com/triskell/init/   |
##      | https://intdevs.triskellsoftware.com/triskell/init/   |
##      | https://intnrs.triskellsoftware.com/triskell/init/    |
#      | https://intaws.triskellsoftwaree.com/triskell/init/   |
##      | https://intdev.triskellsoftwaree.com/triskell/init/   |
##      | https://intnr.triskellsoftwareee.com/triskell/init/   |
#      | https://intaws.triskellsoftware.como/triskell/init/   |
##      | https://intdev.triskellsoftware.como/triskell/init/   |
##      | https://intnr.triskellsoftware.como/triskell/init/    |
#      | https://ondemands.triskellsoftware.com/triskell/init/ |
#
##    # ////////////////////////////////////////////////////////////
##    # Especific domain test
#
#  @PROD
#  Scenario Outline: Ok/close error - Wrong format password
#    Given go to web "<webside>" with domain "domain"
#    Then check to "Login" has loaded
#    When send credential "username" to element "Username"
#    And send credential "123456" to element "Password"
#    And click in "Validate"
#    Then verify the text element "Error" is "User wrong format: \"@\" is not allowed when you log in directly into tenant page."
#    And click in "<quit_error>"
#    Then check to "Login" has loaded
#
#    Examples:
#      | webside                                              | quit_error |
#      | https://intaws.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | OK         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | OK         |
#      | https://intaws.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      #      | https://intdev.triskellsoftware.com/triskell/init/   | X         |
#      | https://ondemand.triskellsoftware.com/triskell/init/ | X         |