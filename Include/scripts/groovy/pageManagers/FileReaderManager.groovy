package pageManagers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import propertiesFileReader.ExcelFileRead
import propertiesFileReader.PropertyReader




public class FileReaderManager {

	static PropertyReader propertyReader
	static ExcelFileRead excelFileReader

	static def PropertyReader getPropertyReaderPage() {
		//propertyReader.prop("")
		//create the object only if it is null or supply the already created object if not null
		return (propertyReader == null) ? propertyReader = new PropertyReader() : propertyReader
	}


	static def ExcelFileRead getExcelFileRead() {

		return (excelFileReader == null) ? excelFileReader = new ExcelFileRead() : excelFileReader
	}
}
