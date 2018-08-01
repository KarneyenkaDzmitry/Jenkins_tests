package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUsersPage extends JenkinsPages {
    private final String MANAGE_USER_PAGE_URI = BASE_URL + "/securityRealm/";

    @FindBy(xpath = "//a[@href='addUser'][@class='task-link']")
    private WebElement createUser;


    public ManageUsersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CreateUserPage choiceCreateUser() {
        openPage();
        createUser.click();
        return new CreateUserPage(driver);
    }

    public DeleteUserPage choiceDeleteUser(User user) {
        openPage();
        By deleteUser = By.xpath("//a[@href=\"user/" + user.getName() + "/delete\"]");
        driver.findElement(deleteUser).click();
        return new DeleteUserPage(driver, user);
    }

    @Override
    public void openPage() {
        driver.get(MANAGE_USER_PAGE_URI);
    }

    public String getNameCreateUserButton() {
        openPage();
        return createUser.getText();
    }

    public boolean isExisting(String name) {
        openPage();
        By userReference = By.linkText(name);
        try {
            driver.findElement(userReference);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
