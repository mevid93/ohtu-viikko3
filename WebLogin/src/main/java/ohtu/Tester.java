package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {

        // test login with valid credentials
        testSuccessfulLogin();

        // test login with invalid password
        testLoginWithInvalidPassword();

        // test login with invalid username
        testLoginWithInvalidUsername();

        // test creating new user
        testCreatingNewUser();

        // test logout after creating new user
        testLogoutForNewUser();

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void testSuccessfulLogin() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        clickLinkWithText("login", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void testLoginWithInvalidPassword() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        clickLinkWithText("login", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep111");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void testLoginWithInvalidUsername() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        clickLinkWithText("login", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("nobody");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void testCreatingNewUser() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        clickLinkWithText("register new user", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        Random r = new Random();
        element.sendKeys("anon" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void testLogoutForNewUser() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        clickLinkWithText("register new user", driver);

        sleep(2);

        WebElement element = driver.findElement(By.name("username"));
        Random r = new Random();
        element.sendKeys("anon" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        sleep(2);
        
        clickLinkWithText("continue to application mainpage", driver);

        sleep(2);
        
        clickLinkWithText("logout", driver);

        sleep(3);

        driver.quit();
    }

    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
