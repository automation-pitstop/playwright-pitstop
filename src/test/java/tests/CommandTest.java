package tests;

import org.nimit.core.CoreUtils;
import org.testng.annotations.Test;
import pages.HomePage;

public class CommandTest extends TestBase {

    @Test
    void testUrlOpening(){
        TestBase.logger.info("Running the test : testUrlOpening");
        HomePage homePage = HomePage.getInstance(TestBase.playwright, TestBase.browser, page, context);
        homePage.launchUrl()
                .navigateToWebForm()
                .setTextInput("HelloWorld")
                .setPassword("Pass")
                .setTextareaField("Text in area")
                .clickRetunToIndex();

//        webFormPage.clickRetunToIndex();


    }
}
