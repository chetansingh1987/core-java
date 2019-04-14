package com.practice.practical;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class GroupingByPractical {
	static List<Employee> employeeList = Arrays.asList(
		      new Employee("Tom Jones", 45, 12000.00,Department.MARKETING),
		      new Employee("Harry Major", 26, 20000.00, Department.LEGAL),
		      new Employee("Ethan Hardy", 65, 30000.00, Department.LEGAL),
		      new Employee("Nancy Smith", 22, 15000.00, Department.MARKETING),
		      new Employee("Catherine Jones", 21, 18000.00, Department.HR),
		      new Employee("James Elliot", 58, 24000.00, Department.OPERATIONS),
		      new Employee("Frank Anthony", 55, 32000.00, Department.MARKETING),
		      new Employee("Michael Reeves", 40, 45000.00, Department.OPERATIONS));
	
	@Test
	 public void test1() {
		 Function<Employee,Department> classifier  	= e->e.getDepartment();
		 employeeList.stream().collect(Collectors.groupingBy(classifier));
		 employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
	 }
	 
	 public static void main(String[] args) {
		new GroupingByPractical().test1();
	}
}


class Employee {
	  private String name;
	  private Integer age;
	  private Double salary;
	  private Department department;
	 
	  public Employee(String name, Integer age, Double salary, Department department) {
	    this.name = name;
	    this.age = age;
	    this.salary = salary;
	    this.department = department;
	  }
	 
	 
	  public Department getDepartment() {
		return department;
	}


	public String toString(){
	    return "Employee Name:"+this.name;
	  }
	 
	  //Standard equals and hashcode implementations go here
	 
	}
	//Enum Department.java
	 enum Department {
	  HR, OPERATIONS, LEGAL, MARKETING
	}