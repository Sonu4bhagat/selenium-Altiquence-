Feature: SA ServiceNodeSSO redirection functionality

  Background:
    Given User should login using the valid credential
    And User should landed to the dashboard

  @ServiceNodeSO @TC1
  Scenario Outline: User should be login the service using superadmin credential
    And User click on the ServiceNodeSSO
    And User find the WABA service from the list
    And User find the Login button
    When User click login as Superadmin
    Then Redirects to the OCMP page