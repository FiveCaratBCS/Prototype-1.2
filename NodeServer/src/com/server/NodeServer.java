package com.server;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashSet;

 
public class NodeServer {
	
	
	public void servletConnection() throws IOException, InterruptedException, JAXBException {
		
		//We want to maintain a HashSet to store the IP Addresses
				HashSet<SocketAddress> sAddressSet = new HashSet<SocketAddress>();
				
				//Create a DBObject
				//Closing the connection in MOngoDB is a costly affair and so we keep the connection open
				DBWrite dbObject = new DBWrite();
				
				InetAddress ip = InetAddress.getLocalHost();
				
				System.out.println("server ip "+ip);
				
				String hostname = ip.getHostName();
				
				System.out.println("serverhostname "+hostname);
				
				
				// Selector: multiplexor of SelectableChannel objects
				Selector selector = Selector.open(); // selector is open here
		 
				// ServerSocketChannel: selectable channel for stream-oriented listening sockets
				ServerSocketChannel nodeSocket = ServerSocketChannel.open();
				InetSocketAddress nodeAddr = new InetSocketAddress(1111);
		 
				// Binds the channel's socket to a local address and configures the socket to listen for connections
				nodeSocket.bind(nodeAddr);
		 
				// Adjusts this channel's blocking mode.
				nodeSocket.configureBlocking(false);
		 
				int ops = nodeSocket.validOps();
				SelectionKey selectKy = nodeSocket.register(selector, ops, null);
				
				Transaction b = new Transaction();
		 
				// Infinite loop..
				// Keep server running
				while (true) {
		 
					log("i'm a server and i'm waiting for new connection with select...");
					// Selects a set of keys whose corresponding channels are ready for I/O operations
					selector.select();
		 
					//log("selector..."+selector.select());	
					
					// token representing the registration of a SelectableChannel with a Selector
					Set<SelectionKey> nodeSelectKeys = selector.selectedKeys();
					Iterator<SelectionKey> selectIterator = nodeSelectKeys.iterator();
		 
					//log("one");
					
					while (selectIterator.hasNext()) {
						
						//log("two");
						
						SelectionKey myKey = selectIterator.next();
						
						//log("three");
		 
						//count++;
						// Tests whether this key's channel is ready to accept a new socket connection
						if (myKey.isAcceptable()) {
							
							//log("four");
							SocketChannel nClient = nodeSocket.accept();
		 
							//log("five");
							
							// Adjusts this channel's blocking mode to false
							nClient.configureBlocking(false);
		 
							// Operation-set bit for read operations
							nClient.register(selector, SelectionKey.OP_READ);
							log("Connection Accepted: " + nClient.getLocalAddress() + "\n");
							
							//Add the address to the HashMap; duplicates not allowed
							sAddressSet.add(nClient.getLocalAddress());
							
							// Tests whether this key's channel is ready for reading
						} else if (myKey.isReadable()) {
							
							SocketChannel nClient = (SocketChannel) myKey.channel();
								
								ByteBuffer buffer = ByteBuffer.allocate(30000); //TODO:: Rush
								nClient.read(buffer);
								System.out.println("server buffer "+buffer);
								
							JAXBContext jc = JAXBContext.newInstance(Transaction.class);
							
							
							Unmarshaller u = jc.createUnmarshaller();
							
							String result = new String(buffer.array()).trim();
							
							System.out.println("String is..."+result);
							
							StreamSource streamSource = new StreamSource(new StringReader(result));
						
							JAXBElement<Transaction> je = u.unmarshal(streamSource,Transaction.class);
							
							
							b = je.getValue();
							
							System.out.println(b.getData());
							
							nClient.close();

							dbObject.DBWriteToDB(b);
						
						}
						selectIterator.remove();
					}
					
					
				}
				
				
			}
		 
			private static void log(String str) {
				System.out.println(str);
			}
}