package pages;
import org.openqa.selenium.WebDriver;

public abstract class JenkinsPages {

    final WebDriver driver;
    final String BASE_URL = "http://localhost:8080";

    public JenkinsPages(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public abstract  void openPage();
}
