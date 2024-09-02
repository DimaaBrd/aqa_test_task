import com.microsoft.playwright.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.sanatorium.LoginPage;
import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class InvalidCredentialsLoginTest {
    private Browser browser;
    private Page page;
    private Playwright playwright;
    private BrowserContext context;
    private LoginPage loginPage;
    private String email;
    private String password;
    public InvalidCredentialsLoginTest(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][]data(){
        return new Object[][]{
                {"aqa" , "admin"},
                {"test" , "AQA123"},
                {"admin", "test123"},
                {"admin", ""},
                {"", ""}
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
//Тут баг, ссылка в README.md
    public void loginWithInvalidCredentialsTest(){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertEquals("Incorrect password",true, loginPage.isPasswordIncorrect());
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

