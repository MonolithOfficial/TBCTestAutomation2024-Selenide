package ge.tbc.testautomation.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite :
                suites) {
            String suiteName = suite.getName();
            System.out.println(suiteName);
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult :
                    suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                System.out.println(testContext.getName());
                // კოდი არ არის დასრულებული ( ͡° ͜ʖ ͡°)
            }
        }
    }
}
