package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "saveNote")
    private WebElement noteSubmitBtn;

    @FindBy(id = "showNoteModal")
    private WebElement showNoteModalBtn;

    @FindBy(id = "showCredentialModal")
    private WebElement showCredentialModalBtn;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "saveCredential")
    private WebElement credentialSubmitBtn;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[1]/button")
    private WebElement editNote1Btn;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[1]/a")
    private WebElement deleteNote1Btn;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[1]/button")
    public static WebElement editCredential1Btn;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[1]/a")
    private WebElement deleteCredentialBtn;

    public static String noteRowCssAddress = "tr.note-row";
    public static String noteTitle1XpathAddress = "//*[@id=\"userTable\"]/tbody/tr[1]/td[2]";
    public static String noteDescription1XpathAddress = "//*[@id=\"userTable\"]/tbody/tr[1]/td[3]";

    public static String credentialRowCssAddress = "tr.credential-row";
    public static String credentialUrlXpathAddress = "//*[@id=\"credentialTable\"]/tbody/tr/td[2]";
    public static String credentialUsernameXpathAddress = "//*[@id=\"credentialTable\"]/tbody/tr/td[3]";
    public static String credentialPasswordXpathAddress = "//*[@id=\"credentialTable\"]/tbody/tr/td[4]";
    public static String credentialPasswordDecryptedIdAddress = "credential-password";

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void deleteNote1() {
        deleteNote1Btn.click();
    }

    public void deleteCredential1() {
        deleteCredentialBtn.click();
    }

    public void editNote1(String noteTitle, String noteDescription) throws InterruptedException {
        editNote1Btn.click();
        Thread.sleep(500);
        addNote(noteTitle, noteDescription);
    }

    public void editCredential1(String credentialUrl, String credentialUsername, String credentialPassword) throws InterruptedException {
        Thread.sleep(500);
        addCredential(credentialUrl, credentialUsername, credentialPassword);
    }

    public void goToNote() {
        navNotesTab.click();
    }

    public void showNoteModal() throws InterruptedException {
        showNoteModalBtn.click();
        Thread.sleep(500);
    }

    public void showCredentialModal() throws InterruptedException {
        showCredentialModalBtn.click();
        Thread.sleep(500);
    }

    public void addNote(String noteTitle, String noteDescription) throws InterruptedException {
        noteTitleField.clear();
        noteDescriptionField.clear();
        noteTitleField.sendKeys(noteTitle);
        noteDescriptionField.sendKeys(noteDescription);
        noteSubmitBtn.click();
        Thread.sleep(500);
    }

    public void addCredential(String credentialUrl, String credentialUsername, String credentialPassword) throws InterruptedException {
        credentialUrlField.clear();
        credentialUsernameField.clear();
        credentialPasswordField.clear();
        credentialUrlField.sendKeys(credentialUrl);
        credentialUsernameField.sendKeys(credentialUsername);
        credentialPasswordField.sendKeys(credentialPassword);
        credentialSubmitBtn.click();
        Thread.sleep(500);
    }

    public void goToCredential() {
        navCredentialsTab.click();
    }

    public void logout() {
        logoutButton.click();
    }
}
