package com.cognixia.jumplus.grades;

import com.cognixia.jumplus.classes.SchoolClass.ClassE;

public class StudentGrades {

	private ClassE classId;
	private int studentId;
	private double grade;
	
	public StudentGrades(ClassE classId, int studentId, double grade) {
		super();
		this.classId = classId;
		this.studentId = studentId;
		this.grade = grade;
	}

	public ClassE getClassId() {
		return classId;
	}

	public void setClassId(ClassE classId) {
		this.classId = classId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
	
	
}
