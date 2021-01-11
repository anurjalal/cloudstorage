package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CloudStorageApplicationTests {

    @Autowired
    EncryptionService encryptionService;

    @LocalServerPort
    private Integer port;

    private static final String FIRST_NAME = "Nur";
    private static final String LAST_NAME = "Jalal";
    private static final String USERNAME = "nurjalal";
    private static final String PASSWORD = "12345";
    private static final String NOTE_TITLE = "Stranger123";
    private static final String NOTE_DESCRIPTION = "This is a secret note";
    private static final String RAND_URL = "https://thisisrandomurl.com";
    private static String BASE_URL;

    private static WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private ResultPage resultPage;
    private HomePage homePage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void beforeEach() {
        BASE_URL = "http://localhost:" + this.port;
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        resultPage = new ResultPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void authorizationTest() throws InterruptedException {
        // verity client can always access signup and login
        driver.get(BASE_URL + "/signup");
        assertEquals(BASE_URL + "/signup", driver.getCurrentUrl());

        driver.get(BASE_URL + "/login");
        assertEquals(BASE_URL + "/login", driver.getCurrentUrl());

        // verify that unauthorize user can't access home page and other url that required login
        driver.get(BASE_URL + "/home");
        assertEquals(BASE_URL + "/login", driver.getCurrentUrl());

        driver.get(BASE_URL + "/note");
        assertEquals(BASE_URL + "/login", driver.getCurrentUrl());
    }

    @Test
    public void signUpTest() throws InterruptedException {
        // Signup and Login
        initialize();

        // Then, Logout
        homePage.logout();
        Thread.sleep(500);

        // Go back to home page
        driver.get(BASE_URL + "/home");

        // Verify it can't access home page, must be redirected back to login page
        assertEquals(BASE_URL + "/login", driver.getCurrentUrl());
    }

    @Test
    public void NoteTest() throws InterruptedException {
        // signup user then login
        initialize();

        homePage.goToNote();
        Thread.sleep(500);
        homePage.showNoteModal();

        //Add a note
        homePage.addNote(NOTE_TITLE, NOTE_DESCRIPTION);

        Thread.sleep(500);
        resultPage.goToHome();
        Thread.sleep(500);
        homePage.goToNote();
        Thread.sleep(500);

        // Verify that note row is 1, because the addition
        assertEquals(1, driver.findElements(By.cssSelector(homePage.noteRowCssAddress)).size());
        Thread.sleep(500);

        // Edit note
        homePage.editNote1("Jamal", "Udin");

        resultPage.goToHome();
        Thread.sleep(500);
        homePage.goToNote();
        Thread.sleep(500);

        // Verify that Edited note is correct
        assertEquals("Jamal", driver.findElement(By.xpath(homePage.noteTitle1XpathAddress)).getText());
        assertEquals("Udin", driver.findElement(By.xpath(homePage.noteDescription1XpathAddress)).getText());

        // Delete Note
        homePage.deleteNote1();

        Thread.sleep(500);
        resultPage.goToHome();
        Thread.sleep(500);
        homePage.goToNote();
        Thread.sleep(500);

        // Verify that Note Table Row is 0
        assertEquals(0, driver.findElements(By.cssSelector(homePage.noteRowCssAddress)).size());
    }

    @Test
    public void CredentialsTest() throws InterruptedException {
        // signup user then login
        initialize();

        homePage.goToCredential();
        Thread.sleep(500);
        homePage.showCredentialModal();

        //Add a credential
        homePage.addCredential(RAND_URL, USERNAME, PASSWORD);

        Thread.sleep(500);
        resultPage.goToHome();
        Thread.sleep(500);
        homePage.goToCredential();
        Thread.sleep(500);

        // Verify that credential row is 1, because the addition
        assertEquals(1, driver.findElements(By.cssSelector(homePage.credentialRowCssAddress)).size());
        Thread.sleep(500);
        assertNotEquals(PASSWORD, driver.findElement(By.xpath(homePage.credentialPasswordXpathAddress)).getText());

        //Edit credential
        homePage.editCredential1Btn.click();
        Thread.sleep(500);
        String decryptedPassword = driver.findElement(By.id(homePage.credentialPasswordDecryptedIdAddress)).getAttribute("value");
        Thread.sleep(500);

        // verify the password field alredy decrypted
        assertEquals(PASSWORD, decryptedPassword);

        // Edit credential
        homePage.editCredential1("https://editedpage.coba", "Udin", "123");

        resultPage.goToHome();
        Thread.sleep(500);
        homePage.goToCredential();
        Thread.sleep(500);

        // Verify that Edited credential is correct
        assertEquals("https://editedpage.coba", driver.findElement(By.xpath(homePage.credentialUrlXpathAddress)).getText());
        assertEquals("Udin", driver.findElement(By.xpath(homePage.credentialUsernameXpathAddress)).getText());
        assertNotEquals("123", driver.findElement(By.xpath(homePage.credentialPasswordXpathAddress)).getText());

        // Delete Credential
        homePage.deleteCredential1();

        Thread.sleep(500);
        resultPage.goToHome();
        Thread.sleep(500);
        homePage.goToCredential();
        Thread.sleep(500);

        // Verify that Credential Table Row is 0
        assertEquals(0, driver.findElements(By.cssSelector(homePage.credentialRowCssAddress)).size());
    }

    public void initialize() throws InterruptedException {
        driver.get(BASE_URL + "/signup");
        Thread.sleep(500);

        //Submit User
        signupPage.submitUser(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);

        Thread.sleep(500);
        signupPage.goToLogin();
        Thread.sleep(500);

        //Login with submitted user
        loginPage.signIn(USERNAME, PASSWORD);
        Thread.sleep(500);
    }
}
