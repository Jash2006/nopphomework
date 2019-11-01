import javafx.scene.input.DataFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MavenHomeWork {
    protected  static WebDriver driver;
    String expectedmsg= "Your registration completed";
    String expectedreferalmsg= "Your message has been sent.";
    public static String autodate(){
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyhhmmss");
        return format.format(new Date());
    }
    @Before
    public void setup(){
        // to set the path for driver object
        System.setProperty("webdriver.chrome.driver","src\\main\\BrowserDrivers\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        // to maximise the browser window screen
        driver.manage().window().fullscreen();
        // set implicitly wait driver object
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // to open the website
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void checkAddToCartButtonIsPresent(){
        List<WebElement> itemlist = driver.findElements(By.className("item-box"));
        System.out.println(itemlist.size());
        int count=0;
        for (WebElement e: itemlist) {

            if(e.getAttribute("outerHTML").contains("Add to cart")){
                    count++;
            }
            else{
                System.out.println("NO ADD TO CART BUTTON: "+e.getText());
            }
        }
        System.out.println(count);
        Assert.assertEquals("Some of the item does not have add to cart button",itemlist.size(),count);
    }
    @Test
    public void userRegistrationSuccessfully(){
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter the first name
        driver.findElement(By.id("FirstName")).sendKeys("Manoj");
        //enter the last name
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Vaghasiya");
        // to enter the email
        driver.findElement(By.name("Email")).sendKeys(("abc"+autodate()+"@yahoo.com"));
        System.out.println(autodate());
        // to enter the password
        driver.findElement(By.name("Password")).sendKeys("Jash2006");
        // to enter confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Jash2006");
        // to click on register to finish the registration
        driver.findElement(By.name("register-button")).click();
        String actualmsg= driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(expectedmsg,actualmsg);
    }
    @Test
    public void referProductToFriend(){
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter the first name
        driver.findElement(By.id("FirstName")).sendKeys("Manoj");
        //enter the last name
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Vaghasiya");
        // to enter the email
        driver.findElement(By.name("Email")).sendKeys(("abc"+autodate()+"@yahoo.com"));
        System.out.println(autodate());
        // to enter the password
        driver.findElement(By.name("Password")).sendKeys("Jash2006");
        // to enter confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Jash2006");
        // to click on register to finish the registration
        driver.findElement(By.name("register-button")).click();
        // to click on nopp commerce logo
        driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
        // to select the Apple macbook image
        driver.findElement(By.xpath("//img[@title=\"Show details for Apple MacBook Pro 13-inch\"]")).click();
        // to select email a friend
        driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
        // to enter friend's name
        driver.findElement(By.id("FriendEmail")).sendKeys("manoj.vaghasiya2007@gmail.com");
        // to enter personal message to friend
        driver.findElement(By.id("PersonalMessage")).sendKeys("Thanks");
        // to click on SEND EMAIL button
        driver.findElement(By.xpath("//input[@value='Send email']")).click();
        String actualrefermsg= driver.findElement(By.xpath("//*[@class='result' and contains(text(),'Your message has been sent.')]")).getText();
        Assert.assertEquals(expectedreferalmsg,actualrefermsg);
    }
    @Test
    public void userNavigatetoCameraPhoto(){
        // to click on electronics category
        driver.findElement(By.xpath("//h2/a[@title='Show products in category Electronics']")).click();
        // to click on camera & photo
        driver.findElement(By.xpath("//h2/a[@title='Show products in category Camera & photo']")).click();
        String expectedtitle= "Camera & photo";
        String actualtitle= driver.findElement(By.xpath("//div[@class='page-title']")).getText();
        Assert.assertEquals(expectedtitle,actualtitle);
    }
    @Test
    public void userFilterJewelryByPrice(){
        // to click on jewelry category
        driver.findElement(By.linkText("Jewelry")).click();
        //to select price range
        driver.findElement(By.xpath("//a[@href='//demo.nopcommerce.com/jewelry?price=700-3000']")).click();
        // declaring the expected price range
        String expectedPriceRange="$700.00 - $3,000.00";
        String actualPriceRange= driver.findElement(By.xpath("//span[@class='item']")).getText();
        Assert.assertEquals(expectedPriceRange,actualPriceRange);
        // to store actual product price in String
        String actualPrice= driver.findElement(By.xpath("//span[@class='price actual-price']")).getText();
        // converting string into float
        float a = Float.parseFloat(actualPrice.replaceAll(",","").substring(1));
       System.out.println(a);
       // to store start price in string
       String startPrice =driver.findElement(By.xpath("//span[@class='PriceRange' and text()='$700.00']")).getText();
        // converting string into float
        float s = Float.parseFloat(startPrice.substring(1));
        System.out.println(s);
        // to store the end price
        String endPrice =driver.findElement(By.xpath("//span[@class='PriceRange' and text()='$3,000.00']")).getText();
        // converting string into float
        float e = Float.parseFloat(endPrice.replaceAll(",","").substring(1));
        System.out.println(e);
        // to check whether price is between start price and end price
        Assert.assertTrue(a>=s && a<=e);
        // to print the statement
        System.out.println("The product price "+ actualPrice+" is between "+startPrice+ " and " +endPrice);
    }
    @Test
    public void userAbleToAddItemsIntoBasket() throws InterruptedException {
    // to open the book category page
    driver.findElement(By.linkText("Books")).click();
    // to select first book "First prize pies"
    driver.findElement(By.linkText("First Prize Pies")).click();
    // to add the first book in basket
    driver.findElement(By.xpath("//input[@id='add-to-cart-button-38']")).click();
    // to make webdriver to wait until item adds into the shopping cart
    driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
    // to select second book
    driver.findElement(By.linkText("Fahrenheit 451 by Ray Bradbury")).click();
    // to add second book in shopping cart
    driver.findElement(By.xpath("//input[@id='add-to-cart-button-37']")).click();
   // to make webdriver to wait until item adds into the shopping cart
    driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
    // to click on shopping cart
    driver.findElement(By.xpath("//span[@class='cart-label']")).click();
    // to get the number of total quantity in shopping cart
    String actual = driver.findElement(By.className("cart-qty")).getText();
    String actualQnt =actual.replace('(',' ').replace(')',' ').trim();
    // declaring expected quantity in shopping cart
    String expectedQnt= "2";
    // to check whether actual quantity matches expected quantity
    Assert.assertEquals(actualQnt,expectedQnt);
    }
   @After
    public void teardown(){
        // to close the browser
        driver.quit();
    }
}
