package com.csse.common;

import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonProperties {

	public static final Properties properties = new Properties();

	public static final Logger log = Logger.getLogger(CommonProperties.class.getName());

	static {

		try {
			properties.load(CommonUtil.class.getResourceAsStream("../config/config.properties"));
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}

	}
}
