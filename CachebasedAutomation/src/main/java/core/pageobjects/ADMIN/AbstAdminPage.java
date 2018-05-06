package com.amwell.auto.yahav.sample.pom.core.pageobjects.ADMIN;

import com.amwell.auto.yahav.sample.pom.core.AWBasePage;
import com.amwell.auto.yahav.sample.pom.core.IWebElementPOMInitializer;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public abstract class AbstAdminPage extends AWBasePage implements IWebElementPOMInitializer {

    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    //-----------------------------//
    //------ Welcome Top Bar ------//
    //-----------------------------//

    @FindBy(how=How.CSS, using="span#welcomeBarName")
    @CacheLookup
    protected WebElement welcomeBarNameCached;

    @FindBy(how=How.CSS, using="span#previousLoginString")
    @CacheLookup
    protected WebElement lastLoginCached;

    @FindBy(how=How.CSS, using="span#WelcomeBarAvailability")
    @CacheLookup
    protected WebElement welcomeBarAvailabilityCached;

    @FindBy(how=How.CSS, using="li>a.myAccountLink")
    @CacheLookup
    protected WebElement myAccountLnkCached;

    @FindBy(how=How.CSS, using="span#WelcomeBarLogout")
    @CacheLookup
    protected WebElement logoutLnkCached;

    //--------------------------------//
    //------ Top Navigation Bar ------//
    //--------------------------------//

    @FindBy(how=How.CSS, using="div#homeLink")
    @CacheLookup
    protected WebElement homePageLnkCached;

    @FindBy(how=How.CSS, using="div#enurseLinkAdmin")
    @CacheLookup
    protected WebElement resourceEditorLnkCached;

    @FindBy(how=How.CSS, using="div#reportsLink")
    @CacheLookup
    protected WebElement reportsLinkLnkCached;

    @FindBy(how=How.CSS, using="div#customizationLink")
    @CacheLookup
    protected WebElement businessRulesLnkCached;

    @FindBy(how=How.CSS, using="div#supportLink")
    @CacheLookup
    protected WebElement supportLnkCached;

    @FindBy(how=How.CSS, using="div#sysAdminLink")
    @CacheLookup
    protected WebElement sysAdminLnkCached;


    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public AbstAdminPage(WebDriver driver) {
        super(driver);
    }


    public void initWelcomeBar() {
        log.entering(this.getClass().getSimpleName(), "initWelcomeBar");
        try {
            wait(750);
            this.welcomeBarNameCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","ABS_WELCOME_BAR_NAME"));
            this.lastLoginCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","ABS_WELCOME_BAR_TIME_LOGIN"));
            this.welcomeBarAvailabilityCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","ABS_WELCOME_BAR_STATUS"));
            this.myAccountLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","ABS_WELCOME_BAR_MY_ACCOUNT"));
            this.logoutLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","ABS_WELCOME_BAR_LOGOUT"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    public void initNavigationBar() {
        log.entering(this.getClass().getSimpleName(), "initNavigationBar");
        try {
            wait(750);
            this.homePageLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "ABS_NAV_BAR_HOME"));
            this.resourceEditorLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "ABS_NAV_BAR_RESOURCES"));
            this.reportsLinkLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "ABS_NAV_BAR_REPORTS"));
            this.businessRulesLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "ABS_NAV_BAR_BUSINESS"));
            this.supportLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "ABS_NAV_BAR_SUPPORT"));
            this.sysAdminLnkCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "ABS_NAV_BAR_SYSADMIN"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }


}
