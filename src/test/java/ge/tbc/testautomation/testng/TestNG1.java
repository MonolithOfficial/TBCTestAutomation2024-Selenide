package ge.tbc.testautomation.testng;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.listeners.CustomTestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

@Listeners({CustomTestListener.class})
public class TestNG1 {
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        WebDriverRunner.setWebDriver(new ChromeDriver());
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.assertionMode = AssertionMode.STRICT;
        Configuration.holdBrowserOpen = true;
    }

    @Test(priority = 0, description = "Test that does something", groups = {"smoke tests"})
    public void a() {
        System.out.println("test01");
    }

    @Test(priority = 1, invocationCount = 5, successPercentage = 100)
    public void c() {
        System.out.println("test01");
        Random random = new Random();
        int number = random.nextInt(5);
        System.out.println(number);
        if (number != 1) {
            Assert.fail();
        }
    }

    @Test(priority = 2)
    public void b() {
        Assert.fail();
    }

    @Test(priority = 3)
    public void d() {
        System.out.println("test01");
    }

    @Test
    public void complexSendKeys() {
        open("https://redbubble.com");
        SelenideElement searchBar = $x("//input[@type='search']");

        searchBar.sendKeys("Search query");

        actions()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .keyDown(Keys.BACK_SPACE)
                .keyUp(Keys.BACK_SPACE)
                .build()
                .perform();

        Assert.assertEquals(1, 5); // test fails on purpose. Check CustomTestListener onFailure.
    }

    @AfterClass
    public void tearDown(){
        closeWebDriver();
    }
}
