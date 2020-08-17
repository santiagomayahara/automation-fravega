package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    private WebDriver webdriver;
    public WebDriverWait wait;

    public PageObject(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 10);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webdriver, 100);
        PageFactory.initElements(factory, this);
    }

    public WebDriver getDriver() {
        return this.webdriver;
    }
}
