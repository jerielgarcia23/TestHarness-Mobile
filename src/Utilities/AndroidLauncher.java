package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class AndroidLauncher {

	private static String sdkPath = "C:/Users/jeriel.garcia/AppData/Local/Android/sdk2/"; // or for Mac "/Applications/adt-bundle-mac-x86_64-20140702/sdk/";// or for windows D:/Android/adt-bundle-windows-x86_64-20140702/sdk/
	private static String emulatorPath = sdkPath + "tools" + File.separator + "emulator";

	Process process;
	/**
	 * Starts an emulator for the provided AVD name
	 * 
	 * @param nameOfAVD
	 */
	public void launchEmulator(String nameOfAVD) 
	{
		String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
		try 
		{
			process = new ProcessBuilder(aCommand).start();
			process.waitFor(40, TimeUnit.SECONDS);
			System.out.println("Emulator launched successfully!");
			
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void killEmulator() throws IOException
	{
		Runtime.getRuntime().exec("taskkill /F /IM qemu-system-i386.exe");
	}
}
