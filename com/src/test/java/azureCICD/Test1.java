package azureCICD;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class Test1 {
	@Test
	public void printme() {
		System.out.println("first test printme");
		Assert.assertTrue(true);
	}

	@Test
	public void passMe() {
		System.out.println("second test passme");
		Assert.assertTrue(true);
	}

	@Test(enabled = false)
	public void skipme() {

		throw new SkipException("i am skip test");
	}

	@Test
	public void passthismethod() {
		System.out.println("fourth test passthismethod");
		Assert.assertTrue(true);
	}
}
