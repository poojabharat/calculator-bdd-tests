package report

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

import globalvariables.GlobalVariables


public class Report {

	public static void generateReport(){
		def now = new Date()
		String currentDate = now.format("YYYY-MM-dd-HH-mm-ss")

		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\katalon_report\\" + currentDate + ".html"));
		StringBuffer strBufferReport = new StringBuffer();

		strBufferReport.append(
				"<html><head><script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		strBufferReport.append("</head><body>");
		strBufferReport.append("<table class=width100>");
		strBufferReport.append("<tr><td>");
		strBufferReport.append("<table class=width100>");
		strBufferReport.append("<tr><td></td>"
				+ "<td class=\"headertext\"></td>");
		/*strBufferReport.append("<tr><td><img src=file://" + logoPath + "></img></td>"
		 + "<td class=\"headertext\">" + Vars.getReportHeader() + "</td>");*/
		/*strBufferReport.append("<tr><td><img src=" + Constant.espireLogo + "></img></td>"
		 + "<td class=\"headertext\">" + Vars.getReportHeader() + "</td>");*/

		strBufferReport.append("</tr></table><hr></hr>");
		strBufferReport.append("</td></tr>");

		//<tr><td><table>

		strBufferReport.append(
				"<tr><td><table><tr><td><div class=headertext1>Test Execution Detail Report</div></td></tr>");
		strBufferReport.append("<tr><th class=auto-style2>Action</th>"
				+ "<th class=auto-style4>Execution Result</th>"
				+ "<th class=auto-style5>Test Step</th>"
				+ "<th class=auto-style7>Status</th></tr>");

		try {
			for (ReportBean reportBean : GlobalVariables.getReportList()) {
				System.out.println(reportBean.reportAction);

				strBufferReport.append("<tr>"
						+ "<td class=auto-style2>" + reportBean.getReportAction() // Constant.Vars.action
						+ "</td><td class=auto-style3>" + reportBean.getExecutionResult() // Constant.Vars.obj
						// =
						// Constant.Vars.objprop
						+ "</td><td class=auto-style4>" + reportBean.getReportTestStepID() // Constant.Vars.events
						// =
						// Constant.Vars.Tesdata
						+ "</td><td class=auto-style5>" + reportBean.getExecutionStatus() // TestStep
						// SpiraTest
						+ "</td></tr>");
			}

			strBufferReport.append("</table></td></tr></table></body></html>");
			bufferedWriter.write(strBufferReport.toString());
			bufferedWriter.close();
			System.out.println("Report has been generated!!");
		} catch (Exception ex) {
			System.out.println("Exception in report " + ex.getMessage());
			//Log.error("Report will not be printed. Check the file path.  " + ex);
		}
	}
}
