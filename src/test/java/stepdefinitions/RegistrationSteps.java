package stepdefinitions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.UUID;

public class RegistrationSteps {

    WebDriver driver;  // WebDriver används för att styra webbläsaren
    WebDriverWait wait;  // WebDriverWait används för att vänta på specifika tillstånd (t.ex. synliga eller klickbara element)
    String uniqueEmail;  // För att hålla det unika e-postadressen som genereras för varje test

    // Metod som genererar en unik e-postadress med hjälp av UUID
    private String generateUniqueEmail() {
        String uniquePart = UUID.randomUUID().toString().substring(0, 8);  // Genererar en unik del av e-postadressen
        return "testuser_" + uniquePart + "@mailnesia.com";  // Returnerar den unika Mailnesia e-postadressen
    }

    // Väntemetod för att säkerställa att ett element är synligt
    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  // Väntar tills elementet är synligt
    }

    // Väntemetod för att säkerställa att ett element är klickbart
    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));  // Väntar tills elementet kan klickas på
    }

    // Metod för att fylla i ett fält via dess id
    private void fillFieldById(String id, String value) {
        WebElement field = waitUntilVisible(By.id(id));  // Väntar tills fältet är synligt
        field.clear();  // Rensar eventuellt tidigare värde
        field.sendKeys(value);  // Fyller i det nya värdet
    }

    // Setup-metod som körs innan varje test
    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");  // Hämtar vilken webbläsare som ska användas (standard: chrome)

        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Ashtrey\\ZAP\\webdriver\\windows\\64\\geckodriver.exe");  // Ställer in Firefox WebDriver
            driver = new FirefoxDriver();  // Startar Firefox
        } else {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashtrey\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");  // Ställer in Chrome WebDriver
            driver = new ChromeDriver();  // Startar Chrome
        }

        driver.manage().deleteAllCookies();  // Rensar alla cookies
        driver.manage().window().maximize();  // Maximerar webbläsarfönstret
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Sätter en timeout på 10 sekunder för väntetider
    }

    // TearDown-metod som körs efter varje test
    @After
    public void tearDown() {
        if (driver != null) driver.quit();  // Stänger ner webbläsaren om den är öppen
    }

    // Gherkin-steget som leder användaren till registreringssidan
    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");  // Navigerar till registreringssidan
    }

    // Gherkin-steget för att fylla i förnamnet
    @When("the user enters {string} as first name")
    public void the_user_enters_as_first_name(String firstName) {
        fillFieldById("member_firstname", firstName);  // Anropar metoden för att fylla i förnamnet
    }

    // Gherkin-steget för att fylla i efternamnet
    @When("the user enters {string} as last name")
    public void the_user_enters_as_last_name(String lastName) {
        fillFieldById("member_lastname", lastName);  // Anropar metoden för att fylla i efternamnet
    }

    // Gherkin-steget för att lämna efternamnsfältet tomt
    @When("the user leaves last name field empty")
    public void the_user_leaves_last_name_field_empty() {
        fillFieldById("member_lastname", "");  // Lämnar efternamnsfältet tomt
    }

    // Gherkin-steget för att fylla i ett unikt Mailnesia-e-postadress
    @When("the user enters a unique Mailnesia email")
    public void the_user_enters_a_unique_mailnesia_email() {
        uniqueEmail = generateUniqueEmail();  // Genererar en unik e-postadress
        fillFieldById("member_emailaddress", uniqueEmail);  // Fyller i e-postadressen
    }

    // Gherkin-steget för att bekräfta e-postadressen
    @When("the user confirms the email")
    public void the_user_confirms_the_email() {
        fillFieldById("member_confirmemailaddress", uniqueEmail);  // Fyller i bekräftelsen av e-postadressen
    }

    // Gherkin-steget för att fylla i födelsedatum
    @When("the user enters {string} as date of birth")
    public void the_user_enters_as_date_of_birth(String dob) {
        WebElement dobField = waitUntilVisible(By.id("dp"));  // Väntar tills födelsedatumfältet är synligt
        dobField.sendKeys(dob);  // Fyller i födelsedatum
        dobField.sendKeys(Keys.RETURN);  // Bekräftar genom att trycka Enter
    }

    // Gherkin-steget för att fylla i lösenord
    @When("the user enters {string} as password")
    public void the_user_enters_as_password(String password) {
        fillFieldById("signupunlicenced_password", password);  // Fyller i lösenordet
    }

    // Gherkin-steget för att bekräfta lösenordet
    @When("the user confirms {string} as password")
    public void the_user_confirms_as_password(String password) {
        fillFieldById("signupunlicenced_confirmpassword", password);  // Fyller i bekräftelsen av lösenordet
    }

    // Gherkin-steget för att välja en roll inom basket
    @When("the user selects {string} as basketball role")
    public void the_user_selects_as_basketball_role(String role) {
        // Klickar på checkboxen för "Coach"-rollen
        waitUntilClickable(By.cssSelector("label[for='signup_basketballrole_18']")).click();
    }

    // Gherkin-steget för att godkänna uppförandekod
    @When("the user agrees to the Code of Ethics and Conduct")
    public void the_user_agrees_to_the_code_of_ethics_and_conduct() {
        WebElement ethicsCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")));
        ethicsCheckboxLabel.click();  // Klickar på godkänn-kryssrutan för etiska riktlinjer
    }

    // Gherkin-steget för att bekräfta om användaren är över 18 eller en vårdnadshavare
    @When("the user confirms being over {int} or a guardian")
    public void the_user_confirms_being_over_or_a_guardian(Integer age) {
        WebElement ageCheckbox = waitUntilVisible(By.id("sign_up_26"));
        if (!ageCheckbox.isSelected()) {  // Om checkboxen inte är markerad
            ageCheckbox.click();  // Klicka på checkboxen för att markera den
        }
    }

    // Gherkin-steget för att skicka in formuläret
    @When("the user submits the form")
    public void the_user_submits_the_form() {
        WebElement confirmAndJoinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("join")));  // Väntar på att knappen ska bli synlig
        wait.until(ExpectedConditions.elementToBeClickable(confirmAndJoinButton));  // Väntar tills knappen kan klickas

        // Scrollar till knappen för att säkerställa att den är synlig
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmAndJoinButton);

        // Klickar på knappen för att skicka formuläret
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmAndJoinButton);
    }

    // Gherkin-steget för att kontrollera att kontot skapades framgångsrikt
    @Then("the account should be created successfully")
    public void the_account_should_be_created_successfully() {
        try {
            Thread.sleep(2000);  // Väntar i 2 sekunder för att ge sidan tid att ladda
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentUrl = driver.getCurrentUrl();  // Hämtar den nuvarande URL:en
        assertTrue(currentUrl.contains("SignUpConfirmation"), "Expected to be on sign-up confirmation page, but was on: " + currentUrl);
    }

    // Gherkin-steget för att visa felmeddelande när efternamn saknas
    @Then("an error message should be displayed for missing last name")
    public void an_error_message_should_be_displayed_for_missing_last_name() {
        WebElement error = waitUntilVisible(By.id("member_lastname-error"));  // Väntar tills felmeddelandet för efternamn är synligt
        assertEquals("Last Name is required", error.getText().trim());  // Verifierar att felmeddelandet är korrekt
    }

    // Gherkin-steget för att visa felmeddelande vid lösenordsdiskrepans
    @Then("an error message should be displayed for password mismatch")
    public void an_error_message_should_be_displayed_for_password_mismatch() {
        WebElement error = waitUntilVisible(By.id("signupunlicenced_confirmpassword-error"));  // Väntar på felmeddelande för lösenordsdiskrepans
        assertTrue(error.getText().toLowerCase().contains("same as password"), "Password mismatch error was not displayed correctly");
    }

    // Gherkin-steget för att visa felmeddelande vid ej accepterade villkor
    @Then("an error message should be displayed for terms not accepted")
    public void an_error_message_should_be_displayed_for_terms_not_accepted() {
        WebElement errorBox = waitUntilVisible(By.className("validation-summary-errors"));  // Väntar på felmeddelande för ej accepterade villkor
        assertTrue(errorBox.getText().contains("must accept"), "Expected error message for terms acceptance, but none was found.");
    }

    // Gherkin-steget för att användaren ska godkänna villkoren
    @And("the user agrees to the Terms and Conditions")
    public void theUserAgreesToTheTermsAndConditions() {
        WebElement termsCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_25']")));
        termsCheckboxLabel.click();  // Klickar på godkänn-kryssrutan för villkoren
    }

    // Placeholder för ett Gherkin-stege för att visa ett meddelande (kan implementeras vid behov)
    @Then("{string} should be displayed")
    public void shouldBeDisplayed(String message) {
        // Implementera logik för att kolla om meddelandet visas om nödvändigt
    }

    // Gherkin-steget för att bekräfta om användaren är över 18 år gammal
    @And("the user agrees to being over {int} years old")
    public void theUserAgreesToBeingOverYearsOld(int age) {
        WebElement ageResponsibilityCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_26']")));
        ageResponsibilityCheckboxLabel.click();  // Klickar på checkboxen för att bekräfta ålder
    }

    // Gherkin-steget för att användaren ska godkänna villkoren (alternativ metod)
    @And("the user {string} to the Terms and Conditions")
    public void theUserToTheTermsAndConditions(String arg0) {
        WebElement termsCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_25']")));
        termsCheckboxLabel.click();  // Klickar på godkänn-kryssrutan för villkoren
    }
}
