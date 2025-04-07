import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class RegistrationForm {
    public static void main(String[] args) {
        // Sätt upp WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashtrey\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Navigera till webbsidan
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");

        // Sätt upp explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fyll i förnamn
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname")));
        firstNameField.sendKeys("Kalle");

        // Fyll i efternamn
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_lastname")));
        lastNameField.sendKeys("Kofot");

        // Fyll i e-postadress
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress")));
        emailField.sendKeys("kallekofot4@mailnesia.com");

        // Fyll i bekräfta e-postadress
        WebElement confirmEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress")));
        confirmEmailField.sendKeys("kallekofot4@mailnesia.com");

        // Fyll i födelsedatum (datum i formatet dd/mm/yyyy)
        WebElement dobField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp")));
        dobField.sendKeys("01/01/1987");

        // Fyll i lösenord
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password")));
        passwordField.sendKeys("password123");

        // Fyll i bekräfta lösenord
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword")));
        confirmPasswordField.sendKeys("password123");

        // Klicka på "Coach"-checkbox
        WebElement coachLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='signup_basketballrole_18']")));
        coachLabel.click();

        // Klicka på "I agree to Terms and Conditions"-checkbox (NY RAD)
        WebElement termsCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_25']")));
        termsCheckboxLabel.click();

        // Klicka på "I would like to receive marketing communications"-checkbox (frivillig)
        WebElement marketingCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_27']")));
        marketingCheckboxLabel.click();

        // Klicka på "I agree to Code of Ethics and Conduct"-checkbox
        WebElement ethicsCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")));
        ethicsCheckboxLabel.click();

        // Klicka på "I am aged over 18 or am a person with parental responsibility"-checkbox
        WebElement ageResponsibilityCheckboxLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_26']")));
        ageResponsibilityCheckboxLabel.click();

        // Vänta tills submit-knappen är klickbar och scrolla till den
        WebElement confirmAndJoinButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("join")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmAndJoinButton);

        // Klicka på "Confirm and Join"-knappen via JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmAndJoinButton);


        // driver.quit();
    }
}
