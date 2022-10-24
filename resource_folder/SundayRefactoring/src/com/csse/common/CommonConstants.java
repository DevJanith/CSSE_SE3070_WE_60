package com.csse.common;

/**
 * This is the common consents file for Sunday Refactoring Project
 * 
 * @author Janith Gamage
 * @author Himaasha Ranaweera
 * @author Supun Dileepa
 * @author Ridma Dilshan
 * 
 * @version 1.0
 *
 */

public class CommonConstants extends CommonProperties {

	public static final String DRIVER_NAME = properties.getProperty("driverName");
	public static final String URL = properties.getProperty("url");
	public static final String USER_NAME = properties.getProperty("username");
	public static final String USER_PASSWORD = properties.getProperty("password");

	public static final String CREATE_TABLE_EMPLOYEE = "q1";
	public static final String DROP_TABLE_IF_EXIST_EMPLOYEE = "q2";
	public static final String INSERT_EMPLOYEE = "q3";
	public static final String GET_EMPLOYEE = "q4";
	public static final String GET_ALL_EMPLOYEES = "q5";
	public static final String DELETE_EMPLOYEE = "q6";

	public static final String XPATH_EMPLOYEE_KEY_ID = "XpathEmployeeIDKey";
	public static final String XPATH_EMPLOYEE_NAME_KEY = "XpathEmployeeNameKey";
	public static final String XPATH_EMPLOYEE_ADDRESS_KEY = "XpathEmployeeAddressKey";
	public static final String XPATH_FACULTY_NAME_KEY = "XpathFacultyNameKey";
	public static final String XPATH_DEPARTMENT_KEY = "XpathDepartmentKey";
	public static final String XPATH_DESIGNATON_KEY = "XpathDesignationKey";

}
