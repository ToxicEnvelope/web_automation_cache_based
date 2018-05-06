package com.amwell.auto.yahav.sample.test;

import com.amwell.auto.yahav.sample.pom.core.pageobjects.ADMIN.HomeAdminPage;
import com.amwell.auto.yahav.sample.pom.core.pageobjects.ADMIN.LoginAdminPage;
import com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.PAGES.RecoverPasswordAdminPage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class LoginPageAdminTest {

    String engine = AWMapper.getData("CONFIG_DATA","WD_ENGINE");
    String wdPath = AWMapper.getData("CONFIG_DATA","WD_PATH");
    WebDriver driver;

    LoginAdminPage loginPageAdmin;
    HomeAdminPage homePageAdmin;
    RecoverPasswordAdminPage recoverPasswordAdminPage;

    @BeforeClass
    public void setUp() {
        System.setProperty(engine, wdPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MILLISECONDS);
        driver.get("https://isr-lap-yhoffma.americanwell.com:8443/startAdmin.htm");
    }


    @Test(priority = 1)
    public void loginAdminPageAndLogoutTest() {
        loginPageAdmin = new LoginAdminPage(driver);
        homePageAdmin = loginPageAdmin.attemptToMakeLogin(AWMapper.getData("TEST_DATA", "ADMIN_USERNAME"),
                                                          AWMapper.getData("TEST_DATA", "ADMIN_PASSWORD"));
        Assert.assertEquals(homePageAdmin, HomeAdminPage.class);
    }

    @Test(priority = 2)
    public void recoverAdminCredentialsTest() {
        loginPageAdmin = new LoginAdminPage(driver);
        recoverPasswordAdminPage = loginPageAdmin.attemptToRecoverAdminCredentials();
        recoverPasswordAdminPage.recoverAdminCredentials(AWMapper.getData("TEST_DATA", "RECOVER_PAGE_2FA_LASTNAME"),
                                                                                             AWMapper.getData("TEST_DATA", "RECOVER_PAGE_2FA_USERNAME"));

    }

//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
