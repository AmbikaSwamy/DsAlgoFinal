package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class DataFileHandling 
{	
	File XLFileName;
	Workbook XLWorkBook;
	Sheet shtWorkSheet;

	String sURL = "";
	String sHomePage = "";
	String sLoginPage = "";
	String sUserRegisterPage = "";
	
	String sExcelFilePath = "";
	String sLoginSheetName = "";
	String sRegisterUserSheetName = "";
	
	int iRowStart;
	
	WebPageConstants objWebPageConstants;
	
	public DataFileHandling()
	{
		objWebPageConstants = new WebPageConstants();
	}
	
	public String getExcelFilePath()
	{
		sExcelFilePath = objWebPageConstants.ExcelFile_Path;
		return sExcelFilePath;
	}
	
	@SuppressWarnings("resource")
	public void setXLWorkBook(File XLFile) throws InvalidFormatException, IOException
	{
		XLWorkBook = new XSSFWorkbook(XLFile);
	}
	
	public Workbook getXLWorkBook()
	{
		return XLWorkBook;
	}
	
	public Sheet getXLWorkSheet(Workbook wbXLWorkBook, String sSheetName)
	{
		shtWorkSheet = wbXLWorkBook.getSheet(sSheetName);
		return shtWorkSheet;
	}
	
	public String getLoginSheetName()
	{
//		sLoginSheetName = myProp.getProperty("LoginDataSheet");
		sLoginSheetName = objWebPageConstants.SignIn_XL_DataSheet;
		return sLoginSheetName;
	}
	
	/*
	public String getUserRegisterSheetName()
	{
		sRegisterUserSheetName = myProp.getProperty("RegisterUser");
		return sRegisterUserSheetName;
	}
	*/ 
	
	public Row setRowStart(Sheet shtWorkSheet, int intRowStart)
	{
		return shtWorkSheet.getRow(intRowStart); // Sets the start of the row to user-specified number.
	}
	
	public Row setRowStart(Sheet shtWorkSheet)
	{
		return shtWorkSheet.getRow(1);				// Defaults the row start number to 1.
	}
	
	public void ReadConfigFileParams()
	{
		/*
		 * String sMethodName = new Throwable().getStackTrace()[0].getMethodName(); 
		 * try
		 * { 
		 * 		myProp = new Properties();
		 * 
		 * 		ConfigFileName = new File(System.getProperty("user.dir") +
		 * 						("\\src\\test\\resources\\config\\config.properties")); 
		 * 		myConfigFileIPStream = new FileInputStream(ConfigFileName);
		 * 
		 * 		myProp.load(myConfigFileIPStream); 
		 * }
		 * 
		 * catch(Exception E) 
		 * { 
		 * 		LoggerLoad.ErrorMsg("Class Name: " +
		 * 								this.getClass().getSimpleName() + " -> Method: " + sMethodName +
		 * 								" -> Error: Unable to open the 'CONFIG.properties' file !!"); 
		 * }
		 */
	}
}
