package datamanagement;
import java.util.*;
import java.io.*;

		public class AppProperties 
		{
			private static AppProperties self = null;//static object for app proprties
			private Properties properties;

		public static AppProperties getInstance() 
		{
			if (self == null ) 
		{ 
			self = new AppProperties(); //initiating AppProperties object in case its not null
		} 
			return self;
		}
		private AppProperties() 
		{
			properties = new Properties();
			try 
			{
				properties.load(new FileInputStream("Properties.prop"));//loading properties from file properties.prop 
			} 
			catch (IOException e) 
			{
				throw new RuntimeException("Could not read property file");// throw exception in case file not found
			}
		}
		public Properties getProperties()
		{
			return properties;//return properties getting from file
		}
}
