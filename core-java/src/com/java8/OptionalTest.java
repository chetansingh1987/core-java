package com.java8;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class OptionalTest {
	@Test
	public static void main(String[] args) {
		assertEquals(Optional.of("STRING"), Optional.of("string").flatMap(s -> Optional.of("STRING")));
		
		assertEquals(Optional.of(Optional.of("STRING")), 
				  Optional.of("string")
				  .map(s -> Optional.of("STRING")));
		
}
}
