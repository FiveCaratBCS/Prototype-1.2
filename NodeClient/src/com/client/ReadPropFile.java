package com.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Properties;

public class ReadPropFile {
	
	public String readProp() throws IOException {
		
		InputStream in = ReadPropFile.class.getClassLoader().getResourceAsStream("config/bcconfig.config");
		
		System.out.println("inputStream is: " + in);
		
		Properties props = new Properties();
		props.load(in);
		
		String num = props.getProperty("block_value");
		
		System.out.println("Block value is: " + num);
		
		return num;
	}
	
	public HashSet<String> readIp() throws IOException {
		InputStream in2 = ReadPropFile.class.getClassLoader().getResourceAsStream("config/ip.config");
		Properties prop = new Properties();
		prop.load(in2);
		String[] sss =  prop.getProperty("ip").split(",");
		 HashSet<String> hst=new HashSet<String>();
		 
		 
		 
		 
		 
		System.out.println(sss.toString());
		
		for(String hs:sss) {
			//HashSet<InetSocketAddress> sAddressSet = new HashSet<InetSocketAddress>();
		
			
			//InetSocketAddress clientAddr=new InetSocketAddress(hs, 1111);
			hst.add(hs);	
			
		}
		return hst;
		
	}
	
	
}
