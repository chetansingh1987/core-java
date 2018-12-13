package com.cs.mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

	@Mock
	B bMock;
	
	@InjectMocks
	A aObj = new A(); 
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(bMock.method1()).thenReturn(10);
		
	}
	
	@Test
	public void test1() {
		assertEquals(aObj.methodA(),10);
	}
	
	@Test
	public void test2() {
		bMock.method2();
		//limit the method2 call to 1, no less and no more calls are allowed
		Mockito.verify(bMock,times(1)).method2();
	}
	
	
	@Test
	public void test3() {
		bMock.method3(Mockito.anyString());
		//limit the method3 call to 1, no less and no more calls are allowed
		Mockito.verify(bMock,times(1)).method3(Mockito.anyString());
	}
	
	@Test
	public void test4() {
		bMock.method2();
		Mockito.verify(bMock,Mockito.atLeast(1)).method2();
		Mockito.verify(bMock,Mockito.atLeastOnce()).method2();
		bMock.method2();
		Mockito.verify(bMock,Mockito.atMost(2)).method2();
	}
	
	
	@Test(expected=Exception.class)
	public void test5() throws Exception{
		      //add the behavior to throw exception
		      Mockito.doThrow(new Exception("Add operation not implemented")) .when(bMock).method4();
		      //test the add functionality
		      Assert.assertEquals("Same",bMock.method4(),1); 
	}
	
	
}


class A {
	
	B bObj;
	

	public int methodA() {
		System.out.println(bObj.method1());
		return bObj.method1();
	}
	
	
	public int methodA2(){
		try {
			bObj.method4();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
		return 1;
	}
	

	
}


class B {
	
	public int method1() {
		System.out.println("Method B");
		return 0;
	}
	
	public void method2() {}
	public void method3(String arg) {}
	public int method4() throws Exception{return 1;}
}