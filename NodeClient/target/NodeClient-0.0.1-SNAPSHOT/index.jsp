<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Block Application</title>

<style>
	body {
	  background: url("/docs/images/demo_bg8.jpg");
	  background-size: 100%;
	  background-repeat: no-repeat;
	}
	h2{
		color:#8B4513
	}
	textarea{
		
		border: 3px solid #a9c6c9;
		padding: 5px;
		font-family: Tahoma, sans-serif;
		
	}
	div#block{
		height: 100%;
		width: 100%;
		overflow:auto;
		border:3px solid #a9c6c9;
	}
	
	span#name{
	
	color:red;
	font-weight:bold;
	text-transform: uppercase;
	
	}
	span#value{
	
	color:blue;
	}
	input#button {
	    background-color: #4CAF50; /* Green */
	    border: none;
	    color: white;
	    padding: 10px 20px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	    margin: 4px 2px;
	    cursor: pointer;
	    border-radius: 10px;
	}
</style>

</head>
<body>

	<h1 style="color:red;"> Block Chain Prototype </h1>
	<br>
	
	
	<fieldset>
	
		<legend> <h2>  Transaction Data: </h2> </legend><br>
		
		<div id="div_block_input">
	
			<B>Enter Data Input </B>
			<br> <br>
			
			<form name="inarea" action="clientServlet" method="post">
			
				<textarea name="block_input" rows="4" cols="50" style="color:blue"></textarea>
				<br><br>
				
				<input id="button" type="submit" value="Submit"/> <br>
				
			</form>
			
		</div>
		
	</fieldset>
	
	<fieldset>
	
	
		<legend> 
		
			<h2>  Display Blocks Information: </h2><br>
			
		</legend>
		
		<div id="div_refresh">
			
			<c:if test = "${block.size == null}">
			
				<p>There are no Blocks...</p>
				
			</c:if>
			
			<c:if test = "${block.size != null}">
			
				<h3 style="display:none"> Blocks :  ${block.size} </h3>
				
				 
			
					<c:forEach items="${list}" var="l" varStatus="divCounter">
					
						
						<c:if test = "${divCounter.count % block.transaction_count == 1}">
						
							<div id="block">
							
						</c:if>    
						
								<p><span id="name">Index :</span> <span id="value">${l.index}</span>, 
								<span id="name">Data :</span> <span id="value">${l.data}</span>, 
								<span id="name">Hash :</span> <span id="value">${l.hash}</span>, 
								<span id="name">Previous Hash :</span> <span id="value">${l.previousHash}</span>, 
								
								<span id="name">Created Date :</span> <span id="value">${l.date_format}</span></p>
							
						<c:if test = "${divCounter.count % block.transaction_count == 0 || divCounter.count == block.size}">
							
							</div><br><br>
							
							
						</c:if>
						
					</c:forEach>
					
				
				
			</c:if><br>
	
			<form name="inarea" action="clientServlet" method="get">
		
				<input id="button" type="submit" value="Refresh"> <br>
				
			</form>
	
		</div>
		
	</fieldset>
	
	
</body>
</html>