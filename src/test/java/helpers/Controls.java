package helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.*;

public class Controls extends Helper {
    public Controls(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkLogIn(String userName) {
        HomePage homePage = new HomePage(driver);
        boolean marker = homePage.getLoggedInUserName().equals(userName);
        if (!marker) {
            log.warn("Url: [" + homePage.HOME_PAGE_URI + "]. Expected User name: [" + userName + "]. But actual: [" + homePage.getLoggedInUserName());
        }
        return marker;
    }

    public boolean checkNameManageUsersButton(String text) {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        return manageJenkinsPage.getNameManageUsersButton().equals(text);
    }

    public boolean checkManageUsersText(String text) {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        return manageJenkinsPage.getTextBelowManageUsersButton().equals(text);
    }

    public boolean checkCreateUserButton(String text) {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        return manageUsersPage.getNameCreateUserButton().equals(text);
    }

    /*@value equals: true - the form have to be empty; false - the form can be filled.*/
    public boolean checkCreateUserForm(boolean value) {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        return createUserPage.findAndCheckForm(value);
    }

    public boolean checkIsUserExisting(User user) {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        return manageUsersPage.isExisting(user.getName());
    }

    public boolean checkTextBeforeDeleteUser(User user, String message) {
        DeleteUserPage deleteUserPage = new DeleteUserPage(driver, user);
        return deleteUserPage.getMessageBeforeDelete().contains(message);
    }

    public boolean checkDeleteUserReference(User user) {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        try {
            manageUsersPage.choiceDeleteUser(user);
            return true;
        } catch (NoSuchElementException e) {
            log.warn("[" + user.getName() + " delete reference] doesn't exist.");
            return false;
        }
    }

    public boolean checkColorOfLoginButton(String hexColor) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        return registrationPage.getLogin().getColorHex().equals(hexColor);
    }

    public boolean checkColorOfCreateButton(String hexColor) {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.openPage();
        return createUserPage.getCreateButton().getColorHex().equals(hexColor);
    }

    public boolean checkColorOfYesButton(User user, String hexColor) {
        DeleteUserPage deleteUserPage = new DeleteUserPage(driver, user);
        deleteUserPage.openPage();
        return deleteUserPage.getConfirmationButton().getColorHex().equals(hexColor);
    }

    public boolean checkEmptyNameText(String errorMessage) {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        boolean marker = createUserPage.getErrorMessage().equals(errorMessage);
        if (!marker) {
            log.warn("Actual result: [" + createUserPage.getErrorMessage() + "]." +
                    " Expected result:[" + errorMessage + "].");
        }
        return marker;
    }

    public boolean checkEnableDisable(String message) {
        HomePage homePage = new HomePage(driver);
        return homePage.autoRefreshMessage().equals(message);
    }

}
