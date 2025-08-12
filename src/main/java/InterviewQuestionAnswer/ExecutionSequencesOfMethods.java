package InterviewQuestionAnswer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExecutionSequencesOfMethods {
	
	
	@Test(invocationCount = 2, priority=2)
	public void A1()
	{
		System.out.println("I am in method A1");
		Assert.assertEquals(true, true);
	}
	
	@Test(invocationCount = 2, dependsOnMethods  = {"A1"}, priority=3)
	public void B1()
	{
		System.out.println("I am in method B1");
	}
	
	//@Test
	public static void B2()
	{
		System.out.println("I am in method B2");
	}

}
