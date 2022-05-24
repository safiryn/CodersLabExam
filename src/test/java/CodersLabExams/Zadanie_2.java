package CodersLabExams;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;


public class Zadanie_2 {

    public static void main(String[] args) throws InterruptedException, IOException

    {

        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe"); // ustawiamy systemowe property aby móć skorzystać z odpowiedniego drivera - tutaj akurat jest chrome driver

        WebDriver driver = new ChromeDriver(); // tworzymy obiekt ChromeDriver
        driver.manage().window().maximize(); // maksymalizujemy okno przeglądarki - Manager Windowsa Maksymalizuj
        driver.get("https://mystore-testlab.coderslab.pl/index.php");        // wpisujemy adres strony i przechodzimy na tę stronę

            WebElement MyStoreSightIn = driver.findElement(By.cssSelector("a[title='Log in to your customer account'] span[class='hidden-sm-down']"));
            MyStoreSightIn.click();

            WebElement EmailLogin = driver.findElement(By.xpath("//input[@class='form-control']")); // wprowadzenie adresu email
            EmailLogin.clear();
            EmailLogin.sendKeys("zarbsglyhobydcavbv@nthrw.com");

            WebElement PasswordPutIn = driver.findElement(By.xpath("//input[@name='password']")); // wprowadzenie password
            PasswordPutIn.clear();
            PasswordPutIn.sendKeys("CLpassword");

            WebElement SignInButton = driver.findElement(By.xpath("//button[@id='submit-login']")); // Zatwierdzenie buttonem SIGN IN
            SignInButton.submit();

            WebElement MyStoreButton = driver.findElement(By.xpath("//img[@alt='PrestaShop']")); // przejsćie do HomePage
            MyStoreButton.click();

            WebElement HummingbirdPrintedSweaterButton = driver.findElement(By.xpath("//img[@alt='Brown bear printed sweater']")); // wybór HummingbirdPrintedSweater
            HummingbirdPrintedSweaterButton.click();

            Select SizeMSelect = new Select(driver.findElement(By.name("group[1]"))); // wybór rozmiaru M
            SizeMSelect.selectByValue("2");

            WebElement QuantityButton = driver.findElement(By.id("quantity_wanted")); // wybór 5 sztuk
            QuantityButton.clear();
            driver.findElement(By.id("quantity_wanted")).sendKeys("5");

            WebElement AddToCartButton = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']")); // dadanie produktów do karty
            AddToCartButton.click();

            Thread.sleep(1000); // zwonienie kodu dla wyskakujacego okna
            driver.findElement(By.cssSelector("a[class='btn btn-primary']")).click(); // Przejście od koszyka

            driver.findElement(By.cssSelector("a[class='btn btn-primary']")).click(); // zatwierdzenie kosztyka

            WebElement ConfirmButton = driver.findElement(By.xpath("//button[@name='confirm-addresses']")); // Potwierdzenie zakupów
            ConfirmButton.click();

            driver.findElement(By.xpath("//div[@class='delivery-options']//div[1]//div[1]//span[1]//span[1]")).click(); // wybór odbioru w sklepie

            WebElement PickUpButton = driver.findElement(By.xpath("//button[@name='confirmDeliveryOption']")); // Potwierdzenie odbioru w sklepie
            PickUpButton.click();

            driver.findElement(By.xpath("//input[@id='payment-option-1']")).click(); // płatność by Check
            driver.findElement(By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']")).click(); // zatwierdzenie regulaminu

            WebElement FinalButton = driver.findElement(By.cssSelector("button[class='btn btn-primary center-block']")); // zatwierdzenie zakupów
            FinalButton.click();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  // wykonanie screenshota
        FileUtils.copyFile(scrFile, new File("src/test/screenshot/screenshot.png"));
    }
}