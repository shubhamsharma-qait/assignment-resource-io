package com.qainfotech.tap.training.resourceio;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
	public Object getOptionValue(String optionKey) throws IOException {
        //throw new UnsupportedOperationException("Not implemented.");
    

    	Properties p = new Properties();
    	
        Object obj = null;
    	
        
        try {
        	
    		   FileReader reader= new FileReader("src/main/resources/options.properties");

    		// load a properties file
    		p.load(reader);

    		// get the property value
    		obj = p.getProperty(optionKey);
 
    		
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    return obj;
    
    }
    

    public void addOption(String optionKey, Object optionValue) throws IOException {
      //  throw new UnsupportedOperationException("Not implemented.");

    	Properties prop = new Properties();
    	OutputStream output = null;
    	try {

    		output = new FileOutputStream("src/main/resources/options.properties");
    		//prop.load(output);
    		prop.setProperty("ResourceIOTest","true");
    		prop.setProperty("TestName","PropertiesOptionsIOTest");
    		//prop.setProperty("TestName","");
    		// set the properties value
    		prop.setProperty(optionKey, optionValue.toString());
    		prop.store(output, null);
    	}
    	 catch (IOException io) {
    			io.printStackTrace();
    		} finally {
    			if (output != null) {
    				try {
    					output.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    	

    }
    }
}
