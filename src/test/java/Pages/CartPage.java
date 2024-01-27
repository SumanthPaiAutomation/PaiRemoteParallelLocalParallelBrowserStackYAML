package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

    WebDriver driver;
    public static String productText;
    public CartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"1\"]/p")
    WebElement productOnScreenText;
    @FindBy(xpath = "//*[@id=\"1\"]/div[4]")
    WebElement addCartButton;


    public boolean ifReachedCartPage(){
        // navigate to bstackdemo
        driver.get("https://www.bstackdemo.com");

        // Check the title
        return driver.getTitle().matches("StackDemo");
    }

    public void addProductToCart(){
       productText= productOnScreenText.getText();
       addCartButton.click();
    }
}
