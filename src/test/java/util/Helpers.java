package util;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import hooks.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helpers {

    private static String getBase64Screenshot() {
        return ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64);
    }

    public static void takeScreenShot(){
        ExtentCucumberAdapter.getCurrentStep()
                .info(MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot())
                        .build());
    }

    public static void waitForScreenshot(WebElement element){
        new WebDriverWait(BaseTest.driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void waitToBeVisible(WebElement element){
        new WebDriverWait(BaseTest.driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitToBeAllVisible(WebElement element){
        new WebDriverWait(BaseTest.driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfAllElements(element));
    }


}
