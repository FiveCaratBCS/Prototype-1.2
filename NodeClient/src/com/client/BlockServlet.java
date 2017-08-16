package com.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/clientServlet", asyncSupported = true)
public class BlockServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	BlockChain_Service blockService;
	
	ReadPropFile readPropFile = new ReadPropFile();
	
	DBWrite dw = new DBWrite();
	
	NodeClient nodeClient = new NodeClient();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			System.out.println("sssssss");
			 
			 List<Transaction> l = dw.DBReadToDB();
			
			 for(Transaction b : l) {
				 
				 long stamp = b.getTime_stamp();
				 Date date = new Date(stamp);
				 String sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(date);
				 b.setDate(date);
				 b.setDate_format(sdf);
				 
			 }
			 
			 Transaction b = new Transaction(); 
			
			System.out.println("33333"+l.size());
	  	    b.setSize(l.size());
	       
	  	    String tc = readPropFile.readProp();
	  	    b.setTransaction_count(tc);
	  	    System.out.println("transaction value: " + tc);
	  	  
	  	    request.setAttribute("block",b);
			request.setAttribute("list", l);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String blockdata = request.getParameter("block_input");
		
		System.out.println("block data: " + blockdata);
		
		//doGet(request, response);
		
		final AsyncContext asyncContext = request.startAsync(request, response);
		
		new Thread() {

		      @Override
		      public void run() {
		        try {
			          ServletResponse response = asyncContext.getResponse();
			          response.setContentType("text/plain");
			          PrintWriter out = response.getWriter();
			          try {
						Thread.sleep(2000);
			          } catch (InterruptedException e) {
					  
			        	  // TODO Auto-generated catch block
			        	  e.printStackTrace();
			          }
			          			  		
			  	    
			  	    try {
			        	
			        	  /*
			        	   * Write to DB
			        	   */
			  	    	Transaction b = new Transaction(); 
			    		
			    		b = BlockChain_Service.generateNextBlock(blockdata);
			    		
			    		DBWrite db = new DBWrite();
			    		
			    		db.DBWriteToDB(b);
			              
			              /*
			               * Request DIspatcher stuff here
			               */


			         
			              /*
			               * Iterate through the Hashset and send requests to the NodeServer
			               * 
			               */
			      		
			              nodeClient.clientConnection(b);
			              
			              doGet(request, (HttpServletResponse) response);
			              
			   	   	} catch (InterruptedException e) {
			              e.printStackTrace();
		          }
			         
			        
		          //out.print("Work completed. Time elapsed: " + (System.nanoTime() - startTime));
		          out.flush();
		          asyncContext.complete();
		        } catch (Exception e) {
	          throw new RuntimeException(e);
		        }
		      }
		    }.start();
		
		    
		}
}
