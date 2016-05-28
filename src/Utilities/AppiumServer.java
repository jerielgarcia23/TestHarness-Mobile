package Utilities;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

public class AppiumServer {

	public AppiumDriverLocalService service;
	
	public AppiumServer()
	{
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
		.withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
		.withIPAddress("127.0.0.1")
		.usingPort(4723));
	}
	
	public AppiumServer(String nodeExePath, String appiumJSPath)
	{
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		.usingDriverExecutable(new File(nodeExePath))
		.withAppiumJS(new File(appiumJSPath))
		.withIPAddress("127.0.0.1")
		.usingPort(4723));
	}
	
	public void start()
	{
		service.start();	
	}
	
	public void stop()
	{
		service.stop();
	}
		
}
