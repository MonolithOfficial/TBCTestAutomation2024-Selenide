package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class DataSupplier {
    @DataProvider
    public static Object[][] dataSupplier(){
        return new Object[][] {
                {"კვება", "კვება"},
                {"კინო", "კინო"},
                {"დასვენება", "დასვენება"},
                {"გართობა", "გართობა"},
                {"საბავშვო", "საბავშვო"},
                {"სპორტი", "სპორტი"},
        };
    }
}
