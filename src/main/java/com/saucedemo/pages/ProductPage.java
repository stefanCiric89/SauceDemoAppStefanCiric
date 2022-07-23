package com.saucedemo.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage extends BasePage {



    public ProductPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.url + "inventory.html";
    }



    public Boolean isProductPageDisplayed() {
        Boolean toReturn = true;

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        List<WebElement> listInventoryContainer = webDriver.findElements(By.id("inventory_container"));

        if (listInventoryContainer.size() == 2) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }


    private WebElement getProductSortContainer() {
        return webDriver.findElement(By.xpath(".//select[@class='product_sort_container']"));
    }

    private WebElement getAToZItemFromList() {
        return webDriver.findElement(By.xpath(".//option[@value='az']"));
    }

    public void selectAToZ() {
        this.getProductSortContainer().click();
        this.getAToZItemFromList().click();
    }

    private WebElement getZToAItemFromList() {
        return webDriver.findElement(By.xpath(".//option[@value='za']"));
    }

    public void selectZToA() {
        this.getProductSortContainer().click();
        this.getZToAItemFromList().click();
    }

    private WebElement getLowToHighFromList() {
        return webDriver.findElement(By.xpath(".//option[@value='lohi']"));
    }

    public void selectLowToHigh() {
        this.getProductSortContainer().click();
        this.getLowToHighFromList().click();
    }

    private WebElement getHighToLowFromList() {
        return webDriver.findElement(By.xpath(".//option[@value='hilo']"));
    }
    public void selectHighToLow() {
        this.getProductSortContainer().click();
        this.getHighToLowFromList().click();
    }

    public void addProductToCartByName(String productName) {

        WebElement listInventoryContainer = webDriver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItem = listInventoryContainer.findElements(By.className("inventory_item"));

        Integer indexElement = -1;
        for (int i = 0; i < listInventoryItem.size(); i++) {
            WebElement inventoryName = listInventoryItem.get(i).findElement(By.className("inventory_item_name"));
            if (inventoryName.getText().equals(productName)) {
                indexElement = i;
                break;
            }
        }
        WebElement btnAddItem = listInventoryItem.get(indexElement).findElement(By.xpath(".//button"));
        btnAddItem.click();
    }


    public WebElement getCheapestProduct() {
        List<WebElement> listInventoryItems = webDriver.findElements(By.className("inventory_item"));

        double lowestPrice = Double.parseDouble(listInventoryItems.get(0).findElement(
                By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));

        WebElement lowestPriceProduct = null;

        for (int i = 0; i < listInventoryItems.size(); i++) {
            Double productPrice = Double.parseDouble(listInventoryItems.get(i).findElement(
                    By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));

            if (productPrice < lowestPrice) {
                lowestPrice = productPrice;
                lowestPriceProduct = listInventoryItems.get(i);
            }
        }
        return lowestPriceProduct;
    }


    public String addCheapestProduct(WebElement product) {
        product.findElement(By.xpath(".//button")).click();
        return product.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
    }

    private WebElement getShoppingCartContainer() {
        return webDriver.findElement(By.id("shopping_cart_container"));
    }

    public void clickCartBtn(){
        this.getShoppingCartContainer().click();
    }


    public WebElement cheapestProduct() {
       return getCheapestProduct();
    }

    public void getFirstProductFromList() {

        List<WebElement> listInventoryContainer = webDriver.findElements(By.xpath("//div[@class='inventory_item']"));
        WebElement btnAddItem = listInventoryContainer.get(0).findElement(By.xpath(".//button"));
        btnAddItem.click();
    }


}