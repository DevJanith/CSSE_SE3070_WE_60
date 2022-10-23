package com.csse.model;

public class Employee {

	public String employeeID;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;

	public String getEmployeeID() {
		return employeeID;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAddress() {
		return address;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

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
	public String toString() {

		return "Employee ID = " + employeeID + "\n" + "FullName = " + fullName + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}

}
