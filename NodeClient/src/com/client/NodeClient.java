package com.client;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class NodeClient {
	
	BlockChain_Service blockService;
	
	public void clientConnection(Transaction block) throws IOException, InterruptedException, JAXBException {
 
		//InetSocketAddress clientAddr = new InetSocketAddress("192.168.1.136", 1111);
		
		//We want to maintain a HashSet to store the IP Addresses
	/*	HashSet<InetSocketAddress> sAddressSet = new HashSet<InetSocketAddress>();
		InetSocketAddress clientAddr = new InetSocketAddress("192.168.1.136", 1111);
		InetSocketAddress clientAddr1 = new InetSocketAddress("10.0.0.31", 1111);
		sAddressSet.add(clientAddr);
		sAddressSet.add(clientAddr1);*/
		InputStream in = ReadPropFile.class.getClassLoader().getResourceAsStream("config/ip.config");
		
		
		Properties props = new Properties();
		props.load(in);
		/*List<InetSocketAddress> clientAddr1=new ArrayList<>();
		//String clientAddr1 = props.getProperty("set");
		InetSocketAddress clientAddr=new InetSocketAddress(clientAddr1, 1111);
		sAddressSet.add(clientAddr);*/
		//String[] str = props.getProperty("ip").split(",");
		ReadPropFile rpf=new ReadPropFile();
		HashSet<String> str1=rpf.readIp();
		
		
		for(String st:str1) {
			HashSet<InetSocketAddress> sAddressSet1 = new HashSet<InetSocketAddress>();
			InetSocketAddress ins=new InetSocketAddress(st, 1111);

			sAddressSet1.add(ins);
		
		
		InetAddress ip = InetAddress.getLocalHost();
		
		System.out.println("Host ip "+ip);
		
		String hostname = ip.getHostName();
		
		System.out.println("hostname "+hostname);
		
		
		System.out.println("Connecting to Server...");
		
		
		
		for(InetSocketAddress c:sAddressSet1) {
			
			
		
		SocketChannel client = SocketChannel.open(c);
		
	
		System.out.println("Connecting to Server on port 1111...");
		
		System.out.println("dataaaaa"+block.getData());
		System.out.println("hash"+block.getHash());
		System.out.println("index"+block.getIndex_id());
		
		
		JAXBContext context = JAXBContext.newInstance(Transaction.class);
		
		Marshaller m = context.createMarshaller();
		System.out.println("111111111111111...");
		
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		System.out.println("22222222222222...");
		
		StringWriter w = new StringWriter();
		
		m.marshal(block, w);
		
		//m.marshal(block, new File("C:/Users/Burgeon11/eclipse-workspace/NodeClient/src/config/block.xml"));

		String s = w.toString();
        System.out.println("ssssssssss"+s);
        
        byte[] message =new String(s).getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(message);
		System.out.println("buffer"+buffer);
		client.write(buffer);
       
		//client.write(buffer);
			
		// wait for 2 seconds before sending next message
			//Thread.sleep(2000);
	 
			//System.out.println("buffer"+buffer);
		
		client.close();
		}
		}
	}
	
}