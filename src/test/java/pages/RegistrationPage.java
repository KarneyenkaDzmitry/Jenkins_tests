package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.Button;

public class RegistrationPage extends JenkinsPages {

    private final String REGISTRATION_PAGE_URI = BASE_URL;

    @FindBy(id = "j_username")
    private WebElement userName;

    @FindBy(name = "j_password")
    private WebElement password;

    @FindBy(xpath = "//a[@href=\"/logout\"]")
    private WebElement logout;

    private Button login = new Button(driver, By.id("yui-gen1-button"));

    public Button getLogin() {
        return login;
    }

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public HomePage enterExistingUser(User user) {
        openPage();
        userName.sendKeys(user.getName());
        password.sendKeys(user.getPassword());
        login.pushButton();
        return new HomePage(driver);
    }

    @Override
    public void openPage() {
        driver.get(REGISTRATION_PAGE_URI);
    }
}
