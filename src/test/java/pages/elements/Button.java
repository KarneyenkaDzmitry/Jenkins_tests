package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

public class Button implements ElementsOfJenkinsPage {
    private WebDriver driver;
    private By button;

    public Button(WebDriver driver, By button) {
        this.driver = driver;
        this.button = button;
    }

    public String getColorHex() {
        return Color.fromString(driver.findElement(button).getCssValue("background-color")).asHex();
    }

    public void pushButton() {
        driver.findElement(button).click();
    }
}
