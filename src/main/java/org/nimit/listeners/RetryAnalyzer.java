package org.nimit.listeners;

import org.apache.commons.lang3.StringUtils;
import org.nimit.core.CoreUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
//    private static final int maxRetryCount = 2; // Set your max retry count here
    private static final int maxRetryCount = Integer.parseInt(StringUtils.defaultIfBlank(CoreUtils.testProperties.getProperty("retryCount"),"0"));

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
