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
//import java.sql.SQLException;
//import java.util.logging.Level;
import java.sql.Statement;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class EmployeeService extends CommonProperties {

	private final ArrayList<Employee> el = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	public EmployeeService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
		} catch (Exception e) {
		}
	}

	public void setEmployeesToArrayList() {

		try {
			int statement = XSLTransformUtil.XMLXPATHS().size();
			for (int i = 0; i < statement; i++) {
				Map<String, String> l = XSLTransformUtil.XMLXPATHS().get(i);
				Employee EMPLOYEE = new Employee();
				EMPLOYEE.setEmployeeID(l.get("XpathEmployeeIDKey"));
				EMPLOYEE.setFullName(l.get("XpathEmployeeNameKey"));
				EMPLOYEE.setAddress(l.get("XpathEmployeeAddressKey"));
				EMPLOYEE.setFacultyName(l.get("XpathFacultyNameKey"));
				EMPLOYEE.setDepartment(l.get("XpathDepartmentKey"));
				EMPLOYEE.setDesignation(l.get("XpathDesignationKey"));
				el.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void createEmployeeTable() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(CommonUtil.getEmployeeQueries("q2"));
			statement.executeUpdate(CommonUtil.getEmployeeQueries("q1"));
		} catch (Exception e) {
		}
	}

	public void createEmployee() {
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q3"));
			connection.setAutoCommit(false);
			for (int i = 0; i < el.size(); i++) {
				Employee e = el.get(i);
				preparedStatement.setString(1, e.getEmployeeID());
				preparedStatement.setString(2, e.getFullName());
				preparedStatement.setString(3, e.getAddress());
				preparedStatement.setString(4, e.getFacultyName());
				preparedStatement.setString(5, e.getDepartment());
				preparedStatement.setString(6, e.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
		}
	}

	public void getEmployeeById(String eid) {

		Employee e = new Employee();
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q4"));
			preparedStatement.setString(1, eid);
			ResultSet R = preparedStatement.executeQuery();
			while (R.next()) {
				e.setEmployeeID(R.getString(1));
				e.setFullName(R.getString(2));
				e.setAddress(R.getString(3));
				e.setFacultyName(R.getString(4));
				e.setDepartment(R.getString(5));
				e.setDesignation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(e);
			printEmployeeDetails(l);
		} catch (Exception ex) {
		}
	}

	public void deleteEmployeeById(String eid) {

		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q6"));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAllEmployees() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(CommonUtil.getEmployeeQueries("q5"));
			ResultSet r = preparedStatement.executeQuery();
			while (r.next()) {
				Employee e = new Employee();
				e.setEmployeeID(r.getString(1));
				e.setFullName(r.getString(2));
				e.setAddress(r.getString(3));
				e.setFacultyName(r.getString(4));
				e.setDepartment(r.getString(5));
				e.setDesignation(r.getString(6));
				l.add(e);
			}
		} catch (Exception e) {
		}
		printEmployeeDetails(l);
	}

	public void printEmployeeDetails(ArrayList<Employee> l) {

		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out.println(
				"================================================================================================================");
		for (int i = 0; i < l.size(); i++) {
			Employee e = l.get(i);
			System.out.println(e.getEmployeeID() + "\t" + e.getFullName() + "\t\t" + e.getAddress() + "\t"
					+ e.getFacultyName() + "\t" + e.getDepartment() + "\t" + e.getDesignation() + "\n");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------");
		}

	}
}
