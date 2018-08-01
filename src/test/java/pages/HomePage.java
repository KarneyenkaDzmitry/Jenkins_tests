package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends JenkinsPages {

    public final String HOME_PAGE_URI = BASE_URL + "/";

    @FindBy(xpath = "//a[@href='/manage'][@class='task-link']")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[@href=\"/logout\"]")
    private WebElement logout;

    @FindBy(xpath = "//div[2]/div[1]/div[2]/span/a[1]/b")
    private WebElement linkOfUserName;

    @FindBy(partialLinkText = "AUTO REFRESH")
    private WebElement enableDisableReference;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage pushEnableDisableReference() {
        openPage();
        enableDisableReference.click();
        return this;
    }

    public String autoRefreshMessage() {
        return enableDisableReference.getText();
    }

    public ManageJenkinsPage choiceManageJenkins() {
        openPage();
        manageJenkins.click();
        return new ManageJenkinsPage(driver);
    }

    public String getLoggedInUserName() {
        openPage();
        return linkOfUserName.getText();
    }

    @Override
    public void openPage() {
        driver.get(HOME_PAGE_URI);
    }

    public void logOut() {
        openPage();
        logout.click();
    }
}
