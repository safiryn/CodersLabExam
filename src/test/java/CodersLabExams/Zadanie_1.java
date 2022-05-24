package CodersLabExams;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



// mail: zarbsglyhobydcavbv@nthrw.com
// Mr.
// password: CLpassword
// First Name: Mateusz
// Last Name: Kowalski
// Birthdate: 01/01/1986
// Windows 11 , Chrome Wersja 101.0.4951.67 (Oficjalna wersja) (64-bitowa)

public class Zadanie_1
{
    public String alias;
    private WebDriver driver;


    @Given("open Chrome browser at MyStore Homepage")
            public void openHomePage ()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver(); // Definicja zminnej driver dla Chrome
        driver.manage().window().maximize();  // okno przegládaki na fullscreen
        driver.get("https://mystore-testlab.coderslab.pl/index.php"); // wybór adresu www
    }

    @When ("user click SightInButton")
        public void logingIn()
    {

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
    }

    @And ("user go to new adresses add page")  // wejście w podstronę z formularzem
    public void adressClick()
    {
       WebElement AdressesButton= By.xpath("//i[contains(text(),'\uE567')]").findElement(driver); // Przejście do podstrony z adresem
       AdressesButton.click();
    }

    @And("user provide his country")
    // wykorzystno biblioteke SELECT, wybór kraju zmiania kojekność układu formularza adresu. Dla uzyskania stałego układu wybór kraju jest używany jako pierwszy.
    // https://www.selenium.dev/documentation/webdriver/elements/select_lists/
     public void provideUserCountry()
    {
        Select countrySelect = new Select(driver.findElement(By.name("id_country")));
        countrySelect.selectByValue("17");
            }

    @And("^user provide his alias (.*)") // wprowadzenie aliasu
    public void provideUserAlias(String alias)
    {
        WebElement aliasBar = driver.findElement(By.xpath("//input[@name='alias']"));
        aliasBar.clear();
        aliasBar.sendKeys(alias); // wywołanie parametru
        aliasBar.submit();
    }

    @And("^user provide his adress (.*)") // Wpisanie ulicy
    public void provideUserAdress(String adress)
    {
        WebElement adressBar = driver.findElement(By.xpath("//input[@name='address1']"));
        adressBar.clear();
        adressBar.sendKeys(adress); // wywołanie parametru
        adressBar.submit();
    }

    @And("^user provide his city (.*)") // wprowadzeni miasta
    public void provideUserCity(String city)
    {
        WebElement cityBar = driver.findElement(By.xpath("//input[@name='city']"));
        cityBar.clear();
        cityBar.sendKeys(city); // wywołanie parametru
        //cityBar.submit();
    }

    @And("^user provide his postalCode (.*)")  // wprowadzenie kodu pocztowego
    public void provideUserPostalCode(String postalCode)
    {
        WebElement postalCodeBar = driver.findElement(By.xpath("//input[@name='postcode']"));
        postalCodeBar.clear();
        postalCodeBar.sendKeys(postalCode); // wywołanie parametru
        // postalCodeBar.submit();  -  stona z formularzem mając wpisane 3 dane Adress, city, postalcode po akcji SUMBIT automatycznie zatwierdza formularz i przechodzi dalej
    }


    @And("^user provide his phone (.*)")  // wprowadzenie nr telefonu
    public void provideUserPhone(String phone)
    {
        WebElement phoneBar = driver.findElement(By.xpath("//input[@name='phone']"));
        phoneBar.clear();
        phoneBar.sendKeys(phone); // wywołanie parametru
        phoneBar.submit();
    }

    @Then ("^user is successfully registered as (.*) (.*) (.*) (.*) (.*)")
    public void verifyRegistartion(String expectedAlias, String expectedAdress, String expectedCity, String expectedPostalCode, String expectedPhone)
    {
        String paraText = driver.findElement(By.xpath("//body//main//address[1]")).getText();

        // System.out.println(paraText);
        String[] lista = paraText.split("\n");
        System.out.println(lista[0]);
        System.out.println(lista[1]);
        System.out.println(lista[2]);
        System.out.println(lista[3]);
        System.out.println(lista[4]);
        System.out.println(lista[5]);
        Assert.assertEquals(lista[0],"Mateusz Kowalski");
        Assert.assertEquals(lista[1],expectedAdress);
        Assert.assertEquals(lista[2],expectedCity);
        Assert.assertEquals(lista[3],expectedPostalCode);
        Assert.assertEquals(lista[5],expectedPhone);

    }

    @And("close browser")  // zamknięcie przeglądarki
    public void closeBrowser()
    {
        driver.quit();
    }

}

