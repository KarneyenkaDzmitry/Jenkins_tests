package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.Button;

public class DeleteUserPage extends JenkinsPages {

    private final String DELETE_USER_PAGE_URI;

    private Button confirmationButton = new Button(driver, By.xpath("//span[@name='Submit']/span/button[@type='button']"));

    @FindBy(name = "delete")
    private WebElement confirmationText;

    public DeleteUserPage(WebDriver driver, User user) {
        super(driver);
        DELETE_USER_PAGE_URI = BASE_URL + "/securityRealm/user/" + user.getName() + "/delete";
        PageFactory.initElements(driver, this);
    }

    public Button getConfirmationButton() {
        return confirmationButton;
    }

    public ManageUsersPage confirmation() {
        openPage();
        confirmationButton.pushButton();
        return new ManageUsersPage(driver);
    }

    public String getMessageBeforeDelete() {
        openPage();
        return confirmationText.getText();
    }

    @Override
    public void openPage() {
        driver.get(DELETE_USER_PAGE_URI);
    }

    public String getDeleteUserPageUri() {
        return DELETE_USER_PAGE_URI;
    }
}
