package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageJenkinsPage extends JenkinsPages {
    private static int count = 0;
    private final String MANAGE_JENKINS_PAGE_URI = BASE_URL + "/manage";

    @FindBy(xpath = "//a[@href='securityRealm/'][@title='Manage Users']")
    private WebElement manageUsersReference;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ManageUsersPage choiceManageUsers() {
        openPage();
        manageUsersReference.click();
        return new ManageUsersPage(driver);
    }

    @Override
    public void openPage() {
        driver.get(MANAGE_JENKINS_PAGE_URI);
    }

    public String getNameManageUsersButton() {
        openPage();
        return manageUsersReference.findElement(By.tagName("dt")).getText();
    }

    public String getTextBelowManageUsersButton() {
        openPage();
        return manageUsersReference.findElement(By.tagName("dd")).getText();
    }
}
