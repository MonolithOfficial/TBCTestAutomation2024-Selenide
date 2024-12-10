package ge.tbc.testautomation.testng;

import ge.tbc.testautomation.retry.RetryAnalyzer;
import ge.tbc.testautomation.retry.RetryCount;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"group 1"})
public class GroupsTest {
    @Test(priority = 0, retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(count = 5)
    public void a() {
        Assert.fail();
    }

    @Test(priority = 2, groups = {"group 2"})
    public void b() {
        System.out.println("b");
    }

    @Test(priority = 1, invocationCount = 5, threadPoolSize = 5)
    public void c() {
        System.out.println("c");
    }

    @Test(priority = 3, groups = {"group 2"})
    public void d() {
        System.out.println("d");
    }

    @Test(priority = 4, groups = {"group 2"})
    public void e() {
        System.out.println("e");
    }
}
