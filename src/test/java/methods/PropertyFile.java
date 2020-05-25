package methods;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class PropertyFile extends SetUp{

    public static Properties properties;
    public static InputStream inputStream;
    public ExtentReports report;
    public ExtentTest test;


    @BeforeMethod
    public void readPropertiesFile(){

        try {

            String workingDir = System.getProperty("user.dir");
            System.out.println(workingDir);

            properties = new Properties();
            inputStream = SetUp.readFile("chromedriver/application.properties");

            properties.load(inputStream);

            //uzima vrednost do chromeDrivera
            String driverPath = properties.getProperty("DRIVER_PATH_WIN_CHROME");
            //uzima vrednost do report-a
            String reportPath = properties.getProperty("FOLDER_REPORT_PATH");


            System.setProperty(properties.getProperty("DRIVER_NAME_CHROME"), workingDir + driverPath);
            System.out.println(workingDir + driverPath);

            // kreiranje reporta
            report = new ExtentReports(workingDir + reportPath,false);
            report.addSystemInfo("URL", "https://www.facebook.com");

            SetUp.createDriver();

            getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

            // govorimo pretrazivacu koju stranicu da otvori
            getDriver().get(properties.getProperty("URL"));

        }catch (Exception e){
            System.out.println("Failed to instantiate and load properties and chrome driver!");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws Exception {

        if (testResult.getStatus()== ITestResult.FAILURE){
            String path = Screenshot.takeScreenshot(getDriver(), Screenshot.generateFileName(testResult));
            String imgPath = test.addScreenCapture(path);
            test.log(LogStatus.FAIL, "Test case which FAILED is " + testResult.getName(), imgPath);
            test.log(LogStatus.FAIL, "Method which FAILED is " + testResult.getThrowable());
        }
        else if (testResult.getStatus()== ITestResult.SUCCESS){
            test.log(LogStatus.PASS, "Test case which PASSED IS " + testResult.getName());
        }
        else if (testResult.getStatus()== ITestResult.SKIP){
            test.log(LogStatus.PASS, "Test case which SKIPPED IS " + testResult.getName());
        }
        SetUp.quitDriver();
        report.endTest(test);
        report.flush();
    }
}