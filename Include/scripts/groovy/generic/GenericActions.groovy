package generic


import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import javax.tools.FileObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.List;
import com.kms.katalon.core.webui.driver.WebUIDriverType

import globalvariables.GlobalVariables
import org.openqa.selenium.WebDriver

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.DesiredCapabilities
import report.ReportBean

import org.eclipse.persistence.config.LoggerType
import org.openqa.selenium.By as By
import pageManagers.FileReaderManager
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry
import java.util.Date;
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.logging.KeywordLogger

//import io.netty.handler.codec.http.HttpResponse;
//import io.netty.handler.codec.http.HttpRequest;
//import net.lightbody.bmp.BrowserMobProxy;
//import net.lightbody.bmp.BrowserMobProxyServer;
//import net.lightbody.bmp.client.ClientUtil;
//import net.lightbody.bmp.filters.RequestFilter;
//import net.lightbody.bmp.proxy.auth.AuthType;
//import net.lightbody.bmp.util.HttpMessageContents;
//import net.lightbody.bmp.util.HttpMessageInfo;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Base64;
import org.openqa.selenium.JavascriptExecutor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.testng.annotations.Test;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/***********************************Generic Actions Includes all the possible Interactions for Web Application *******************************************/
/***********************************************Functions Included ***************************************************************************************/
/*
 * input_action(String locator, String value)
 * check_all_links()
 * click_action(String locator)
 * click_action_2(String locator) - Static wait before the click
 * click_action(String locator, int waitInSec) - Wait dynamically for some time 
 * openBrowserParameters(String url, String PageName) - Create a URL for 
 * validation_action(String locator) - Get Text
 * verifyMultipleModulesPresence()
 * openBrowser(String url)
 * verify_Module_Presence(String locatorModule, className)
 * select_Dropdown(String locatorDropdown, String locatorDropdownItem, String locatorByValue, String ByValue)
 * reset_environment(String Environment)
 */

public class GenericActions {
	//ReportBean ReportBean = new ReportBean()
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	def static input_action(String locator, String value){
		try{
			WebUI.waitForElementPresent(findTestObject(locator), 10)

			WebUI.clearText(findTestObject(locator))
			WebUI.sendKeys(findTestObject(locator), value)
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')
			throw e
		}
	}


	/**
	 * Method for selecting the value from the dropdown
	 * @return
	 */
	def static select_Dropdown(String locatorDropdown, String locatorDropdownItem, String locatorByValue, String ByValue){
		try{
			WebUI.waitForElementPresent(findTestObject(locatorDropdown), 10)
			WebUI.click(findTestObject(locatorDropdown))
			WebUI.waitForElementPresent(findTestObject(locatorDropdownItem), 10)
			WebUI.click(findTestObject(locatorDropdownItem))
			WebUI.waitForElementPresent(findTestObject(locatorByValue), 10)
			WebUI.selectOptionByValue(findTestObject(locatorByValue), ByValue, true)
			WebUI.check(findTestObject(locatorDropdown)).size() != 0
		}
		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}
	/**
	 * Method for selecting the value from the dropdown
	 * @return
	 */

	def static verify_Module_Presence(String locatorModule, className){
		try{
			String attributeValue = WebUI.getAttribute(locatorModule, className)
			println (attributeValue)
			if(attributeValue != null){
				println ('Module is present on the page')
			}
			else{
				println ('Module is not present on the page')
			}
		}
		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')
			throw e

			//throw e
		}
	}
	/**
	 * Method for the clicking on the object
	 * @return
	 */
	def static click_action(String locator){

		try{
			//println locator
			click_action(locator,10)
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	/**
	 * Method for the clicking on the object
	 * @return
	 */
	def static click_action_2(String locator){

		try{
			//println locator
			WebUI.waitForElementPresent(findTestObject(locator), 10)
			Thread.sleep(1000)
			WebUI.click(findTestObject(locator))
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	/**
	 * Method for the clicking on the object
	 * @return
	 */
	def static click_action(String locator, int waitInSec){

		try{
			//println locator
			WebUI.waitForElementPresent(findTestObject(locator), waitInSec)
			WebUI.click(findTestObject(locator))
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	/**
	 * Method for validation of object
	 * @param locator
	 * @return
	 */
	def static validation_action(String locator){
		try{
			WebUI.waitForElementPresent(findTestObject(locator), 10)
			def textFieldValue = WebUI.getText(findTestObject(locator))


			return textFieldValue
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	def static verifyMultipleModulesPresence(){
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement element = driver.findElements(By.xpath(""))
		}
		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	def static openBrowser(String url){
		try{
			WebUI.openBrowser('')

			long start = System.currentTimeMillis()
			WebUI.navigateToUrl(url)
			WebUI.maximizeWindow()

			long finish = System.currentTimeMillis()
			long totalTime = finish - start
			long TimeInSeconds = totalTime / 1000
			println ("Total Time for page load - "+ TimeInSeconds + " Seconds")
		}catch(Exception ex){

			throw ex
		}
	}
	//	def static BrowserMobProxyServer setUpProxy(){
	//		BrowserMobProxyServer proxy = new BrowserMobProxyServer();
	//		proxy.addRequestFilter(new RequestFilter() {
	//			@Override
	//			public HttpResponse filterRequest(HttpRequest httpRequest, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
	//				try {
	////					if(httpRequest.getUri().toLowerCase().endsWith("css")){
	////						System.out.println("Skip adding authorization header for: " + httpRequest.getUri());
	////					}else{
	//						//String authHeader = "Basic " + Base64.getEncoder().encodeToString("webelement:click".getBytes("utf-8"));
	//						//System.out.println("Adding header for: " + httpRequest.getUri());
	//					((io.netty.handler.codec.http.HttpResponse)httpRequest.headers().add("autopasscode", "8j_7gxGQA6a9Ld8G"));
	//					//}
	//				} catch (UnsupportedEncodingException e) {
	//					System.err.println("Couldn't add authorization header..");
	//					e.printStackTrace();
	//				}
	//				return null;
	//			}
	//		});
	//		proxy.start(0);
	//		return proxy;
	//	}

	def static openBrowserParameters(String url, String PageName){

		Date today = new Date()
		String todaysDate = today.format('MM_dd_yy')
		String nowTime = today.format('hh_mm_ss')

		try{
			//WebUI.openBrowser('')

			long start = System.currentTimeMillis()
			//BrowserMobProxyServer proxy = this.setUpProxy();
			//ChromeOptions ffOptions = new ChromeOptions();
			//ffOptions.setProxy(ClientUtil.createSeleniumProxy(proxy));
			//ChromeDriver chromedriver = new ChromeDriver(ffOptions);
			//chromedriver.get(url);

			//WebUI.authenticate(url, "passcode","value", 0);

			WebUI.openBrowser(url);
			WebUI.maximizeWindow()

			long finish = System.currentTimeMillis()
			long totalTime = finish - start
			long TimeInSeconds = totalTime / 1000
			String newTime = ("Total Time for page load of " +PageName +" property page is " +  TimeInSeconds + " Seconds")
			println (newTime)
			KeywordLogger log = new KeywordLogger()
			log.logInfo(newTime)
			WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
			if (executedBrowser == WebUIDriverType.FIREFOX_DRIVER) {
				WebDriver driver = DriverFactory.getWebDriver()
				LogEntries logs = driver.manage().logs().get('browser')
				logs.getAll().size()
				List<String> list = logs.getAll();

				FileInputStream file = new FileInputStream (new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('TestOutputExcelFileUrl')))
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				//FileOutputStream fileOut = new FileOutputStream("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('TestOutputExcelFileUrl'))


				XSSFSheet sheet
				//sheet = workbook.cl
				for(int i=workbook.getNumberOfSheets()-1;i>=0;i--){
					XSSFSheet tmpSheet =workbook.getSheetAt(i);
					if(tmpSheet.getSheetName().equals(PageName)){
						workbook.removeSheetAt(i);
					}
				}
				sheet = workbook.createSheet(PageName)


				for(int i=0; i<list.size(); i++){

					//println (new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
					String value = list.get(i)
					sheet.createRow(0).createCell(1).setCellValue(newTime);
					log.logInfo(value)
					sheet.createRow(i+1).createCell(1).setCellValue(value);
					println (value)
					//LogEntry entry = logs

					//FileReaderManager.getExcelFileRead().ExcelWrite(0, 1, newTime, i+1, 1, value, PageName)
				}

				/*for (LogEntry entry : logs){
				 println(">>> ${entry}")
				 }*/

				FileOutputStream outFile =new FileOutputStream(new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('TestOutputExcelFileUrl')));
				//XSSFWorkbook workbook = new XSSFWorkbook(file);
				//XSSFSheet sheet = workbook.getSheetAt(0);
				workbook.write(outFile);
				//workbook.write(fileOut)
				outFile.close();
				file.close();
			}
		}
		catch(Exception e){

			throw e
		}

		WebUI.takeScreenshot("C:\\Users\\kbhogal\\Documents\\screenshots\\check_"  + todaysDate +'_' + nowTime + '.PNG')
	}

	def static openBrowserSanityPack(String url, String PageName){


		Date today = new Date()
		String todaysDate = today.format('MM_dd_yy')
		String nowTime = today.format('hh_mm_ss')
		try{
			//WebUI.openBrowser('')

			long start = System.currentTimeMillis()
			Thread.sleep(2000)
			WebUI.openBrowser(url)
			WebUI.maximizeWindow()
			HttpClient client = HttpClientBuilder.create().build()
			try {
				HttpGet request = new HttpGet(url)
				HttpResponse response = client.execute(request)
				if (response.getStatusLine().getStatusCode() != 200) {

					println (" Server Exception. Getting response code as "+response.getStatusLine().getStatusCode() + " While hitting the url -" + url)
					assert println (response.getStatusLine().getStatusCode())
				}
			}
			catch(Exception e){
			}
			long finish = System.currentTimeMillis()
			long totalTime = finish - start
			long TimeInSeconds = totalTime / 1000
			String newTime = ("Total Time for page load of " +PageName +" property page is " +  TimeInSeconds + " Seconds")
			println (newTime)
			KeywordLogger log = new KeywordLogger()
			log.logInfo(newTime)

			WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
			if (executedBrowser == WebUIDriverType.CHROME_DRIVER) {
				WebDriver driver = DriverFactory.getWebDriver()
				LogEntries logs = driver.manage().logs().get('browser')
				logs.getAll().size()
				List<String> list = logs.getAll();

				FileInputStream file = new FileInputStream (new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('SanityPackExcelurl')))
				XSSFWorkbook workbook = new XSSFWorkbook(file);

				XSSFSheet sheet

				for(int i=workbook.getNumberOfSheets()-1;i>=0;i--){
					XSSFSheet tmpSheet =workbook.getSheetAt(i);
					if(tmpSheet.getSheetName().equals(PageName)){
						workbook.removeSheetAt(i);
					}
				}
				sheet = workbook.createSheet(PageName)


				for(int i=0; i<list.size(); i++){

					//println (new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
					String value = list.get(i)

					sheet.createRow(0).createCell(1).setCellValue(newTime);
					log.logInfo(value)
					sheet.createRow(i+1).createCell(1).setCellValue(value);
				}


				file.close();
			}

			//WebUI.takeScreenshot("C:\\Users\\kbhogal\\Documents\\screenshots\\check_"  + todaysDate +'_' + nowTime + '.PNG')
		}
		catch(Exception e){

			throw e
		}
	}

	def static openBrowserScrollBottom(String url){


		Date today = new Date()
		String todaysDate = today.format('MM_dd_yy')
		String nowTime = today.format('hh_mm_ss')

		//WebUI.openBrowser('')

		long start = System.currentTimeMillis()

		WebUI.openBrowser(url)
		WebUI.maximizeWindow()
		HttpClient client = HttpClientBuilder.create().build()

		try {
			HttpGet request = new HttpGet(url)
			HttpResponse response = client.execute(request)
			if (response.getStatusLine().getStatusCode() != 200) {

				println (" Server Exception. Getting response code as "+response.getStatusLine().getStatusCode() + " While hitting the url -" + url)
				assert println (response.getStatusLine().getStatusCode())
			}
		}
		catch(Exception e){
		}

		KeywordLogger log = new KeywordLogger()

		WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()

		if (executedBrowser == WebUIDriverType.CHROME_DRIVER) {
			WebDriver driver = DriverFactory.getWebDriver()
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Launch the application
			//driver.get(url);

			//This will scroll the web page till end.
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
	}


	//new code
	def static responseCheck(String url) {
		try {


			HttpClient client = HttpClientBuilder.create().build()
			HttpGet request = new HttpGet(url)
			HttpResponse response = client.execute(request)
			//verifyElementNotPresent(String locator)
			if (response.getStatusLine().getStatusCode() != 200) {
				KeywordLogger log = new KeywordLogger()
				log.logError("Something wrong here. Getting response code as "+response.getStatusLine().getStatusCode()+ " on loading " +url)
			}
		}catch(Exception exp) {
			throw new Exception("Broken link Error while navigating to url ${url}")
		}
	}
	def static verify_AttributeCheck(String locator, String attribute){
		try{
			WebUI.waitForElementPresent(findTestObject(locator), 10)
			def textFieldValue = WebUI.getAttribute(locator, attribute)
			println (textFieldValue)
			return textFieldValue
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	def static verifyElementPresent(String locator){
		try{
			if( !WebUI.waitForElementPresent(findTestObject(locator), 10) ) {
				throw new Exception(locator + "not found")
			}
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	def static verifyElementPresent(String locator, int waitInSec){
		try{
			if( !WebUI.waitForElementPresent(findTestObject(locator),  waitInSec) ) {
				throw new Exception(locator + "not found")
			}
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	def static boolean verifyElementNotPresent(String locator){
		try{
			if(WebUI.waitForElementPresent(findTestObject(locator), 10) ) {
				throw new Exception(locator + "found");
			}
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError(e.message)
			throw e
		}
	}

	def static hover_action(String locator){

		try{
			println "Going to Hover Over :" : locator
			WebUI.waitForElementPresent(findTestObject(locator), 10)
			WebUI.mouseOver((findTestObject(locator)))
		}

		catch(Exception e){
			KeywordLogger log = new KeywordLogger()
			log.logError('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	def static check_all_links() {
		WebUI.verifyAllLinksOnCurrentPageAccessible(true, [])
	}

	def static reset_environment(String Environment) {
		PropertiesConfiguration conf = new PropertiesConfiguration("config.properties")
		conf.setProperty("environment", Environment)
		conf.save()
	}
}
