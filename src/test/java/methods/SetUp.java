package methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;

public class SetUp {

    private static WebDriver driver = null;


    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver createDriver(){

        if (getDriver() != null){
            return driver;
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--enable-automation");

        driver = new ChromeDriver(options);
        return driver;
    }

    public static void quitDriver(){
        getDriver().quit();
        driver = null;
    }

    public static InputStream readFile(String fileName){
        InputStream input = PropertyFile.class.getClassLoader().getResourceAsStream(fileName);
        if (input == null){
            System.out.println("Sorry, unable to find " + fileName);
        }
        return input;
    }
}
