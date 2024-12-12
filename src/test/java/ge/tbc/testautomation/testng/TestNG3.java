package ge.tbc.testautomation.testng;

import com.codeborne.selenide.*;
import ge.tbc.testautomation.data.DataSupplier;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.*;

public class TestNG3 {
    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.assertionMode = AssertionMode.STRICT;
    }
    @Test(dataProvider = "dataSupplier", dataProviderClass = DataSupplier.class)
    public void testCollections(String testData, String validationData) {
        open("https://swoop.ge");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        SelenideElement eatIcon = $x(String.format("//img[@alt='%s']", testData));
        eatIcon.click();
        SelenideElement h3 = $x("//h3[@type='h3']");
        h3.shouldHave(Condition.text(validationData));
        System.out.println("testData = " + testData + ", validationData = " + validationData);
    }


}
