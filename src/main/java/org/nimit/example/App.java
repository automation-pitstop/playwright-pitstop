package org.nimit.example;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.nimit.core.CoreUtils.loadTestDataIntoMap;

public class App {
    public static void main(String[] args){
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50) );
//            Page page = browser.newPage();
//            page.navigate("http://playwright.dev");
//
//            // Expect a title "to contain" a substring.
//            assertThat(page).hasTitle(Pattern.compile("Playwright"));
//
//            // create a locator
//            Locator getStarted = page.locator("text=Get Started");
//
//            // Expect an attribute "to be strictly equal" to the value.
//            assertThat(getStarted).hasAttribute("href", "/docs/intro");
//
//            // Click the get started link.
//            getStarted.click();
//
//            // Expects page to have a heading with the name of Installation.
//            assertThat(page.getByRole(AriaRole.HEADING,
//                    new Page.GetByRoleOptions().setName("Installation"))).isVisible();
//
//            System.out.println("Done");
//        }

        Map<String, Map<String, String>> dataMap = loadTestDataIntoMap("src/test/resources/data.csv");

        // Print the loaded map
        for (Map.Entry<String, Map<String, String>> entry : dataMap.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            for (Map.Entry<String, String> innerEntry : entry.getValue().entrySet()) {
                System.out.println("\t" + innerEntry.getKey() + ": " + innerEntry.getValue());
            }
        }
    }
}
