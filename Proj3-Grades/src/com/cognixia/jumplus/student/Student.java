package com.cognixia.jumplus.student;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.cognixia.jumplus.classes.SchoolClass;
import com.cognixia.jumplus.grades.StudentGrades;

public class Student {

	private String fName;
	private String lName;
	
	private ArrayList<SchoolClass> classes;
	
	private int studentId;
	
	static int numOfStudents;

	static {
		numOfStudents = 1;
	}
	
	
	public Student(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
		classes = new ArrayList<SchoolClass>();
		studentId = numOfStudents;
		numOfStudents++;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public Double getGrade(ArrayList<StudentGrades> gradebook, SchoolClass classi) {
		ArrayList<StudentGrades> myclasses = gradebook.stream().filter(g -> g.getStudentId() == studentId).collect(Collectors.toCollection(ArrayList::new));
		for(StudentGrades c: myclasses) {
			if(c.getClassId().equals(classi.classId)) {
				return c.getGrade();
			}
		}
		return new Double(0);
	}

	@Override
	public String toString() {
		return lName + ", " + fName;
	}
	
	
	
	
}
