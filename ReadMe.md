Command to run the TestNG test
mvn clean test -DxmlFilePath=testng.xml

----------------
Tracing code:
try (Playwright playwright = Playwright.create()) {
    Browser browser = playwright.chromium().launch();
    BrowserContext context = browser.newContext();

    // Start tracing before creating / navigating a page.
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
// Stop tracing and export it into a zip archive.
context.tracing().stop(new Tracing.StopOptions()
.setPath(Paths.get("trace.zip")));
}
--------------
Opening the trace.zip file
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"