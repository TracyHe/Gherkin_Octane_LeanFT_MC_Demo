#Auto generated Octane revision tag
@TID2001REV0.1.0
Feature: google web search

  @iPad
  Scenario: Find Sogeti's homepage using google search #2
    Given User has opened google homepage
    When searching for Sogeti
    And clicking the Sogeti link
    Then The Sogeti homepage is displayed