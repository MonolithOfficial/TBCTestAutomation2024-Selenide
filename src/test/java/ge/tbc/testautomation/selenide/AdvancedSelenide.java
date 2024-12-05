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
    }

    @Test
    public void testCustomCondition() {
        open("https://swoop.ge");
        SelenideElement annualSavings = $(byText("წლიური დანაზოგი")).$x(".//following-sibling::p");
        annualSavings.scrollTo().shouldHave(textOfLength(10));
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
