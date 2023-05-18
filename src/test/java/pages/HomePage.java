package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Helpers;

public class HomePage {

    WebDriver driver;
    JavascriptExecutor js;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "mainSearchbar")
    WebElement searchBar;

    @FindBy(xpath = "//li[@class='m-navDesktop__section pt-2 pb-2 mr-xl-5']")
    WebElement categoriasMenu;

    @FindBy(xpath = "//ul[@class='m-desktop-subcategory-list']//a[text()='Perfumes Hombre']")
    WebElement mensPerfumeMenu;

    @FindBy(xpath = "//li[@class='m-megamenu__category']//span[contains(text(),'Belleza')]//ancestor::li[@class='m-megamenu__category']")
    WebElement beautyMenu;


    public void writeInSearchBar(String searchText){
        Helpers.waitToBeVisible(searchBar);
        searchBar.sendKeys(searchText);
    }

    public void moveToCategoriasMenu(){
        new Actions(driver).moveToElement(categoriasMenu).build().perform();
    }

    public void moveToBeautyMenu() {
        new Actions(driver).moveToElement(beautyMenu).build().perform();
    }

    public void moveToMensPerfumeMenu() {
        js.executeScript("arguments[0].click();", mensPerfumeMenu);
    }



}
