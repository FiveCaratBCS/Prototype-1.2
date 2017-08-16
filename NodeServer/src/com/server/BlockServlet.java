package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

@WebServlet("/serverServlet")
public class BlockServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	BlockChain_Service blockService;
	
	ReadPropFile readPropFile = new ReadPropFile();
	
	DBWrite dw = new DBWrite();
	
	NodeServer nodeServer = new NodeServer();
	
	private static List<Transaction> bl = new ArrayList<Transaction>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			System.out.println("Server Calling....");
			
			try {
				nodeServer.servletConnection();
			} catch (InterruptedException | JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String blockdata = request.getParameter("block_input");
		
		System.out.println("Server Calling....");
		
		try {
			nodeServer.servletConnection();
		} catch (InterruptedException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("block data: " + blockdata);
		
		}
}
