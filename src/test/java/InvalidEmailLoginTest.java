import com.microsoft.playwright.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sanatorium.LoginPage;

import static org.junit.Assert.assertEquals;

public class InvalidEmailLoginTest {
    private Browser browser;
    private Page page;
    private Playwright playwright;
    private BrowserContext context;
    private LoginPage loginPage;
    String email = "";
    String password = "AQA123";
    @Before
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("http://localhost:3000/");
        loginPage = new LoginPage(page);
    }

    @Test
    public void loginWithInvalidEmail(){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertEquals("User should be not found", true, loginPage.isUserNotFoundVisible());
    }

    @After
    public void tearDown(){
        {
            if(page != null ){
                page.close();
            }
            if(browser != null){
                browser.close();
            }
            if(playwright!= null){
                playwright.close();
            }

        }
    }

}

