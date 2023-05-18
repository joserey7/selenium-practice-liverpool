package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.HomePage;
import pages.ProductPage;
import pages.StorePage;
import util.Helpers;

import java.time.Duration;

import static hooks.BaseTest.driver;

public class LiverpoolStepDef {

    HomePage homePage = new HomePage(driver);
    StorePage storePage = new StorePage(driver);
    ProductPage productPage = new ProductPage(driver);

    //Scenario 1
    @Given("the user navigates to {string}")
    public void the_user_navigates_to(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @When("searches for {string} using the search bar")
    public void searches_for_using_the_search_bar(String searchText) {
        homePage.writeInSearchBar(searchText + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @When("verifies there are games for playstation5 and other playstation consoles")
    public void verifies_there_are_games_for_playstation5_and_other_playstation_consoles() {
        Assert.assertTrue(storePage.verifyGamesForPlayStationConsoles());
    }
    @Then("selects a {string}")
    public void selects_a(String name) {
        storePage.selectItem(name.trim());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @Then("validates that title should be {string} and price {string}")
    public void validates_that_title_should_be_and_price(String title, String price) throws InterruptedException {
        // PUEDE MEJORAR CON IMPLICIT WAIT QUIZÁ
//        Thread.sleep(8000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(productPage.verifyTitle().equalsIgnoreCase(title));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
        Assert.assertEquals(productPage.verifyPrice(), price.trim());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }

    //Scenario 2
    @When("verifies that size and price filters are displayed")
    public void verifies_that_size_and_price_filters_are_displayed() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue(storePage.sizeFilterIsDisplayed());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
        Assert.assertTrue(storePage.priceFilterIsDisplayed());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @Then("filters the results by size")
    public void filters_the_results_by_size() throws InterruptedException {
        Thread.sleep(3000);
        storePage.filterSize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @Then("filters the results by price")
    public void filters_the_results_by_price() throws InterruptedException {
        Thread.sleep(3000);
        storePage.filterPrice();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @Then("filters the results by brand {string}")
    public void filters_the_results_by_brand(String brand) throws InterruptedException {
        Thread.sleep(4000);
        storePage.filterBrand(brand);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @Then("validates the results count")
    public void validates_the_results_count() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue(storePage.validateResultCount());
    }

    //Scenario 3
    @When("selects categorias menu")
    public void selects_categorias_menu() {
        homePage.moveToCategoriasMenu();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
    }
    @When("moves to perfumes hombre inside belleza submenu")
    public void moves_to_perfumes_hombre_inside_belleza_submenu() throws InterruptedException {
        Thread.sleep(1500);
        homePage.moveToBeautyMenu();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Helpers.takeScreenShot();
        Thread.sleep(1500);
        homePage.moveToMensPerfumeMenu();
    }

}
