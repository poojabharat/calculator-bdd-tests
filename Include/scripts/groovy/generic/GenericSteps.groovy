package generic
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import pageManagers.FileReaderManager
import generic.GenericActions
import stepDefs.*


class GenericSteps {

	TestObject ObjectLocator
	def autopass = '?autopasscode='+FileReaderManager.getPropertyReaderPage().prop('autopasscode')
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("I Navigate to (.*)")
	def I_Navigate_To(String URL) {
		WebUI.comment( "Here I am navigating the URL : " + URL)
		println "Here I am navigating the URL : " + URL
		GenericActions.openBrowser(URL+autopass)
		try {
			WebUI.takeScreenshotAsCheckpoint('full_view', FailureHandling.CONTINUE_ON_FAILURE)
		}
		catch(Exception e) {

			KeywordUtil.logInfo('Error in taking the screenshot for checkpoint')

			throw e
		}
	}

	@Given("I go to property Enquire us page  for (.*)")
	def I_Navigate_To_Enquire_Page(String pageName) {
		WebUI.comment("Here I am navigating the Enquire Page for : " + pageName)
		println "Here I am navigating the Enquire Page for : " + pageName
		user_is_on_the_property_Enquire_us_page(pageName)
	}

	@When("I Click on the (.*) (.*)")
	def I_check_for_the_value_in_step(String ObjectType,String ObjectName) {
		WebUI.comment("I am going to click on Object Type: "  + ObjectType +" Object name : " + ObjectName)
		println "I am going to click on Object Type: "  + ObjectType +" Object name : " + ObjectName
		GetObjectLocator(ObjectType,ObjectName)
		try{
			//println locator
			WebUI.waitForElementPresent(ObjectLocator, 10)
			Thread.sleep(1000)
			WebUI.click(ObjectLocator)
		}

		catch(Exception e){

			KeywordUtil.logInfo('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}


	@When("I Enter (.*) in the ([A-Za-z]*) (.*)")
	//	@Then("I Enter (.*) in the (.*) (.*)")
	def I_verify_the_status_in_step(String DataValue, String ObjectType, String ObjectName) {
		WebUI.comment("Step for Enter Text as Data Value: "  + DataValue +" Object name : " + ObjectName +" Object type : " + ObjectType)
		println  "Step for Enter Text as Data Value: "  + DataValue +" Object name : " + ObjectName +" Object type : " + ObjectType
		GetObjectLocator(ObjectType,ObjectName)
		try{
			WebUI.waitForElementPresent(ObjectLocator, 10)
			try {
				WebUI.clearText(ObjectLocator)
			}
			catch(Exception e) {}
			WebUI.sendKeys(ObjectLocator, DataValue)
		}

		catch(Exception e){

			KeywordUtil.logInfo('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')
			throw e
		}
	}

	@When("I select (.*) from ([A-Za-z]*) (.*)")
	def I_select_value(String DataValue, String ObjectType, String ObjectName) {
		WebUI.comment("Select Data Value: "  + DataValue +" Object name : " + ObjectName +" Object type : " + ObjectType)
		println  "Select Data Value: "  + DataValue +" Object name : " + ObjectName +" Object type : " + ObjectType
		GetObjectLocator(ObjectType,ObjectName)
		try{
			WebUI.waitForElementPresent(ObjectLocator, 10)
			WebUI.click(ObjectLocator)
			GetObjectLocator("DropdownItem",DataValue)
			WebUI.click(ObjectLocator)
			//WebUI.selectOptionByValue(ObjectLocator, DataValue, true)
		}
		catch(Exception e){

			KeywordUtil.logInfo('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}


	@Then("I validate the text displayed for (.*)")
	def I_validate_text(String Object) {
		WebUI.comment("I need to validate the text: "  + Object)
		println  "I need to validate the text: "  + Object
		GetObjectLocator("textelement",Object)
		try{
			WebUI.waitForElementPresent(ObjectLocator, 10)
			def textFieldValue = WebUI.getText(ObjectLocator)
			WebUI.takeFullPageScreenshotAsCheckpoint('current_viewport')
			println "Expected Text found : " + textFieldValue
		}

		catch(Exception e){
			KeywordUtil.logInfo('Html element can not be found, It has been changed or not available anymore. Try modifying the test script')

			throw e
		}
	}

	@Then("Verify the SalesApp API")
	def verify_the_salesapp() {
		WS.sendRequest(findTestObject('SalesAppAPI'))
	}


	def GetObjectLocator(String ObjectType, String ObjectName) {

		switch(ObjectType.toLowerCase()) {
			case "textbox":
				ObjectLocator = findTestObject('1. Generic/WebTextBox', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "button":
				ObjectLocator = findTestObject('1. Generic/WebButton', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "link":
				ObjectLocator = findTestObject('1. Generic/WebLink', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "dropdown":
				ObjectLocator = findTestObject('1. Generic/WebDropdown', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "dropdownitem":
				ObjectLocator = findTestObject('1. Generic/WebDropdownItem', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "textarea":
				ObjectLocator = findTestObject('1. Generic/WebTextArea', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "textelement":
				ObjectLocator = findTestObject('1. Generic/WebTextElement', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "radiobutton":
				ObjectLocator = findTestObject('1. Generic/WebRadioButton', [('ObjectName') : ObjectName.toLowerCase()])
				break
			case "default":
				ObjectLocator = findTestObject('1. Generic/WebDefault', [('ObjectName') : ObjectName.toLowerCase()])
				break
		}
		println "Created an object for " + ObjectType + " and Object Name : " + ObjectName + " Object is: " + ObjectLocator.toString()
	}
}