import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public class Assignment_Q1 {

    WebDriver driver;
    String filePath = "./src/test/resources/Excel.xlsx";


    LocalDate date = LocalDate.now();
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    String sheetName = dayOfWeek.toString().trim();
    int startingPointRow = 2;

    @BeforeClass
    public void setup(){

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();

    }

    @Test(dataProvider = "MyData")
    public void TC001_MyTest(String data) throws IOException {

        WebElement searchBox = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
        searchBox.clear();
        searchBox.sendKeys(data);

        List<WebElement> searchList = driver.findElements(By.xpath("//div[@id='Alh6id']//ul[@role='listbox']/li"));

        String shortestText = Assignment_Q1.shortestOption(searchList);
        String longestText = Assignment_Q1.longestOption(searchList);

        ExcelUtility excelUtility = new ExcelUtility(filePath);

        excelUtility.setCellData(sheetName,startingPointRow,3,longestText);
        excelUtility.setCellData(sheetName,startingPointRow,4,shortestText);

        startingPointRow++;

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @DataProvider(name="MyData")
    private String[] dataProvider() throws IOException {

        ExcelUtility utility = new ExcelUtility(filePath);

        int rowCount = utility.getNumberOfRow(sheetName);
        String[] data = new String[rowCount];

        for(int i=2; i<=rowCount; i++){

            data[i-2] = utility.getCellData(sheetName,i,2);

        }

        return data;
    }

    public static String longestOption(List<WebElement> elementList){

        String longText = "";
        int count = 0;
        for(WebElement element: elementList){

            String text = element.getText();
            int textLen = text.length();

            if(textLen>count){

                longText = text;
                count = textLen;

            }


        }

        return longText;

    }

    public static String shortestOption(List<WebElement> elementList){

        String shortText = "";
        int count = Integer.MAX_VALUE;

        for(WebElement element: elementList){

            String text = element.getText();
            int textLen = text.length();

            if(textLen<count){

                shortText = text;
                count = textLen;

            }

        }

        return shortText;
    }

}
