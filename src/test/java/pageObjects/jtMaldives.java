package pageObjects;

import methods.MainMethods;
import methods.PropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class jtMaldives extends PropertyFile {
   
    WebElement search = getDriver().findElement(By.xpath("//input[@class='form-control rounded-pill search-input search-input-with-icon']"));
    public By mldvClck = By.xpath("//a[@class='text-body'][text()='Maldivi']");
    public By cookies = By.xpath("//button[@class='btn btn-primary btn-lg align-middle mr-2']");

    public void enterLocation() {
        MainMethods.clickOnButton(cookies);
        search.sendKeys("Maldivi");
        search.sendKeys(Keys.ENTER);
    }

    public void clickOnLocation(){
        MainMethods.waitElementToBeVisible(mldvClck);
        MainMethods.clickOnButton(mldvClck);

    }

    public void scrollToInfo(){
        WebElement scrollTo = getDriver().findElement(By.xpath("//div[contains(text(),'Polasci i cene')]"));
        scrollTo.click();
    }

    public void holidayInfo() throws IOException {
        String polazak = getDriver().findElement(By.xpath("//div[@class='col-4 col-lg-2 mb-2 mb-lg-0 pr-1 pl-0 order-1']")).getText();
        polazak = polazak.substring(8);
        System.out.println(polazak);

        String povratak = getDriver().findElement(By.xpath("//div[@class='col-4 col-lg-2 mb-2 mb-lg-0 px-1 order-2']")).getText();
        povratak = povratak.substring(9);
        System.out.println(povratak);

        String dana = getDriver().findElement(By.xpath("//div[@class='col-4 col-lg-auto mb-2 mb-lg-0 px-1 order-3 text-center']")).getText();
        dana = dana.substring(5);
        System.out.println(dana);

        String cena = getDriver().findElement(By.xpath("//div[@class='font-weight-bold text-nowrap']")).getText();
        cena = cena.substring(0, cena.length() - 3);
        System.out.println(cena);

        String dostupnost = getDriver().findElement(By.xpath("//div[@class='col px-lg-1 order-5 order-lg-4 text-primary text-left pl-lg-4 pl-xl-5']")).getText();
        System.out.println(dostupnost);

        String desktopPath = System.getProperty("user.home") + "/Desktop/jungleMS048.txt";

        File f = new File(desktopPath);
        f.createNewFile();

        FileWriter w = new FileWriter(desktopPath);
        BufferedWriter out = new BufferedWriter(w);

        out.write("Cena putovanja je: " + cena + " eur");
        out.newLine();

        out.write("Polazak je: " + polazak);
        out.newLine();

        out.write("Povratak je: " + povratak);
        out.newLine();

        out.write("Broj dana je: " + dana);
        out.newLine();

        out.write("Da li je putovanje dostupnu: " + dostupnost);

        out.flush();
    }

}

