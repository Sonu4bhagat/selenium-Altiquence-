Feature: SA Dashboard service redirection functionality

  Background:
    Given User should login using the valid credential
    And User should landed to the dashboard

  @Dashboard @TC1
  Scenario Outline: User should redirects to the IVR Service
    When User click on the IVR
    Then User redirects to the IVR page

  @Dashboard @TC2
  Scenario: User should redirects to the OBD Service
    When User click on the OBD
    Then User redirects to the OBD page

  @Dashboard @TC3
  Scenario: User should redirects to the CCS Service
    When User click on the CCS
    Then User redirects to the CCS page

  @Dashboard @TC4
  Scenario: User should redirects to the WABA Service

    When User click on the WABA
    Then User redirects to the WABA page