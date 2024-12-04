package selenide;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTests {
    SoftAssert sfa;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.timeout = 10;
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        sfa = new SoftAssert();
    }

    @Test
    public void test01(){
        open("https://swoop.ge");
        SelenideElement someText = $(withText("ხუთშაბათ"));
        someText.scrollTo();
        System.out.println(someText.innerHtml());

        SelenideElement searchInput = $(byXpath("//input[@placeholder='მოძებნე კომპანია ან შეთავაზება']"));
        searchInput.shouldBe(Condition.clickable);
        searchInput.sendKeys("spiderman");

        sfa.assertEquals(0, 1);
        sfa.assertEquals(0, 0);
        sfa.assertEquals(0, 1);

        sfa.assertAll();
    }

    @Test
    public void testCollections() {
        open("https://swoop.ge");
        SelenideElement anchor = $(byTagName("a"));
        System.out.println(anchor.innerHtml());

        ElementsCollection anchors = $$(byTagName("a"));
        SelenideElement vacationLink = anchors.findBy(Condition.attribute("href", "/category/24/dasveneba/"));
        System.out.println(vacationLink);

        anchors.shouldHave(containExactTextsCaseSensitive("კინო", "დასვენება"));

//        ElementsCollection nonVacationLinks = anchors.excludeWith(Condition.attributeMatching("href", ".*dasveneba.*"));
//        System.out.println(nonVacationLinks);
    }

    @Test
    public void selenideElementTest() {
//        SelenideElement removeButton = $(byText("Remove"));
//        SelenideElement removeButton2 = $(withText("move"));
//        SelenideElement removeButton3 = $(by("onClick", "swapCheckbox()"));
//        removeButton3.click();
//
//        SelenideElement removeButton4 = $("#checkbox").$(by("onClick", "swapCheckbox()")).shouldBe(visible);
//        System.out.println(removeButton4.text());

        SelenideElement enableButton = $(byText("Enable"));
        enableButton.click();

        SelenideElement enableMsg = $(byText("It's enabled!")).shouldBe(Condition.visible);
        System.out.println(enableMsg.text());
    }

    @Test
    public void collectionTest() {
        ElementsCollection rows = $$(By.tagName("a"));
        System.out.println(rows.size());
        rows.shouldHave(CollectionCondition.attributes("href", "https://github.com/tourdedave/the-internet"
                , "http://elementalselenium.com/"));
        for (SelenideElement row :
                rows) {
            System.out.println(row.innerHtml());
        }
    }

    @Test
    public void scrollTest(){
        open("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        SelenideElement bloggerDiv = $(withText("pular Tutorials"));
        bloggerDiv.scrollIntoView(false);
    }


    @Test
    public void inputTest() {
        open("https://techcanvass.com/examples/register.html");
        SelenideElement firstNameInput = $(byValue("First Name"));
        firstNameInput.click();
        firstNameInput.shouldBe(Condition.empty);
        firstNameInput.sendKeys("HELLO I AM ROBOT");
        firstNameInput.shouldHave(text("HELLO I AM ROBOT"));
    }

    @Test
    public void checkBoxTest() {
        WebDriverManager.chromedriver().setup();
        open("https://techcanvass.com/examples/register.html");
        Configuration.assertionMode = AssertionMode.STRICT;
        SelenideElement checkBox = $(byAttribute("type", "checkbox"));
        checkBox.shouldNotBe(checked);
        checkBox.setSelected(true);
        checkBox.click();
        checkBox.shouldBe(checked);
    }

    @Test
    public void dropDownTest(){
        open("https://techcanvass.com/examples/register.html");
        SelenideElement select = $(byName("model"));
        select.selectOption("Serene Pad 64G", "Mega 123 Medium screen");
        select.shouldHave(value("Mega 123 Medium screen"));
    }
}
