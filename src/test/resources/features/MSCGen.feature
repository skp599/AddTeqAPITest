Feature: API Testing
  Scenario: Verify the webpage
    When the webpage is accessible
    Then it returns a 200 status code
    And it has a valid title
#    And it has an image
#    And it has a description of the page
    And it has Author details
#    And it has Published date
