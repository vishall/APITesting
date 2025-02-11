package pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import stepdefinations.BaseClass;
import java.time.Duration;

    public class Section2Page extends BaseClass {

    protected BaseClass base;

    public Section2Page(BaseClass baseClass) {

            base = baseClass;
    }

    public String ext = null;
    public By topHeading = By.id("com.youtility.test:id/tvTop");
    public By getStartedButton = By.id("com.youtility.test:id/btnGetStarted");
    public By appTour = By.id("com.youtility.test:id/pagerIndicator");
    final String homeScreen = "home finances";
    final String homeScreenText = "home\nfinances";
    final String secondScreenText = "Switch providers";
    final String thirdScreenText = "bank accounts";

    public void isAppLoaded(){

        waitForElementToBeAvailable(topHeading);
        MobileElement topBanner = (MobileElement) driver.findElement(topHeading);
        ext = topBanner.getText();
        waitForElementToBeAvailable(getStartedButton);
        Assert.assertTrue(isElementPresent(getStartedButton));
    }

    public void swipeScreen() {

        waitForElementToBeAvailable(topHeading);
        MobileElement topBanner = (MobileElement) driver.findElement(topHeading);
        ext = topBanner.getText();
        MobileElement elementIndicator = (MobileElement) driver.findElement(appTour);
        Point bannerPoint = elementIndicator.getLocation();
        // Get size of device screen
        Dimension screenSize = driver.manage().window().getSize();
        // Get start and end coordinates for horizontal swipe
        int startX = Math.toIntExact(Math.round(screenSize.getWidth() * 0.8));
        int endX = 0;
        TouchAction swipe = new TouchAction(driver);
        swipe
                .press(PointOption.point(startX, bannerPoint.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endX, bannerPoint.getY()))
                .release();
        driver.performTouchAction(swipe);
    }

    public void navigateToScreen(String page) {

        if(page.equals(homeScreen)){

            Assert.assertTrue(ext.contains(homeScreenText));
        }
        else if(page.equals(secondScreenText)){

            Assert.assertTrue(ext.contains(secondScreenText));

        }
        else if(page.equals(thirdScreenText)){
            swipeScreen();
            Assert.assertTrue(ext.contains(thirdScreenText));
            tearDown();
        }

    }
}
