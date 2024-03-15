package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuccessfullMessagePage {

//    //Step 8. Weryfikacja success message
//    WebElement successMessage = driver.findElement(By.className("alert-success"));
//    String expectedMessage = "Your message has been successfully sent to our team.";
//    String actualMessage = successMessage.getText();
//    assertThat(actualMessage).isEqualTo(expectedMessage);
//        log.info("Step 8: Wiadomość prawidłowa");

    private Logger log = LoggerFactory.getLogger(SuccessfullMessagePage.class);

    @FindBy(className = "alert-success")
    private WebElement successMessage;

    public SuccessfullMessagePage(WebDriver driver) {
        PageFactory.initElements(driver,this); //konstruktor zawsze wygląda tak samo w każdej klasie Page
    }

    //Step 8
    public String getSuccessMessageText(){
        return successMessage.getText();
    }
}
