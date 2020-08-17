package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    public HomePage(WebDriver webdriver) {
        super(webdriver);
    }

    @FindBy(css = "input[class=\"InputBar__SearchInput-t6v2m1-1 iJaFAt\"]")
    public WebElement inputBuscador;

    @FindBy(css = "button[class=\"InputBar__SearchButton-t6v2m1-2 jRChuZ\"]")
    public WebElement lupaBuscar;

    public void busquedaProducto(String producto) {
        inputBuscador.sendKeys(producto);
        lupaBuscar.click();
    }
}
