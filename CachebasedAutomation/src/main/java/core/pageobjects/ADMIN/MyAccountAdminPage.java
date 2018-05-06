package com.amwell.auto.yahav.sample.pom.core.pageobjects.ADMIN;

import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountAdminPage extends AbstAdminPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //-------------------------------//
    //----- General WebElements -----//
    //-------------------------------//

    @FindBy(how=How.CSS, using="div > button#editLoginCredsButton")
    @CacheLookup
    private WebElement editLoginCredsBtnCached;

    //--------------------------------//
    //----- Dynamic Popup Fields -----//
    //--------------------------------//

    @FindBy(how=How.CSS, using="div>input#oldPassword")
    @CacheLookup
    private WebElement oldPasswordFieldCached;

    @FindBy(how=How.CSS, using="div>input#password1")
    @CacheLookup
    private WebElement newPasswordFieldCached;

    @FindBy(how=How.CSS, using="div#password1\\2e errors")
    @CacheLookup
    private WebElement newPasswordErrorCached;

    @FindBy(how=How.CSS, using="div>input#password2")
    @CacheLookup
    private WebElement confirmPasswordFieldCached;

    @FindBy(how=How.CSS, using="div#password2\\2e errors")
    @CacheLookup
    private WebElement confirmPasswordErrorCached;

    @FindBy(how=How.CSS, using="div > button#okButton")
    @CacheLookup
    private WebElement popUpSaveBtnCached;

    @FindBy(how=How.CSS, using="div > button#cancelButton")
    @CacheLookup
    private WebElement popUpCancelBtnCached;



    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public MyAccountAdminPage(WebDriver driver) {
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
            super.initWelcomeBar();
            super.initNavigationBar();
            this.editLoginCredsBtnCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","EDIT_LOGIN_CREDS_BTN"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *  initializes all WebElement objects located at Edit Login Credentials Popup
     */
    private void initPopUpElements() {
        log.info(this.getClass().getSimpleName(), "initPopUpElements");
        try {
            this.oldPasswordFieldCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","OLD_PWD_FIELD"));
            this.newPasswordFieldCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","NEW_PWD_FIELD"));
            this.confirmPasswordFieldCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","CONF_PWD_FIELD"));
            this.confirmPasswordFieldCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","NEW_PWD_ERROR"));
            this.confirmPasswordErrorCached  = waitUntilElementPresence(
                    AWMapper.getData("ADMIN","CONF_PWD_ERROR"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * attemptToEditLoginCredentialsAndSave method simulates
     * editing Admin Credentials and Save the changes
     *
     * @author Yahav N. Hoffman
     * @param oldPwd -> String object represents the old password
     * @param newPwd -> String object represents the new password
     * @param confPwd -> String object represents the confirmed password
     */
    public void attemptToEditLoginCredentialsAndSave(String oldPwd, String newPwd, String confPwd) {
        log.info(this.getClass().getSimpleName(), "attemptToEditLoginCredentialsAndSave -> "+ oldPwd +" : "+ newPwd +"="+confPwd);
        try {
           // edit button
            if (!editLoginCredsBtnCached.isDisplayed()) {
               editLoginCredsBtnCached = waitUntilElementPresence(
                       AWMapper.getData("ADMIN","EDIT_LOGIN_CREDS_BTN"));
               click(editLoginCredsBtnCached);
           }
           else {
               click(editLoginCredsBtnCached);
           }
           fillEditAdminLoginCredsForm(oldPwd, newPwd, confPwd);
           // popup button - save
           if (!popUpSaveBtnCached.isDisplayed()) {
               popUpSaveBtnCached = waitUntilElementPresence(
                       AWMapper.getData("ADMIN", "SAVE_BTN"));
               click(popUpSaveBtnCached);
           }
           else {
               click(popUpSaveBtnCached);
           }
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * fillEditAdminLoginCredsForm method responsible of
     * validating existence and filling all fields with data
     *
     * @authot Yahav N. Hoffman
     * @param oldPwd -> String object represents the old password
     * @param newPwd -> String object represents the new password
     * @param confPwd -> String object represents the confirmed password
     */
    private void fillEditAdminLoginCredsForm(String oldPwd, String newPwd, String confPwd) {
        log.info(this.getClass().getSimpleName(), "fillEditAdminLoginCredsForm -> "+ oldPwd +" : "+ newPwd +"="+confPwd);
        try {
            initPopUpElements();
            // old password field
            if (!oldPasswordFieldCached.isDisplayed()) {
                this.oldPasswordFieldCached = waitUntilElementPresence(
                        AWMapper.getData("ADMIN","OLD_PWD_FIELD"));
                clearAndFillText(oldPasswordFieldCached, oldPwd);
            }
            else {
                clearAndFillText(oldPasswordFieldCached, oldPwd);
            }
            // new password field
            if (!newPasswordFieldCached.isDisplayed()) {
                this.newPasswordFieldCached = waitUntilElementPresence(
                        AWMapper.getData("ADMIN","NEW_PWD_FIELD"));
                clearAndFillText(newPasswordFieldCached, newPwd);
            }
            else {
                clearAndFillText(newPasswordFieldCached, newPwd);
            }
            // confirm password field
            if (!confirmPasswordFieldCached.isDisplayed()) {
                this.confirmPasswordFieldCached = waitUntilElementPresence(
                        AWMapper.getData("ADMIN","CONF_PWD_FIELD"));
                clearAndFillText(confirmPasswordFieldCached, confPwd);
            }
            else {
                clearAndFillText(confirmPasswordFieldCached, confPwd);
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }


}
