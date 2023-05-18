package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Helpers;

public class ProductPage {

    WebDriver driver;
    JavascriptExecutor js;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "a-product__information--title")
    WebElement title;

    @FindBy(className = "a-product__paragraphDiscountPrice")
    WebElement price;


    public String verifyTitle() {
        System.out.println(title.getText().trim().toLowerCase());
        Helpers.waitToBeVisible(title);
        return title.getText().trim().toLowerCase();
    }

    public String verifyPrice(){
        System.out.println(price.getText().trim().substring(0, price.getText().length()-2));
        Helpers.waitToBeVisible(price);
        return price.getText().trim().substring(0, price.getText().length()-2).trim();
    }

}
