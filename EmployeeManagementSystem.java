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
		
		ArrayList<Employee> employees = readEmployees();
		
		countEmployees(employees);
//	
//		Scanner scan = new Scanner(System.in);
//		scan.close();
//		
		Employee newEmployee = new Employee(Department.HUMAN_RESOURCES, 44000, "Maddie", "Sherman", 147852);
//		
//		updateSalary(employees, 123456, 62000);
//		
//		updateDepartment(employees, 135790, Department.MANAGEMENT);
		
//		removeEmployee(employees, 134679);
		
		employees.add(newEmployee);

	}
	
	public static void initializeEmployees() {

		File file = new File("resources/employees.txt");
		
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
			
			ArrayList<Employee> employees = new ArrayList<Employee>();
			employees.add(employee01);
			employees.add(employee02);
			employees.add(employee03);
			employees.add(employee04);
			employees.add(employee05);
			employees.add(employee06);
			employees.add(employee07);
			employees.add(employee08);
			employees.add(employee09);
			
			writer.writeObject(employees);

		} catch(IOException e) {
			System.out.println("System did not add employees to file");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Employee> readEmployees() {
		
		File file = new File("resources/employees.data");
		
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))){
			
			@SuppressWarnings("unchecked")
			List<Employee> employees = (List<Employee>) reader.readObject();
			
			for(Employee employee : employees) {
				System.out.println(employee.toString());
			}
			
			return employees;
			
		} catch(IOException e) {
			e.printStackTrace();
			ArrayList<Employee> employees = new ArrayList<Employee>();
			return employees;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Employee> employees = new ArrayList<Employee>();
			return employees;
		}
	}
	
	public static void writeToEmployees(ArrayList<Employee> employees) {
		File file = new File("resources/employees.txt");
	}
	
	public static void updateSalary(ArrayList<Employee> employees, int id, int salary) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setSalary(salary);
			}
		}
	}
	
	public static void updateDepartment(ArrayList<Employee> employees, int id, Department department) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setDepartment(department);
			}
		}
	}
	
	public static void removeEmployee(ArrayList<Employee> employees, int id) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				System.out.println("found employee");
				employees.remove(i);
			}
		}
	}
	
	public static void countEmployees(ArrayList<Employee> employees) {
		
		long count = employees.stream().distinct().count();
		System.out.println(count);
	}

}
