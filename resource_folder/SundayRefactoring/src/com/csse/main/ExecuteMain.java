package com.csse.main;

//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactoryConfigurationError;

import com.csse.common.XSLTransformUtil;
import com.csse.service.EmployeeService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			XSLTransformUtil.requestTransform();
			employeeService.setEmployeesToArrayList();
			employeeService.createEmployeeTable();
			employeeService.createEmployee();
			// employeeService.employeeGetById("EMP10004");
			// employeeService.employeeDelete("EMP10001");
			employeeService.getAllEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
