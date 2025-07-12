package propertiesFileReader

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.lang.String
import pageManagers.FileReaderManager

import internal.GlobalVariable

public class ExcelFileRead {

	def String ExcelRead(int rowNum, int cellNum) {
		FileInputStream file = new FileInputStream (new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('ExcelFileUrl')))
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		Cell cell = sheet.getRow(rowNum).getCell(cellNum);
		String Data_fromCell = formatter.formatCellValue(cell);
		//'Read data from excel'
		//String Data_fromCell=sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
		//println (Data_fromCell)


		file.close();
		return Data_fromCell
	}

	def String ExcelReadMessages(int rowNum, int cellNum) {
		FileInputStream file = new FileInputStream (new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('ExcelFileUrl')))
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(1);
		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		Cell cell = sheet.getRow(rowNum).getCell(cellNum);
		String Data_fromCell = formatter.formatCellValue(cell);
		//'Read data from excel'
		//String Data_fromCell=sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
		//println (Data_fromCell)


		file.close();
		return Data_fromCell
	}
	def String ExcelReadSheetInfo(int sheetNum, int rowNum, int cellNum){
		FileInputStream file = new FileInputStream (new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('ExcelFileUrl')))
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		Cell cell = sheet.getRow(rowNum).getCell(cellNum);
		String Data_fromCell = formatter.formatCellValue(cell);
		//'Read data from excel'
		//String Data_fromCell=sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
		//println (Data_fromCell)


		file.close();
		return Data_fromCell
	}


	def ExcelWrite(int pageLoadRowNum, int pageLoadcellNum, String pageLoadTime, int rowNum, int cellNum,String value, String SheetName) {

		FileInputStream file = new FileInputStream (new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('TestOutputExcelFileUrl')))
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		//FileOutputStream fileOut = new FileOutputStream("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('TestOutputExcelFileUrl'))


		XSSFSheet sheet
		//sheet = workbook.cl
		for(int i=workbook.getNumberOfSheets()-1;i>=0;i--){
			XSSFSheet tmpSheet =workbook.getSheetAt(i);
			if(tmpSheet.getSheetName().equals(SheetName)){
				workbook.removeSheetAt(i);
			}
		}
		sheet = workbook.createSheet(SheetName)

		//'Write data to excel'
		sheet.createRow(pageLoadRowNum).createCell(pageLoadcellNum).setCellValue(pageLoadTime);
		sheet.createRow(rowNum).createCell(cellNum).setCellValue(value);
		//file.close();
		FileOutputStream outFile =new FileOutputStream(new File("${RunConfiguration.getProjectDir()}" + FileReaderManager.propertyReaderPage.prop('TestOutputExcelFileUrl')));
		//XSSFWorkbook workbook = new XSSFWorkbook(file);
		//XSSFSheet sheet = workbook.getSheetAt(0);
		workbook.write(outFile);
		//workbook.write(fileOut)
		outFile.close();
		file.close();
	}
}
