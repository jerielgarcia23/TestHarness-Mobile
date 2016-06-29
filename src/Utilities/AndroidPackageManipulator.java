package Utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;

public class AndroidPackageManipulator 
{
	private Runtime runtime;
	private Process process;
	private String packagePath; 
	
	public AndroidPackageManipulator(String packagePath) throws Exception
	{
		this.packagePath = packagePath;
		runtime = Runtime.getRuntime();
		this.cleanBuild();
	}
	
	public void uninstallPackage() throws Exception
	{
		//C:\sampleAPK\app-debug.apk
		process = runtime.exec("adb uninstall "+ packagePath);
		System.out.println("Uninstalled - Huzzah!!");
	}
	
	public void installPackage() throws Exception
	{
		//C:\sampleAPK\app-debug.apk
		process = runtime.exec("adb install "+ packagePath);
		System.out.println("Installed - Even Better!!");
	}
	
	public void cleanBuild() throws Exception
	{
		uninstallPackage();
		installPackage();
	}
}
