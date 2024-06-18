import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestNgTest extends TestBase {
//    // Shared between all tests in this class.
//    static Playwright playwright;
//    static Browser browser;

//    // New instance for each test method.
//    BrowserContext context;
//    Page page;

//    @BeforeClass
//    static void launchBrowser() {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
//    }
//
//    @AfterClass
//    static void closeBrowser() {
//        playwright.close();
//    }

//    @BeforeMethod
//    void createContextAndPage() {
//        context = browser.newContext();
//        page = context.newPage();
//    }
//
//    @AfterMethod
//    void closeContext() {
//        context.close();
//    }



    @Test
    void shouldClickButton1() {
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals("Clicked", page.evaluate("result"));
    }

    @Test
    void shouldCheckTheBox2() {
        page.setContent("<input id='checkbox' type='checkbox'></input>");
        page.locator("input").check();
        assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
    }

    @Test
    void shouldSearchWiki3() {
        page.navigate("https://www.wikipedia.org/");
        page.locator("input[name=\"search\"]").click();
        page.locator("input[name=\"search\"]").fill("playwright");
        page.locator("input[name=\"search\"]").press("Enter");
        assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
    }
}
