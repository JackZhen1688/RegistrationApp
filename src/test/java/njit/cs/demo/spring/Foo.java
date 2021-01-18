package njit.cs.demo.spring;

import org.springframework.stereotype.Service;

@Service
public class Foo {

	public String run (final String input) {
		return input + " from Foo";
	}
}
