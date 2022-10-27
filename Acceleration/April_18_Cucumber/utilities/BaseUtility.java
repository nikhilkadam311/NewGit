package org.utilities;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUtility {
	
	public void DataWriteInExcel(String filePath, String sheetName, int i, int j, String call) {
		try {	
			FileInputStream fis = new FileInputStream(filePath);	
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s1 = wb.getSheet(sheetName);
			s1.getRow(i).createCell(j).setCellValue(call);  //we can add any format-> .setCellValue
//			s1.getRow(i).createCell(j).setCellValue("Fail");
//			s1.getRow(i).createCell(j).setCellValue("Fail");
			
//			FileOutputStream fos = new FileOutputStream("filePath");	
//			wb.write(fos);
//			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
	public Integer GetAllSheetName(String filePath) {
		Integer totalSheets = null;
		try {
					FileInputStream fis = new FileInputStream(filePath);
					Workbook wb = WorkbookFactory.create(fis);
					Integer totalSheets2 = wb.getNumberOfSheets();
					for(int i=0; i<totalSheets2; i++) {
						System.out.println("sheetName at "+i+" : "+wb.getSheetName(i));
					}					
					return totalSheets2;
				}catch(Exception e){
					e.printStackTrace();
				}
		return totalSheets;
	}
	public Integer GetParticularSheetIndex(String filePath,String sheetName) {
		Integer index = null;
		try {
					FileInputStream fis = new FileInputStream(filePath);
					Workbook wb = WorkbookFactory.create(fis);
			        int index2 = wb.getSheetIndex("sheetName");
					System.out.println("index123 = "+index2);					
					return index2;
				}catch(Exception e){
					e.printStackTrace();
				}
		return index;
	}
	public Integer GetNumberOfSheet(String filePath) {
		Integer totalSheets = null;
		try {
					FileInputStream fis = new FileInputStream(filePath);
					Workbook wb = WorkbookFactory.create(fis);
					Integer totalSheets1 = wb.getNumberOfSheets();
					System.out.println("Total Sheets = "+totalSheets1);
					for(int i=0; i<totalSheets1; i++) {
						System.out.println("sheetName at "+i+" : "+wb.getSheetName(i));
					}					
					return totalSheets1;
				}catch(Exception e){
					e.printStackTrace();
				}
		return totalSheets;
	}
	
	public String getParticularStringCellValue(String filePath,String sheetName,int rowNum,int cellNum) {
		String text = "";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fis);	
			Sheet s1 = wb.getSheet(sheetName);
			Row r1 = s1.getRow(rowNum);
			Cell c1 = r1.getCell(cellNum);
			//			Cell c1 = WorkbookFactory.create(fis).getSheet("sheetName").getRow(rowNum).getCell(cellNum);
			text = c1.getStringCellValue();
			System.out.println("text1 = "+text);
			return text;
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("text1 = "+text);
		return text;
	}

public void test(WebDriver driver, int time, String type, String locator) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(locator)));
	switch(type) {
	case"id":wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(locator)));
	break;
	case"xpath":wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(locator)));
	break;
	case"cssSelector":wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(locator)));
	break;
	case"class":wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(locator)));
	break;
	// remaining cases for remaining locator  
	}
}

public int getDropdownCount(WebElement ele) {
	Select sel = new Select(ele);
	return sel.getOptions().size();
}
public String getDDLSelectedText(WebElement ele) {
	Select sel = new Select(ele);
	return sel.getFirstSelectedOption().getText();
}
public String singleDropdownSelect(WebElement ele,String value) {
	Select drp = new Select(ele);
	List<WebElement>allOptions = drp.getOptions();
	for(WebElement option:allOptions) {
		if(option.getText().equals(value)) {
//			option.click();
			drp.selectByVisibleText(value);
			break;
		}
	}
	return drp.getFirstSelectedOption().getText();
}

public List<WebElement> getAllOptionsOfDDL(WebElement ele) {
	Select sel = new Select(ele);
	return sel.getOptions();
}	

public void clickByAction (WebDriver driver, WebElement ele) {
	Actions act = new Actions(driver);
	act.click(ele).perform();
}
public boolean isAlertPresent(WebDriver driver, int time) {
	try {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}catch (Exception e) {
		return false;
	}
}
public void scrollByPageDown (WebDriver driver, int numOfScrolls) {
	WebElement ele = driver.findElement(By.tagName("body"));
	for(int i=1;i<= numOfScrolls; i++ ) {
		ele.sendKeys(Keys.PAGE_DOWN);
	}	
}
public void scrollByJS(WebDriver driver, WebElement ele) {
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("argument[0].scrollIntoView(true)", ele);
}
public void clickByJS(WebDriver driver, WebElement ele) {
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("argument[0].click();", ele);
}

public void waitForVisibilityOfElement(WebDriver driver, int time, WebElement ele) {
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(time));
	wt.until(ExpectedConditions.visibilityOf(ele));	
}

public void waitForUrlContains(WebDriver driver, int time, String text) {
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(time));
	wt.until(ExpectedConditions.urlContains(text));	
}

public void waitForTitleContains(WebDriver driver, int time, String text) {
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(time));
	wt.until(ExpectedConditions.titleContains(text));	
}
public void waitForVisibilityOfElement(WebDriver driver, int time,String type, String expression) {
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(time));
	switch(type) {
	case"id":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(type)));	
	break;
	case"xpath":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(type)));	
	break;
	case"cssSelector":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(type)));	
	break;
	case"class":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(type)));	
	break;
	}
}

public void waitForVisibilityOfElementByLocator(WebDriver driver, int time,String type, String expression) {
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(time));
	switch(type) {
	case"id":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(expression)));	
	break;
	case"xpath":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(expression)));	
	break;
	case"cssSelector":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(expression)));	
	break;
	case"class":wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(expression)));	
	break;
	}
}
//public void clickOn(By locator, WebElement driver, int timeout) 
// { final WebDriverWait wait = new WebDriverWait(driver, timeout);
//	wait.until(ExpectedConditions.refreshed(
//			ExpectedConditions.elementToBeClickable(locator)));
//	driver.findElement(locator).click();
//}

public void waitForAttributeContainsByLocator(WebDriver driver, int time, WebElement ele, String attributeName, String value) {
	WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(time));
	wt.until(ExpectedConditions.attributeContains(ele, attributeName, value));
}

public void waitForVisibilityOfElementByLocatorByLocator(WebDriver driver, int time,String type, String expression,String attributeName, String value) {
	WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
	switch(type) {
	case"id":wt.until(ExpectedConditions.attributeContains(By.id(expression), attributeName, value));
	break;
	case"xpath":wt.until(ExpectedConditions.attributeContains(By.xpath(expression), attributeName, value));
	break;
	case"cssSelector":wt.until(ExpectedConditions.attributeContains(By.cssSelector(expression), attributeName, value));	
	break;
	case"class":wt.until(ExpectedConditions.attributeContains(By.className(expression), attributeName, value));
	break;
	}
}
public void startUp11 (String browserName, String url) {
	WebDriver driver = null;
	if(browserName.equalsIgnoreCase("ch") || browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
	} else if(browserName.equalsIgnoreCase("ff") || browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	} else if(browserName.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	} else {  
		System.out.println("Invalid brower name");
	}
	//		driver.manage().window().minimize();
	//		driver.manage().window().setSize(new Dimension(1000, 200));
	driver.get(url);	
}
public void startUpWithWM (String browserName, String url) {
	WebDriver driver = null;
	if(browserName.equalsIgnoreCase("ch") || browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
	} else if(browserName.equalsIgnoreCase("ff") || browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	} else if(browserName.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	} else {  
		System.out.println("Invalid brower name");
	}
	//		driver.manage().window().minimize();
	//		driver.manage().window().setSize(new Dimension(1000, 200));
	driver.get(url);	
}
public WebDriver startUp (String browserName, String url) {
	WebDriver driver = null;
	if(browserName.equalsIgnoreCase("ch") || browserName.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
	} else if(browserName.equalsIgnoreCase("ff") || browserName.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	} else if(browserName.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver","./drivers/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	} else {  
		System.out.println("Invalid browser name");
	}
	//		driver.manage().window().minimize();
	//		driver.manage().window().setSize(new Dimension(1000, 200));
	//		upto selenium 3
	//		driver.manage().timeout().implicitWait(15, TimeUnit.SECONDS);
	//		Selenium 4 onwards
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get(url);
	return driver;
}

public void startUp1 (String browserName, String url) {
	WebDriver driver = null;
	//		ChromeDriver driverCh = null;
	//		FirefoxDriver driverFF = null;
	//		EdgeDriver driverEdge = null;
	if(browserName.equalsIgnoreCase("ch") || browserName.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//			options.addArguments("--disable-notification");
		options.addArguments("start-maximized");
		//			driverCh = new ChromeDriver(options);
		driver = new ChromeDriver(options);
		//			driverCh.manage().window().maximize();
		//			driverCh.get(url);
	} else if(browserName.equalsIgnoreCase("ff") || 
			browserName.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		//			driverFF.get(url);
	} else if(browserName.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver","./drivers/msedgedriver.exe");
		driver = new EdgeDriver();
		//			driverEdge.get(url);
	} else {  
		System.out.println("Invalid brower name");
	}
	driver.get(url);
}
}