package com.csse.main;

import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactoryConfigurationError;

import com.csse.common.XSLTransformUtil;
import com.csse.service.EmployeeService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static final Logger log = Logger.getLogger(ExecuteMain.class.getName());
	
	public static void main(String[] args) {
		

//		EmployeeService employeeService = new EmployeeService();
		
		//	Get Singleton instance from employee service
		EmployeeService employeeService = EmployeeService.getInstance();
		
		try {
			XSLTransformUtil.requestTransform();
		/*	employeeService.setEmployeesToArrayList();
			employeeService.createEmployeeTable();
			employeeService.createEmployee();
			// employeeService.employeeGetById("EMP10004");
			// employeeService.employeeDelete("EMP10001");
			employeeService.getAllEmployees();*/
			
			
			//	Execute All methods according to template
			employeeService.executeEmployeeServiceMethods();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

	}

}
