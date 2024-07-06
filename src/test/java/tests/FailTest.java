package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.nimit.core.CoreUtils.loadTestDataIntoMap;

public class FailTest extends TestBase {

    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void loginTestFail(){
        Map<String, Map<String, String>> dataMap = loadTestDataIntoMap("src/test/resources/testData/data.csv");
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login form")).click();
        page.getByLabel("Login").click();
        page.getByLabel("Login").fill(dataMap.get("testid002").get("username"));
        page.getByLabel("Password").click();
        page.getByLabel("Password").fill(dataMap.get("testid002").get("password"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        assertThat(page.locator("#success")).containsText("Login successful");
    }

}
