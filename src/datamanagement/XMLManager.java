package datamanagement;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import java.io.FileWriter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import java.io.IOException;
import org.jdom.JDOMException;

		public class XMLManager 
		{
			private static XMLManager self = null;
			private Document doc;// object of document class in java
			public static XMLManager getXML() // static function to get XML document objeccts
			{ 
			if (self == null ) self = new XMLManager(); return self;// initiating XMLManager to object self
			}
			private XMLManager() {init();
		}

		public void init() 
		{
			String s = AppProperties.getInstance().getProperties().getProperty("XMLFILE");// get the properties from XML file 
			try 
				{
					SAXBuilder b = new SAXBuilder();
					b.setExpandEntities(true);
					doc = b.build(s);}
			catch (JDOMException e) 
				{
					System.err.printf( "%s", "DBMD: XMLManager : init : caught JDOMException\n" );
			throw new RuntimeException("DBMD: XMLManager : init : JDOMException");
				} 
			catch (IOException e) 
				{
					System.err.printf( "%s", "DBMD: XMLManager : init : caught IOException\n" );
			throw new RuntimeException("DBMD: XMLManager : init : IOException");
				}  
		}      
		public Document getDocument() 
		{
			return doc;
		}
    
		public void saveDocument() // get the properties from XML file and save them in seperate document
		{
			String xmlfile = AppProperties.getInstance().getProperties().getProperty("XMLFILE");
			try (FileWriter fout = new FileWriter(xmlfile)) // FileWriter help to write within text file or document
			{
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(doc, fout);//output on text file or document
				fout.close();//closing file or document
			}
			catch (IOException ioe) 
			{
				System.err.printf( "%s\n", "DBMD : XMLManager : saveDocument : Error saving XML to " + xmlfile);
				throw new RuntimeException("DBMD: XMLManager : saveDocument : error writing to file");
			}
		}
		
}
