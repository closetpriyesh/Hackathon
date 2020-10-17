package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 * This is used to write the Properties that our test scripts use.
 */
public class WriteProperties {
	public static void main(String[] args) {
		try (OutputStream output = new FileOutputStream("src\\main\\resources\\config.properties")) {
			Properties properties = new Properties();
			properties.setProperty("path.chrome", "src\\main\\resources\\chromedriver.exe");
			properties.setProperty("path.firefox", "src\\main\\resources\\geckodriver.exe");
			properties.setProperty("path.ie", "src\\main\\resources\\IEDriverServer.exe");
			properties.setProperty("browser", "chrome");
			properties.setProperty("operation.count", "2");
			properties.setProperty("url", "https://www.covid19india.org/");

			properties.store(output, null);
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}
}
