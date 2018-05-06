package com.amwell.auto.yahav.sample.pom.core.pageobjects.PROVIDER;

import com.amwell.auto.yahav.sample.pom.core.AWBasePage;
import com.amwell.auto.yahav.sample.pom.core.IWebElementPOMInitializer;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.logging.Logger;

public abstract class AbstProviderPage extends AWBasePage implements IWebElementPOMInitializer {

    public Logger log = Logger.getLogger(this.getClass().getName());

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

    @FindBy(how=How.CSS, using="span#WelcomeBarLogout")
    @CacheLookup
    protected WebElement logoutLnkCached;

    //--------------------------------//
    //------ Top Navigation Bar ------//
    //--------------------------------//

    @FindBy(how=How.CSS, using="div div#provNavMyPracticeId")
    @CacheLookup
    protected WebElement myPracticeBtnCached;

    @FindBy(how=How.CSS, using="div div#provAdministrationId")
    @CacheLookup
    protected WebElement administrationBtnCached;

    @FindBy(how=How.CSS, using="div div#provSystemTestReferenceId")
    @CacheLookup
    protected WebElement systemTestBtnCached;

    @FindBy(how=How.CSS, using="div div#userMessageCenterId")
    @CacheLookup
    protected WebElement userMessageCenterBtnCached;

    @FindBy(how=How.CSS, using="div div#providerWrapUpsNav")
    @CacheLookup
    protected WebElement wrapUpsBtnCached;

    @FindBy(how=How.CSS, using="div div#provPatientsWaitingId")
    @CacheLookup
    protected WebElement patientsWaitingBtnCached;

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public AbstProviderPage(WebDriver driver) { super(driver); }

    /**
     * initWelcomeBar method,
     * initialize all WebElements childes that located inside <div id="WelcomeBar"></div>
     *
     * @author Yahav N, Hoffman
     */
    public void initWelcomeBar() {
        log.entering(this.getClass().getSimpleName(), "initWelcomeBar");
        try {
            wait(750);
            this.welcomeBarNameCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_WELCOME_BAR_NAME"));
            this.lastLoginCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_WELCOME_BAR_TIME_LOGIN"));
            this.welcomeBarAvailabilityCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_WELCOME_BAR_STATUS"));
            this.logoutLnkCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_WELCOME_BAR_LOGOUT"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * initNavigationBar method,
     * initialize all WebElements childes that located inside <div id="cwTopNav"></div>
     *
     * @author Yahav N. Hoffman
     */
    @Override
    public void initNavigationBar() {
        log.entering(this.getClass().getSimpleName(), "initNavigationBar");
        try {
            wait(750);
            this.myPracticeBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_NAV_BAR_MY_PRACTICE_BTN"));
            this.administrationBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_NAV_BAR_ADMINISTRATION_BTN"));
            this.systemTestBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_NAV_BAR_SYSTEM_TEST_BTN"));
            this.userMessageCenterBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_NAV_BAR_MESSAGE_CENTER_BTN"));
            this.wrapUpsBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_NAV_BAR_WRAP_UPS_BTN"));
            this.patientsWaitingBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ABS_NAV_BAR_PATIENTS_WAITING_BTN"));
        }
        catch (Exception e) {
            log.warning("Exception occurred at "+ this.getClass().getSimpleName() +" caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

}
