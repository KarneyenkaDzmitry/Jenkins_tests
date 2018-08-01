package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;
    private static Logger logger = LogManager.getRootLogger();
    private DriverSingleton() { }

    public static WebDriver getDriver(String browser, String keyProperty, String valueProperty) {
        if (null == driver) {
            System.setProperty(keyProperty, valueProperty);
            switch (browser) {
                case "Firefox":
                    driver = new FirefoxDriver();
                    break;
                case "Chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //Java code should be pretty much the same (untested).
                    //locale here is in the form language[-country] where language is the 2 letter code from ISO-639.
                    chromeOptions.addArguments("--lang=en-US");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                /*I can't to do the test with InternetExplorer and Opera.
                 * I'm really sorry.*/
            /*case "Opera":
                OperaOptions op = new OperaOptions();
                op.setBinary(valueProperty);
                driver = new OperaDriver(op);
                break;
            case "InternetExplorer":
                driver = new InternetExplorerDriver();
                break;*/
                //new SingletonDriver(browser, keyProperty, valueProperty);
            }
            logger.info("Driver has been initialised.");
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
