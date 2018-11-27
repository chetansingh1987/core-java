package core.java8;

import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {
	@Test
	public static void main(String[] args) {
		String s = null;
		Optional<String> x = Optional.ofNullable(s);
		assertFalse(x.isPresent());
		x.get();

	}
}
