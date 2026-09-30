package utilities;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenshotUtility {

    public static String capture(WebDriver driver, String testname) throws IOException {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File folder = new File("Screenshots");

        if (!folder.exists()) {
            folder.mkdir();
        }

        File destination = new File("Screenshots/" + testname + ".png");

        Files.copy(source, destination);

        return destination.getAbsolutePath();
    }
}