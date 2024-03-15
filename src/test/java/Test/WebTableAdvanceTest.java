package Test;

import Base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTableAdvanceTest extends BaseTest {
    private Logger log = LoggerFactory.getLogger(WebTableAdvanceTest.class);
    @ParameterizedTest
    @CsvFileSource(resources = "/countries.csv")
    @DisplayName("Check countries and capitals")

    void webTablePolandTest(String country, String capital){
        assertThat(getCapitalForCountry(country)).isEqualTo(capital);
    }

//    @Test
//    @DisplayName("Check countries and capitals")
//
//    void webTableBurkinaFasoTest(){
//        assertThat(getCapitalForCountry("Burkina Faso")).isEqualTo("Ouagadougou");
//    }

    @Test
    @DisplayName("Check currency")

    void getCurrencyForCountry(){
        assertThat(getCurrencyForCountry("Poland")).isEqualTo("Zloty");
    }

}
