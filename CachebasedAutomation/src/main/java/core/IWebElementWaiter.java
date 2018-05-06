package com.amwell.auto.yahav.sample.pom.core;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IWebElementWaiter {

    WebElement waitUntilElementPresence(String locator);
    WebElement waitUntilElementVisible(String locator);

    List<WebElement> waitUntilElementsPresence(String rootLocator);
    List<WebElement> waitUntilElementsVisible(String rootLocator);

    WebElement waitForVisibilityOf(WebElement elem);

    WebElement waitUntilElementClickable(String locator);
    Boolean waitUntilInvisibilityOf(WebElement elem);
}
