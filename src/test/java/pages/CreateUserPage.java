package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.Button;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CreateUserPage extends JenkinsPages {
    private String CREATE_USER_PAGE_URI = BASE_URL +  "/securityRealm/addUser";

    @FindBy(tagName = "table")
    private WebElement form;

    @FindBy(name = "username")
    private WebElement userName;

    @FindBy(name = "password1")
    private WebElement password;

    @FindBy(name = "password2")
    private WebElement confirmPassword;

    @FindBy(name = "fullname")
    private WebElement fullName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(className = "error")
    private WebElement errorMessage;

    private Button createButton = new Button(driver, By.xpath("//span[@name='Submit']/span/button[@type='button']"));

    public CreateUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Button getCreateButton() {
        return createButton;
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (NoSuchElementException e) {
            return "No message";
        }
    }

    @Override
    public void openPage() {
        driver.get(CREATE_USER_PAGE_URI);
    }

    public ManageUsersPage createNewUser(User user) {
        openPage();
        Collection<WebElement> types = findForm();
        userName.sendKeys(user.getName());
        password.sendKeys(user.getPassword());
        confirmPassword.sendKeys(user.getPassword());
        fullName.sendKeys(user.getFullName());
        email.sendKeys(user.getEmail());
        createButton.pushButton();
        return new ManageUsersPage(driver);
    }

    private Collection<WebElement> findForm() {
        openPage();
        Collection<WebElement> types = form.findElements(By.xpath("//input[@type = \"text\"]"));
        types.addAll(form.findElements(By.xpath("//input[@type = \"password\"]")));
        return types;
    }

    private boolean formIsEmpty(Collection<WebElement> elements) {
        for (WebElement element : elements) {
            if (!(element.getText().isEmpty())) {
                return false;
            }
        }
        return true;
    }

    private boolean formIsRight(Collection<WebElement> elements) {
        if (elements.size() == 5) {
            if (!((elements.contains(userName)) && (elements.contains(fullName)) && (elements.contains(email)) &&
                    (elements.contains(password)) && (elements.contains(confirmPassword)))) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /*@value equals: true - the form have to be empty; false - the form can be filled.*/
    public boolean findAndCheckForm(boolean value) {
        openPage();
        Collection<WebElement> elements = findForm();
        if (value) {
            return (formIsEmpty(elements) && (formIsRight(elements)));
        } else {
            return formIsRight(elements);
        }
    }
}
