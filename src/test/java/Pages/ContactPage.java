package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactPage {

    private Logger log = LoggerFactory.getLogger(ContactPage.class);
//    //Step 2. Wybierz  subject heading
//    WebElement dropdownList = driver.findElement(By.id("id_contact"));
//    Select select = new Select(dropdownList);
//        select.selectByVisibleText("Webmaster");
//        log.info("Step 2: Subject heading wybrany prawidłowo");

//     //Step 3. Wpisz e-mail
//    WebElement emailInput = driver.findElement(By.cssSelector("#email"));
//        emailInput.sendKeys("mail@op.pl");
//        log.info("Step 3: Email wpisany prawidłowo");
//
//    //Step 4. Wpisz order reference
//    //WebElement orderRefInput = driver.findElement(By.cssSelector("#id_order"));
//    WebElement orderRefInput = driver.findElement(By.id("id_order"));
//        orderRefInput.sendKeys("1235");
//        log.info("Step 4: Order reference wpisany prawidłowo");
//
//    //Step 5. Wpisz Message
//    WebElement messageTextArea = driver.findElement(By.id("message"));
//        messageTextArea.sendKeys("""
//                To jest pierwsza linijka.
//                To jest druga linijka.
//                To jest ostatnia linijka.
//                """);
//        log.info("Step 5: Message wpisana prawidłowo");
//
//    //Step 6. Załącz plik
//    WebElement fileUpload = driver.findElement(By.id("fileUpload"));
//        fileUpload.sendKeys("C:\\test.txt");
//        log.info("Step 6: Plik został załączony");
//
//    //Step 7. Click send button
//    WebElement sendButton = driver.findElement(By.id("submitMessage"));
//        sendButton.click();
//        log.info("Step 7: Send button został kliknięty");

    @FindBy(css = "#id_contact")
    private WebElement dropdownList;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#id_order")
    private WebElement orderRefInput;

    @FindBy(id = "message")
    private WebElement messageTextArea;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "submitMessage")
    private WebElement sendButton;

    //Konstruktor
    public ContactPage(WebDriver driver) {
        PageFactory.initElements(driver,this); //konstruktor zawsze wygląda tak samo w każdej klasie Page
    }

    //Step 2
    public void selectOptionFromDropdownList(String option){
        Select select = new Select(dropdownList);
        select.selectByVisibleText(option);
        log.info("Step 2: Subject heading wybrany prawidłowo: "  + option);
    }

    //Step 3
    public void inputEmailAdress(String email){
        emailInput.sendKeys(email);
        log.info("Step 3: Email wpisany prawidłowo: " + email);
    }

    //Step 4. Wpisz order reference
    public void inputOrderNumber(String idOrderNumber){
        orderRefInput.sendKeys(idOrderNumber);
        log.info("Step 4: Order reference wpisany prawidłowo: " + idOrderNumber);
    }

    //Step 5
    public void inputMessage(String message){
        messageTextArea.sendKeys(message);
        log.info("Step 5: Message wpisana prawidłowo: " + message);
    }

    //Step 6
    public void inputFile(String filePath){
        fileUpload.sendKeys(filePath);
        log.info("Step 6: Plik został załączony: " + filePath);
    }

    //Step 7
    public void sendButtonClick(){
        sendButton.click();
        log.info("Step 7: Send button został kliknięty");
    }

}
