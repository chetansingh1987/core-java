package core.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTester {

	public static void main(String[] args) {
		new StreamsTester().test1();
		new StreamsTester().test2();
		new StreamsTester().test3();
		new StreamsTester().test4();
	}
	public void test1() {
		Student s1 = new Student("Anurag");
		Student s2 = new Student("Anupam");
		Student s3 = new Student("Chetan");
		
		List<Student> listStu = Arrays.asList(s1,s2,s3);
		listStu.stream().map(student->student.getName()).filter(name->name.startsWith("A")).collect(Collectors.toList()).forEach(System.out::println);
	}
	
	public void test2() {
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		Optional<Integer> intOptional = numbers.reduce((i,j) -> {return i*j;});
		if(intOptional.isPresent()) System.out.println("Multiplication = "+intOptional.get()); //120
	}
	
	public void test3() {
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		System.out.println(numbers.count());
	}
	
	public void test4() {
		Stream<Integer> numbers3 = Stream.of(1,2,3,4,5);
		System.out.println(numbers3.anyMatch(i->i==4));
		
		Stream<Integer> numbers4 = Stream.of(1,2,3,4,5);
		System.out.println(numbers4.noneMatch(i->i==4));
		
		Stream<Integer> numbers5 = Stream.of(1,2,3,4,5);
		System.out.println(numbers5.allMatch(i->i<10));
	}
		
}

class Student {
	String name ;

	
	public Student(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}
	
	
	
}