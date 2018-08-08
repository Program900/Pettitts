Feature: ChooseyourDestination
  In order to search for a Destination
  As a Leisure Travel
  I want to Choose Destination

  @sanity
  Scenario: Count Destination
    Given I am on HomePage
    And  I have  ChooseyourDestination
    When I click on ChooseyourDestination
    Then  I should see '38' Destination

  Scenario: Options  Destination
    Given I am on HomePage
    And  I have  ChooseyourDestination
    When I click on ChooseyourDestination
    Then I should see OptionsOfDestinationCollection

  Scenario: Choose Destination
    Given I am on HomePage
    And  I have  ChooseyourDestination
    When I click on ChooseyourDestination
    And  I select any ChooseyourDestination
    Then  I should see selected ChooseyourDestination

