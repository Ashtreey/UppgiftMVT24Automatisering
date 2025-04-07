# Basketball England - Automatisk Registreringstest

## Projektbeskrivning
Detta projekt automatiserar registreringsflödet för https://membership.basketballengland.co.uk/NewSupporterAccount med hjälp av:

- 🧪 Cucumber (Gherkin BDD)
- 🧰 Selenium WebDriver
- ⚙️ JUnit 5
- 🌐 Körning i Chrome & Firefox

## Funktioner
- Scenario Outline för datadrivna tester
- Stöd för flera webbläsare
- Explicit Wait-metoder
- Automatiska asserts för alla valideringspunkter

## Körning av tester
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
