import com.microsoft.playwright.*;

public class PlaywrightLinearScript {
    public static void main(String[] args) {
        // Create Playwright and launch the browser
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new     BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            try {
                // Navigate to the login page
                page.navigate("https://www.saucedemo.com/");

                // Perform login
                page.fill("#user-name", "standard_user");
                page.fill("#password", "secret_sauce");
                page.click("#login-button");

                // Verify home page
                page.waitForSelector(".inventory_list");

                // Perform logout
                page.click("#react-burger-menu-btn");
                page.waitForSelector("#logout_sidebar_link");
                page.click("#logout_sidebar_link");
            } catch (Exception e) {
                System.err.println("An error occurred during the test execution: " + e.getMessage());
            } finally {
                // Close browser
                browser.close();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while launching the browser: " + e.getMessage());
        }
    }
}
