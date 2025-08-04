Feature: Verify the log in functionality

  @login @TC1
  Scenario Outline: User should be able to login using valid credential
    Given User landed on the login page
    And User Enter the username in the field "<email>"
    And User enter the password in the field "<password>"
    And User click on the login button
    And User enter the OTP
    When User click on the verify button
    Then User redirects to the Dashboard


    Examples:
      | email                          | password |
      | sonu.bhagat+3@altiquence.com   | Sonu@altiquence2 |
      | sonu.bhagat+6@altiquence.com   | Test@12346 |
      | sonu.bhagat+18@altiquence.com  | Test@12346 |


  @login @TC2
  Scenario: User should get the error message when entering invalid credential
    Given User landed on the login page
    And User Enter the username in the field "Sonu.bhagat@altiquence.com"
    And User enter the password in the field "Iam@123456"
    When User click on the login button
    Then User get the error message

  @login @TC3
  Scenario: User should redirect to the forgot password page and successfully send the rest password link
    Given User landed on the login page
    And User click on the forgot password link
    And User enter the email "Test@test.com"
    When User Click on the Get Reset password link
    Then User should landed to the Check your mail page

  @login @TC4
  Scenario: User should redirect to the login from forgot password page and login using valid credential
    Given User landed on the login page
    And User click on the forgot password link
    And User enter the email "Test@test.com"
    And User Click on the Get Reset password link
    And User should landed to the Check your mail page
    And User click on the Back to login button
    And User Enter the username in the field "<email1>"
    And User enter the password in the field "<password1>"
    And User click on the login button
    And User enter the OTP
    When User click on the verify button
    Then User redirects to the Dashboard

    Examples:
      |email1                         |password1         |
      |sonu.bhagat+3@altiquence.com   | Sonu@altiquence2 |


