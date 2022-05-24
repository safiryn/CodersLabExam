Feature: MyStore_Zad1

  Scenario Outline: ser can add his delivery adress
    Given open Chrome browser at MyStore Homepage
    When user click SightInButton
    And user go to new adresses add page
    And user provide his country
    And user provide his alias <alias>
    And user provide his adress <adress>
    And user provide his city  <city>
    And user provide his postalCode <postalCode>
    And user provide his phone <phone>

    Then user is successfully registered as <expectedAlias> <expectedAdress> <expectedCity> <expectedPostalCode> <expectedPhone>

    And close browser

    Examples:
      | alias | adress      | city | postalCode | phone      | expectedAlias| expectedAdress | expectedCity | expectedPostalCode | expectedPhone |
      | Mati  | Piotrkowska | Lodz | 91-000    | 444555666 | Mati         |Piotrkowska    |  Lodz         | 91-000            | 444555666   |
