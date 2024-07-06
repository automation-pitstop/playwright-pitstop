package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.nimit.core.CoreUtils.getTestDataFromCsvFile;

public class DataTest extends TestBase {
    public static Map<String, Map<String, String>> testData;
    @BeforeClass
    public static void loadData(){
        logger.info("---------Loading the test data--------");
        testData = getTestDataFromCsvFile("src/test/resources/testData/data.csv");
    }

    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void loginTest(){
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login form")).click();
        page.getByLabel("Login").click();
        page.getByLabel("Login").fill(testData.get("testid001").get("username"));
        page.getByLabel("Password").click();
        page.getByLabel("Password").fill(testData.get("testid001").get("password"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        assertThat(page.locator("#success")).containsText("Login successful");
    }

    @Test
    public void loginTestFail(){
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login form")).click();
        page.getByLabel("Login").click();
        page.getByLabel("Login").fill(testData.get("testid002").get("username"));
        page.getByLabel("Password").click();
        page.getByLabel("Password").fill(testData.get("testid002").get("password"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        assertThat(page.locator("#success")).containsText("Login successful");
    }

}
