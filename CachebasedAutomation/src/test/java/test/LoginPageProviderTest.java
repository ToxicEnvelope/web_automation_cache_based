package com.amwell.auto.yahav.sample.test;

import com.amwell.auto.yahav.sample.pom.core.pageobjects.PROVIDER.WaitingRoomProviderPage;
import com.amwell.auto.yahav.sample.pom.core.pageobjects.PROVIDER.LoginProviderPage;
import com.amwell.auto.yahav.sample.pom.core.pageobjects.RECOVER.PAGES.RecoverPasswordProviderPage;
import com.amwell.auto.yahav.sample.pom.core.utils.AWMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginPageProviderTest {

    String engine = AWMapper.getData("CONFIG_DATA","WD_ENGINE");
    String wdPath = AWMapper.getData("CONFIG_DATA","WD_PATH");
    WebDriver driver;

    LoginProviderPage loginProviderPage;
    WaitingRoomProviderPage waitingRoomProviderPage;
    RecoverPasswordProviderPage recoverPasswordProviderPage;

    @BeforeClass
    public void setUp() {
        System.setProperty(engine, wdPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MILLISECONDS);
        driver.get("https://bos-qa-app-05.americanwell.com/loginPhysician.htm");
    }


    @Test()
    public void loginProviderChangeAvailabilityAndLogoutTest() {
        loginProviderPage = new LoginProviderPage(driver);
        waitingRoomProviderPage = loginProviderPage.attemptToMakeLogin(AWMapper.getData("TEST_DATA", "PROVIDER_USERNAME"),
                                                                       AWMapper.getData("TEST_DATA", "PROVIDER_PASSWORD"));
        Assert.assertEquals(waitingRoomProviderPage, WaitingRoomProviderPage.class);
        waitingRoomProviderPage.changeProviderAvailability(AWMapper.getData("TEST_DATA", "PROVIDER_STATE_UNAVAILABLE"));
        waitingRoomProviderPage.changeProviderAvailability(AWMapper.getData("TEST_DATA", "PROVIDER_STATE_AVAILABLE"));
        Assert.assertEquals(waitingRoomProviderPage.attemptToLogout(), LoginProviderPage.class);
        recoverPasswordProviderPage = loginProviderPage.recoverProviderCredentials();
        recoverPasswordProviderPage.attemptToForgotByPassword(AWMapper.getData("TEST_DATA", "RECOVER_PROVIDER_PAGE_USERNAME"),
                                                              AWMapper.getData("TEST_DATA", "RECOVER_PROVIDER_PAGE_LASTNAME"),
                                                              1,
                                                              AWMapper.getData("TEST_DATA", "RECOVER_PROVIDER_PAGE_DAY_OF_BIRTH"),
                                                              AWMapper.getData("TEST_DATA", "RECOVER_PROVIDER_PAGE_YEAR_OF_BIRTH"));
    }

//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }


}
