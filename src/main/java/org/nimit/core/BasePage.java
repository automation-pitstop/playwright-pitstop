package org.nimit.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Properties;

public class BasePage {
    public Playwright playwright;
    public Browser browser;
    protected BrowserContext context;
    protected Page page;

    public Properties testProperties = CoreUtils.testProperties;

    public BasePage(Playwright playwright, Browser browser, Page page, BrowserContext context){
        this.playwright = playwright;
        this.browser = browser;
        this.page = page;
        this.context = context;
    }
}
