package Test;

import Base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTableTestNew extends BaseTest {
    private Logger log = LoggerFactory.getLogger(WebTableTest.class);
    @Test
    public void webTableVerification()
    {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        //policz ilość wierszy w tabeli
        List<WebElement> table = driver.findElements(By.cssSelector("#countries tbody tr"));
        int size = table.size();
        int countriesCount = size - 1;

        log.info("Number of countries: " + countriesCount);
        assertThat(countriesCount).isEqualTo(196);

        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".hasVisited[type='checkbox']"));
        String country = "Poland";
        int position = 0;

        for (WebElement x : table)
        {
            //log.info(x.getText());
            if (x.getText().toLowerCase().contains(country.toLowerCase())) break;
            position++;
        }
        //position-=1;
        //scroll to element
        new Actions(driver)
                .scrollToElement(checkboxes.get(position+8)).perform();
        checkboxes.get(position).click();

        Assertions.assertTrue(checkboxes.get(position).isSelected());

        //check capital of Poland
        int capital = 3;
        WebElement capitalOfPoland = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (position + 1) + ") td:nth-child("+(capital)+")"));
        log.info("Capital of Poland: "+capitalOfPoland.getText());

        assertThat(capitalOfPoland.getText()).isEqualTo("Warsaw");
    }
}
