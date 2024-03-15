package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class FillFormBasicTest {
    private final String browserName = "chrome";
    private final boolean headlessBrowser = false;
    private WebDriver driver;
    private final String appUrl = "http://www.automationpractice.pl/index.php";
    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

    @Test
    void fillFormBasicScenario() {
        //System.out.println("Start test");
        log.info("Start test");
        //inicjalizacja drivera
        driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //zaczeka 5 sekund aż się strona załaduje
        //otwarcie przeglądrki na URL
        driver.get(appUrl);

        //Step 1. Click "contact us"
        WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link a"));
        contactUsLink.click();
        log.info("Step 1: Contact us kliknięty");

        //Step 2. Wybierz  subject heading
        WebElement dropdownList = driver.findElement(By.id("id_contact"));
        Select select = new Select(dropdownList);
        select.selectByVisibleText("Webmaster");
        log.info("Step 2: Subject heading wybrany prawidłowo");

        //Step 3. Wpisz e-mail
        WebElement emailInput = driver.findElement(By.cssSelector("#email"));
        emailInput.sendKeys("mail@op.pl");
        log.info("Step 3: Email wpisany prawidłowo");

        //Step 4. Wpisz order reference
        //WebElement orderRefInput = driver.findElement(By.cssSelector("#id_order"));
        WebElement orderRefInput = driver.findElement(By.id("id_order"));
        orderRefInput.sendKeys("1235");
        log.info("Step 4: Order reference wpisany prawidłowo");

        //Step 5. Wpisz Message
        WebElement messageTextArea = driver.findElement(By.id("message"));
        messageTextArea.sendKeys("""
                To jest pierwsza linijka. 
                To jest druga linijka.
                To jest ostatnia linijka.
                """);
        log.info("Step 5: Message wpisana prawidłowo");

        //Step 6. Załącz plik
        WebElement fileUpload = driver.findElement(By.id("fileUpload"));
        fileUpload.sendKeys("C:\\test.txt");
        log.info("Step 6: Plik został załączony");

        //Step 7. Click send button
        WebElement sendButton = driver.findElement(By.id("submitMessage"));
        sendButton.click();
        log.info("Step 7: Send button został kliknięty");

        //Step 8. Weryfikacja success message
        WebElement successMessage = driver.findElement(By.className("alert-success"));
        String expectedMessage = "Your message has been successfully sent to our team.";
        String actualMessage = successMessage.getText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
        log.info("Step 8: Wiadomość prawidłowa");


        //System.out.println("Test stop");
        log.info("Test stop");
        log.debug("Tutaj jest wiadomość z debuugera");
        driver.quit(); //zamyka proces przeglądarki
        //driver.close(); //zamyka okno przeglądarki (ale w procesach nadal będzie otwarta)
        log.info("Driver is closed");
    }

    private WebDriver getDriver() {
        switch (this.browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); //przeglądarka dostosuje się rozmiarem do naszego monitora
                chromeOptions.addArguments("--remote-allow-origins=*"); //oddanie kontroli przeglądarce
                if (this.headlessBrowser) {
                    chromeOptions.addArguments("--headLess");
                }
                driver = new ChromeDriver(chromeOptions); //otwieranie przeglądarki z uwzględnieniem chromeOptions
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
            }
            default -> System.out.println("Przeglądarka nie jest wspierana");
        }
        return driver;
    }
}
