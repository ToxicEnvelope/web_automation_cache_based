package com.amwell.auto.yahav.sample.pom.core.pageobjects.PROVIDER;

import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitingRoomProviderPage extends AbstProviderPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    @FindBy(how=How.CSS, using="div span.fontA4")
    @CacheLookup
    private WebElement scheduleAppointmentBtnCached;

    @FindBy(how=How.CSS, using="div select#cmbAvailability")
    @CacheLookup
    private WebElement selectAvailabilityCached;

    @FindBy(how=How.CSS, using="div div#availInfoIcon")
    @CacheLookup
    private WebElement availabilityInfoIconCached;

    //----------------------------------//
    //--- On Call Notification Popup ---//
    //----------------------------------//

    @FindBy(how=How.CSS, using="div > input#smsNumber")
    @CacheLookup
    private WebElement cellNumberFieldCached;

    @FindBy(how=How.CSS, using="div > input#pagerAddress")
    @CacheLookup
    private WebElement pagerEmailAddressFieldCached;

    @FindBy(how=How.CSS, using="div > button#updateNotificationSettingsPopup-hider")
    @CacheLookup
    private WebElement cancelBtnCached;

    @FindBy(how=How.CSS, using="div > button#updateNotificationSettingsPopup-submit")
    @CacheLookup
    private WebElement saveBtnCached;

    //---------------------------//
    //--- Availability Loader ---//
    //---------------------------//

    @FindBy(how=How.CSS, using="div#availabilityLoading")
    @CacheLookup
    private WebElement loaderContainerCached;

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public WaitingRoomProviderPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    /**
     * initElements method initialize POM WebElement instances
     */
    @Override
    public void initElements() {
        log.info(this.getClass().getSimpleName(), "initElement");
        try {
            wait(750);
            super.initWelcomeBar();
            super.initNavigationBar();
            this.scheduleAppointmentBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","SCHEDULE_APPOINTMENT_BTN"));
            this.selectAvailabilityCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","SELECT_AVAILABILITY_OPTIONS"));
            this.availabilityInfoIconCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","AVAILABILITY_INFO_ICON"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * attemptToLogout method simulate a
     * log out action taken by a logged-in in user.
     *
     * @author Yahav N. Hoffman
     * @return - returns LoginProviderPage instance
     */
    public LoginProviderPage attemptToLogout() {
        log.info(this.getClass().getSimpleName(), "attemptToLogout");
        try {
            click(super.logoutLnkCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new LoginProviderPage(_driver);
    }

    /**
     * changeProviderAvailability method simulates
     * the change of provider's availability status ()
     *
     * @author Yahav N. Hoffman
     * @param state - String object represents the availability state of a provider
     */
    public void changeProviderAvailability(String state) {
        log.info(this.getClass().getSimpleName(), "changeProviderAvailability -> "+ state);
        try {
            selectElementByValue(scheduleAppointmentBtnCached, state);
            log.info("Availability State : " + state);
            if (!loaderContainerCached.isDisplayed()) {
                loaderContainerCached = waitUntilElementPresence(
                        AWMapper.getData("PROVIDER","LOADER_CONTAINER"));
            }
            log.info("Is Loader Displayed : " + loaderContainerCached.isDisplayed());
            log.info("Loader Location : " + loaderContainerCached.getLocation());
            wait(750);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * changeProviderAvailability method simulates a
     * click and selection change of availability state
     * of a provider inside the waiting room.
     *
     * @author Yahav N. Hoffman
     * @param state - String object represents the availability state of a provider
     */
    public void changeProviderAvailabilityAndFillOnCallData(String state, String smsNumber, String pagerEmail) {
        log.info(this.getClass().getSimpleName(), "changeProviderAvailability -> "+ state);
        try {
            selectElementByValue(scheduleAppointmentBtnCached, state);
            initOnCallElementsAndFillData(smsNumber,pagerEmail);
            click(saveBtnCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * initOnCallElements method initializes all
     * On Call Popup view.
     *
     * @author Yahav N. Hoffman
     */
    private void initOnCallElements() {
        log.info(this.getClass().getSimpleName(), "initOnCallElements");
        try {
            this.cellNumberFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ON_CALL_SMS_NUMBER_FIELD"));
            this.pagerEmailAddressFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ON_CALL_PAGER_EMAIL_FIELD"));
            this.cancelBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ON_CALL_CANCEL_BTN"));
            this.saveBtnCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","ON_CALL_SAVE_BTN"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * initOnCallElementsAndFillData method initializes all
     * On Call Popup view and simulate a text fill into both forms.
     *
     * @author Yahav N. Hoffman
     * @param smsNumber - String object represents the sms number
     * @param pagerEmail - String object represents the pager email
     */

    private void initOnCallElementsAndFillData(String smsNumber, String pagerEmail) {
        log.info(this.getClass().getSimpleName(), "initOnCallElementsAndFillData -> "+ smsNumber +" : "+ pagerEmail);
        try {
            initOnCallElements();
            clearAndFillText(cellNumberFieldCached, smsNumber);
            clearAndFillText(pagerEmailAddressFieldCached, pagerEmail);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
