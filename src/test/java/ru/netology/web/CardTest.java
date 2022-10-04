package ru.netology.web;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;


class CardTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll()  {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setup()  {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {

        driver.quit();
    }

    @Test
    void shouldTestCardForm() throws InterruptedException {
        driver.get("http://localhost:9999/");

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Петров-Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79888888888");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
        Thread.sleep(5000);
    }

}
