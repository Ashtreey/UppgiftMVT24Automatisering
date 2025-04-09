Feature: User Registration on Basketball England
  As a new user
  I want to register on the website
  So that I can become a supporter

  # Positivt testfall - detta ska gå igenom hela registreringen och skapa ett konto.
  Scenario: Successful user registration
    Given the user is on the registration page
    When the user enters "Kalle" as first name
    And the user enters "Kofot" as last name
    And the user enters a unique Mailnesia email
    And the user confirms the email
    And the user enters "01/01/1987" as date of birth
    And the user enters "password123" as password
    And the user confirms "password123" as password
    And the user selects "Coach" as basketball role
    And the user agrees to the Terms and Conditions
    And the user agrees to being over 18 years old
    And the user agrees to the Code of Ethics and Conduct
    And the user submits the form
    Then the account should be created successfully

  # Scenario Outline - för att testa med olika input som ej är korrekta/fullständiga
  Scenario Outline: Registration validation with different inputs
    Given the user is on the registration page
    When the user enters "<firstName>" as first name
    And the user enters "<lastName>" as last name
    And the user enters a unique Mailnesia email
    And the user confirms the email
    And the user enters "<dob>" as date of birth
    And the user enters "<password>" as password
    And the user confirms "<confirmPassword>" as password
    And the user selects "<role>" as basketball role
    And the user "<termsAccepted>" to the Terms and Conditions
    And the user agrees to the Code of Ethics and Conduct
    And the user agrees to being over 18 years old
    And the user submits the form
    Then the registration should not be completed

    Examples:
      | firstName | lastName | dob        | password    | confirmPassword | role  | termsAccepted   | expectedOutcome                                                         |
      | Kalle     |          | 01/01/1987 | password123 | password123     | Coach | accepts         | Last Name is required                                                     |
      | Kalle     | Kofot    | 01/01/1987 | password123 | password456     | Coach | accepts         | Passwords do not match                                                    |
      | Kalle     | Kofot    | 01/01/1987 | password123 | password123     | Coach | does not accept | You must confirm that you have read and accepted our Terms and Conditions  |


  # Scenario Outline - detta ska gå igenom hela registreringen och skapa ett konto.
  Scenario Outline: Registration validation with correct input
    Given the user is on the registration page
    When the user enters "<firstName>" as first name
    And the user enters "<lastName>" as last name
    And the user enters a unique Mailnesia email
    And the user confirms the email
    And the user enters "<dob>" as date of birth
    And the user enters "<password>" as password
    And the user confirms "<confirmPassword>" as password
    And the user selects "<role>" as basketball role
    And the user "<termsAccepted>" to the Terms and Conditions
    And the user agrees to the Code of Ethics and Conduct
    And the user agrees to being over 18 years old
    And the user submits the form
    Then "<expectedOutcome>" should be displayed

    Examples:
      | firstName | lastName | dob        | password    | confirmPassword | role  | termsAccepted   | expectedOutcome                 |
      | Kalle     | Kofot    | 01/01/1987 | password123 | password123     | Coach | accepts         | Account created successfully    |
