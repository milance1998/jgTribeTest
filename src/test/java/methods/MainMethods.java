package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMethods extends PropertyFile {

    static WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    private static final int elementLocatingTimeout = 10;

    // metoda koja ceka da element postane klikabilan
    public static WebElement waitForClickableElement(WebDriver webDriver, By selector)
    {
        return new WebDriverWait(webDriver, elementLocatingTimeout).until(ExpectedConditions.elementToBeClickable(selector));
    }


    // metoda koja ceka da element bude vidljiv u DOM-u
    public static WebElement waitForElement(WebDriver webDriver, By selector)
    {
        return new WebDriverWait(webDriver, elementLocatingTimeout).until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    // metoda koja ceka da neki element bude fizicki vidljiv na stranici
    public static void waitElementToBeVisible(By waitElement) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
    }

    // metoda koja ceka da neki element ne bude vise vidljiv
    public static void waitElementToBeNotVisible(By waitElement){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(waitElement));
    }


    // metoda koja klikce na dugme
    public static void clickOnButton(By locator){
        waitForClickableElement(getDriver(), locator).click();
    }

    // metoda koja upisuje neku vrednost u input polje
    public static void type(By locator,String text){
        waitForElement(getDriver(), locator).sendKeys(text);
    }

    //metoda koja brise vrednost iz input polja
    public static void clearValue(By locator){
        waitForElement(getDriver(), locator).clear();
    }



}
