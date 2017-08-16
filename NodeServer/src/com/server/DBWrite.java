package com.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class DBWrite {

	MongoClient mongo;
	DB db;
	DBCollection collection ;
	
	@SuppressWarnings("deprecation")
	public DBWrite()
	{
	
		mongo = new MongoClient(" localhost", 27017);
	
		//@SuppressWarnings("deprecation")
		db=mongo.getDB("Adriot");	
		
	}
	
	public void DBWriteToDB(Transaction block)
	{
		
		 BasicDBObject basicDBObject = new BasicDBObject();	 
		 DBCollection collection = db.getCollection("ProtoCollection");
		 //basicDBObject.put("index", block.getIndex());
		 basicDBObject.put("index_id",block.getIndex_id());
		 basicDBObject.put("previous_hash", block.getPrevious_Hash());
		 basicDBObject.put("timestamp", block.getTime_stamp());
		 basicDBObject.put("data", block.getData());
		 basicDBObject.put("nextHash", block.getHash());
		 WriteResult db1=collection.insert(basicDBObject);
	 
	}
	
	public List<Transaction> DBReadToDB() {
		
		System.out.println("111111");
		DBCollection collection = db.getCollection("ProtoCollection");
		DBCursor  cursorDocJSON=collection.find();
		//DBCursor  cursorDocJSON=collection.find().sort(new BasicDBObject("timestamp", -1));
		System.out.println("222222");
		
		//Block b1=new Block();
		List<Transaction> b = new ArrayList<Transaction>();
		
		
	 	while (cursorDocJSON.hasNext()) {
	 			//System.out.println( "cursorJSON " + cursorDocJSON.next());
		
	 		Transaction b1=new Transaction();
	 			DBObject obj = cursorDocJSON.next();
	 			//System.out.println( "cursorJSON1111 " + obj);
	 			
	 			b1.setData((String) obj.get("data"));
	 			b1.setHash((String) obj.get("nextHash"));
	 			//b1.setIndex((int) obj.get("index"));
	 			b1.setIndex_id((UUID) obj.get("index_id"));
	 			b1.setPrevious_Hash((String) obj.get("previous_hash"));
	 			b1.setTime_stamp((long) obj.get("timestamp"));
	 			//b1=(Block) cursorDocJSON.next();
	 			//System.out.println("666666"+b1.getData());
	 			
	 			b.add(b1);
	 		
	 		}
	 	
	 	//System.out.println("77777"+b.size());
		
		return b;
	}
	
}
