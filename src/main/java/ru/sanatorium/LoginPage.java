package ru.sanatorium;
import com.microsoft.playwright.*;

public class LoginPage {
    private Page page;
    public void enterEmail(String email){
        Locator emailField = page.locator("#username");
        emailField.fill(email);
    }
    public void enterPassword(String password){
        Locator passwordField = page.locator("#password");
        passwordField.fill(password);
    }

    public void clickLoginButton(){
        Locator loginButton = page.locator("//button[@type='submit']");
        loginButton.click();
    }

    public void clickLogoutButton(){
        Locator logoutButton = page.locator("#logoutButton");
        logoutButton.click();
    }
    public boolean isUserNotFoundVisible(){
        Locator userNotFound = page.locator("#message");
        return userNotFound.isVisible();
    }
    public boolean isUserSuccessFulAuthorized(){
        Locator successFullAuth = page.locator("//div[text()='Вы авторизовались']");
        successFullAuth.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        return successFullAuth.isVisible();
    }

    public boolean isPasswordIncorrect(){
        Locator incorrectPassword = page.locator("//div[@id='message' and text()='Incorrect password']");
        incorrectPassword.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        return incorrectPassword.isVisible();
    }
    public boolean isLoginButtonDisplayed(){
        Locator loginButton = page.locator("//button[@type='submit']");
        loginButton.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        return loginButton.isVisible();
    }

    public LoginPage(Page page){
        this.page = page;
    }


}

