package SampleUIAndConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import Utilities.AndroidLauncher;
import Utilities.AndroidPackageManipulator;
import Utilities.AppiumServer;



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
		
		for (String device : deviceNames)
		{
			launcher = new AndroidLauncher();
			launcher.launchEmulator(device);
			
			apm.cleanBuild();
			
			TestListenerAdapter tla = new TestListenerAdapter();
			TestNG testng = new TestNG();
			testng.setTestClasses(new Class[] { NetworkConnectivityTesting.class });
			
			testng.addListener(tla);
			testng.run();
			
			launcher.killEmulator();
		}
	}

}
