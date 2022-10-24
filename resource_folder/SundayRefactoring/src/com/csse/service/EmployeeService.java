package com.csse.service;

import com.csse.common.CommonConstants;

//import org.xml.sax.SAXException;

import com.csse.common.CommonProperties;
import com.csse.common.CommonUtil;
import com.csse.common.XSLTransformUtil;
import com.csse.model.Employee;

import java.io.IOException;
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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * The class Employee service extends common properties
 */
public class EmployeeService extends CommonProperties {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	private static EmployeeService uniqueInstance;

	public static final Logger log = Logger.getLogger(EmployeeService.class.getName());

	/**
	 *
	 * Connect to Database , and startEmployee service
	 *
	 * @return
	 */
	private EmployeeService() {

		try {
			Class.forName(CommonConstants.DRIVER_NAME);
			connection = DriverManager.getConnection(CommonConstants.URL, CommonConstants.USER_NAME,
					CommonConstants.USER_PASSWORD);

			log.log(Level.INFO, "Database Connection Success");
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Get Singleton Object
	 * 
	 * @return
	 */
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

	/**
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
	 * This method read EmployeeResponce.xml and load data into ArrayList of
	 * employee objects
	 * 
	 * @throws NumberFormatException
	 *             - Thrown to indicate that the application has attempted to
	 *             convert a string to one of the numeric types
	 * @throws XPathExpressionException
	 *             - Thrown when error in an Xpath expression
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws XPathExpressionException
	 *             - Indicate a serious configuration error
	 */
	public void setEmployeesToArrayList() {
		try {
			for (Map<String, String> employeeOutPutMap : XSLTransformUtil.readXMLPathValues()) {
				Employee employee = new Employee();
				employee.setEmployeeID(employeeOutPutMap.get(CommonConstants.XPATH_EMPLOYEE_KEY_ID));
				employee.setFullName(employeeOutPutMap.get(CommonConstants.XPATH_EMPLOYEE_NAME_KEY));
				employee.setAddress(employeeOutPutMap.get(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY));
				employee.setFacultyName(employeeOutPutMap.get(CommonConstants.XPATH_FACULTY_NAME_KEY));
				employee.setDepartment(employeeOutPutMap.get(CommonConstants.XPATH_DEPARTMENT_KEY));
				employee.setDesignation(employeeOutPutMap.get(CommonConstants.XPATH_DESIGNATON_KEY));

				employeeList.add(employee);
				log.info(employee.toString() + "\n");
			}
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * This method drop table if exists and create EmployeeTable.
	 * 
	 * @throws NumberFormatException
	 *             - Thrown to indicate that the application has attempted to
	 *             convert a string to one of the numeric types
	 * @throws XPathExpressionException
	 *             - Thrown when error in an Xpath expression
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws XPathExpressionException
	 *             - Indicate a serious configuration error
	 */
	public void createEmployeeTable() {

		try {
			statement = connection.createStatement();
			statement.executeUpdate(CommonUtil.getEmployeeQueries(CommonConstants.DROP_TABLE_IF_EXIST_EMPLOYEE));
			statement.executeUpdate(CommonUtil.getEmployeeQueries(CommonConstants.CREATE_TABLE_EMPLOYEE));

		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * This method create Employee record in the Employee Table.
	 * 
	 * @throws NumberFormatException
	 *             - Thrown to indicate that the application has attempted to
	 *             convert a string to one of the numeric types
	 * @throws XPathExpressionException
	 *             - Thrown when error in an Xpath expression
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws XPathExpressionException
	 *             - Indicate a serious configuration error
	 */
	public void createEmployee() {

		try {
			preparedStatement = connection
					.prepareStatement(CommonUtil.getEmployeeQueries(CommonConstants.INSERT_EMPLOYEE));
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
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * This method retrieve Employee record from the Employee Table according to
	 * EmployeeID.
	 * 
	 * @param eid
	 *            => employee ID
	 * 
	 * @throws NumberFormatException
	 *             - Thrown to indicate that the application has attempted to
	 *             convert a string to one of the numeric types
	 * @throws XPathExpressionException
	 *             - Thrown when error in an Xpath expression
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws XPathExpressionException
	 *             - Indicate a serious configuration error
	 */
	public void getEmployeeById(String eid) {

		Employee employee = new Employee();
		try {
			preparedStatement = connection
					.prepareStatement(CommonUtil.getEmployeeQueries(CommonConstants.GET_EMPLOYEE));
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
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * This method delete Employee record from the Employee Table according to
	 * EmployeeID.
	 * 
	 * @param eid
	 *            => employee ID
	 * 
	 * @throws NumberFormatException
	 *             - Thrown to indicate that the application has attempted to
	 *             convert a string to one of the numeric types
	 * @throws XPathExpressionException
	 *             - Thrown when error in an Xpath expression
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws XPathExpressionException
	 *             - Indicate a serious configuration error
	 */
	public void deleteEmployeeById(String eid) {

		try {
			preparedStatement = connection
					.prepareStatement(CommonUtil.getEmployeeQueries(CommonConstants.DELETE_EMPLOYEE));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * This method retrieve All Employee records from the Employee Table.
	 * 
	 * @param eid
	 *            => employee ID
	 * 
	 * @throws NumberFormatException
	 *             - Thrown to indicate that the application has attempted to
	 *             convert a string to one of the numeric types
	 * @throws XPathExpressionException
	 *             - Thrown when error in an Xpath expression
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws XPathExpressionException
	 *             - Indicate a serious configuration error
	 */
	public void getAllEmployees() {

		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		try {
			preparedStatement = connection
					.prepareStatement(CommonUtil.getEmployeeQueries(CommonConstants.GET_ALL_EMPLOYEES));
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
		} catch (NumberFormatException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		printEmployeeDetails(employeeList);
	}

	/**
	 * This method display Employee records from the Employee Table.
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
