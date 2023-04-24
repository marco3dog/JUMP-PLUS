package com.cognixia.jumplus.classes;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.cognixia.jumplus.grades.StudentGrades;
import com.cognixia.jumplus.student.Student;

public class SchoolClass {

	public enum ClassE {
		HISTORY, SCIENCE, MATH, READING, MUSIC, ART
	}
	
	public ClassE classId;
	
	public ArrayList<Student> students;

	public SchoolClass(ClassE classId) {
		super();
		this.classId = classId;
		students = new ArrayList<Student>();
	}
	
	public double classAverage(ArrayList<StudentGrades> gradebook) {
		
		double result = 0;
		int size = students.size();
		if(size == 0) {
			return 0;
		}
		ArrayList<StudentGrades> grades = getClassGrades(gradebook);
		for (int i = 0; i < grades.size(); i++) {
			result += grades.get(i).getGrade();
		}
		return result/grades.size();
	}
	
	public double classMedian(ArrayList<StudentGrades> gradebook) {
		
		int size = students.size();
		if(size == 0) {
			return 0;
		}
		ArrayList<StudentGrades> grades = getClassGrades(gradebook);
		double result;
		if(grades.size()%2 == 0) { //even
			result = (grades.get(size/2 - 1).getGrade() + grades.get(size/2).getGrade()) / 2;
			return result;
		}
		else {
			result = grades.get(size/2).getGrade();
			return result;
		}
		
	}
	
	public ArrayList<StudentGrades> getClassGrades(ArrayList<StudentGrades> gradebook){
		
		return gradebook.stream().filter(s -> s.getClassId() == classId).sorted((s2, s1) -> s1.getGrade().compareTo(s2.getGrade())).collect(Collectors.toCollection(ArrayList::new));
	}
	
	
}
