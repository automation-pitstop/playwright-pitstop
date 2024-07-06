package tests;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.StringUtils;
import org.nimit.core.CoreUtils;
import org.nimit.listeners.RetryListener;
import listeners.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.Properties;

import static java.lang.invoke.MethodHandles.lookup;

@Listeners({TestListener.class, RetryListener.class})
public class TestBase {
    protected static final Logger logger = LoggerFactory.getLogger(lookup().lookupClass());
    public static Playwright playwright;
    public static Browser browser;

    //New instance for each test method.
    public BrowserContext context;
    public Page page;
    public static Properties testProperties;

    @BeforeSuite
    static void prepareTestEnv(){
        logger.info("Before Suite called : Preparing the test env");
        testProperties = CoreUtils.testProperties;
    }

    @BeforeClass
    static void launchBrowser() {
        logger.info("Before Class called : Creating the Playwright object");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
    }

    @AfterClass
    static void closeBrowser() {
        logger.info("After Class called : Closing the browser");
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        logger.info("Before Method called : Creating context and page object");
        context = browser.newContext();
        page = context.newPage();
//        if( StringUtils.isNotBlank(CoreUtils.getOverriddenProperty("default.timeout"))){
//            page.setDefaultTimeout(Double.parseDouble(CoreUtils.getOverriddenProperty("default.timeout")));
//        }
        if( StringUtils.isNotBlank(testProperties.getProperty("default.timeout"))){
            page.setDefaultTimeout(Double.parseDouble(testProperties.getProperty("default.timeout")));
        }
    }

    @AfterMethod
    void closeContext() {
        logger.info("After Method called : Closing the context");
        context.close();
    }

    public Page getPageObj(){
        return page;
    }
}
