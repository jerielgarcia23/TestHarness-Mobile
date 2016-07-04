package SampleUIAndConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Listeners;

import Utilities.AndroidLauncher;
import Utilities.AndroidPackageManipulator;
import Utilities.AppiumServer;
import Utilities.AutomatedEmailNotification;
import Utilities.TestReporter;


@Listeners(TestReporter.class)
public class TestRunner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String appPath = "C:/sampleAPK/app-Stage-debug.apk";
		//String deviceName = "Android_Tablet";
		AndroidPackageManipulator apm = new AndroidPackageManipulator(appPath);
               
        AppiumServer server = new AppiumServer();
		server.start();
		
		AndroidLauncher launcher;
		
		List<String> deviceNames = new LinkedList<String>();
		deviceNames.add("Test");
		deviceNames.add("Android_Tablet");
		
		TestReporter testReporter = new TestReporter();
		
		for (String device : deviceNames)
		{
			testReporter.updateDeviceName(device);
			
			launcher = new AndroidLauncher();
			launcher.launchEmulator(device);
			
			apm.cleanBuild();
			
			TestListenerAdapter tla = new TestListenerAdapter();
			TestNG testng = new TestNG();
			testng.setTestClasses(new Class[] { NetworkConnectivityTesting.class });
			testng.setTestClasses(new Class[] { SampleTest.class });
			
			testng.addListener(tla);
			testng.run();
			
			launcher.killEmulator();
		}
		
		System.out.println(testReporter.generateReport());
		AutomatedEmailNotification.sendEmail("felix.livshits@shomi.com, subject, body","Test Results",testReporter.generateReport());
	}

}
