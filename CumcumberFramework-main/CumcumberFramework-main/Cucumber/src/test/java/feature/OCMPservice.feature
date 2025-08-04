Feature: Service account redirection from the OCMP

  Background:
    Given User should login using the valid credential
    And User click on the ServiceNodeSSO
    And User find the WABA service from the list
    And User find the Login button
    And User click login as Superadmin
    And Redirects to the OCMP page

  @ServiceNodeSO @TC1
  Scenario Outline: User should be redirect to the SSO Dashboard from service account section from OCMP
    And User click on the service account
    And User redirects to the service account board
    And User store the list of service accounts
    And User click on the next for Meta title active user
    When User find the Meta title active user then click on right
    Then User should landed to the SSO Dashboard