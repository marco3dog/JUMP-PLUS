package com.cognixia.jumplus.toplevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cognixia.jumplus.student.Student;
import com.cognixia.jumplus.teacher.Teacher;
import com.cognixia.jumplus.classes.SchoolClass;
import com.cognixia.jumplus.classes.SchoolClass.ClassE;
import com.cognixia.jumplus.grades.StudentGrades;

public class TopLevel {
	
	static ArrayList<Teacher> teachers;
	static ArrayList<Student> allStudents;
	static Teacher currentTeacher;
	static ArrayList<StudentGrades> gradebook;
	static Scanner scan;
	
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
		scan = new Scanner(System.in);
	}
	
	public static void run() {

		System.out.println("       Login to Teacher Portal.");
		int option = 1;
		 
		while(true) {
			printMainMenu();
			try {
				option = scan.nextInt();
			}
			catch(Exception e) {
				System.out.println("        Enter a number.");
				option = 0;
				scan.nextLine();
				continue;
			}
			
			switch(option) {
			case 1: {
				boolean didRegister = false;
				do {
					System.out.println("        Enter your last name: ");
					String lname = scan.next();
					System.out.println("        Enter your username: ");
					String username = scan.next();
					System.out.println("        Enter your password: ");
					String password = scan.next();
					didRegister = teachers.add(new Teacher(username, password, lname));
				}
				while(!didRegister);
				continue;
			}
			case 2: {
				int loggedTeacherIndex = -1;
				do {
					System.out.println("        Enter your username: ");
					String username = scan.next();
					System.out.println("        Enter your password: ");
					String password = scan.next();
					loggedTeacherIndex = authenticateTeacher(username, password);
				}
				while(loggedTeacherIndex == -1);
				currentTeacher = teachers.get(loggedTeacherIndex);
				break;
			}
			case 3: {
				System.out.println("        Program Terminated.");
				return;
			}

			default: {
				System.out.println("        Enter a number between 1-3.");
				continue;
			}
			}
			break;
		}
		while(true) { //no breaks
			printClassList();
			try {
				option = scan.nextInt();
			}
			catch(Exception e) {
				System.out.println("        Enter a number.");
				option = 0;
				scan.nextLine();
				continue;
			}
			if(option == currentTeacher.getClasses().size() + 2) {
				System.out.println("        Program Terminated.");
				scan.close();
				return;
			}
			if(option == currentTeacher.getClasses().size() + 1) {
				//add a class
				addAClass();
				continue;
			}
			if(option < 1 || option > currentTeacher.getClasses().size() + 2) {
				System.out.println("        Selection out of range.");
			}
			else {
				//displayClass(option-1);
				selectAClass(option-1);
				continue;
			}
		}
		
		
	}
	
	public static void printMainMenu() {
		
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.format("%24s","Selection Number");
		System.out.println("");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		String [] data = {"REGISTER","LOGIN","EXIT"};
		for(int i = 0; i < data.length; i++) {
			System.out.format("%24s", (i + 1) + ").");
			System.out.format("%24s", data[i]);
			System.out.println("");
		}
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");

		for(int i = 0; i<8;i++) {
			System.out.println("");
		}
	}
	
	static int authenticateTeacher(String username, String password) {
		for(int i = 0; i < teachers.size(); i++) {
			if(teachers.get(i).getUsername().equals(username) && teachers.get(i).getPassword().equals(password)) {
				System.out.println("        Welcome, Professor " + teachers.get(i).getlName());
				return i;
			}
		}
		
		return -1;
	}
	
	public static void printClassList() {
		if(currentTeacher.getClasses().size() == 0) {
			System.out.println("        You have no classes");
		}
		else {
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.println("        View classes or use a selection number to edit a class.");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.println("");	
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			//Column Names
			System.out.format("%24s", "Selection Number");
			System.out.format("%24s", "Class");
			System.out.format("%24s", "Average Grade");
			System.out.format("%24s", "Median");
			System.out.println("");
			
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			
			//Print data
			for(int i = 0; i < currentTeacher.getClasses().size(); i++) {
				System.out.format("%24s", (i + 1) + ".)");
				System.out.format("%24s", currentTeacher.getClasses().get(i).classId);
				System.out.format("%24s", currentTeacher.getClasses().get(i).classAverage(gradebook));
				System.out.format("%24s", currentTeacher.getClasses().get(i).classMedian(gradebook));
				System.out.println("");
			}
			
			System.out.println("");
			System.out.format("%24s", (currentTeacher.getClasses().size() + 1) + ".)");
			System.out.format("%24s", "Add a new class");
			System.out.println("");
			System.out.format("%24s", (currentTeacher.getClasses().size() + 2) + ".)");
			System.out.format("%24s", "Exit Program");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
		}
	}
	
	public static void addAClass(){
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.println("        Select from the available classes to add.");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.println("");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.format("%24s", "Selection Number");
		System.out.format("%24s", "Class");
		System.out.println("");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		ArrayList<ClassE> availClasses = new ArrayList<>();		
		boolean notTeaching = true;
		for(ClassE c : ClassE.values()) {
			notTeaching = true;
			for (int j = 0; j < currentTeacher.getClasses().size(); j++) {
				if (c.equals(currentTeacher.getClasses().get(j).classId)) {
					notTeaching = false;
					break;
				}
			}
			if(notTeaching) {
				availClasses.add(c);
			}
			
		}
		for(int i = 0; i < availClasses.size(); i++) {
			System.out.format("%24s", (i + 1) + ".)");
			System.out.format("%24s", availClasses.get(i));
			System.out.println("");
		}
		System.out.println("");
		System.out.format("%24s", "Any other button.)");
		System.out.format("%24s", "Exit");
		System.out.println("");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		int option = 0;
		try {
			option = scan.nextInt();
			if(option < 1 || option > availClasses.size()) {
				throw new Exception();
			}
		}
		catch(Exception e) {
			System.out.println("       Returning to main menu.");
			return;
		}
		currentTeacher.getClasses().add(new SchoolClass(availClasses.get(option - 1)));
		System.out.println("        The " + availClasses.get(option - 1) + " has been added to your schedule.");
		return;
	}
	
	public static void selectAClass(int index) {
		SchoolClass selectedClass = currentTeacher.getClasses().get(index);
		int option = 0;
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.format("%24s","Selection Number");
		System.out.format("%24s","Class: " + selectedClass.classId);
		System.out.println("");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		String [] data = {"SORT BY NAME","SORT BY GRADE","ADD A STUDENT","UPDATE A GRADE","EXIT"};
		for(int i = 0; i < data.length; i++) {
			System.out.format("%24s", (i + 1) + ").");
			System.out.format("%24s", data[i]);
			System.out.println("");
		}
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");

		for(int i = 0; i<4;i++) {
			System.out.println("");
		}
		try {
			option = scan.nextInt();
			if(option < 1 || option > 4) {
				throw new Exception();
			}
		}
		catch(Exception e) {
			return;
		}
		switch(option) {
		case 1:{
			ArrayList<Student> sortedStudents = selectedClass.students.stream().sorted((s1, s2) -> s1.getlName().compareTo(s2.getlName())).collect(Collectors.toCollection(ArrayList::new));
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.format("%24s","Name");
			System.out.format("%24s","Grade");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			for(Student s : sortedStudents) {
				System.out.format("%24s", s.getlName() + ", " + s.getfName());
				System.out.format("%24s", s.getGrade(gradebook, selectedClass));
				System.out.println("");
			}
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			break;
		}
		case 2:{
			ArrayList<Student> sortedStudents = selectedClass.students.stream().sorted((s2, s1) -> s1.getGrade(gradebook, selectedClass).compareTo(s2.getGrade(gradebook, selectedClass))).collect(Collectors.toCollection(ArrayList::new));
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.format("%24s","Name");
			System.out.format("%24s","Grade");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			for(Student s : sortedStudents) {
				System.out.format("%24s", s.getlName() + ", " + s.getfName());
				System.out.format("%24s", s.getGrade(gradebook, selectedClass));
				System.out.println("");
			}
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			break;
		}
		case 3:{
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.println("        Select from the available students to add.");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.format("%24s", "Selection Number");
			System.out.format("%24s", "Student");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			ArrayList<Student> availStudents = new ArrayList<>();
			boolean notInClass = true;
			for(Student s : allStudents) {
				notInClass = true;
				for (Student in : selectedClass.students) {
					if (s.equals(in)) {
						notInClass = false;
						break;
					}
				}
				if(notInClass) {
					availStudents.add(s);
				}
				
			}
			for(int i = 0; i < availStudents.size(); i++) {
				System.out.format("%24s", (i + 1) + ".)");
				System.out.format("%24s", availStudents.get(i).getlName() + ", " + availStudents.get(i).getfName());
				System.out.println("");
			}
			System.out.println("");
			System.out.format("%24s", "Any other button.)");
			System.out.format("%24s", "Exit");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			int option2 = 0;
			try {
				option2 = scan.nextInt();
				if(option2 < 1 || option2 > availStudents.size()) {
					throw new Exception();
				}
			}
			catch(Exception e) {
				System.out.println("       Returning to main menu.");
				return;
			}
			Student selectedStudent = availStudents.get(option2 - 1);
			System.out.println("       Enter " + selectedStudent.getfName() +"'s grade: ");
			double grade = 0;
			try {
				grade = scan.nextDouble();
				if(grade > 100 || grade < 0) {
					throw new Exception();
				}
			}
			catch(Exception e) {
				System.out.println("       Invalid Grade.");
				return;
			}
			
			selectedClass.students.add(selectedStudent);
			gradebook.add(new StudentGrades(selectedClass.classId, selectedStudent.getStudentId(), grade));
			break;
		}
		case 4:{
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.println("        Select the student in your class to update.");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			System.out.format("%24s", "Selection Number");
			System.out.format("%24s", "Student");
			System.out.format("%24s", "Grade");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			for(int i = 0; i < selectedClass.students.size(); i++) {
				System.out.format("%24s", (i + 1) + ".)");
				System.out.format("%24s", selectedClass.students.get(i).getlName() + ", " + selectedClass.students.get(i).getfName());
				System.out.format("%24s", selectedClass.students.get(i).getGrade(gradebook, selectedClass));
				System.out.println("");
			}
			System.out.println("");
			System.out.format("%24s", "Any other button.)");
			System.out.format("%24s", "Exit");
			System.out.println("");
			System.out.print("+");
			for(int i = 0; i < 94; i++) {
				System.out.print("-");
			}
			System.out.print("+");
			System.out.println("");
			int option2 = 0;
			try {
				option2 = scan.nextInt();
				if(option2 < 1 || option2 > selectedClass.students.size()) {
					throw new Exception();
				}
			}
			catch(Exception e) {
				System.out.println("       Returning to main menu.");
				return;
			}
			Student selectedStudent = selectedClass.students.get(option2 - 1);
			System.out.println("       Enter " + selectedStudent.getfName() +"'s new grade: ");
			double grade = 0;
			try {
				grade = scan.nextDouble();
				if(grade > 100 || grade < 0) {
					throw new Exception();
				}
			}
			catch(Exception e) {
				System.out.println("       Invalid Grade.");
				return;
			}
			ArrayList<StudentGrades> selectedClassGrades = selectedClass.getClassGrades(gradebook);
			for(StudentGrades sg : selectedClassGrades) {
				if(sg.getStudentId() == selectedStudent.getStudentId()) {
					sg.setGrade(grade);
				}
			}
			System.out.println("       " + selectedStudent.getfName() +"'s grade has been set to " + grade);
			break;
		}
		default: return;
		}
	}

}
