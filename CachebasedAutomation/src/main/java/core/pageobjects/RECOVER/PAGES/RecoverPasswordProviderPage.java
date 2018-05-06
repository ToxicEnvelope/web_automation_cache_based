package com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.PAGES;

import com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.AbsRecoverPasswordPage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RecoverPasswordProviderPage extends AbsRecoverPasswordPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */
    //-----------------------------//
    //----- Page Web Elements -----//
    //-----------------------------//

    @FindBy(how=How.CSS, using="div > input#emailAddress")
    @CacheLookup
    private WebElement emailAddressFieldCached;

    @FindBy(how=How.CSS, using="div > input#lastNameLookup")
    @CacheLookup
    private WebElement lastNameLookupFieldCached;

    @FindBy(how=How.CSS, using="tbody.dijitReset")
    @CacheLookup
    private WebElement monthDropdownRootCached;

    @FindBy(how=How.CSS, using="div > input#lastNameLookup")
    @CacheLookup
    private WebElement dayFieldCached;

    @FindBy(how=How.CSS, using="div > input#lastNameLookup")
    @CacheLookup
    private WebElement yearFieldCached;

    @FindBy(how=How.CSS, using="div > input#securityAnswer")
    @CacheLookup
    private WebElement securityAnswerFieldCached;
    //--------------------------//
    //----- Error Messages -----//
    //--------------------------//

    @FindBy(how=How.CSS, using="div > div.error#emailAddress\\2e errors")
    @CacheLookup
    private WebElement emailErrorMessageCached;

    @FindBy(how=How.CSS, using="div > div.error#lastName\\2e errors")
    @CacheLookup
    private WebElement lastNameErrorMessageCached;

    @FindBy(how=How.CSS, using="div > div#dobLookupError")
    @CacheLookup
    private WebElement dobErrorMessageCached;


    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public RecoverPasswordProviderPage(WebDriver driver) {
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
            super.initProviderOrMemberFormContent();
            // Manipulative WebElements
            this.emailAddressFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "RECOVER_EMAIL_ADDRESS_FIELD"));
            this.lastNameLookupFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","RECOVER_LAST_NAME_FIELD"));
            this.monthDropdownRootCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "RECOVER_MONTH_DROPDOWN_ROOT"));
            this.dayFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","RECOVER_DAY_FIELD"));
            this.yearFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","RECOVER_YEAR_FIELD"));
            this.securityAnswerFieldCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","RECOVER_SEC_ANSWER_FIELD"));
            // Error Messages
            this.emailErrorMessageCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER", "RECOVER_EMAIL_ERROR_MSG"));
            this.lastNameErrorMessageCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","RECOVER_LAST_NAME_FIELD"));
            this.dobErrorMessageCached = waitUntilElementPresence(
                    AWMapper.getData("PROVIDER","RECOVER_LAST_NAME_FIELD"));
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: " +e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * attemptToForgotByPassword method simulates
     * a recovery of a Provider by password
     *
     * @author Yahav N. Hoffman
     * @param recoveryEmail -> String object represent a recovery email
     * @param lastName -> String object represent a Last Name of Provider
     * @param month -> Integer object represent a Month
     * @param day -> String object represent a Day
     * @param year -> String object represent a Year
     */
    public void attemptToForgotByPassword(String recoveryEmail, String lastName, Integer month, String day, String year) {
        log.info(this.getClass().getSimpleName(), "attemptToForgotByUsername -> "+ recoveryEmail +" : "+ lastName +" "+ month +"-"+ day+"-"+year);
        try {
            // select password
            if (!super.passwordBltCacehed.isDisplayed()) {
                super.passwordBltCacehed = waitUntilElementPresence(
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_PASSWORD_RADIO"));
                click(super.passwordBltCacehed);
            }
            else {
                click(super.passwordBltCacehed);
            }
            // click on continue
            if (!super.continueBtnCached.isDisplayed()) {
                super.continueBtnCached = waitUntilElementPresence (
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_CONTINUE_BTN"));
                click(super.continueBtnCached);
            }
            else {
                click(super.continueBtnCached);
            }
            // username / email field
            if (!emailAddressFieldCached.isDisplayed()) {
                emailAddressFieldCached = waitUntilElementPresence(
                        AWMapper.getData("PROVIDER", "RECOVER_EMAIL_ADDRESS_FIELD"));
                clearAndFillText(emailAddressFieldCached, recoveryEmail);
                click(super.continueBtnCached);
            }
            else {
                clearAndFillText(emailAddressFieldCached, recoveryEmail);
                click(super.continueBtnCached);
            }
            fillDemographicSection(lastName, month, day, year);
            click(super.continueBtnCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: " +e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * attemptToForgotByUsername method simulates
     * a recovery of a Provider by username
     *
     * @author Yahav N. Hoffman
     * @param lastName -> String object represent a Last Name of Provider
     * @param month -> Integer object represent a Month
     * @param day -> String object represent a Day
     * @param year -> String object represent a Year
     */
    public void attemptToForgotByUsername(String lastName, Integer month, String day, String year, String secAnswer) {
        log.info(this.getClass().getSimpleName(), "attemptToForgotByUsername -> "+ lastName +" : "+ month +"-"+ day+"-"+year);
        try {
            // select username
            if (!super.usernameBltCacehed.isDisplayed()) {
                super.usernameBltCacehed = waitUntilElementPresence(
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_EMAIL_RADIO"));
                click(super.usernameBltCacehed);
            }
            else {
                click(super.usernameBltCacehed);
            }
            // click on continue
            if (!super.continueBtnCached.isDisplayed()) {
                super.continueBtnCached = waitUntilElementPresence (
                        AWMapper.getData("RECOVER", "RECOVER_PAGE_CONTINUE_BTN"));
                click(super.continueBtnCached);
            }
            else {
                click(super.continueBtnCached);
            }
            fillDemographicSection(lastName, month, day, year);
            click(super.continueBtnCached);
            clearAndFillText(securityAnswerFieldCached, secAnswer);
            click(super.continueBtnCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: " +e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * fillDemographicSection method fill all data
     * at the demographic form
     *
     * @author Yahav N. Hoffman
     * @param lastName -> String object represent a Last Name of Provider
     * @param month -> Integer object represent a Month
     * @param day -> String object represent a Day
     * @param year -> String object represent a Year
     */
    private void fillDemographicSection(String lastName, Integer month, String day, String year) {
        log.info(this.getClass().getSimpleName(), "fillDemographicSection -> "+ lastName +" : "+ month +"-"+ day+"-"+year);
        try {
           // last name field
           if (!lastNameLookupFieldCached.isDisplayed()) {
               lastNameLookupFieldCached = waitUntilElementPresence(
                       AWMapper.getData("PROVIDER", "RECOVER_EMAIL_ADDRESS_FIELD"));
               clearAndFillText(lastNameLookupFieldCached, lastName);
           }
           else {
               clearAndFillText(lastNameLookupFieldCached, lastName);
           }
           // year field
           if (!yearFieldCached.isDisplayed()) {
               yearFieldCached = waitUntilElementPresence(
                       AWMapper.getData("PROVIDER","RECOVER_YEAR_FIELD"));
               clearAndFillText(yearFieldCached, year);
           }
           else {
               clearAndFillText(yearFieldCached, year);
           }
           // month selection
           if (!monthDropdownRootCached.isDisplayed()) {
               monthDropdownRootCached = waitUntilElementPresence(
                       AWMapper.getData("PROVIDER","RECOVER_MONTH_DROPDOWN_ROOT"));
               selectDoB(month);
           }
           else {
               selectDoB(month);
           }
           // day field
           if (!dayFieldCached.isDisplayed()) {
               dayFieldCached = waitUntilElementPresence(
                       AWMapper.getData("PROVIDER", "RECOVER_DAY_FIELD"));
               clearAndFillText(dayFieldCached, day);
           }
           else {
               clearAndFillText(dayFieldCached, day);
           }
       }
       catch (Exception e) {
           log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: " +e.getMessage());
           e.printStackTrace();
       }
    }


    private void selectDoB(Integer number) {
        log.info(this.getClass().getSimpleName(), "selectDoB -> "+ number);
        try {
            List<WebElement> months = monthDropdownRootCached.findElements(By.cssSelector(
                    AWMapper.getData("PROVIDER", "RECOVER_MONTH_DROPDOWN_LIST")));
            log.info("Number of months : " + months.size());
            if (number >= 0 && number <= 12) {
                for (int i=0; i<months.size(); ++i) {
                    if (number == i)
                    {
                        click(months.get(number));
                        break;
                    }
                    continue;
                }
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: " +e.getMessage());
            e.printStackTrace();
        }
    }
}
