import helpers.Actions;
import helpers.Controls;
import org.testng.annotations.*;
import pages.User;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/*The test has created and ran for jenkins ver.2.111.
* Run the test is used testng.xml - file.*/
public class JenkinsTest {
    private Actions actions;
    private Controls controls;
    private String error = "See more in \"Log\"";
    private User emptyFields = new User("", "", "", "");
    private User existingUser = new User("admin", "admin",
            "admin@admin.ru", "admin");
    private User testUser = new User("someuser", "Some Full Name", "some@addr.dom",
            "somepassword");
    private User emptyUser = new User("", "empty",
            "empty@empty.ru", "empty");

    @Parameters({"browser", "keyProperty", "valueProperty"})
    @BeforeClass(description = "Initializations: WebDriver, actions, controls")
    public void beforeClass(String browser, String keyProperty, String valueProperty) throws Exception {
        actions = new Actions();
        controls = new Controls(actions.initDriver(browser, keyProperty, valueProperty));
    }

    @AfterClass(description = "stop driver")
    public void afterClassMethod() {
        actions.closeDriver();
    }

    @BeforeMethod(description = "login to Jenkins")
    public void AutoLogin() {
        actions.logIn(existingUser);
    }

    @AfterMethod(description = "logout of the Jenkins")
    public void AutoLogout() {
        actions.logOut();
    }

    @Test(description = "check login ")
    public void testAutoLogin() {
        assertTrue(controls.checkLogIn(existingUser.getFullName()), error);
    }

    @Test(description = "Hometask(HT1) task1")
    public void testManageJenkins() {
        actions.pushManageJenkins();
        assertTrue(controls.checkNameManageUsersButton("Manage Users"), error);
        assertTrue(controls.checkManageUsersText("Create/delete/modify users that can log in to this Jenkins"), error);
    }

    @Test(description = "Hometask(HT1) task2")
    public void testManageUsers() {
        actions.pushManageUsers();
        assertTrue(controls.checkCreateUserButton("Create User"), error);
    }

    @Test(description = "Hometask(HT1) task3", groups = "createAndDeleteNewUser")
    public void testCreateUserForm() {
        actions.pushCreateUser();
        assertTrue(controls.checkCreateUserForm(false), error);
        assertTrue(controls.checkColorOfCreateButton("#4b758b"), "The button [Create] has different background colour.");
    }

    @Test(description = "Hometask(HT1) task4", groups = "createAndDeleteNewUser")
    public void testCreateUser() {
        actions.createUser(testUser);
        assertTrue(controls.checkIsUserExisting(testUser), error);
    }

    @Test(description = "Hometask(HT1) task5", dependsOnMethods = "testCreateUser", groups = "createAndDeleteNewUser")
    public void testDeleteMessage() {
        actions.pushDeleteUser(testUser);
        assertTrue(controls.checkTextBeforeDeleteUser(testUser, "Are you sure about deleting the user from Jenkins?"), error);
        assertTrue(controls.checkColorOfYesButton(testUser, "#4b758b"), "The button [Yes] has different background colour.");
    }

    @Test(description = "Hometask(HT1) task6 part1", dependsOnMethods = "testDeleteMessage", groups = "createAndDeleteNewUser")
    public void testConfirmDeleteUser() {
        actions.pushConfirmationDeleteUser(testUser);
        assertFalse(controls.checkIsUserExisting(testUser), error);
    }

    @Test(description = "Hometask(HT1) task6 all", dependsOnMethods = "testConfirmDeleteUser", groups = "createAndDeleteNewUser")
    public void testCheckDeletedUser() {
        actions.pushManageUsers();
        assertFalse(controls.checkDeleteUserReference(testUser), error);
        assertFalse(controls.checkIsUserExisting(testUser), error);
    }

    @Test(description = "Hometask(HT1) task7")
    public void testCheckDeleteLinkOfAdmin() {
        actions.pushManageUsers();
        assertFalse(controls.checkDeleteUserReference(existingUser), error);
        assertTrue(controls.checkIsUserExisting(existingUser), error);
    }

    @Test(description = "Hometask(HT1), addition task1, check colour of [login] button .")
    public void testButtonLogin() {
        actions.logOut();
        assertTrue(controls.checkColorOfLoginButton("#4b758b"), "The button [Login] has different background colour.");
        actions.logIn(existingUser);
    }

    @Test(description = "Hometask(HT1), addition task2. Create user with empty name.")
    public void testCreateEmptyNameUser() {
        actions.createUser(emptyUser);
        assertTrue(controls.checkEmptyNameText("\"\" is prohibited as a full name for security reasons."), error);
    }

    @Test(description = "Hometask(HT1), addition task2. Create user with empty name.")
    public void testCreateUserAllFieldsEmpty() {
        actions.createUser(emptyFields);
        assertTrue(controls.checkEmptyNameText("\"\" is prohibited as a full name for security reasons."), error);
    }

    @Test(description = "Hometask(HT1), addition task3. Cyclic change of messages.")
    public void testEnableDisable() {
        for (int i = 0; i < 3; i++) {
            assertTrue(controls.checkEnableDisable("ENABLE AUTO REFRESH"), error);
            actions.pushEnableDisable();
            assertTrue(controls.checkEnableDisable("DISABLE AUTO REFRESH"), error);
            actions.pushEnableDisable();
        }
    }
}
