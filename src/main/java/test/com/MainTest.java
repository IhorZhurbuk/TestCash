package test.com;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.util.HashMap;


public class MainTest extends WebDriverSettings {

    @Test
    public void openMainPage() {
        var title = driver.getTitle();
        Assert.assertEquals("Cashalot", title);
    }

    @Test
    public void loginTest() {
        var name = driver.findElement(By.id("PhoneNumberView"));
        var pass = driver.findElement(By.id("Password"));
        var login = driver.findElement(By.xpath("/html/body/div/section/div/div/div[2]/main/div/div[2]/div/form/div[4]/input"));
        name.sendKeys(usernameAndPassword.username());
        pass.sendKeys(usernameAndPassword.password());
        login.click();
        var expectedUrl = "https://my.cashalot.org.ua/?returnUrl=%2F";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);

    }

    @Test
    public void appleOpen() {
        if (driver.getWindowHandles().size() != 1) throw new AssertionError();
        driver.findElement(By.xpath("/html/body/div/section/div/div/div[1]/div/div[3]/a[2]/img")).click();
        var originalWindow = driver.getWindowHandle();

        for (var windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.manage().timeouts().getPageLoadTimeout();

        var expectedUrl = "https://apps.apple.com/in/app/cashalot/id1609631918";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void downloadZip() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        var downloadPathLocation = "C:\\Users\\Ihor\\Downloads";
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 1);
        chromePrefs.put("download.default_directory", downloadPathLocation);

        options.setExperimentalOption("prefs", chromePrefs);

        var element = driver.findElement(By.xpath("/html/body/div/section/div/div/div[1]/div/div[3]/a[1]/img"));
        element.click();

        File file = new File(downloadPathLocation + "\\cashalot.zip.crdownload");
        Thread.sleep(5000);
        Assert.assertTrue(file.exists());
        file.deleteOnExit();

    }

    @Test
    public void androidOpen() {
        assert driver.getWindowHandles().size() == 1;
        driver.findElement(By.xpath("/html/body/div/section/div/div/div[1]/div/div[3]/a[3]/img")).click();
        var originalWindow = driver.getWindowHandle();

        for (var windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.manage().timeouts().getPageLoadTimeout();
        var expectedUrl = "https://play.google.com/store/apps/details?id=com.medoc.cashalot&gl=UA";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void forgotPass() {
        var name = driver.findElement(By.id("PhoneNumberView"));
        name.sendKeys(usernameAndPassword.username());
        driver.findElement(By.xpath("/html/body/div/section/div/div/div[2]/main/div/div[2]/div/div/div/div[1]/form/a")).click();
        var expectedUrl = "https://my.cashalot.org.ua/Account/ForgotPasswordBefore";
        var actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void registration() {
        driver.findElement(By.xpath("/html/body/div/section/div/div/div[2]/main/div/div[2]/div/div/div/div[2]/a")).click();
        var name = driver.findElement(By.id("Pib"));
        name.sendKeys("Testovui");
        var tel = driver.findElement(By.id("PhoneNumberView"));
        tel.sendKeys("222222222222");
        var email = driver.findElement(By.id("Email"));
        email.sendKeys("1223@gmail.com");
        var pass = driver.findElement(By.id("Password"));
        pass.sendKeys("RenoDeCheDesU");
        var passToReply = driver.findElement(By.id("PasswordConfirm"));
        passToReply.sendKeys("RenoDeCheDesU");
        driver.findElement(By.xpath("/html/body/div/section/div/div/div[2]/main/div/div[2]/form/div[6]/div/label/span")).click();
    }

    @Test
    public void incorrectPass() {
        String expectedText = "Введено некоректний пароль";
        var name = driver.findElement(By.id("PhoneNumberView"));
        var pass = driver.findElement(By.id("Password"));
        var login = driver.findElement(By.xpath("/html/body/div/section/div/div/div[2]/main/div/div[2]/div/form/div[4]/input"));
        name.sendKeys(usernameAndPassword.username());
        pass.sendKeys("<script>window.eval = function(code){console.log(code)}</script>");
        login.click();
        var webElement = driver.findElement(By.xpath("/html/body/div/section/div/div/div[2]/main/div/div[2]/div/form/div[1]/ul/li"));
        Assert.assertEquals(expectedText, webElement.getText());
    }
}
