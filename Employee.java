package com.cognixia.JUMP.intermediateJava.employeemanagementsystem;

import java.io.Serializable;

public class Employee extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5407993219272094642L;
	
	private Department department;
	private int salary;
	private int id;
	
	public Employee(Department department, int salary, String firstName, String lastName, int id) {
		super(firstName, lastName);
		this.department = department;
		this.salary = salary;
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [department=" + department + ", Salary=" + salary + ", firstName=" + firstName + ", lastName="
				+ lastName + ", id=" + id + "]";
	}	
	
}
