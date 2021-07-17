package com.cognixia.JUMP.intermediateJava.employeemanagementsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Would you like to initialize employees file? ('yes' or 'no')");
		String initialize = scan.nextLine();
		initialize = initialize.trim();
		initialize = initialize.toLowerCase();
		
		switch(initialize) {
		case "yes":
			initializeEmployees();
			
			List<Employee> employeesInit = readEmployees();

			writeToCSVFile(employeesInit);
			break;
		case "no":
			break;
		default:
			break;
		}
		
		boolean keepEditingBool = false;
		
		List<Employee> employees = readFromCSV();
		printEmployees(employees);
		System.out.println("\n");
		
		do {
			boolean printedDepartmentList = false;
			
			System.out.println("What would you like to do with Employee list?");
			System.out.println("1 : Add employee");
			System.out.println("2 : Print out employees");
			System.out.println("3 : Print Department List");
			System.out.println("4 : Update Salary");
			System.out.println("5 : Update Department");
			System.out.println("6 : Update first name");
			System.out.println("7 : Update last name");
			System.out.println("8 : Remove employee");
			System.out.println("9 : Count employees");
			int input = scan.nextInt();
			scan.nextLine();
			
			switch(input) {
			case 1:		// New employee
				System.out.println("What is the new employee's first name? (String)");
				String firstName = scan.nextLine();
				
				System.out.println("What is the new employee's last name? (String)");
				String lastName = scan.nextLine();
				
				System.out.println("What is the new employee's department? (enum)");
				Department department = Department.valueOf(scan.nextLine().trim().toUpperCase());
				
				System.out.println("What is the new employee's id? (int)");
				int id = scan.nextInt();
				
				System.out.println("What is the new employee's salary? (int)");
				int salary = scan.nextInt();
				scan.nextLine();
				
				Employee newEmployee = new Employee(department, salary, firstName, lastName, id);
				employees.add(newEmployee);
				
				break;
			case 2:		// Print employees
				break;
			case 3:		// Print department list
				System.out.println("What department would you like to look at? (enum)");
				Department departmentListDepartment = Department.valueOf(scan.nextLine().trim().toUpperCase());
				
				departmentEmployeeList(employees, departmentListDepartment);
				
				printedDepartmentList = true;

				break;

			case 4:		// Update Salary
				System.out.println("What's the id of the employee whose salary you would like to update? (int)");
				int updatedSalaryId = scan.nextInt();
				
				System.out.println("How much should their salary be? (int)");
				int updatedSalary = scan.nextInt();
				
				scan.nextLine();
				updateSalary(employees, updatedSalaryId, updatedSalary);
				
				break;
			case 5:		// Update Department
				System.out.println("What's the id of the employee whose department you would like to update? (int)");
				int updatedDepartmentId = scan.nextInt();
				scan.nextLine();

				System.out.println("What department do they now belong to? (enum)");
				Department updatedDepartment = Department.valueOf(scan.nextLine().trim().toUpperCase());
				
				updateDepartment(employees, updatedDepartmentId, updatedDepartment);

				break;
			case 6:		// Update First name
				System.out.println("What's the id of the employee whose first name you would like to update? (int)");
				int updatedFirstNameId = scan.nextInt();
				scan.nextLine();
				
				System.out.println("What should their first name be? (String)");
				String updatedFirstName = scan.nextLine();
				
				updateFirstName(employees, updatedFirstNameId, updatedFirstName);
				
				break;
			case 7:		// Update Last name
				System.out.println("What's the id of the employee whose last name you would like to update? (int)");
				int updatedLastNameId = scan.nextInt();
				scan.nextLine();
				
				System.out.println("What should their last name be? (String)");
				String updatedLastName = scan.nextLine();
				
				updateLastName(employees, updatedLastNameId, updatedLastName);
				
				break;
			case 8:		// Remove Employee
				System.out.println("What is the id of the employee you wish to remove? (int)");
				int toRemoveId = scan.nextInt();
				
				scan.nextLine();
				removeEmployee(employees, toRemoveId);
				
				break;
			case 9:		// Count employees
				countEmployees(employees);
				break;
			default:
				System.out.println("Please select an integer from 1-9");
				break;
			}
			
			if(!printedDepartmentList) {
				printEmployees(employees);	
			}
			
			System.out.println("\nWould you like to keep editing? ('yes' or 'no')");
			String keepEditing = scan.nextLine();
			keepEditing = keepEditing.trim();
			keepEditing = keepEditing.toLowerCase();
			
			switch(keepEditing) {
			case "yes":
				keepEditingBool = true;
				break;
			case "no":
				keepEditingBool = false;
				break;
			default:
				keepEditingBool = false;
				break;
				
			}
			
		} while(keepEditingBool);
		
		scan.close();
		
		writeToCSVFile(employees);
		
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

			Employee employee1 = new Employee(Department.ACCOUNTING, 50000, "Alexa", "Lee", 123456);
			Employee employee2 = new Employee(Department.HUMAN_RESOURCES, 63000, "Fern", "West", 987654);
			Employee employee3 = new Employee(Department.MANAGEMENT, 74000, "Constance", "Lin", 246801);
			Employee employee4 = new Employee(Department.MARKETING, 43000, "Daniella", "McCarthy", 135790);
			Employee employee5 = new Employee(Department.OPERATIONS, 47000, "Connie", "Porter", 124578);
			Employee employee6 = new Employee(Department.PRODUCTION, 60000, "Flora", "Harris", 235689);
			Employee employee7 = new Employee(Department.PURCHASING, 54000, "Rhea", "Parsons", 134679);
			Employee employee8 = new Employee(Department.RESEARCH_AND_DEVELOPMENT, 71000, "Hannah", "Campos", 125678);
			Employee employee9 = new Employee(Department.SALES, 53000, "Esme", "Hammond", 234589);
			
			List<Employee> employees = new ArrayList<Employee>();
			employees.add(employee1);
			employees.add(employee2);
			employees.add(employee3);
			employees.add(employee4);
			employees.add(employee5);
			employees.add(employee6);
			employees.add(employee7);
			employees.add(employee8);
			employees.add(employee9);
			
			writer.writeObject(employees);

		} catch(IOException e) {
			System.out.println("System did not add employees to file");
			e.printStackTrace();
		}
	}
	
	public static List<Employee> readEmployees() {
		
		File file = new File("resources/employees.txt");
		
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))){
			
			@SuppressWarnings("unchecked")
			List<Employee> employeesReceived = (List<Employee>) reader.readObject();
			List<Employee> employees = new ArrayList<Employee>(employeesReceived);
			
			return employees;
			
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Could not read employees.txt");
			List<Employee> employees = new ArrayList<Employee>();
			return employees;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not read employees.txt");
			List<Employee> employees = new ArrayList<Employee>();
			return employees;
		}
	}
	
	public static void writeToEmployees(List<Employee> employees) {
		File file = new File("resources/employees.txt");
		
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))){
			writer.writeObject(employees);
		} catch(IOException e) {
			System.out.println("System did not update employees file");
			e.printStackTrace();
		}
	}
	
	public static void printEmployees(List<Employee> employees) {
		for(Employee employee : employees) {
			System.out.println(employee.toString());
		}
	}
	
	public static void updateSalary(List<Employee> employees, int id, int salary) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setSalary(salary);
				break;
			}
		}
	}
	
	public static void updateDepartment(List<Employee> employees, int id, Department department) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setDepartment(department);
				break;
			}
		}
	}
	
	public static void updateFirstName(List<Employee> employees, int id, String firstName) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setFirstName(firstName);
				break;
			}
		}
	}
	
	public static void updateLastName(List<Employee> employees, int id, String lastName) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.get(i).setLastName(lastName);
				break;
			}
		}
	}
	
	public static void removeEmployee(List<Employee> employees, int id) {
		for(int i = 0; i < employees.size(); i++) {
			if(employees.get(i).getId() == id) {
				employees.remove(i);
				break;
			}
		}
	}
	
	public static void countEmployees(List<Employee> employees) {
		long count = employees.stream().distinct().count();
		System.out.println("There are " + count + " employees\n");
	}

	public static void writeToCSVFile(List<Employee> employees) {
		
		List<List<String>> employeeCSV = new ArrayList<List<String>>();
		for(int i = 0; i < employees.size(); i++ ) {
			List<String> employeeAttributes = Arrays.asList(employees.get(i).getDepartment().toString(),
					String.valueOf(employees.get(i).getSalary()),
					employees.get(i).getFirstName(),
					employees.get(i).getLastName(),
					String.valueOf(employees.get(i).getId()));
			employeeCSV.add(employeeAttributes);
		}
		
		File csvFile = new File("resources/employees.csv");
		
		try {
			if(!csvFile.exists()) {
				csvFile.createNewFile();
			}
		} catch(IOException e) {
			System.out.println("System did not create file");
			e.printStackTrace();
		}
			
		FileWriter csvWriter = null;
		
		try {
			csvWriter = new FileWriter(csvFile);
			csvWriter.append("Department,Salary,FirstName,LastName,Id\n");
			
			for(List<String> row : employeeCSV) {
				csvWriter.append(String.join(",", row));
				csvWriter.append("\n");
			}
		} catch (IOException e) {
			System.out.println("Did not write to csv file");
			e.printStackTrace();
		} finally {
			if(csvWriter != null ) {
				try {
					csvWriter.close();
				} catch(IOException e) {
					System.out.println("Did not close csv writer");
					e.printStackTrace();
				}
			}
		}

	}
	
	public static List<Employee> readFromCSV() {
		File file = new File("resources/employees.csv");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find employees.csv");
			e.printStackTrace();
		}
		BufferedReader csvReader = new BufferedReader(fileReader);
		
		String line = "";
		List<Employee> employees = new ArrayList<Employee>();
		
		try {						// Skip header row
			csvReader.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			while((line = csvReader.readLine()) != null) {
				String[] employee = line.split(",");
				employees.add(new Employee(Department.valueOf(employee[0].trim().toUpperCase()),
						Integer.parseInt(employee[1]), employee[2], employee[3], Integer.parseInt(employee[4])));
			}
		} catch (IOException e) {
			System.out.println("Couldn't read employee line from csv file");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvReader.close();
			} catch(IOException e) {
				System.out.println("Did not close csv reader");
				e.printStackTrace();
			}
		}
		
		return employees;
	}
	
	public static void departmentEmployeeList(List<Employee> employees, Department department) {
		System.out.println(department + " Department List:");
		for(int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getDepartment().equals(department)) {
				System.out.println(employees.get(i).toString());
			}	
		}
		System.out.println("\n");
	}
}
