package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Helpers;

import java.util.List;
import java.util.stream.Collectors;

public class StorePage {

    WebDriver driver;
    JavascriptExecutor js;
    public StorePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search-filter-brands")
    WebElement searchBrand;

    @FindBy(className = "m-product__listingPlp")
    WebElement gridItems;

    @FindBy(xpath = "//*[@class='a-title__filter']//label[text()='Tamaño']//parent::div")
    WebElement sizeFilterTitle;

    @FindBy(xpath = "//div[@class='filter-brands']//input[@id='variants.normalizedSize-55 pulgadas']")
    WebElement inch55sizeFilter;

    @FindBy(xpath = "//*[@class='a-title__filter']//label[text()='Precios']//parent::div")
    WebElement priceFilterTitle;

    @FindBy(xpath = "//div[@class='fiterl-prices']//input[@id='variants.prices.sortPrice-10000-700000']")
    WebElement moreThan10000priceFilter;

    @FindBy(xpath = "//p[@class='a-plp-results-title']/span")
    WebElement resultCount;


    public Boolean verifyGamesForPlayStationConsoles(){
        Helpers.waitToBeAllVisible(gridItems);
        List<WebElement> filteredTitles;
        List<WebElement> titles = gridItems.findElements(By.tagName("h5"));

        filteredTitles = titles.stream().filter(
                title -> title.getText().toLowerCase().contains("playstation 5 juego") ||
                        title.getText().toLowerCase().contains("playstation 4 juego")
        ).collect(Collectors.toList());

        int countPlay5 = 0;
        int countPlay4 = 0;
        for (WebElement filteredTitle : filteredTitles) {
            if (filteredTitle.getText().toLowerCase().contains("playstation 4 juego")) {
                countPlay4++;
            } else if (filteredTitle.getText().toLowerCase().contains("playstation 5 juego")) {
                countPlay5++;
            }
            System.out.println(filteredTitle.getText());
        }
        System.out.println("count play4 - "+countPlay4);
        System.out.println("count  play5 - "+countPlay5);

        return countPlay4 > 0 && countPlay5 > 0;
    }

    public void selectItem(String name){
        Helpers.waitToBeAllVisible(gridItems);
        List<WebElement> items = gridItems.findElements(By.tagName("li"));
        List<WebElement> filteredTitles;

        filteredTitles = items.stream().filter(
                item -> item.findElement(By.xpath("//*[@id=\"__next\"]//h5[text()='"+name+"']"))
                        .getText().trim().toLowerCase().equalsIgnoreCase(name)
        ).collect(Collectors.toList());
        filteredTitles.get(0).findElement(By.tagName("a")).click();
    }

    public Boolean sizeFilterIsDisplayed(){
        js.executeScript("arguments[0].scrollIntoView({'block':'center','inline':'center'});", sizeFilterTitle);
        Helpers.waitToBeVisible(sizeFilterTitle);
        return sizeFilterTitle.isDisplayed();
    }

    public Boolean priceFilterIsDisplayed(){
        js.executeScript("arguments[0].scrollIntoView({'block':'center','inline':'center'});", priceFilterTitle);
        Helpers.waitToBeVisible(priceFilterTitle);
        return priceFilterTitle.isDisplayed();
    }

    public void filterSize(){
        js.executeScript("arguments[0].scrollIntoView({'block':'center','inline':'center'});", inch55sizeFilter);
        inch55sizeFilter.click();
    }

    public void filterPrice(){
        js.executeScript("arguments[0].scrollIntoView({'block':'center','inline':'center'});", moreThan10000priceFilter);
        moreThan10000priceFilter.click();
    }

    public void filterBrand(String brand){
        js.executeScript("arguments[0].scrollIntoView({'block':'center','inline':'center'});", searchBrand);
        searchBrand.sendKeys(brand);
        driver.findElement(By.xpath("//div[@class='filter-brands']//input[@id='brand-"+brand.toUpperCase()+"']")).click();
    }

    public boolean validateResultCount(){
        System.out.println(resultCount.getText());

        int count = gridItems.findElements(By.className("m-product__card")).size();
        System.out.println(count);
        int countItems = Integer.parseInt(resultCount.getText());
        System.out.println(countItems);

        return count == countItems;

    }

}
