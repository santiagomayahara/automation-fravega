import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver webdriver;
    public WebDriverWait wait;

    @BeforeTest
    public void setUp(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\test\\resources\\drivers\\chromedriver.exe");
        ChromeOptions capability = new ChromeOptions();
        capability.setCapability("browserName","chrome");
        capability.addArguments("--start-maximized");
        webdriver = new ChromeDriver(capability);
        context.setAttribute("WebDriver", webdriver);
        wait = new WebDriverWait(webdriver, 10);
    }

    @AfterTest
    public void close() {
        webdriver.quit();
    }
}
