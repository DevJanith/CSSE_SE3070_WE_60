package com.csse.common;


//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.io.IOException;
import java.util.Properties;


public class CommonProperties {

	public static final Properties properties = new Properties();

	static {
		try {
			properties.load(CommonUtil.class.getResourceAsStream("../config/config.properties"));
		} catch (Exception e) {
			
		}
	}
}
