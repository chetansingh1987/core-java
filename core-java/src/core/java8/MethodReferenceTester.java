package core.java8;


import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;


public class MethodReferenceTester {

	@Test
	public void test1() {
		Person p =new Person();
		Function<Person,Integer> method = Person::getAge;
		int age = method.apply(p);
		Assert.assertEquals(age,10);
	}
}

class Person {
	int age=10;
	public int getAge() {
		return age;
	}
}
