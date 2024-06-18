package org.nimit.app;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.StringUtils;
import org.nimit.core.ValueMissMatchException;

public class WebFormPage extends Homepage{

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
        page.locator("#my-text-id").click();
        page.locator("#my-text-id").clear();
        page.locator("#my-text-id").click();
        page.locator("#my-text-id").fill(inputText);
        if(!StringUtils.equals( page.locator("#my-text-id").inputValue(),inputText)){
            throw new ValueMissMatchException("ERROR : Incorrect input value : "+ page.url());
        }else{
            return this;
        }
    }

    public Homepage clickRetunToIndex() {
        page.locator("//a[contains(text(),'Return to index')]").click();
        return Homepage.getInstance(playwright,browser, page, context);
    }
}
