package com.cognixia.jumplus.toplevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jumplus.classes.SchoolClass;
import com.cognixia.jumplus.classes.SchoolClass.ClassE;
import com.cognixia.jumplus.grades.StudentGrades;
import com.cognixia.jumplus.student.Student;
import com.cognixia.jumplus.teacher.Teacher;
import com.cognixia.jumplus.utils.ConsoleColors;
import com.cognixia.jumplus.utils.ConsoleScanner;




public class TopLevelUpdated {
	
	private static ArrayList<Teacher> teachers;
	private static ArrayList<Student> allStudents;
	private static Teacher currentUser;
	private static ArrayList<StudentGrades> gradebook;
	
	static {
		teachers = new ArrayList<>();
		allStudents = new ArrayList<>();
		allStudents.add(new Student("Jessica", "Mcneil"));
		allStudents.add(new Student("Natasha", "Sutherland"));
		allStudents.add(new Student("Alessia", "Klein"));
		allStudents.add(new Student("Erik", "Hilton"));
		allStudents.add(new Student("Crystal", "Bowen"));
		allStudents.add(new Student("Chester", "Hartman"));
		allStudents.add(new Student("Jon", "Cobb"));
		allStudents.add(new Student("Ayah", "Hogan"));
		allStudents.add(new Student("Robert", "Hale"));
		allStudents.add(new Student("Euan", "Richardson"));
		teachers.add(new Teacher("f","f", "Caruso"));
		teachers.get(0).getClasses().add(new SchoolClass(ClassE.SCIENCE));
		teachers.get(0).getClasses().get(0).students = (new ArrayList<Student>(Arrays.asList(allStudents.get(0), allStudents.get(1), allStudents.get(2))));;
		gradebook = new ArrayList<StudentGrades>();
		gradebook.add(new StudentGrades(ClassE.SCIENCE, allStudents.get(0).getStudentId(), 80));
		gradebook.add(new StudentGrades(ClassE.SCIENCE, allStudents.get(1).getStudentId(), 90));
		gradebook.add(new StudentGrades(ClassE.SCIENCE, allStudents.get(2).getStudentId(), 40));
	}
	

	public static void run() {
		while (true) {
			
				
				System.out.println(ConsoleColors.CYAN_BOLD + "+---------------------+");
				System.out.println("+------ Welcome ------+");
				System.out.println("+---------------------+" + ConsoleColors.RESET);
				System.out.println("1. Create account");
				System.out.println("2. Login");
				System.out.println("3. Exit program\n");
				System.out.print(ConsoleColors.ITALIC + "Choose an option (1-3): " + ConsoleColors.RESET);
				String choice = ConsoleScanner.getString();
				
				while (!choice.matches("^[1-3]$")) {
					System.out.println(ConsoleColors.RED + "Not a valid choice." + ConsoleColors.RESET);
					System.out.print(ConsoleColors.ITALIC + "Choose an option (1-3): "+ ConsoleColors.RESET);
					choice = ConsoleScanner.getString();
				}
				System.out.println();
				
				if (choice.equals("1")) {
					
					while(true) {
						
						System.out.println(ConsoleColors.CYAN_BOLD + "+---------------------+");
						System.out.println("+------ Register -----+");
						System.out.println("+---------------------+\n" + ConsoleColors.RESET);
						System.out.print("Enter your last name: ");
						String lastName = ConsoleScanner.getString();
						
						while (!lastName.matches("^\\w+$")) {
							System.out.println("Not a valid last name.");
							System.out.print("Enter your last name: ");
							lastName = ConsoleScanner.getString();
						}
						
						System.out.print("Enter a username: ");
						String username = ConsoleScanner.getString();
						
						while (!username.matches("^\\w+$")) {
							System.out.println("Not a valid username.");
							System.out.print("Enter a username: ");
							username = ConsoleScanner.getString();
						}
						
						System.out.print("Enter a password: ");
						String password = ConsoleScanner.getString();
						
						while (!password.matches("^.{1,}$")) {
							System.out.println("Not a valid password.");
							System.out.print("Enter a password: ");
							password = ConsoleScanner.getString();
						}
						
						if (getTeacherByUsername(username).isPresent()) 
							System.out.println(ConsoleColors.RED + "Username already taken" + ConsoleColors.RESET);
						
						else
							teachers.add(new Teacher(username, password, lastName));;
							break;
					}
				}
				
				else if (choice.equals("2")) {
					
					while (true) {
						
						System.out.println(ConsoleColors.CYAN_BOLD +"+---------------------+");
						System.out.println("+------- Login -------+");
						System.out.println("+---------------------+" + ConsoleColors.RESET);
						System.out.print("Username: ");
						String username = ConsoleScanner.getString();
						System.out.print("Password: ");
						String password = ConsoleScanner.getString();
						Optional<Teacher> loginAttemptOutcome = login(username, password);
						
						while (loginAttemptOutcome.isEmpty()) {
							
							System.out.println(ConsoleColors.RED + "Invalid credentials. Try again." + ConsoleColors.RESET);
							System.out.print("Username: ");
							username = ConsoleScanner.getString();
							System.out.print("Password: ");
							password = ConsoleScanner.getString();
							loginAttemptOutcome = login(username, password);
						}
						
						currentUser = loginAttemptOutcome.get();
						userSession();
						break;
					}
				}
				else {
					return;
				}
			
		}
	}
	
	//Session Functions
	private static void userSession() {
		printClassList();
		int option = 0;
		while(true) {
			System.out.println("1. Add a class.");
			System.out.println("2. Update a class.");
			System.out.println("3. View your classes.");
			System.out.println("4. Exit.");
			System.out.print(ConsoleColors.ITALIC + "Choose an option (1-4): " + ConsoleColors.RESET);

			try {
				option = ConsoleScanner.getInt();
				if (option < 1 || option > 4) {
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				option = 0;
				ConsoleScanner.getString();
				continue;
			}
			catch (Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				option = 0;
				continue;
			}
			System.out.println();
			
			switch(option) {

				case 1: 
				{ 
					addClass();
					break;
				}
				case 2: 
				{
					if(currentUser.getClasses().isEmpty()) {
						System.out.println(ConsoleColors.RED + "No classes to update" + ConsoleColors.RESET);
						break;
					}
					updateClass();
					break;
				}
				case 3: 
				{
					if(currentUser.getClasses().isEmpty()) {
						System.out.println(ConsoleColors.RED + "No classes to view" + ConsoleColors.RESET);
						break;
					}
					printClassList();
					break;
				}
				case 4: 
				{
					System.out.println(ConsoleColors.ITALIC + ConsoleColors.GREEN + "Goodbye!\n"+ ConsoleColors.RESET );
					return;
				}
				default:
				{
					continue;
				}
			
			}
			
		}
	}
	
	public static void addClass() {
		//Determine available classes
		ArrayList<ClassE> availClasses = new ArrayList<>();
		boolean notTeaching = true;
		for(ClassE c : ClassE.values()) {
			notTeaching = true;
			for (int j = 0; j < currentUser.getClasses().size(); j++) {
				if (c.equals(currentUser.getClasses().get(j).classId)) {
					notTeaching = false;
					break;
				}
			}
			if(notTeaching) {
				availClasses.add(c);
			}
		}
		if(availClasses.isEmpty()) {
			System.out.println(ConsoleColors.RED + "No classes are left to add" + ConsoleColors.RESET);
			return;
		}
		
		//Print Classes
		System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-10s %-20s\n", "Class ID", "Name" + ConsoleColors.RESET);
		for(int i = 0; i < availClasses.size(); i++) {
			System.out.printf("%-10d %-20s\n", i+1, availClasses.get(i));
		}
		
		//Choose Class
		boolean goodInput = false;
		int classId = 0;
		do {
			System.out.print(ConsoleColors.ITALIC + "Enter the Class ID of the class you want to add: " + ConsoleColors.RESET);
			try {
				classId = ConsoleScanner.getInt();
				goodInput = true;
				if(classId > availClasses.size() || classId < 1) {
					goodInput = false;
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
			catch(Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
		}
		while(!goodInput);
		System.out.println("");
		
		//Add Chosen Class
		ClassE className = availClasses.get(classId - 1);
		currentUser.getClasses().add(new SchoolClass(className));
		System.out.printf(ConsoleColors.GREEN + ConsoleColors.ITALIC + "Added " + className + " to your teaching schedule\n" + ConsoleColors.RESET);
	}
	
	public static void updateClass() {
		
		//Print Classes
		System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-10s %-20s\n", "Class ID", "Name" + ConsoleColors.RESET);
		for(int i = 0; i < currentUser.getClasses().size(); i++) {
			System.out.printf("%-10d %-20s\n", i+1, currentUser.getClasses().get(i).classId);
		}
		
		//Choose a class to update
		boolean goodInput = false;
		int classId = 0;
		do {
			System.out.print(ConsoleColors.ITALIC + "Enter the Class ID of the class you want to update: " + ConsoleColors.RESET);
			try {
				classId = ConsoleScanner.getInt();
				goodInput = true;
				if(classId > currentUser.getClasses().size() || classId < 1) {
					goodInput = false;
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
			catch(Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
		}
		while(!goodInput);
		System.out.println("");
		
		//Which kind of update
		SchoolClass selectedClass = currentUser.getClasses().get(classId - 1);
		System.out.println("1. Add a student.");
		System.out.println("2. Update a student's grade.");
		System.out.println("3. Exit.");
		int updateOption = 0;
		do {
			System.out.print(ConsoleColors.ITALIC + "Enter the type of update you wish to perform: " + ConsoleColors.RESET);
			try {
				updateOption = ConsoleScanner.getInt();
				goodInput = true;
				if(updateOption > 3 || updateOption < 1) {
					goodInput = false;
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
			catch(Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
		}
		while(!goodInput);
		if(updateOption == 1) {
			addStudent(selectedClass);
		}
		else if(updateOption == 2){
			updateGrade(selectedClass);
		}
		else {
			return;
		}
		
	}
	
	public static void addStudent(SchoolClass selectedClass) {
		//Get available students
		ArrayList<Student> availStudents = new ArrayList<>();
		boolean notTeaching = true;
		for(Student s : allStudents) {
			notTeaching = true;
			for (int j = 0; j < selectedClass.students.size(); j++) {
				if (s.getStudentId() == selectedClass.students.get(j).getStudentId()) {
					notTeaching = false;
					break;
				}
			}
			if(notTeaching) {
				availStudents.add(s);
			}
		}
		if(availStudents.isEmpty()) {
			System.out.println(ConsoleColors.RED + "No students are left to add" + ConsoleColors.RESET);
			return;
		}
		
		//Print available students
		System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-12s %-40s\n", "Student ID", "Name" + ConsoleColors.RESET);
		for(int i = 0; i < availStudents.size(); i++) {
			System.out.printf("%-12d %-40s\n", i+1, availStudents.get(i).getlName() + ", " + availStudents.get(i).getfName());
		}
		System.out.println();
		
		//Choose Student
		boolean goodInput = false;
		int studentId = 0;
		do {
			System.out.print(ConsoleColors.ITALIC + "Enter the Student ID of the student you want to add: " + ConsoleColors.RESET);
			try {
				studentId = ConsoleScanner.getInt();
				goodInput = true;
				if(studentId > availStudents.size() || studentId < 1) {
					goodInput = false;
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
			catch(Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
		}
		while(!goodInput);
		System.out.println("");
		Student selectedStudent = availStudents.get(studentId - 1);
		selectedClass.students.add(selectedStudent);
		gradebook.add(new StudentGrades(selectedClass.classId, selectedStudent.getStudentId(), 100));
	}
	
	public static void printClassList() {
		System.out.println("");
		if(currentUser.getClasses().isEmpty()) {
			System.out.println(ConsoleColors.YELLOW + ConsoleColors.ITALIC + "You have no classes\n"  + ConsoleColors.RESET);
		}
		else {
			System.out.println("Displaying your classes\n");
			System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-16s %-16s %-16s\n", "Name", "Average Grade", "Median Grade" + ConsoleColors.RESET);
			for(SchoolClass c : currentUser.getClasses()) {
				System.out.printf("%-16s %-16s %-16s\n", 
						c.classId, 
						c.classAverage(gradebook), 
						c.classMedian(gradebook));
			}
		}
		System.out.println("");
	}
	
	private static void updateGrade(SchoolClass selectedClass) {
		
		if(selectedClass.students.isEmpty()) {
			System.out.printf(ConsoleColors.RED + ConsoleColors.ITALIC + "There are no students in this class" + ConsoleColors.RESET);
		}
		
		//Display students
		System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-12s %-40s\n", "Student ID", "Name" + ConsoleColors.RESET);
		for(int i = 0; i < selectedClass.students.size(); i++) {
			System.out.printf("%-12d %-40s\n", i+1, selectedClass.students.get(i).getlName() + ", " + selectedClass.students.get(i).getfName());
		}
		System.out.println();
		
		//Choose student
		boolean goodInput = false;
		int studentId = 0;
		do {
			System.out.print(ConsoleColors.ITALIC + "Enter the Student ID of the student you want to update: " + ConsoleColors.RESET);
			try {
				studentId = ConsoleScanner.getInt();
				goodInput = true;
				if(studentId > selectedClass.students.size() || studentId < 1) {
					goodInput = false;
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
			catch(Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
		}
		while(!goodInput);
		System.out.println("");
		Student selectedStudent = selectedClass.students.get(studentId - 1);
		
		// Update Grade
		int grade = -1;
		do {
			System.out.print(ConsoleColors.ITALIC + "Enter " +selectedStudent.getfName()+ "'s new grade: " + ConsoleColors.RESET);
			try {
				grade = ConsoleScanner.getInt();
				goodInput = true;
				if(grade > 100 || grade < 0) {
					goodInput = false;
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
			catch(Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				ConsoleScanner.getString();
				goodInput = false;
			}
		}
		while(!goodInput);
		ArrayList<StudentGrades> selectedClassGrades = selectedClass.getClassGrades(gradebook);
		for(StudentGrades sg : selectedClassGrades) {
			if(sg.getStudentId() == selectedStudent.getStudentId()) {
				sg.setGrade(grade);
			}
		}
		System.out.println("");
		System.out.println(ConsoleColors.GREEN + ConsoleColors.ITALIC + selectedStudent.getfName() +"'s grade has been set to " + grade + ConsoleColors.RESET);
		System.out.println("");
	}
	
	
	//Repo Functions
    private static Optional<Teacher> getTeacherByUsername(String username) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(username)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }
    
    private static Optional<Teacher> login(String username, String password) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }
    
}
