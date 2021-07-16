package com.cognixia.JUMP.intermediateJava.employeemanagementsystem;

public class Employee {

	private Department department;
	private int Salary;
	private String firstName;
	private String lastName;
	private int id;
	
	public Employee(Department department, int salary, String firstName, String lastName, int id) {
		super();
		this.department = department;
		Salary = salary;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		Salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
