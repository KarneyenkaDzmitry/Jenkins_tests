package helpers;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;

public class Actions extends Helper {

    public WebDriver initDriver(String browser, String keyProperty, String valueProperty) {
        driver = DriverSingleton.getDriver(browser, keyProperty, valueProperty);
        log.info("Initialization [" + browser + "] browser.");
        return driver;
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
        log.info("Browser is closed.");
    }

    public void logIn(User user) {
        RegistrationPage registration = new RegistrationPage(driver);
        registration.enterExistingUser(user);
    }

    public void logOut() {
        HomePage homePage = new HomePage(driver);
        homePage.logOut();
    }

    public void pushManageJenkins() {
        HomePage homePage = new HomePage(driver);
        homePage.choiceManageJenkins();
    }

    public void pushManageUsers() {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        manageJenkinsPage.choiceManageUsers();
    }

    public void pushCreateUser() {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        manageUsersPage.choiceCreateUser();
    }

    public void createUser(User user) {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.createNewUser(user);
    }

    public void pushDeleteUser(User user) {
        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        manageUsersPage.choiceDeleteUser(user);
    }

    public void pushConfirmationDeleteUser(User user) {
        DeleteUserPage deleteUserPage = new DeleteUserPage(driver, user);
        deleteUserPage.confirmation();
    }

    public void pushEnableDisable() {
        HomePage homePage = new HomePage(driver);
        homePage.pushEnableDisableReference();
    }
}
