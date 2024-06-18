package org.nimit.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BasePage {
    public static Playwright playwright;
    public static Browser browser;
    protected static BrowserContext context;
    protected static Page page;

    public BasePage(Playwright playwright, Browser browser, Page page, BrowserContext context){
        this.playwright = playwright;
        this.browser = browser;
        this.page = page;
        this.context = context;
    }

}
