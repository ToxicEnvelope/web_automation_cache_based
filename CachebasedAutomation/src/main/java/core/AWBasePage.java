package com.amwell.auto.yahav.sample.pom.core;


import com.amwell.auto.caretalks.config.GlobalConfig;
import com.amwell.auto.caretalks.enums.WaitTime;
import com.amwell.auto.yahav.sample.pom.core.utils.RestManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public abstract class AWBasePage extends RestManager implements IWebElementWaiter {
    public Logger log = Logger.getLogger(this.getClass().getName());

    private static final String SCREEN_SHOTS_DIR = System.getProperty("user.dir") + "\\test-output\\screenshots\\";

    protected WebDriver _driver;
    private JavascriptExecutor _js;
    private TakesScreenshot _snap;

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @author Yahav N. Hoffman
     * @param driver - WebDriver object
     */
    public AWBasePage(WebDriver driver) {
        this._driver = driver;
        this._js = (JavascriptExecutor) _driver;
        this._snap = (TakesScreenshot) _driver;

        PageFactory.initElements(_driver, this);
        System.out.println(SCREEN_SHOTS_DIR);
    }

    /**
     * initElements method is abstract and usually will
     * take place inside each individual POM to initialize
     * his WebElement instances
     *
     * @author Yahav N. Hoffman
     */
    public abstract void initElements();

    /**
     * click method simulate a click on
     * WebElement object.
     *
     * @author Yahav N. Hoffman
     * @param elem - WebElement object
     */
    public void click(WebElement elem) {
        log.entering(getClass().getSimpleName(), "click -> " + elem);
        try {
            if (elem.isDisplayed()) {
                log.info(getClass().getSimpleName() + " click -> " + elem);
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid green; padding: 5px;');", elem);
                elem.click();
                wait(750);
            }
            else {
                WebElement el = waitForVisibilityOf(elem);
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid green; padding: 5px;');", el);
                el.click();
                wait(750);
            }
        }
        catch (StaleElementReferenceException e) {
            log.info("Exception occurred at'"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            log.info("Simulating JS click");
            _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid yellow; padding: 5px;');", elem);
            _js.executeScript("argument[0].click();", elem);
            wait(750);
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
        }
    }

    /**
     * fillText method simulate a text sent
     * to a WebElement object to be filled
     *
     * @author Yahav N. Hoffman
     * @param elem - WebElement object
     * @param phrase - String object
     */
    protected void clearAndFillText(WebElement elem, String phrase) {
        log.entering(this.getClass().getSimpleName()," fillText -> "+ elem +" : "+ phrase);
        try {
            log.info(this.getClass().getSimpleName() + " fillText -> " + elem + " : " + phrase);
            if (elem.isDisplayed()) {
                elem.clear();
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid blue; padding: 5px;');", elem);
                breakToCharsAndSendKeys(elem, phrase);
                wait(750);
            }
            else {
                WebElement el = waitForVisibilityOf(elem);
                el.clear();
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid blue; padding: 5px;');", el);
                breakToCharsAndSendKeys(elem, phrase);
                wait(750);
            }
        }
        catch (StaleElementReferenceException e) {
            log.info("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            log.info("Simulating JS fillText");
            _js.executeScript("arguments[0].value="+null+";", elem);
            _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid cyan; padding: 5px;');", elem);
            _js.executeScript("argument[0].value="+phrase+";", elem);
            wait(750);
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
        }
    }

    /**
     * mouseOverWebElement methods simulates a
     * hover action on a given WebElement object
     *
     * @author Yahav N. Hoffman
     * @param elem - WebElement object
     */
    public void mouseOverWebElement(WebElement elem) {
        log.entering(this.getClass().getSimpleName(), "mouseOverWebElement -> "+ elem);
        try {
            if (elem.isDisplayed()) {
                Actions builder = new Actions(_driver);
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid orange; padding: 5px;');", elem);
                builder.moveToElement(elem).build().perform();
                wait(750);
            }
            else {
                WebElement el = waitForVisibilityOf(elem);
                Actions builder = new Actions(_driver);
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid orange; padding: 5px;');", el);
                builder.moveToElement(el).build().perform();wait(750);
            }
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
        }
    }

    /**
     * selectElementByValue method simulates a selection
     * from select dropdown and chckout selection by a given VALUE
     *
     * @author Yahav N. Hoffman
     * @param elem -> WebElement object as root of <select></select> HTML tag
     * @param value -> String object as a value
     */
    protected void selectElementByValue(WebElement elem, String value) {
        log.entering(this.getClass().getSimpleName(), "selectElementByValue -> "+ elem + " : "+ value);
        try {
            if(elem.isDisplayed()) {
                Select select = new Select(elem);
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid pink; padding: 5px;');", elem);
                select.selectByValue(value);
            }
            else {
                WebElement el = waitForVisibilityOf(elem);
                Select select = new Select(el);
                _js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid pink; padding: 5px;');", el);
                select.selectByValue(value);
            }
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
        }
    }

    /**
     * waitUntilInvisibilityOf method waits for a WebElement to no longer exists
     * based of Boolean condition where an HTML element is INVISIBLE within the DOM.
     * This will wait for MAX of 15.0 seconds and expects a True value,
     * else will fail and return False.
     *
     * @author Yahav N. Hoffman
     * @param elem - WebElement
     * @return True | False
     */
    public Boolean waitUntilInvisibilityOf(WebElement elem) {
        log.entering(this.getClass().getSimpleName(),"waitUntilInvisibilityOf -> " + elem);
        try {
            final WebDriverWait wait = new WebDriverWait(_driver, 15);
            return wait.until(ExpectedConditions.invisibilityOf(elem));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * waitUntilElementClickable method waits for WebElement to be exist based
     * of Boolean condition where an HTML element is CLICKABLE within the DOM.
     * This will wait for MAX of 15.0 seconds and expects a True value,
     * else will fail and return Null.
     * Basically solving AJAX problem with dynamic WebElement locating
     *
     * @author Yahav N. Hoffman
     * @param locator - String object
     * @return WebElement | Null
     */
    public WebElement waitUntilElementClickable(String locator) {
        log.entering(this.getClass().getSimpleName(),"waitUntilElementClickable -> " + locator);
        try {
            final WebDriverWait wait = new WebDriverWait(_driver, 15);
            return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * waitUntilElementVisible method waits for WebElement to be exist based
     * of Boolean condition where an HTML element is VISIBLE within the DOM.
     * This will wait for MAX of 15.0 seconds and expects a True value,
     * else will fail and return Null.
     * Basically solving AJAX problem with dynamic WebElement locating
     *
     * @author Yahav N. Hoffman
     * @param locator - String object
     * @return WebElement | Null
     */
    public WebElement waitUntilElementVisible(String locator) {
        log.entering(this.getClass().getSimpleName(),"waitUntilInvisibilityOf -> " + locator);
        try {
            final WebDriverWait wait = new WebDriverWait(_driver, 15);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * waitUntilElementsVisible method waits for List of WebElement objects to be exist based
     * of Boolean condition where an HTML element is VISIBLE within the DOM.
     * This will wait for MAX of 15.0 seconds and expects a True value,
     * else will fail and return Null.
     * Basically solving AJAX problem with dynamic WebElement locating
     *
     * @author Yahav N. Hoffman
     * @param rootLocator - String object
     * @return List<WebElement> | Null
     */
    @Override
    public List<WebElement> waitUntilElementsVisible(String rootLocator) {
        log.entering(this.getClass().getSimpleName(), "waitUntilElementsVisible -> " + rootLocator);
        try {
            final WebDriverWait wait = new WebDriverWait(_driver, 15);
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(rootLocator)));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * waitUntilElementPresence return a WebElement based
     * of Boolean condition where an HTML element is PRESENCE within the DOM's CACHE.
     * This will wait for MAX of 15.0 seconds and expects a True value,
     * else will fail and return Null
     *
     * @author Yahav N. Hoffman
     * @param locator - String object
     * @return WebElement | Null
     */
    public WebElement waitUntilElementPresence(String locator) {
        log.entering(this.getClass().getSimpleName(),"waitUntilElementPresence -> " + locator);
        try {
            final WebDriverWait wait = new WebDriverWait(_driver, 15);
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * waitUntilElementsPresence return a List of WebElement objects based
     * of Boolean condition where an HTML element is PRESENCE within the DOM's CACHE.
     * This will wait for MAX of 15.0 seconds and expects a True value,
     * else will fail and return Null
     *
     * @author Yahav N. Hoffman
     * @param rootLocator - String object
     * @return List<WebElement> | Null
     */
    @Override
    public List<WebElement> waitUntilElementsPresence(String rootLocator) {
        log.entering(this.getClass().getSimpleName(), "waitUntilElementsPresence -> " + rootLocator);
        try {
            final WebDriverWait wait = new WebDriverWait(_driver, 15);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(rootLocator)));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * waitFoeVisibilityOf method will return WebElement instance
     * based on WebElement given from DOM declaration
     *
     * @author Yahav N. Hoffman
     * @param elem - WebElement object to be verified
     * @return - WebElement object
     */
    public WebElement waitForVisibilityOf(WebElement elem) {
        log.entering(this.getClass().getSimpleName(),"waitUntilElementPresence -> " + elem);
        try {
            WebDriverWait wait = new WebDriverWait(_driver, 10);
            return wait.until(ExpectedConditions.visibilityOf(elem));
        }
        catch (Exception e) {
            log.warning("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            takeScreenShot();
            wait(750);
            return null;
        }
    }

    /**
     * wait method make a sleep for X of milliseconds
     *
     * @author Yahav N. Hoffman
     * @param ms - Integer object
     */
    protected void wait(int ms){
        try {
            Thread.sleep(ms);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * takeScreenshot method create a .jpg file in case of an
     * unknown exception during WebDriver functions.
     * Files located under path: PROJECT_ROOT_DIR/src/test/test-output/screenshots
     *
     * @author Yahav N. Hoffman
     */
    private void takeScreenShot() {
        File src = ( _snap ).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(SCREEN_SHOTS_DIR + getTimestamp() + ".png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getTimestamp method returns the actual time of instance
     *
     * @author Yahav N. Hoffman
     * @return - String object of the actual time
     */
    private String getTimestamp() {
        try {
            return Calendar.getInstance().getTime().toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * breakToCharsAndSendKeys method takes a String phrase
     * and break it to Chars,
     * Then simulate sendKeys of each individual char to element
     *
     * @author Yahav N. Hoffman
     * @param phrase - String object that passed as a phrase
     */
    private void breakToCharsAndSendKeys(WebElement elem, String phrase) {
        try {
            char[] charset = phrase.toCharArray();
            for(Character c : charset) {
                elem.sendKeys(c.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}