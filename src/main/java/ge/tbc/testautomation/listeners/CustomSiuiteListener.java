package ge.tbc.testautomation.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class CustomSiuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }
}
