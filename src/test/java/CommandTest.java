import org.nimit.app.Homepage;
import org.nimit.app.WebFormPage;
import org.testng.annotations.Test;

public class CommandTest extends TestBase{

    @Test
    void testUrlOpening(){
        Homepage homepage = Homepage.getInstance(playwright,browser, page, context);
        WebFormPage webFormPage = homepage.launchUrl()
                .navigateToWebForm()
                .setTextInput("HelloWorld");

        webFormPage.clickRetunToIndex();

    }
}
