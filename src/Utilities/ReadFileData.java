package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;

public class ReadFileData {
	//public static String localUIPath = System.getProperty("user.dir") + "/UIElements/";
	public static String localTestDataPath = System.getProperty("user.dir") + "/TestData/";
	
	public static String TestDataFile 	= localTestDataPath + "TestData.properties";
	//public static String UIElementsFile = localUIPath + "UIElements.properties";
	
	public static Properties readDataFile (){
		File file = new File(TestDataFile);
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static Properties readDataFile (String[] listOfFiles){
		//Consolidated Properties
		Properties prop = new Properties();
		//Temporary Properties Placeholder
		Properties tempProp;
		
		File file;
		for (String filename : listOfFiles) {
			tempProp = new Properties();
			file = new File(localTestDataPath + filename);
			  
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);

				//load properties file
				try {
					tempProp.load(fileInput);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					//Close FileInputStream
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//Add all entry to main properties
			prop.putAll(tempProp);
		}
		
		//Add Default to main properties
		prop.putAll(readDataFile());
		
		return prop;
	}
	


}
