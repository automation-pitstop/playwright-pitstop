package tests;

import org.testng.annotations.Test;

public class MouseHoverTest extends TestBase{

    @Test
    void moveMouse() throws InterruptedException {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        page.setDefaultTimeout(60 * 1000);
        page.locator("text=Use double-click here").dblclick();
        page.wait(15 * 1000);
        logger.info("Test is completed");
    }
}
