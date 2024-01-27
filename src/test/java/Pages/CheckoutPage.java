package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".float\\-cart__content")
    WebElement checkOutDisplay;
    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
    WebElement checkOutProduct;

    public boolean ifCheckOutDisplayed(){
        return checkOutDisplay.isDisplayed();
    }

    public String returnProductOnCheckOutText(){
        String productOnCartText=checkOutProduct.getText();
        return productOnCartText;
    }
}
