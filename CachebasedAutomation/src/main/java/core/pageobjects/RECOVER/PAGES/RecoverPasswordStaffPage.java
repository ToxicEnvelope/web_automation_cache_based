package com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.PAGES;

import com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.AbsRecoverPasswordPage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecoverPasswordStaffPage extends AbsRecoverPasswordPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    @FindBy(how=How.CSS, using="div>div#twoFactorAuthLookupConfirm")
    @CacheLookup
    private WebElement twoFacAuthMsgCached;

    @FindBy(how=How.CSS, using="div>button#okBtn")
    @CacheLookup
    private WebElement okBtnCached;


    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public RecoverPasswordStaffPage(WebDriver driver) {
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
            super.initRecoverPageNavigationLinks();
            super.initAdminOrStaffFormContent();
            this.twoFacAuthMsgCached = waitUntilElementPresence(
                    AWMapper.getData("STAFF", "RECOVER_PAGE_2FA_MSG"));
            this.okBtnCached = waitUntilElementPresence(
                    AWMapper.getData("STAFF", "RECOVER_PAGE_OK_BTN"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at "+ this.getClass().getSimpleName() + " caused by: " +e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * recoverAdminCredentials method attempt to simulate credential recovery
     * of an Admin with wrong credentials
     *
     * @author Yahav N. Hoffman
     * @param lastName - last name of staff
     * @param username - username of staff
     */
    public void recoverAdminCredentials(String lastName, String username) {
        log.info(this.getClass().getSimpleName(), "recoverAdminCredentials -> "+ lastName +" : "+ username );
        try {
            // last name field
            if (!(super.lastNameFieldCached.isDisplayed())) {
                super.lastNameFieldCached = waitUntilElementPresence(
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_LAST_NAME"));
            }
            else {
                clearAndFillText(super.lastNameFieldCached, lastName);
            }
            // username field
            if (!super.usernameFieldCached.isDisplayed()) {
                super.usernameFieldCached = waitUntilElementPresence(
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_USERNAME"));
            }
            else {
                clearAndFillText(super.usernameFieldCached, username);
            }
            // continue button
            if (!super.continueBtnCached.isDisplayed()) {
                super.continueBtnCached = waitUntilElementPresence(
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_CONTINUE_BTN"));
                click(super.continueBtnCached);
            }
            else {
                click(super.continueBtnCached);
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at "+ this.getClass().getSimpleName() + " caused by: " +e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * collectTwoFactorAuthMsgAndClickOk method collects the actual 2FM message
     * presented after recovering admin credentials
     *
     * @author Yahav N. Hoffman
     * @return - returns 2FA message as String object
     */
    public String collectTwoFactorAuthMsgAndClickOk() {
        log.info(this.getClass().getSimpleName(), "collectTwoFactorAuthMsgAndClickOk");
        String resultMessage = "";
        try {
            if (!twoFacAuthMsgCached.isDisplayed()) {
                twoFacAuthMsgCached = waitUntilElementVisible(
                        AWMapper.getData("STAFF", "RECOVER_PAGE_2FA_MSG"));
            }
            else {
                resultMessage = twoFacAuthMsgCached.getText();
                if (!okBtnCached.isDisplayed()) {
                    okBtnCached = waitUntilElementPresence(
                            AWMapper.getData("STAFF", "RECOVER_PAGE_OK_BTN"));
                }
                click(okBtnCached);
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at "+ this.getClass().getSimpleName() + " caused by: " +e.getMessage());
            e.printStackTrace();
            return null;
        }
        return resultMessage;
    }
}


