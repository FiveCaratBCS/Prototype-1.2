package com.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlockChain_Service {

	private static final long time_stamp = 0;

	private static List<Transaction> block = new ArrayList<Transaction>();
	
	private static UUID index_id = UUID.randomUUID();
	
	public static List<Transaction> getBlockChain()
	{
		//block = new ArrayList<Block>();
		
		return block;
	}
	
	/*public static Transaction getFirstBlock() {
		
		 Logic to calulate the first hash has to be in place
		 * SHould not be hardcoded
		 
		String nextHash = calculateHash(index_id, "0", System.currentTimeMillis(), "hello");
	  
	  
        return new Transaction(index_id, "0", System.currentTimeMillis(), "hello", nextHash);
    
    }*/
	
	public static Transaction generateNextBlock(String blockData) 
	{
		
		/*
		 * Get the block count here. If count == 0, create the first block
		 * else we will invoke the generateNextBlock()
		 */
		Transaction currentBlock;
		
		//System.out.println("sssssss1111");
		
		int blockCnt = block.size();
		
		//System.out.println("sssssss222"+blockCnt);
		
		if (blockCnt == 0)
		{
			
			long timestamp = System.currentTimeMillis();
			String nextHash = calculateHash(index_id, "0", time_stamp, blockData);
			currentBlock = new Transaction(index_id, "0", time_stamp, blockData, nextHash);
		}
		
		else
		{
			
			Transaction previousBlock = getLatestBlock();
		        
	        UUID nextIndex = UUID.randomUUID();
	        
	        long nextTimestamp = System.currentTimeMillis();
	        String nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData);
	        currentBlock =  new Transaction(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, nextHash);
		}
		
		block.add(currentBlock);
		
	    return currentBlock;
	    //add to db --> by calling DBWriteToDB
       //TODO:Rush
	   
    
	}
	
	private static String calculateHash(UUID index_id, String previous_Hash, long time_stamp, String data) {
        String index = index_id.toString();
		StringBuilder builder = new StringBuilder(index);
        builder.append(previous_Hash).append(time_stamp).append(data);
        return Sha256.getSHA_256(builder.toString());
    }
	
	public static Transaction getLatestBlock() {
		
		/* The DBCount has to be used to fetch the latest record from the DB 
		 * 
		 */
        return block.get(block.size() - 1);
    }
}
