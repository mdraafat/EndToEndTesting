import framework.Framework;

public class App {
    public static void main(String[] args) {
        Framework framework = Framework
                .startChrome()
                .goToURL("https://www.automationexercise.com");

        framework.close();
    }
}