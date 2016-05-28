package Utilities;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.By;

public class Logger {
	
	Timestamp timestamp;
	Date date;
	Writer writer;
	
	public Logger() throws UnsupportedEncodingException, FileNotFoundException
	{
		date = new Date();
		timestamp = new Timestamp(date.getTime());
		System.out.println(date.toString());
		String URL = getCleanPath();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(URL + "Log -" + timestamp.getTime() + ".txt"), "utf-8"));
	}
	
	public void write(String content) throws Exception
	{
		writer.write(content);
	}

	public String getCleanPath() {
		String URL = System.getProperty("user.dir").toString() + "\\test-output\\";
		return URL;
	}
	
}
