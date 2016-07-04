package Utilities;

import java.util.LinkedList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestReporter extends TestListenerAdapter {
	
	private static List<String> passed = new LinkedList<String>();
	private static List<String> failed = new LinkedList<String>();
	private static List<String> skipped = new LinkedList<String>();
	private static String deviceName = "";
	
	public void updateDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	@Override
	public void onTestSuccess(ITestResult testResult)
	{
		passed.add(this.deviceName + " - " + testResult.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult testResult)
	{
		failed.add(this.deviceName + " - " + testResult.getName());
	}
	
	@Override
	public void onTestSkipped(ITestResult testResult)
	{
		skipped.add(this.deviceName + " - " + testResult.getName());
	}
	
	public static String generateReport()
	{
		String reportResult = "";
		reportResult +="----- Passed Tests -----\n";
		for (String test : passed)
		{
			reportResult += test + "\n";
		}
		
		reportResult +="----- Failed Tests -----\n";
		for (String test : failed)
		{
			reportResult += test + "\n";
		}
		
		reportResult +="----- Skipped Tests -----\n";
		for (String test : skipped)
		{
			reportResult += test + "\n";
		}
		
		return reportResult;
	}
}
