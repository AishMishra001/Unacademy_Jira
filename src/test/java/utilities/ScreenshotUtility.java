package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    public static String capture(
            WebDriver driver,
            String testname) throws IOException {

        File source =
                ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String folderPath =
                System.getProperty("user.dir")
                + File.separator
                + "Screenshots";
System.out.println("path:"+folderPath);
        File folder = new File(folderPath);

        if (!folder.exists()) {

            folder.mkdirs();
        }

        String fileName =
                testname
                + "_"
                + System.currentTimeMillis()
                + ".png";

        File destination =
                new File(folder, fileName);

        Files.copy(
                source.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        return destination.getAbsolutePath();
    }
}