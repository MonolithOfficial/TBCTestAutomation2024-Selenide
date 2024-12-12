package ge.tbc.testautomation;

import org.testng.annotations.Factory;

public class FactoryExecutor {
    @Factory
    public Object[] factoryExecutor(){
        return new Object[]{
                new ParametrizedTestClass("GE"),
                new ParametrizedTestClass("EN"),
                new ParametrizedTestClass("RUS"),
        };
    }
}
