import com.microsoft.playwright.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sanatorium.LoginPage;
import static org.junit.Assert.assertEquals;
public class LogoutTest {
    private Browser browser;
    private Page page;
    private Playwright playwright;
    private BrowserContext context;
    private LoginPage loginPage;
    String email = "aqa";
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
    public void logoutTest(){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.clickLogoutButton();
        assertEquals("Successful logout", true, loginPage.isLoginButtonDisplayed());
    }

    @After
    public void tearDown(){
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
