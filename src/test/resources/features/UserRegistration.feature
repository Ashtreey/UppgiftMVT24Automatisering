Feature: User Registration on Basketball England

  Scenario: Successful user registration
    Given the user is on the registration page
    When the user enters "Kalle" as first name
    And the user enters "Kofot" as last name
    And the user enters a unique Mailnesia email
    And the user enters "01/01/1987" as date of birth
    And the user enters "password123" as password
    And the user confirms "password123" as password
    And the user selects "Coach" as basketball role
    And the user agrees to the Terms and Conditions
    And the user agrees to the Code of Ethics and Conduct
    And the user confirms being over 18 or a guardian
    And the user submits the form
    Then the account should be created successfully

  Scenario: Registration fails when last name is missing
    Given the user is on the registration page
    When the user enters "Kalle" as first name
    And the user leaves last name field empty
    And the user enters a unique Mailnesia email
    And the user enters "01/01/1987" as date of birth
    And the user enters "password123" as password
    And the user confirms "password123" as password
    And the user selects "Coach" as basketball role
    And the user agrees to the Terms and Conditions
    And the user agrees to the Code of Ethics and Conduct
    And the user confirms being over 18 or a guardian
    And the user submits the form
    Then an error message should be displayed for missing last name

  Scenario: Registration fails when passwords do not match
    Given the user is on the registration page
    When the user enters "Kalle" as first name
    And the user enters "Kofot" as last name
    And the user enters a unique Mailnesia email
    And the user enters "01/01/1987" as date of birth
    And the user enters "password123" as password
    And the user confirms "password456" as password
    And the user selects "Coach" as basketball role
    And the user agrees to the Terms and Conditions
    And the user agrees to the Code of Ethics and Conduct
    And the user confirms being over 18 or a guardian
    And the user submits the form
    Then an error message should be displayed for password mismatch

  Scenario Outline: Registration validation with different inputs
    Given the user is on the registration page
    When the user enters "<firstName>" as first name
    And the user enters "<lastName>" as last name
    And the user enters a unique Mailnesia email
    And the user enters "<dob>" as date of birth
    And the user enters "<password>" as password
    And the user confirms "<confirmPassword>" as password
    And the user selects "<role>" as basketball role
    And the user "<termsAccepted>" to the Terms and Conditions
    And the user agrees to the Code of Ethics and Conduct
    And the user confirms being over 18 or a guardian
    And the user submits the form
    Then "<expectedOutcome>" should be displayed

    Examples:
      | firstName | lastName | dob        | password    | confirmPassword | role  | termsAccepted   | expectedOutcome                     |
      | Kalle     | Kofot    | 01/01/1987 | password123 | password123     | Coach | accepts         | Account created successfully        |
      | Kalle     |          | 01/01/1987 | password123 | password123     | Coach | accepts         | Error: Last name is required        |
      | Kalle     | Kofot    | 01/01/1987 | password123 | password456     | Coach | accepts         | Error: Passwords do not match       |
      | Kalle     | Kofot    | 01/01/1987 | password123 | password123     | Coach | does not accept | Error: Terms must be accepted       |
