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
	public void failme() {
		System.out.println("second test failme");
		Assert.assertTrue(true);
	}

	@Test(enabled = false)
	public void skipme() {

		throw new SkipException("i am skip test");
	}

	@Test
	public void whoisme() {
		System.out.println("fourth test whosisme");
		Assert.assertTrue(true);
	}
}
