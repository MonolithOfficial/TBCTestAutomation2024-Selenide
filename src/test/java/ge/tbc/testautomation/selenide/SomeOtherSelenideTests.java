package ge.tbc.testautomation.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

public class SomeOtherSelenideTests {
    WebDriver driver;
    SoftAssert sfa;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        sfa = new SoftAssert();
    }

    @Test
    public void testChained() {
        open("https://techcanvass.com/examples/register.html");
        driver = WebDriverRunner.getWebDriver(); // ACCESS TO WEBDRIVER IN SELENIDE
        driver.manage().window().maximize();
        sfa.assertEquals(1, 5);
        SelenideElement form = $(by("action", "javascript:void(0);"));
        SelenideElement input = form.find(By.tagName("input"));
        System.out.println(input.getValue());
        sfa.assertEquals(1, 1);
    }

    @Test
    public void testStream() {
        open("https://techcanvass.com/examples/register.html");
        SelenideElement form = $(by("action", "javascript:void(0);"));
        ElementsCollection inputs = form.findAll(By.tagName("input"));
        title();
    }

    @Test
    public void testTitle() {
        open("https://example.com");
        $("input[name='username']").setValue("Mamuka");
        $("input[name='password']").setValue("M4mUK4#");
        $("button[type='submit']").click();
        String title = title();
        System.out.println("Page Title: " + title);
    }

    @Test
    public void forQuizTests() {
        open("https://example.com");
        SelenideElement element = $(By.id("element1")).shouldBe(not(clickable));

    }
}
