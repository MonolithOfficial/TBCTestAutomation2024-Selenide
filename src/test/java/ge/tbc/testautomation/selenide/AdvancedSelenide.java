package ge.tbc.testautomation.selenide;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.FileNotDownloadedError;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AdvancedSelenide extends BaseTest {

    @Test
    public void fileUpload() {
        open("https://tsotne-aburjania-redberry-project.netlify.app/");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ronaldokneeslide.jpg").getFile());
        SelenideElement addListingBtn = $(byId("add-listing")).shouldHave(Condition.attribute("aria-label"));
        addListingBtn.click();

        SelenideElement fileInput = $(byAttribute("type", "file"));
        fileInput.scrollTo().uploadFile(file);
    }

    @Test
    public void downloadFile() {
        open("http://xcal1.vodafone.co.uk/");
        SelenideElement smallFileDownload = $(byAttribute("href", "http://212.183.159.230/10MB.zip"));
        try {
            File downloadedFile = smallFileDownload.scrollTo().download();
            if (downloadedFile.exists()) {
                System.out.println("File downloaded successfully and the path is " + downloadedFile.getAbsoluteFile());
            } else {
                System.out.println("File was not downloaded");
            }
        } catch (FileNotDownloadedError e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"group 2"})
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
    }

    @Test
    public void testCustomCondition() {
        open("https://swoop.ge");
        SelenideElement annualSavings = $(byText("წლიური დანაზოგი")).$x(".//following-sibling::p");
        annualSavings.scrollTo().shouldHave(textOfLength(10));
    }

    @Test
    public void chainingTest() {
        open("https://www.telerik.com/support/demos");

        // find (დააკვირდით, რომ find(WebElementCondition condition) მეთოდი წვდომადია ElementsCollection ობიექტებიდან მხოლოდ,
        // პარამეტრად იღებს WebElementCondition-ს, აბრუნებს SelenideElement).
        // როდის ვიყენებთ? - როდესაც გვაქვს კოლექცია და გვინდა იქიდან რამე ერთი ელემენტი ამოვიღოთ რაღაც კონდიციის საფუძველზე.
        SelenideElement navBar = $x("//div[@data-tlrk-plugin='navspy']"); // მოგვაქვს ნავბარი
        SelenideElement desktopLink = navBar
                .$$(byTagName("a")) // მოგვაქვს ყველა ენქორ ტეგი
                .find(Condition.exactText("Desktop")); // ვიღებთ იმ ენქორ ტეგს, რომლის ტექსტი არის ზუსტად 'Desktop'

        // findAll (დააკვირდით, რომ findAll მეთდი წვდომადია SelenideElement ობიექტებიდან მხოლოდ,
        // პარამეტრად იღებს By-ს (რამე ლოკატორს), აბრუნებს ElementsCollection-ს).
        // როდის ვიყენებთ? - როდესაც გვაქვს ერთეულოვანი ვებელემენტი და გვინდა მასში მოვძებნოთ *რამდენიმე* ელემენტი.
        // SelenideElement-საც აქვს find მეთოდი, ოღონდ ის პარამეტრად იღებს By-ს და არა WebElementConditions.
        ElementsCollection anchorLinks = navBar.findAll(byTagName("a")); // ჩათვალეთ, რომ $$ და findAll ერთი და იგივეა.

        // filter (წვდომადია მხოლოდ ElementsCollection-ის ობიექტებიდან მხოლოდ,
        // პარამეტრად იღებს WebElementCondition-ს, აბრუნებს კოლექციას)
        // როდის ვიყენებთ? - როცა გვაქვს კოლექცია და აქედან გვინდა გავფილტროთ ელემენტები რაღაც ქონდიშენის საფუძველზე
        // და ისევ კოლექცია მივიღოთ (გაფილტრული)
        ElementsCollection someKindOfAnchorLinks = navBar.findAll(byTagName("a"))
                .filter(Condition.partialText("p")); // მოგვაქვს ყველა ენქორ ტეგი, რომლის ტექსტიც შეიცავს p-ს.
    }

    public static WebElementCondition textOfLength(int expectedLength) {
        return new WebElementCondition("text of length " + expectedLength) {
            @Nonnull
            @Override
            public CheckResult check(Driver driver, WebElement webElement) {
                String text = webElement.getText();
                return new CheckResult(text.length() == expectedLength, "length(\"" + text + "\") = " + text.length());
            }
        };
    }
}
