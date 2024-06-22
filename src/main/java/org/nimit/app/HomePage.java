package org.nimit.app;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.commons.lang3.StringUtils;
import org.nimit.core.BasePage;
import org.nimit.core.CoreUtils;
import org.nimit.core.WrongPageException;

public class HomePage extends BasePage {

    private static HomePage instance = null;

    public static HomePage getInstance(Playwright playwright, Browser browser, Page page, BrowserContext context){
        if(instance == null){
            instance = new HomePage(playwright, browser, page, context);
        }
        return instance;
    }
    protected HomePage(Playwright playwright, Browser browser, Page page, BrowserContext context) {
        super(playwright, browser, page, context);
    }

    public HomePage launchUrl() {
        page.navigate(CoreUtils.getOverriddenProperty("url"));
        if(!StringUtils.containsAnyIgnoreCase(page.url(),"selenium-webdriver-java")){
            throw new WrongPageException("ERROR : Incorrect URL is loaded, current url : "+ page.url());
        }else{
            System.out.println("INFO : Home page loaded");
            return this;
        }
    }

    public WebFormPage navigateToWebForm(){
        System.out.println("INFO : Navigating to Web Form");
        page.locator("//a[contains(@href,'web-form')]").click();
        if(!StringUtils.containsAnyIgnoreCase(page.url(),"web-form.html")){
            throw new WrongPageException("ERROR : Incorrect URL is loaded, current url : "+ page.url());
        }else{
            return WebFormPage.getInstance(playwright,browser, page, context);
        }
    }


}
