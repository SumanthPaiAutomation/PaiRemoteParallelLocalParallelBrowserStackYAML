package com.browserstack;

import Utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
//import io.qameta.allure.Allure;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.List;


public class SeleniumTest {
    public WebDriver driver;

    @Parameters({"localbrowsername"})
    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public void setUp( @Optional("chrome")String localbrowsername) throws Exception {

        if(ConfigReader.getInstance().getEnvironmentType().equalsIgnoreCase("remote")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        }

        else if(ConfigReader.getInstance().getEnvironmentType().equalsIgnoreCase("local")){
            //String localBrowser=ConfigReader.getInstance().getLocalBrowser();
            switch (localbrowsername){
                case "chrome":
                    System.setProperty("webdriver.chrome.driver","C:\\testng-browserstack-master\\Drivers\\chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    //WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver();
                    break;
                case "edge":
                   //WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    break;

                default:
                    throw new RuntimeException("Unable to initiate local driver");

            }
            try{
                driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("Illegal runtype , runtype should be either remote or local");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {
        //
        if(ConfigReader.getInstance().getEnvironmentType().equalsIgnoreCase("local")){
            if(!result.isSuccess()){
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = dateFormat.format(cal.getTime());
                String fileName = "TestResult" + dateStr;
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(fileName,new ByteArrayInputStream(screenshot));
            }
        }
        driver.quit();
    }
}
