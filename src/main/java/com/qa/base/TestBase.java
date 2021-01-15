package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;	
	public TestBase() throws IOException
	{
		try {
			
			prop= new Properties();
			FileInputStream ip= new FileInputStream("/home/vishnu/Documents/Trupti_Test_Automation/Workspace/RestApi/src/main/java/com/qa/config/config.properties");//System.getProperty("user.dir")+"");
			prop.load(ip);
			
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		    }
			catch(IOException e) 
			{
			e.printStackTrace();
			}		
	}

}
