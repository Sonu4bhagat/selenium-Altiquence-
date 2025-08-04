Feature: Verify the Customer Org Functionality

  Background:
    Given User should login using the valid credential
    And User should landed to the dashboard

  @CustomerORG @TC1
  Scenario: User should be able to find the searched item
    And User should click on the CustomerORG
    And User should click on the search field.
    When User enter the text in search field "<svCustomerID>"
    Then Searched result should appears

    Examples:
    |svCustomerID|
    |SV1315794031|

  @CustomerORG @TC2
  Scenario: User should be able to redirect to the wallet page
    And User should click on the CustomerORG
    And User should click on the search field.
    And User enter the text in search field "<svCustomerID>"
    And Searched result should appears
    When User click on the next button
    Then created wallet should appears

    Examples:
      |svCustomerID|
      |SV1315794031|

  @CustomerORG @TC3
  Scenario: User should be able to redirect to the Organizations detail section
    And User should click on the CustomerORG
    And User should click on the search field.
    And User enter the text in search field "SV1315794031"
    And Searched result should appears
    And User click on the next button
    When User click on the organization detail
    Then User should get the information detail page


