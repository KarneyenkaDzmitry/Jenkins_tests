package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class Helper {

    Helper() {
        log = LogManager.getRootLogger();
    }

    protected WebDriver driver;

    final Logger log;

}
