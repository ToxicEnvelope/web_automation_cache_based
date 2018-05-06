package com.amwell.auto.yahav.sample.pom.core.pageobjects.ADMIN;

import com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.PAGES.RecoverPasswordAdminPage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LoginAdminPage extends AbstAdminPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    @FindBy(how=How.CSS, using="div>input#usernmae")
    @CacheLookup
    private WebElement usernameFieldCached;

    @FindBy(how=How.CSS, using="div>input#password")
    @CacheLookup
    private WebElement passwordFieldCached;

    @FindBy(how=How.CSS, using="div>button#loginBtn")
    @CacheLookup
    private WebElement loginBtnCached;

    @FindBy(how=How.CSS, using="div>a.forgotPasswordLink")
    @CacheLookup
    private WebElement recoverPasswordLnkCached;

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public LoginAdminPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    /**
     * initElements method initialize POM WebElement instances
     */
    @Override
    public void initElements() {
        log.info(this.getClass().getSimpleName(), "initElements");
        try {
            wait(750);
            this.usernameFieldCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","LOGIN_PAGE_USERNAME"));
            this.passwordFieldCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "LOGIN_PAGE_PASSWORD"));
            this.recoverPasswordLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "LOGIN_PAGE_RECOVER_PASS_LNK"));
            this.loginBtnCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "LOGIN_PAGE_LOGIN_BTN"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * attemptToMakeLogin method simulates
     * a login in admin page give username and password
     *
     * @author Yahav N. Hoffman
     * @param username - username as String
     * @param password - password as String
     * @return - returns a instance of HomeAdminPage as POM
     */
    public HomeAdminPage attemptToMakeLogin(String username, String password) {
        log.info(this.getClass().getSimpleName(), "attemptToMakeLogin -> "+ username +" : "+ password);
        try {
            // Username filed section
            if (!usernameFieldCached.isDisplayed()) {
                usernameFieldCached = waitUntilElementPresence(
                        AWMapper.getData("ADMIN", "USERNAME"));
            }
            else {
                clearAndFillText(usernameFieldCached, username);
            }
            // Password filed section
            if (!passwordFieldCached.isDisplayed()) {
                passwordFieldCached = waitUntilElementPresence(
                        AWMapper.getData("ADMIN", "PASSWORD"));
            }
            else {
                clearAndFillText(passwordFieldCached, password);
            }
            wait(750);
            // Log in button section
            if (!loginBtnCached.isDisplayed()) {
                loginBtnCached = waitUntilElementPresence(
                        AWMapper.getData("ADMIN", "LOGIN_BTN"));
                click(loginBtnCached);
            }
            else {
                click(loginBtnCached);
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new HomeAdminPage(_driver);
    }

    /**
     * recoverPasswordAdminPage method
     * simulate a click on recover password link,
     * Then return a new instance of RecoverPasswordAdminPage
     *
     * @author Yahav N. Hoffman
     * @return - returns an instance of RecoverPasswordAdminPage as POM
     */
    public RecoverPasswordAdminPage attemptToRecoverAdminCredentials() {
        log.info(this.getClass().getSimpleName(), "attemptToRecoverAdminCredentials");
        try {
            click(recoverPasswordLnkCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new RecoverPasswordAdminPage(_driver);
    }

}
