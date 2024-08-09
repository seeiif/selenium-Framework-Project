Feature: User Registration
  I want to check that user can register to our e-commerce website

  Scenario Outline: User Registration
    Given the user in the home page
    When I click on  register link
    And I enter the user data "<firstname>", "<lastname>", "<email>", "<password>"
    Then The registration page displayed successfully

    Examples:
      | firstname | lastname | email         | password |
      | seif      | kchiche  | s.t@gmail.com | 125535   |
      | test      | test     | t.t@gmail.com | 4455444  |