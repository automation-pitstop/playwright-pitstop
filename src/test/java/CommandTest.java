import org.nimit.app.HomePage;
import org.nimit.app.WebFormPage;
import org.nimit.core.CoreUtils;
import org.testng.annotations.Test;

public class CommandTest extends TestBase{

    @Test
    void testUrlOpening(){
        HomePage homePage = HomePage.getInstance(playwright,browser, page, context);
        System.out.println(CoreUtils.getOverriddenProperty("key1"));
        WebFormPage webFormPage = homePage.launchUrl()
                .navigateToWebForm()
                .setTextInput("HelloWorld");

        webFormPage.clickRetunToIndex();


    }
}
