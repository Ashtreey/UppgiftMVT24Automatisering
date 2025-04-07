import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.UUID;

public class RegistrationSteps {

    WebDriver driver;
    WebDriverWait wait;

    // Denna metod skapar en unik e-postadress varje gång den kallas
    private String generateUniqueEmail() {
        String uniquePart = UUID.randomUUID().toString().substring(0, 8); // Generera en unik del med hjälp av UUID
        return "testuser_" + uniquePart + "@mailnesia.com"; // Skapa en Mailnesia-adress
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashtrey\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("the user enters {string} as first name")
    public void the_user_enters_as_first_name(String firstName) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname")));
        firstNameField.sendKeys(firstName);
    }

    @When("the user enters {string} as last name")
    public void the_user_enters_as_last_name(String lastName) {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_lastname")));
        lastNameField.sendKeys(lastName);
    }

    @When("the user enters a unique Mailnesia email")
    public void the_user_enters_a_unique_mailnesia_email() {
        String uniqueEmail = generateUniqueEmail(); // Generera en unik e-postadress
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress")));
        emailField.sendKeys(uniqueEmail); // Skriv den genererade e-postadressen
    }

    @When("the user confirms the email")
    public void the_user_confirms_the_email() {
        String uniqueEmail = generateUniqueEmail(); // Generera en unik e-postadress
        WebElement confirmEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress")));
        confirmEmailField.sendKeys(uniqueEmail); // Bekräfta samma e-postadress
    }

    @When("the user enters {string} as date of birth")
    public void the_user_enters_as_date_of_birth(String dob) {
        WebElement dobField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp")));
        dobField.sendKeys(dob);
        dobField.sendKeys(Keys.RETURN); // Press Enter to confirm the date
    }

    @When("the user enters {string} as password")
    public void the_user_enters_as_password(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password")));
        passwordField.sendKeys(password);
    }

    @When("the user confirms {string} as password")
    public void the_user_confirms_as_password(String password) {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword")));
        confirmPasswordField.sendKeys(password);
    }

    @When("the user selects {string} as basketball role")
    public void the_user_selects_as_basketball_role(String role) {
        WebElement roleLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='signup_basketballrole_18']")));

        roleLabel.click(); // Select "Coach"
    }

    @When("the user agrees to the Terms and Conditions")
    public void the_user_agrees_to_the_terms_and_conditions() {
        WebElement termsCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign_up_25")));
        termsCheckbox.click(); // Agree to Terms and Conditions
    }

    @When("the user agrees to the Code of Ethics and Conduct")
    public void the_user_agrees_to_the_code_of_ethics_and_conduct() {
        WebElement ethicsCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fanmembersignup_agreetocodeofethicsandconduct")));
        ethicsCheckbox.click(); // Agree to the Code of Ethics
    }

    @When("the user confirms being over {int} or a guardian")
    public void the_user_confirms_being_over_or_a_guardian(Integer age) {
        WebElement ageCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign_up_26")));
        ageCheckbox.click(); // Confirm being over 18 or a guardian
    }

    @When("the user submits the form")
    public void the_user_submits_the_form() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("join")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton); // Scroll to the submit button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton); // Click submit
    }

    @Then("the account should be created successfully")
    public void the_account_should_be_created_successfully() {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-message")));
        assertTrue(successMessage.isDisplayed());
    }


}
