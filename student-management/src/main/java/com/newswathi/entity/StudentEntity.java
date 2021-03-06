package com.newswathi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.newswathi.School;
import com.newswathi.Student;

@Entity
@Table(name="STUDENT")
public class StudentEntity implements Validator{
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="Student_Name")
	private String studName;
	@Column(name="Student_Age")
	private int studAge;
	@Column(name="Student_Roll_No")
	private int studRollNo;
	@ManyToOne
	private SchoolEntity school;
	
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public int getStudAge() {
		return studAge;
	}
	public void setStudAge(int studAge) {
		this.studAge = studAge;
	}
	public int getStudRollNo() {
		return studRollNo;
	}
	public void setStudRollNo(int studRollNo) {
		this.studRollNo = studRollNo;
	}
 
	public SchoolEntity getSchool() {
		return school;
	}
	public void setSchool(SchoolEntity school) {
		this.school = school;
	}
	@Override
	public String toString() {
		return "Student [studName=" + studName + ", studAge=" + studAge + ", studRollNo=" + studRollNo + ", school="
				+ school + "]";
	}
	public boolean supports(Class<?> clazz) {
		return StudentEntity.class.equals(clazz);
	}
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "studName", "student.name.invalid");
		StudentEntity student = (StudentEntity)target;
		if(student.getStudAge()>5)
		{
			System.out.println("Valid age for admission"+" "+student.getStudName());
		}
		else
		{
			errors.reject("student.age.invalid");
		}
		
	}
	public Student toStudent() {
		Student student = new Student();
		student.setStudName(this.getStudName());
		student.setStudRollNo(this.getStudRollNo());
		student.setStudAge(this.getStudAge());
		SchoolEntity schoolEntity = this.getSchool();
		if(schoolEntity!=null) {
			student.setSchool(schoolEntity.toSchool());
		}
		return student;
	}
	
}
