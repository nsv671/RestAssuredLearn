Feature: Validataing add place API

Scenario: Verify if place is being successfully added unsing addPlaceAPI
    Given Add place payload
    When User calls addPlaceAPI with post http request
    Then The API call is success with succes code 200
    And Status code response body is OK
