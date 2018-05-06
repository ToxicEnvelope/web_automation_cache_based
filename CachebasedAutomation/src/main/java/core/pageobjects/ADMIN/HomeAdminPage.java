package com.amwell.auto.yahav.sample.pom.core.pageobjects.ADMIN;

import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import jdk.jfr.events.ExceptionThrownEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HomeAdminPage extends AbstAdminPage {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @see FindBy - searched a pointed locator in DOM using REST calls
     * @see CacheLookup - overrides the FindBy activity by looking in DOM cache instead making REST calls
     */


    @FindBy(how=How.CSS, using="div div#memberFeatures")
    @CacheLookup
    private WebElement featuresContainerCached;

    List<WebElement> featuresList = new ArrayList<>(11);

    /**
     * Constructor, excepts only WebDriver instance
     *
     * @param driver - WebDriver object
     * @author Yahav N. Hoffman
     */
    public HomeAdminPage(WebDriver driver) {
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
            super.initWelcomeBar();
            super.initNavigationBar();
            this.featuresContainerCached = waitUntilElementPresence(
                    AWMapper.getData("ADMIN", "HOME_PAGE_FEATURES_LIST"));
            initFeaturesList();
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * attemptToLogout method simulates an
     * admin user make a logout by clicking on logout link
     *
     * @author Yahav N. Hoffman
     * @return -> returns a new instance of LoginAdminPage object
     */
    public LoginAdminPage attemptToLogout() {
        log.info(this.getClass().getSimpleName(), "attemptToLogout");
        try {
            click(super.logoutLnkCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new LoginAdminPage(_driver);
    }

    /**
     * navigateToMyAccount method simulate a navigation
     * to Admin my account page
     * @return -> returns a new instance of MyAccountAdminPage object
     */
    public MyAccountAdminPage navigateToMyAccount() {
        log.info(this.getClass().getSimpleName(), "navigateToMyAccount");
        try {
            click(super.myAccountLnkCached);
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getSimpleName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
        return new MyAccountAdminPage(_driver);
    }

    /**
     * initFeaturesList method responsible of
     * initializing all WebElement objects that located at the CENTER of page
     * each and every one of them is then stored at a local variable this.featureList
     *
     * @author Yahav N. Hoffman
     */
    private void initFeaturesList() {
        log.info(this.getClass().getSimpleName(), "initFeaturesList");
        try {
            featuresList.addAll(featuresContainerCached.findElements(By.cssSelector(".roundedLink")));
            for(WebElement e : featuresList) {
                System.out.println(e + " -> " +e.getLocation());
            }
        }
        catch (Exception e) {
            log.warn("Exception occurred at '"+ this.getClass().getName() +"' caused by: "+ e.getMessage());
            e.printStackTrace();
        }
    }



}
