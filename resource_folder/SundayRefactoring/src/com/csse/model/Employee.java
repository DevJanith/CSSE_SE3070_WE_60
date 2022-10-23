package com.csse.model;

/**
 * The class Employee
 */
public class Employee {

	public String employeeID;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;

	/**
	 *
	 * Gets the employee identifier
	 *
	 * @return the employee identifier
	 */
	public String getEmployeeID() {

		return employeeID;
	}

	/**
	 *
	 * Gets the full name
	 *
	 * @return the full name
	 */
	public String getFullName() {

		return fullName;
	}

	/**
	 *
	 * Gets the address
	 *
	 * @return the address
	 */
	public String getAddress() {

		return address;
	}

	/**
	 *
	 * Gets the faculty name
	 *
	 * @return the faculty name
	 */
	public String getFacultyName() {

		return facultyName;
	}

	/**
	 *
	 * Gets the department
	 *
	 * @return the department
	 */
	public String getDepartment() {

		return department;
	}

	/**
	 *
	 * Gets the designation
	 *
	 * @return the designation
	 */
	public String getDesignation() {

		return designation;
	}

	/**
	 *
	 * Sets the employee identifier
	 *
	 * @param employeeID
	 *            the employee identifier
	 */
	public void setEmployeeID(String employeeID) {

		this.employeeID = employeeID;
	}

	/**
	 *
	 * Sets the full name
	 *
	 * @param fullName
	 *            the full name
	 */
	public void setFullName(String fullName) {

		this.fullName = fullName;
	}

	/**
	 *
	 * Sets the address
	 *
	 * @param address
	 *            the address
	 */
	public void setAddress(String address) {

		this.address = address;
	}

	/**
	 *
	 * Sets the faculty name
	 *
	 * @param facultyName
	 *            the faculty name
	 */
	public void setFacultyName(String facultyName) {

		this.facultyName = facultyName;
	}

	/**
	 *
	 * Sets the department
	 *
	 * @param department
	 *            the department
	 */
	public void setDepartment(String department) {

		this.department = department;
	}

	/**
	 *
	 * Sets the designation
	 *
	 * @param designation
	 *            the designation
	 */
	public void setDesignation(String designation) {

		this.designation = designation;
	}

	// @Override
	// public String toString() {
	// return "Employee [employeeID=" + employeeID + ", fullName=" + fullName +
	// ", address=" + address
	// + ", facultyName=" + facultyName + ", department=" + department + ",
	// designation=" + designation + "]";
	// }

	@Override

	/**
	 *
	 * To string
	 *
	 * @return String
	 */
	public String toString() {

		return "Employee ID = " + employeeID + "\n" + "FullName = " + fullName + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}

}
