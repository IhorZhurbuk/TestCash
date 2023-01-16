package test.com;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class WebDriverSettings {
    public ChromeDriver driver;
    public String key = "webdriver.chrome.driver";
    public String value = "C:\\Users\\Ihor\\Driver\\chromedriver.exe";
    public  String url = "https://my.cashalot.org.ua/";
  /*  public static String userName;
    public static String userPassword;
*/
    UsernameAndPassword usernameAndPassword = new UsernameAndPassword("+38 (111) 111 - 11 - 11", "rsZc4aQSQWW2cX8wn5KE_");

    @Before
    public void setUp() {
        System.setProperty(key, value);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    @After
    public void close() {
        driver.quit();
    }

}
