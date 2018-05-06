package com.amwell.auto.yahav.sample.pom.core.pageobjects.PROVIDER;

import com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.PAGES.RecoverPasswordProviderPage;
import com.amwell.auto.yahav.sample.pom.core.pageobjects.STAFF.LoginStaffPage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginProviderPage extends AbstProviderPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    @FindBy(how=How.CSS, using="div>input#username")
    @CacheLookup
    private WebElement usernameFieldCached;

    @FindBy(how=How.CSS, using="div>input#password")
    @CacheLookup
    private WebElement passwordFieldCached;

    @FindBy(how=How.CSS, using="div.loginHelpContainer>a")
    @CacheLookup
    private WebElement recoverPasswordLnkCached;

    @FindBy(how=How.CSS, using="div>button#loginBtn")
    @CacheLookup
    private WebElement loginBtnCached;

    @FindBy(how=How.CSS, using="div#enrollSection>a")
    @CacheLookup
    private WebElement enrollLnkCached;

    @FindBy(how=How.CSS, using="div#practiceStaffSection>a")
    @CacheLookup
    private WebElement loginAssitantLnkCached;

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public LoginProviderPage(WebDriver driver) {
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
                    AWMapper.getData("PROVIDER", "LOGIN_PAGE_USERNAME"));
            this.passwordFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "LOGIN_PAGE_PASSWORD"));
            this.recoverPasswordLnkCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "LOGIN_PAGE_RECOVER_PASS_LNK"));
            this.loginBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "LOGIN_PAGE_LOGIN_BTN"));
            this.enrollLnkCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "LOGIN_PAGE_ENROLL_LNK"));
            this.loginAssitantLnkCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "LOGIN_PAGE_ASSISTANT_LNK"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * attemptToMakeLogin method simulates
     * a login in provider page given a username and password
     *
     * @author Yahav N. Hoffman
     * @param username - String object that represent a username
     * @param password - String object that represent a password
     * @return - returns a HomePageProvider instance
     */
    public WaitingRoomProviderPage attemptToMakeLogin(String username, String password) {
        log.info(this.getClass().getSimpleName(), "attemptToMakeLogin -> "+ username +" : "+ password);
        try {
            // Username filed section
            if (!usernameFieldCached.isDisplayed()) {
                usernameFieldCached = waitUntilElementPresence(AWMapper.getData("ADMIN", "USERNAME"));
            }
            else {
                clearAndFillText(usernameFieldCached, username);
            }
            // Password filed section
            if (!passwordFieldCached.isDisplayed()) {
                passwordFieldCached = waitUntilElementPresence(AWMapper.getData("ADMIN", "PASSWORD"));
            }
            else {
                clearAndFillText(passwordFieldCached, password);
            }
            wait(750);
            // Log in button section
            if (!loginBtnCached.isDisplayed()) {
                loginBtnCached = waitUntilElementPresence(AWMapper.getData("ADMIN", "LOGIN_BTN"));
                click(loginBtnCached);
            }
            else {
                click(loginBtnCached);
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new WaitingRoomProviderPage(_driver);
    }

    /**
     * recoverProviderCredentials method simulates a
     * log out from a concurrent session
     *
     * @author Yahav N. Hoffman
     * @return - returns a RecoverProviderPage instance
     */
    public RecoverPasswordProviderPage recoverProviderCredentials() {
        log.info(this.getClass().getSimpleName(), "recoverProviderCredentials");
        try {
            click(recoverPasswordLnkCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new RecoverPasswordProviderPage(_driver);
    }

    /**
     * navigateToStaffPage method simulates a
     * navigation between Provider.Login page to Staff.Login page
     *
     * @author Yahav N. Hoffman
     * @return - returns an instance of LoginStaffPage object
     */
    public LoginStaffPage navigateToLoginStaffPage() {
        log.info(this.getClass().getSimpleName(), "navigateToLoginStaffPage");
        try {
            click(loginAssitantLnkCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '" + this.getClass().getSimpleName() +"' caused by: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new LoginStaffPage(_driver);
    }
}
