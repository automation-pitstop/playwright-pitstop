package tests;

import com.microsoft.playwright.*;
import org.nimit.core.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import static java.lang.invoke.MethodHandles.lookup;

@Listeners(TestListener.class)
public class TestBase {
    protected static final Logger logger = LoggerFactory.getLogger(lookup().lookupClass());
    public static Playwright playwright;
    public static Browser browser;

    //New instance for each test method.
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    static void launchBrowser() {
        logger.info("Creating the Playwright object");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
    }

    @AfterClass
    static void closeBrowser() {
        logger.info("Closing the browser");
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        logger.info("Creating context and page object");
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    void closeContext() {
        logger.info("Closing the context");
        context.close();
    }
}
