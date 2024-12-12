package ge.tbc.testautomation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParametrizedTestClass {
    private String language;

    public ParametrizedTestClass(String language){
        this.language = language;
    }

    @BeforeClass
    public void setUp(){
        if (language.equalsIgnoreCase("GE")){
            // CONFIG GEORGIAN LANGUAGE TESTS
            System.out.println("ქართულ ჯიგრულ პონტში");
        } else if (language.equalsIgnoreCase("EN")) {
            System.out.println("SETTING UP ENG TESTS");
        } else if (language.equalsIgnoreCase("RUS")) {
            System.out.println("LANGUAGE NOT SUPPORTED");
        }
        else {
            System.out.println("NO LANGUAGE");
        }
    }

    @Test
    public void testName() {
        System.out.println(language);
    }
}
