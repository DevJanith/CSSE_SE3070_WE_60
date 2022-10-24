package com.csse.common;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csse.service.EmployeeService;

/**
 * The class Common properties
 */
public class CommonProperties {

	public static final Properties properties = new Properties();
	public static final String CONFIG_FILE_PATH = "../config/config.properties";
	public static final Logger log = Logger.getLogger(EmployeeService.class.getName());

	static {
		try {
			properties.load(CommonUtil.class.getResourceAsStream(CONFIG_FILE_PATH));
		} catch (Exception e) {
			
			log.log(Level.INFO, e.getMessage());

		}
	}
}
