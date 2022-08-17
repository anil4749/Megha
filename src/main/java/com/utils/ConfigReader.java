package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;
	
	//private Properies prop 
	

	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	public Properties init_prop() {
		
		//prop = new Properties();

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			System.out.println("Exception handle file not present");
			e.printStackTrace();
			
			
		} catch (IOException e) {
			System.out.println("file is present bu no content");
			e.printStackTrace();
		}

		return prop;  ///./src/test/resources/config/config.properties

	}

}
