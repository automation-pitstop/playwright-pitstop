package listeners;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.TestBase;

import java.nio.file.Paths;

public class TestListener implements ITestListener{
    static final Logger logger = getLogger(lookup().lookupClass());

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        logger.debug("Listener On Start : ", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        logger.debug("Listener Test Start : ", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.debug("Listener on test success : ", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        Object testClass = result.getInstance();
        ((TestBase) testClass).getPageObj()
                .screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(result.getName()+".png")));
        logger.debug("Listener onTestFailure : ", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        logger.debug("Listener onTestSkipped : ", result.getSkipCausedBy());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        logger.debug("Listener onTestFailedButWithinSuccessPercentage : ",
                result.getThrowable());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        logger.debug("Listener onTestFailedWithTimeout : ", result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        logger.debug("Listener onFinish : ", context.getName());
    }

}
