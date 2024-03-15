package Base;

import Test.FillFormBasicTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    protected final String appUrl = "http://www.automationpractice.pl/index.php";
    protected final String webUrl = "https://cosmocode.io/automation-practice-webtable/";
    protected final String browserName = "chrome";
    protected final boolean headlessBrowser = false;
    protected WebDriver driver;
    private Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected final String expectedMessage = "Your message has been successfully sent to our team.";

    @BeforeEach
    void setApp(){
        //System.out.println("Start test");
        log.info("Start test");
        //inicjalizacja drivera
        driver = getDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //zaczeka 5 sekund aż się strona załaduje
        //otwarcie przeglądrki na URL
        driver.get(webUrl);
    }

    @AfterEach
    void teatDown(){
        driver.quit(); //zamyka proces przeglądarki
        //driver.close(); //zamyka okno przeglądarki (ale w procesach nadal będzie otwarta)
        //log.info("Driver is closed");
    }

    protected String getCurrencyForCountry(String country){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        int position = 0;
        List<WebElement> table = driver.findElements(By.cssSelector("#countries tbody tr"));

        for (WebElement x : table)
        {
            //log.info(x.getText());
            if (x.getText().toLowerCase().contains(country.toLowerCase())) break;
            position++;
        }
        int currency = 4;
        WebElement currencyOfCountry = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (position + 1) + ") td:nth-child("+(currency)+")"));
        return currencyOfCountry.getText();
    }

    protected String getCapitalForCountry(String country){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        int position = 0;
        List<WebElement> table = driver.findElements(By.cssSelector("#countries tbody tr"));

        for (WebElement x : table)
        {
            //log.info(x.getText());
            if (x.getText().toLowerCase().contains(country.toLowerCase())) break;
            position++;
        }
        int capital = 3;
        WebElement capitalOfCountry = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (position + 1) + ") td:nth-child("+(capital)+")"));
        return capitalOfCountry.getText();
    }

    protected WebDriver getDriver() {
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
