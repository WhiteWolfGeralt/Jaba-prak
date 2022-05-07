package ru.msu.cmc.webprak;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {

    private final String rootTitle = "Главная страница";

    private final String peopleTitle = "Люди";
    private final String placesTitle = "Места";

    private final String placeTitle = "Информация о месте";

    private final String editPlaceTitle = "Редактировать место";

    @Test
    void MainPage() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        assertEquals(rootTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void HeaderTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/");

        WebElement peopleButton = driver.findElement(By.id("peopleListLink"));
        peopleButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(peopleTitle, driver.getTitle());

        WebElement rootButton = driver.findElement(By.id("rootLink"));
        rootButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(rootTitle, driver.getTitle());

        WebElement placesButton = driver.findElement(By.id("placesListLink"));
        placesButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(placesTitle, driver.getTitle());

        rootButton = driver.findElement(By.id("rootLink"));
        rootButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(rootTitle, driver.getTitle());

        driver.quit();
    }

    @Test
    void addPlace() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/places");
        assertEquals(placesTitle, driver.getTitle());
        WebElement addPlace = driver.findElement(By.id("addPlaceButton"));
        addPlace.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(editPlaceTitle, driver.getTitle());

        driver.findElement(By.id("placeName")).sendKeys("Тестовое место");
        driver.findElement(By.id("placeDescription")).sendKeys("Тестовое описание");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals(placeTitle, driver.getTitle());
        WebElement placeInfo = driver.findElement(By.id("placeInfo"));
        List<WebElement> cells = placeInfo.findElements(By.tagName("p"));

        assertEquals(cells.get(0).getText(), "Название: Тестовое место");
        assertEquals(cells.get(1).getText(), "Описание: Тестовое описание");

        driver.quit();
    }
}
