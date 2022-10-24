package com.csse.service;

//import org.xml.sax.SAXException;

import com.csse.common.CommonProperties;
import com.csse.common.CommonUtil;
import com.csse.common.XSLTransformUtil;
import com.csse.model.Employee;

import java.sql.Connection;
//import java.util.logging.Logger;
import java.sql.DriverManager;
//import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
//import javax.xml.xpath.XPathExpressionException;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLException;
//import java.util.logging.Level;
import java.sql.Statement;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class Employee service extends common properties
 */
public class EmployeeService extends CommonProperties {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	private static EmployeeService uniqueInstance;

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

	public static final Logger log = Logger.getLogger(EmployeeService.class.getName());

	/**
	 *
	 * Employee service
	 *
	 * @return
	 */
	private EmployeeService() {

		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);

			log.log(Level.INFO, "Database Connection Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Singleton Object

	public static EmployeeService getInstance() {
		if (uniqueInstance == null) {
			synchronized (EmployeeService.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new EmployeeService();
				}
			}
		}
		return uniqueInstance;
	}

	/*
	 * 
	 * Execute Employee Service methods according to template pattern
	 *
	 */
	public final void executeEmployeeServiceMethods() {
		setEmployeesToArrayList();
		createEmployeeTable();
		createEmployee();
		getAllEmployees();
	}

	/**
	 *
	 * Sets the employees to array list
	 *
	 */
	public void setEmployeesToArrayList() {

		try {
			int statement = XSLTransformUtil.XMLXPATHS().size();
			for (int i = 0; i < statement; i++) {
				Map<String, String> element = XSLTransformUtil.XMLXPATHS().get(i);
				Employee EMPLOYEE = new Employee();
				EMPLOYEE.setEmployeeID(element.get("XpathEmployeeIDKey"));
				EMPLOYEE.setFullName(element.get("XpathEmployeeNameKey"));
				EMPLOYEE.setAddress(element.get("XpathEmployeeAddressKey"));
				EMPLOYEE.setFacultyName(element.get("XpathFacultyNameKey"));
				EMPLOYEE.setDepartment(element.get("XpathDepartmentKey"));
				EMPLOYEE.setDesignation(element.get("XpathDesignationKey"));
				employeeList.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * Create employee table
	 *
	 */
	public void createEmployeeTable() {

		try {
			statement = connection.createStatement();
			statement.executeUpdate(CommonUtil.getEmployeeQueries(DROP_TABLE_IF_EXIST_EMPLOYEE));
			statement.executeUpdate(CommonUtil.getEmployeeQueries(CREATE_TABLE_EMPLOYEE));

		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * Create employee
	 *
	 */
	public void createEmployee() {

		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries(INSERT_EMPLOYEE));
			connection.setAutoCommit(false);
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				preparedStatement.setString(1, employee.getEmployeeID());
				preparedStatement.setString(2, employee.getFullName());
				preparedStatement.setString(3, employee.getAddress());
				preparedStatement.setString(4, employee.getFacultyName());
				preparedStatement.setString(5, employee.getDepartment());
				preparedStatement.setString(6, employee.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * Gets the employee by identifier
	 *
	 * @param eid
	 *            the employee ID
	 * 
	 */
	public void getEmployeeById(String eid) {

		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries(GET_EMPLOYEE));
			preparedStatement.setString(1, eid);
			ResultSet R = preparedStatement.executeQuery();
			while (R.next()) {
				employee.setEmployeeID(R.getString(1));
				employee.setFullName(R.getString(2));
				employee.setAddress(R.getString(3));
				employee.setFacultyName(R.getString(4));
				employee.setDepartment(R.getString(5));
				employee.setDesignation(R.getString(6));
			}
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			printEmployeeDetails(employeeList);
		} catch (SQLException ex) {
			log.log(Level.SEVERE, ex.getMessage());
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}
	}

	/**
	 *
	 * Delete employee by identifier
	 *
	 * @param eid
	 *            the employee ID
	 */
	public void deleteEmployeeById(String eid) {

		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries(DELETE_EMPLOYEE));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		}catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 *
	 * Gets the all employees
	 *
	 */
	public void getAllEmployees() {

		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries(GET_ALL_EMPLOYEES));
			ResultSet r = preparedStatement.executeQuery();
			while (r.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(r.getString(1));
				employee.setFullName(r.getString(2));
				employee.setAddress(r.getString(3));
				employee.setFacultyName(r.getString(4));
				employee.setDepartment(r.getString(5));
				employee.setDesignation(r.getString(6));
				employeeList.add(employee);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		printEmployeeDetails(employeeList);
	}

	/**
	 *
	 * Print employee details
	 *
	 * @param employeeList
	 *            the employee list
	 */
	public void printEmployeeDetails(ArrayList<Employee> employeeList) {

		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out.println(
				"================================================================================================================");
		for (int i = 0; i < employeeList.size(); i++) {
			Employee employee = employeeList.get(i);
			System.out.println(employee.getEmployeeID() + "\t" + employee.getFullName() + "\t\t" + employee.getAddress()
					+ "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------");
		}

	}
}
