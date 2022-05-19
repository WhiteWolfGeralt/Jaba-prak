package ru.msu.cmc.webprak;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {

    private final String rootTitle = "Главная страница";
    private final String peopleTitle = "Люди";
    private final String placesTitle = "Места";
    private final String placeTitle = "Информация о месте";

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
        String editPlaceTitle = "Редактировать место";
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

    @Test
    void getPlaceInfo() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/place?placeId=1");
        assertEquals(placeTitle, driver.getTitle());

        WebElement placeInfo = driver.findElement(By.id("placeInfo"));
        List<WebElement> cells = placeInfo.findElements(By.tagName("p"));

        assertEquals(cells.get(0).getText(), "Название: Королевство Темерия");
        assertEquals(cells.get(1).getText(), "Описание: Одно из самых крупных и могущественных королевств Севера, имеющее важное как стратегическое, так и политическое значение");

        List<WebElement> personsFromPlace = cells.get(2).findElements(By.tagName("a"));
        assertEquals(personsFromPlace.size(), 8);
        assertEquals(personsFromPlace.get(0).findElement(By.tagName("span")).getText(), "Фольтест Темерский,");
        assertEquals(personsFromPlace.get(1).findElement(By.tagName("span")).getText(), "Адда Белая,");
        assertEquals(personsFromPlace.get(2).findElement(By.tagName("span")).getText(), "Цирилла Фиона Элен Рианнон,");
        assertEquals(personsFromPlace.get(3).findElement(By.tagName("span")).getText(), "Эмгыр вар Эмрейс,");

        driver.quit();
    }

    @Test
    void getPersonInfo() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/person?personId=8");
        String personTitle = "Информация о человеке";
        assertEquals(personTitle, driver.getTitle());

        WebElement personInfo = driver.findElement(By.id("personInfo"));
        List<WebElement> cells = personInfo.findElements(By.tagName("p"));

        assertEquals(cells.get(0).getText(), "Имя: Эмгыр вар Эмрейс");
        assertEquals(cells.get(1).getText(), "Пол: Муж");
        assertEquals(cells.get(2).getText(), "Дата рождения: 1220");
        assertEquals(cells.get(3).getText(), "Дата смерти: 1290");
        assertEquals(cells.get(4).getText(), "Законнорожденный: да");
        assertEquals(cells.get(5).getText(), "Краткая характеристика: Император Нильфгаарда. Белое Пламя, Пляшущее на Курганах Врагов.");

        List<WebElement> placeForPerson = cells.get(6).findElements(By.tagName("a"));
        assertEquals(placeForPerson.size(), 3);
        assertEquals(placeForPerson.get(0).findElement(By.tagName("span")).getText(), "Королевство Темерия,");
        assertEquals(placeForPerson.get(1).findElement(By.tagName("span")).getText(), "Империя Нильфгаард,");
        assertEquals(placeForPerson.get(2).findElement(By.tagName("span")).getText(), "Цинтра");

        driver.quit();
    }

    @Test
    void getParents() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/persons");
        assertEquals(peopleTitle, driver.getTitle());

        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> cells = table.findElements(By.tagName("tr"));
        WebElement Cirilla = null;
        for (WebElement elem : cells) {
            List<WebElement> info = elem.findElements(By.tagName("td"));
            if (info.size() == 0) {
                continue;
            }
            if (Objects.equals(info.get(0).findElement(By.tagName("span")).getText(), "Цирилла Фиона Элен Рианнон")) {
                Cirilla = elem;
                break;
            }
        }
        assert Cirilla != null;
        List<WebElement> ciriInfo = Cirilla.findElements(By.tagName("td"));
        assertEquals(ciriInfo.get(1).findElement(By.tagName("span")).getText(), "1252-неизвестно");
        assertEquals(ciriInfo.get(2).findElement(By.tagName("span")).getText(), "Паветта Фиона Элен");
        assertEquals(ciriInfo.get(3).findElement(By.tagName("span")).getText(), "Эмгыр вар Эмрейс");

        ciriInfo.get(2).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Информация о человеке", driver.getTitle());


        WebElement personInfo = driver.findElement(By.id("personInfo"));
        List<WebElement> personCells = personInfo.findElements(By.tagName("p"));

        assertEquals(personCells.get(0).getText(), "Имя: Паветта Фиона Элен");
        assertEquals(personCells.get(1).getText(), "Пол: Жен");
        assertEquals(personCells.get(2).getText(), "Дата рождения: 1236");
        assertEquals(personCells.get(3).getText(), "Дата смерти: 1256");
        assertEquals(personCells.get(4).getText(), "Законнорожденный: да");
        assertEquals(personCells.get(5).getText(), "Краткая характеристика: Принцесса Цинтры. Носитель гена Старшей крови.");
        driver.quit();
    }
}
