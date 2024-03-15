package Test;

import Base.BaseTest;
import Pages.ContactPage;
import Pages.MainPage;
import Pages.SuccessfullMessagePage;
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

public class FillFormAdvanceTest extends BaseTest {
        private Logger log = LoggerFactory.getLogger(FillFormAdvanceTest.class);
        MainPage mainPage;
        ContactPage contactPage;
        SuccessfullMessagePage successfullMessagePage;

    @Test
    void fillFormBasicScenario() {
        //należy utworzyć obiekty klas wszytkich pages
        mainPage = new MainPage(driver);
        contactPage = new ContactPage(driver);
        successfullMessagePage = new SuccessfullMessagePage(driver);

        // Krok 1. Kliknąć Contact us
        mainPage.clickContactLink();

        //Krok 2. Uzupełnić formularz i klinąć przycisc
        contactPage.selectOptionFromDropdownList("Webmaster");
        contactPage.inputEmailAdress("mail@op.pl");
        contactPage.inputOrderNumber("1235");
        contactPage.inputMessage("""
                To jest pierwsza linijka.
                To jest druga linijka.
                To jest ostatnia linijka.
                """);
        contactPage.inputFile("C:\\test.txt");
        contactPage.sendButtonClick();

        //Krok 3. Asercja
       assertThat(successfullMessagePage.getSuccessMessageText()).isEqualTo(expectedMessage);
        log.info("Step 8: Wiadomość prawidłowa");


        //System.out.println("Test stop");
        log.info("Test stop");
        //log.debug("Tutaj jest wiadomość z debuugera");

    }

}
