package com.cognixia.JUMP.intermediateJava.employeemanagementsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EmployeeManagementSystem {

	public static void main(String[] args) {
		
		initializeEmployees();
		
//		Employee[] employees = new Employee[10];

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

			Employee employee1 = new Employee(Department.ACCOUNTING, 50000, "Alexa", "Lee", 123456);
			Employee employee2 = new Employee(Department.HUMAN_RESOURCES, 63000, "Fern", "West", 987654);
			Employee employee3 = new Employee(Department.MANAGEMENT, 74000, "Constance", "Lin", 246801);
			Employee employee4 = new Employee(Department.MARKETING, 43000, "Daniella", "McCarthy", 135790);
			Employee employee5 = new Employee(Department.OPERATIONS, 47000, "Connie", "Porter", 124578);
			Employee employee6 = new Employee(Department.PRODUCTION, 60000, "Flora", "Harris", 235689);
			Employee employee7 = new Employee(Department.PURCHASING, 54000, "Rhea", "Parsons", 134679);
			Employee employee8 = new Employee(Department.RESEARCH_AND_DEVELOPMENT, 71000, "Hannah", "Campos", 125678);
			Employee employee9 = new Employee(Department.SALES, 53000, "Esme", "Hammond", 234589);
			
			writer.writeObject(employee1);
			writer.writeObject(employee2);
			writer.writeObject(employee3);
			writer.writeObject(employee4);
			writer.writeObject(employee5);
			writer.writeObject(employee6);
			writer.writeObject(employee7);
			writer.writeObject(employee8);
			writer.writeObject(employee9);
			
		} catch(IOException e) {
			System.out.println("System did not add employees to file");
			e.printStackTrace();
		}
	}

}
