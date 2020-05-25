package tests;

import com.relevantcodes.extentreports.LogStatus;
import methods.PropertyFile;
import org.testng.annotations.Test;
import pageObjects.jtMaldives;

import java.io.IOException;

public class testMaldives extends PropertyFile {

    @Test
    public void Maldives() throws IOException, InterruptedException {
        test= report.startTest("Check Maldives info");

        jtMaldives maldives = new jtMaldives();

       maldives.enterLocation();
        test.log(LogStatus.INFO, "Enter search Maldives");

        maldives.clickOnLocation();
        test.log(LogStatus.INFO, "Select Maldives");

        maldives.scrollToInfo();
        test.log(LogStatus.INFO, "Scroll to info");

        maldives.holidayInfo();
        test.log(LogStatus.PASS, "Holiday info saved at desktop");


    }
}
