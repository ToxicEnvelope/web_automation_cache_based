package com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER;

import com.amwell.auto.yahav.sample.pom.core.AWBasePage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public abstract class AbsRecoverPasswordPage extends AWBasePage implements IWebElementRecoverInitializer {

    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    //-----------------------------------------------------//
    //------ Recover Password Page Navigation Links ------//
    //-----------------------------------------------------//

    @FindBy(how=How.CSS, using="nav#navMenu a:first-child")
    @CacheLookup
    protected WebElement homeLnkCached;

    @FindBy(how=How.CSS, using="#navMenu a:nth-child(3)")
    @CacheLookup
    protected WebElement consumerLoginLnkCached;

    @FindBy(how=How.CSS, using="nav#navMenu a:last-child")
    @CacheLookup
    protected WebElement providerLoginLnkCached;

    //------------------------------------------------//
    //------ Recover Password Page Form Content ------//
    //------  Provider Page  AMD  Member Page   ------//
    //------------------------------------------------//

    @FindBy(how=How.CSS, using="div > input#forgotPasswordOption")
    @CacheLookup
    protected WebElement passwordBltCacehed;

    @FindBy(how=How.CSS, using="div > input#forgotEmailOption")
    @CacheLookup
    protected WebElement usernameBltCacehed;

    //------------------------------------------------//
    //------ Recover Password Page Form Content ------//
    //------              Admin Page            ------//
    //------------------------------------------------//

    @FindBy(how=How.CSS, using="div>input#lastName")
    @CacheLookup
    protected WebElement lastNameFieldCached;

    @FindBy(how=How.CSS, using="div>input#username")
    @CacheLookup
    protected WebElement usernameFieldCached;

    //--------------------------------------------------//
    //------ Recover Password Generic  WebElement ------//
    //------ used in both Provider & Member Pages ------//
    //--------------------------------------------------//

    @FindBy(how=How.CSS, using="div > button#continueBtn")
    @CacheLookup
    protected WebElement continueBtnCached;

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public AbsRecoverPasswordPage(WebDriver driver) {
        super(driver);
    }

    /**
     * initRecoverPageNavigationLinks method initializes
     * the WebElement objects in the top navigation bar
     */
    @Override
    public void initRecoverPageNavigationLinks() {
        log.entering(this.getClass().getSimpleName(), "initRecoverNavigationLinks");
        try {
            wait(750);
            this.homeLnkCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_NAV_HOME_LNK"));
            this.consumerLoginLnkCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_NAV_CONSUMER_LOGIN_LNK"));
            this.providerLoginLnkCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_NAV_PROVIDER_LOGIN_LNK"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public void initProviderOrMemberFormContent() {
        log.entering(this.getClass().getSimpleName(), "initProviderOrMemberFormContent");
        try {
            wait(750);
            this.passwordBltCacehed = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_PASSWORD_RADIO"));
            this.usernameBltCacehed = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_EMAIL_RADIO"));
            this.continueBtnCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_CONTINUE_BTN"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public void initAdminOrStaffFormContent() {
        log.entering(this.getClass().getSimpleName(), "initAdminOrStaffFormContent");
        try {
            wait(750);
            this.lastNameFieldCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_LAST_NAME"));
            this.usernameFieldCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_USERNAME"));
            this.continueBtnCached = waitUntilElementPresence(
                    AWMapper.getData("RECOVER", "RECOVER_PAGE_CONTINUE_BTN"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }





}
