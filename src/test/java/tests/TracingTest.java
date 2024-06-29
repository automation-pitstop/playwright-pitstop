package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TracingTest extends TestBase {

    @Test
    void startTracing(){
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        Page page = context.newPage();
        page.navigate("https://playwright.dev");

        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

}
