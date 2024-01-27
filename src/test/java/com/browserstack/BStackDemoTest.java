package com.browserstack;

import Pages.CartPage;
import Pages.CheckoutPage;
import com.browserstack.SeleniumTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BStackDemoTest extends SeleniumTest {
    @Test
    public void addProductToCart() throws Exception {
        CartPage cartPage= new CartPage(driver);
        CheckoutPage checkoutPage=new CheckoutPage(driver);

        Assert.assertTrue(cartPage.ifReachedCartPage());
        // Save the text of the product for later verify
        cartPage.addProductToCart();

        // See if the cart is opened or not
        Assert.assertTrue(checkoutPage.ifCheckOutDisplayed());

        // Check the product inside the cart is same as of the main page

        Assert.assertEquals(CartPage.productText, checkoutPage.returnProductOnCheckOutText());
    }
}
