import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stefan\\Downloads\\Selenium\\chromedriver.exe");
        this.webDriver = new ChromeDriver();
    }

  /* @AfterClass
    public void close() {
        this.webDriver.quit();
    }*/
}
