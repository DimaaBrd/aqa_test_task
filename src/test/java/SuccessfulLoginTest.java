import com.microsoft.playwright.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.sanatorium.LoginPage;
import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class SuccessfulLoginTest {
    private Browser browser;
    private Page page;
    private Playwright playwright;
    private BrowserContext context;
    private LoginPage loginPage;
    private String email;
    private String password;
    public SuccessfulLoginTest(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][]data(){
        return new Object[][]{
                {"aqa" , "AQA123"},
                {"test" , "test123"},
                {"admin", "admin"}
        };
    }

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
    public void successfulLoginTest(){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertEquals("Вы авторизовались", true, loginPage.isUserSuccessFulAuthorized());
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



