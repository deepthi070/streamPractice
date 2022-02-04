package com.streamPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student st1 = new Student("Jayesh",20,
				new Address("1234"),
				Arrays.asList(new MobileNumber("1233"),new MobileNumber("3333")));
		
		Student st2 = new Student("Komal",22,
				new Address("2345"),
				Arrays.asList(new MobileNumber("2345"),new MobileNumber("1234"),new MobileNumber("2341")));
		
		Student st3 = new Student("Ashish",21,
				new Address("1235"),
				Arrays.asList(new MobileNumber("2222"),new MobileNumber("3333")));
		
		List<Student> students = Arrays.asList(st1,st2,st3);
		
		/*1.finding student Jayesh*/
		Optional<Student> stud = students.stream()
				.filter(x -> x.getName().equals("Jayesh"))
				.findFirst();
		System.out.println(stud.isPresent()?stud.get().getName():"No Student");
		System.out.println("--------------------");
		
		/*2.student with address 1235*/
		Optional<Student> stud1 = students.stream()
				.filter(x-> x.getAddress().getZipCode().equals("1235"))
				.findFirst();
		System.out.println(stud1.isPresent()?stud1.get().getName():"No students");
		System.out.println("--------------------");
		
		
		/*3.students with mobilenum 3333*/
		List<Student> lstud = students.stream()
				.filter(x1->x1.getMobileNumber().stream().anyMatch(num->Objects.equals(num.getNumber(),"3333" )))
				.collect(Collectors.toList());
		String result = lstud.stream().map(y -> y.getName()).collect(Collectors.joining(",","{", "}"));
		System.out.println(result);
		System.out.println("--------------------");
		
		/*4.students with mobile num 1234 and 1233*/
		List<Student> lstud1 = students.stream()
				.filter(num1 -> num1.getMobileNumber().stream().allMatch(x-> Objects.equals(x.getNumber(), "1233")|| Objects.equals(x.getNumber(), "1234")))
				.collect(Collectors.toList());
		String result1 = lstud1.stream().map(y->y.getName()).collect(Collectors.joining(",", "{","}"));
		System.out.println(result1);
		System.out.println("--------------------");
		
		/*5.creating list<Student> from List<tempStudent>*/
		TempStudent tstu1= new TempStudent("Jayesh1",121,
				new Address("12341"),
				Arrays.asList(new MobileNumber("12331"),new MobileNumber("23331")));
		TempStudent tstu2= new TempStudent("Mahesh",21,
				new Address("33341"),
				Arrays.asList(new MobileNumber("33331"),new MobileNumber("21231")));
		List<TempStudent> ltstu = Arrays.asList(tstu1,tstu2);
		List<Student> lstu = ltstu.stream().map(x-> new Student(x.name,x.age,x.address,x.mobileNumber))
				.collect(Collectors.toList());
		System.out.println(lstu);
		System.out.println("--------------------");
		
		 
        /*6.Convert List<Student> to List<String> of student name*/
       
		List<String> newList = lstu.stream()
				.map(Student::getName).collect(Collectors.toList());
		System.out.println(newList.stream().collect(Collectors.joining(",")));
		System.out.println("--------------------");
		
		/* 7.Convert List<students> to String*/
		String newStr = students.stream()
				.map(Student::getName).collect(Collectors.joining(","));
		System.out.println(newStr);
		System.out.println("--------------------");
		
		
		
        /*8.Change the case of List<String>*/
		List<String> strList = Arrays.asList("Josh","Vonid","Ravi");
		strList.stream().map(String :: toUpperCase).forEach(System.out::println);
		System.out.println("--------------------");
		
		/*9.sort List*/
		strList.stream().sorted().forEach(System.out::println);
		System.out.println("--------------------");
		
		/*10.Conditionally apply Filter condition, say if flag is enabled then*/
		boolean flag = true;
		Stream<Student> condResult= students.stream()
				.filter(x-> x.getName().startsWith("J"));
		if(flag) {
			condResult = condResult.sorted(Comparator.comparing(Student:: getName));
			
		}
		System.out.println("Before sorting:");
		students.forEach(x-> System.out.println(x.getName()));
		
		System.out.println("After sorting:");
		List<Student> newList1 = condResult.collect(Collectors.toList());
		newList1.forEach(x-> System.out.println(x.getName()) );
		
		
		
		
		
		
		
		
		
		
		
	}

}
