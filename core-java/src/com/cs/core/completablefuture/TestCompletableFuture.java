package com.cs.core.completablefuture;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class TestCompletableFuture {

    @Test
	public void test1() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> future = new CompletableFuture<String>();
		Executors.newCachedThreadPool().submit(()-> {
			Thread.sleep(500);
			future.complete("Hello");
			return null;
		});
		assertEquals("Hello",future.get());
	}
    
    public void test2() {
    	Future<String> completableFuture =  CompletableFuture.completedFuture("Already know the answer");
    }
    
    @Test
    public void test3() throws InterruptedException, ExecutionException {
    	
    	//Demo supplyAsync
    	CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
    		//Some ASYNC Calculations 
    		//Return Future result
    		return "Hello";
    	});
    	assertEquals("Hello", completableFuture.get());
    	//Demo runAsync 
    	CompletableFuture completableFuture2 = CompletableFuture.runAsync(()->{
    		//Some ASYNC Calculations 
    		});
    }
    
    @Test
    public void test4() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> compFuture = CompletableFuture.supplyAsync(()->"Hello");
    	CompletableFuture<String> future 	 = compFuture.thenApply(s -> s + " World");
    	assertEquals("Hello World", future.get());
    }
    
    @Test
    public void test5() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> compFuture = CompletableFuture.supplyAsync(()->"Hello");
    	CompletableFuture<Void> future 	 = compFuture.thenAccept(s->System.out.println("Computation returned: " + s));
    	assertEquals(null, future.get());
    }
    
    
    @Test
    public void test6() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> compFuture = CompletableFuture.supplyAsync(()->"Hello");
    	CompletableFuture<Void> future 	 = compFuture.thenRun(()->System.out.println("Computation finished."));
    	assertEquals(null, future.get());
    }
    
    @Test
    public void test7() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> compFuture = CompletableFuture.supplyAsync(()->"Hello");
    	CompletableFuture<String> future 	 = compFuture.thenCompose(s->CompletableFuture.supplyAsync(() -> s + " World"));
    	assertEquals("Hello World", future.get());
    }
    
    @Test
    public void test8() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> complFuture = CompletableFuture.supplyAsync(()-> "Hello").
    											thenCombine(CompletableFuture.supplyAsync(()->" World"),(s1,s2)->s1+s2);
    	assertEquals("Hello World", complFuture.get());
    }
    
    @Test
    public void test9() throws InterruptedException, ExecutionException {
    	CompletableFuture<String> future1  		 = CompletableFuture.supplyAsync(() -> "Hello");
    	CompletableFuture<String> future2  		 = CompletableFuture.supplyAsync(() -> "Beautiful");
    	CompletableFuture<String> future3  		 = CompletableFuture.supplyAsync(() -> "World");
    	CompletableFuture<Void> combinedFuture   = CompletableFuture.allOf(future1, future2, future3);
    	 
    	// ...
    	 
    	combinedFuture.get();
    	assertTrue(future1.isDone());
    	assertTrue(future2.isDone());
    	assertTrue(future3.isDone());
    }
	
    @Test
    public void test10() throws InterruptedException, ExecutionException {
    	String name = null;
    	 
    	CompletableFuture<String> completableFuture   =  CompletableFuture.supplyAsync(() -> {
											    	      if (name == null) {
											    	          throw new RuntimeException("Computation error!");
											    	      }
											    	      return "Hello, " + name;
											    	  }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
    	 
    	assertEquals("Hello, Stranger!", completableFuture.get());
    }
}
