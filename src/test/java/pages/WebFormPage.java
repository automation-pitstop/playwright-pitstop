package pages;

import com.microsoft.playwright.*;

public class WebFormPage extends HomePage {

    private static WebFormPage instance = null;

    public static WebFormPage getInstance(Playwright playwright, Browser browser, Page page, BrowserContext context){
        if(instance == null){
            instance = new WebFormPage(playwright, browser, page, context);
        }
        return instance;
    }

    protected WebFormPage(Playwright playwright, Browser browser, Page page, BrowserContext context) {
        super(playwright, browser, page, context);
        System.out.println("INFO : Navigated to Web Form");
    }

    public WebFormPage setTextInput(String inputText){
        page.getByLabel("Text input").click();
        page.getByLabel("Text input").fill(inputText);
//        page.locator("#my-text-id").click();
//        page.locator("#my-text-id").clear();
//        page.locator("#my-text-id").click();
//        page.locator("#my-text-id").fill(inputText);
//        if(!StringUtils.equals( page.locator("#my-text-id").inputValue(),inputText)){
//            throw new ValueMissMatchException("ERROR : Incorrect input value : "+ page.url());
//        }
//        else{
//
//

//        page.getByLabel("Password").click();
//        page.getByLabel("Password").fill("passwordFill");
//        page.getByLabel("Textarea").click();
//        page.getByLabel("Textarea").fill("TextArea");
//        assertThat(page.getByText("Disabled input")).isVisible();
//        assertThat(page.getByLabel("Readonly input")).isVisible();
//        page.getByLabel("Dropdown (select) Open this").selectOption("1");
//        page.getByLabel("Dropdown (select) Open this").selectOption("2");
//        page.getByLabel("Dropdown (select) Open this").selectOption("3");
//        page.getByPlaceholder("Type to search...").click();
//        page.getByPlaceholder("Type to search...").fill("Chicago");
////        page.getByLabel("File input").click();
////        page.getByLabel("File input").setInputFiles(Paths.get("default.properties"));
//        page.getByLabel("Checked checkbox").uncheck();
//        page.getByLabel("Default checkbox").check();
//        page.getByLabel("Default radio").check();
//        page.getByLabel("Color picker").click();
//        page.getByLabel("Color picker").fill("#650de7");
//        page.getByLabel("Date picker").click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("June")).click();
//        page.getByText("Jan").click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("January")).click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("«")).click();
//        page.getByLabel("Date picker").click();
//        page.getByLabel("Date picker").click();
//        page.getByLabel("Date picker").click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("2023")).click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("-2029")).click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("-2090")).click();
//        page.getByText("1900").click();
//        page.getByText("1980").click();
//        page.getByText("1986").click();
//        page.getByText("Jan", new Page.GetByTextOptions().setExact(true)).click();
//        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("25")).click();
//        page.getByLabel("Example range").fill("0");
//            page.screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get("screenshot1.png"))
//                    .setFullPage(true));
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
//        assertThat(page.getByRole(AriaRole.MAIN)).containsText("Form submitted");
//
//            page.screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get("screenshot2.png"))
//                    .setFullPage(true));
//            return this;
//        }

//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Shadow DOM")).click();
//        page.getByText("Hello Shadow DOM").click();
//        assertThat(page.getByText("Hello Shadow DOM")).isVisible();
//        assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Hello Shadow DOM");
//        page.screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get("screenshot2.png"))
//                    .setFullPage(true));
        return this;
    }

    public HomePage clickRetunToIndex() {
        page.locator("//a[contains(text(),'Return to index')]").click();
        return HomePage.getInstance(playwright,browser, page, context);
    }

    public WebFormPage setPassword(String pass) {
        page.getByLabel("Password").click();
        page.getByLabel("Password").fill(pass);
        return this;
    }

    public WebFormPage setTextareaField(String input) {
        page.getByLabel("Textarea").click();
        page.getByLabel("Textarea").fill(input);
        return this;
    }
}
