package Utilities;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.Callable;


public class LatencyChecker 
{
	private Object object;
	Date beforeTime, afterTime;
	//private Timestamp beforeMethodCall, afterMethodCall;
	private int msGood;
	private int msWarning;
	
	public LatencyChecker(int good, int warning)
	{
		this.msGood = good;
		this.msWarning = warning;
				
	}

	public void getLatency(Method method, Object object, String str) throws Exception
	{
		beforeTime = new Date();
		// Method Call
		method.invoke(object,str);
		
		afterTime = new Date();
		Timestamp deltaTime= new Timestamp(afterTime.getTime() - beforeTime.getTime());
		System.out.println("Time Delta : " + deltaTime.toString());
		//return deltaTime;
	}
}
