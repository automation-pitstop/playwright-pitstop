### Command to run the TestNG test
* ````mvn clean test```` ==> default test will run from testng.xml
* ````mvn clean test -DtestNgXmlFilePath=testng1.xml -Denv=tst```` (to override the testng.xml) 

----------------

#### Tracing code:

```
try (Playwright playwright = Playwright.create()) {
    Browser browser = playwright.chromium().launch();
    BrowserContext context = browser.newContext();

    Start tracing before creating / navigating a page.
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
```

--------------
#### Opening the trace.zip file

````
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
````

#### Loading Properties

*default.properties* ==> Consist all the default properties
This can be overridden by any property file. 
For that provide _-denv=<property_file_name>_
e.g. ```-Devn=tst``` or ```-Denv=prod```

##### Load hierarchy is as below:
1. VM args
2. System env variables
3. Property file

Also to override any property directly place that in VM arguments with -d.

-------
#### Run recorder : 

````mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://bonigarcia.dev/selenium-webdriver-java/"````

-----

#### Adding logger:

````static final Logger logger = LoggerFactory.getLogger(lookup().lookupClass());````

Changing the log level from mvn command line :
````mvn clean install -Dlog.level=DEBUG````

#### To-Do list : 
1. Make data sheet load once ==> done : Use loadTestDataIntoMap method in BeforeClass method to load the data
2. Add the default retry count in properties => done : retryCount
3. Take screenshot after every method
4. Take screenshot if fails ==> done : 

#### Data loading
Keep the data sheet csv in testData folder
Note : Must add columns "TestId" and "env". 'env' column data is to keep environment specific data.