package com.cognixia.JUMP.intermediateJava.employeemanagementsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {

	public static void main(String[] args) {
		
		initializeEmployees();
		
		List<Employee> employees = readEmployees();
	
		Scanner scan = new Scanner(System.in);
		
		Employee newEmployee = new Employee(Department.HUMAN_RESOURCES, 44000, "Maddie", "Sherman", 147852);
		
		updateSalary(employees, 123456, 62000);
		
		updateDepartment(employees, 135790, Department.MANAGEMENT);
		
		removeEmployee(employees, 134679);
//		
//		employees.add(newEmployee);

	}
	
	public static void initializeEmployees() {

		File file = new File("resources/employees.data");
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch(IOException e) {
			System.out.println("System did not create file");
			e.printStackTrace();
		}
		
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))){

			Employee employee01 = new Employee(Department.ACCOUNTING, 50000, "Alexa", "Lee", 123456);
			Employee employee02 = new Employee(Department.HUMAN_RESOURCES, 63000, "Fern", "West", 987654);
			Employee employee03 = new Employee(Department.MANAGEMENT, 74000, "Constance", "Lin", 246801);
			Employee employee04 = new Employee(Department.MARKETING, 43000, "Daniella", "McCarthy", 135790);
			Employee employee05 = new Employee(Department.OPERATIONS, 47000, "Connie", "Porter", 124578);
			Employee employee06 = new Employee(Department.PRODUCTION, 60000, "Flora", "Harris", 235689);
			Employee employee07 = new Employee(Department.PURCHASING, 54000, "Rhea", "Parsons", 134679);
			Employee employee08 = new Employee(Department.RESEARCH_AND_DEVELOPMENT, 71000, "Hannah", "Campos", 125678);
			Employee employee09 = new Employee(Department.SALES, 53000, "Esme", "Hammond", 234589);
			
			List<Employee> employees = Arrays.asList(employee01, employee02, employee03, employee04, employee05, 
					employee06, employee07, employee08, employee09);
			
			writer.writeObject(employees);

		} catch(IOException e) {
			System.out.println("System did not add employees to file");
			e.printStackTrace();
		}
	}
	
	public static List<Employee> readEmployees() {
		
		File file = new File("resources/employees.data");
		
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))){
			
			List<Employee> employees = (List<Employee>) reader.readObject();
			
			for(Employee employee : employees) {
				System.out.println(employee.toString());
			}
			
			return employees;
			
		} catch(IOException e) {
			e.printStackTrace();
			List<Employee> employees = new ArrayList<Employee>();
			return employees;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			List<Employee> employees = new ArrayList<Employee>();
			return employees;
		}
	}
	
	public static void writeToEmployees(List<Employee> employees) {
		File file = new File("resources/employeesList.data");
	}
	
	public static void updateSalary(List<Employee> employees, int id, int salary) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setSalary(salary);
			}
		}
	}
	
	public static void updateDepartment(List<Employee> employees, int id, Department department) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setDepartment(department);
			}
		}
	}
	
	public static void removeEmployee(List<Employee> employees, int id) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				System.out.println("found employee");
				employees.remove(i);
			}
		}
	}

}
