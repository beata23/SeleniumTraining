package Pages;

import Test.FillFormBasicTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage {
    private Logger log = LoggerFactory.getLogger(MainPage.class);

    //odpowiednik: WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link a"));
    @FindBy(css = "#contact-link a")
    private WebElement contactUsLink;

    //odpowiednik: contactUsLink.click();
    public void clickContactLink(){
        contactUsLink.click();
        log.info("Step 1: Contact us kliknięty");
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver,this); //konstruktor zawsze wygląda tak samo w każdej klasie Page
    }
}
