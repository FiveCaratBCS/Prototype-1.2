package com.client;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Block")
@XmlType
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 5950169519310163575L;

	//private int    index;
	private UUID index_id;
    private String previous_Hash;
    private long   time_stamp;
    private Date date;
    private String date_format;
    private String data;
    private String hash;
    private int size;
    private String transaction_count;
    
    public Transaction() {
    	
    }

    public Transaction(UUID index_id, String previous_Hash, long times_tamp, String data, String hash) {
        this.index_id = index_id;
        this.previous_Hash = previous_Hash;
        this.time_stamp = time_stamp;
        this.data = data;
        this.hash = hash;
    }
    
	/*public int getIndex() {
		return index;
	}*/
	/*public void setIndex(int index) {
		this.index = index;
	}*/
	public UUID getIndex_id() {
		return index_id;
	}

	public void setIndex_id(UUID index_id) {
		this.index_id = index_id;
	}

	public String getPrevious_Hash() {
		return previous_Hash;
	}
	public void setPrevious_Hash(String previous_Hash) {
		this.previous_Hash = previous_Hash;
	}
	public long getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(long time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getTransaction_count() {
		return transaction_count;
	}

	public void setTransaction_count(String transaction_count) {
		this.transaction_count = transaction_count;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDate_format() {
		return date_format;
	}

	public void setDate_format(String date_format) {
		this.date_format = date_format;
	}

	public String toString() {
		return "index = " + getIndex_id() + " ; data = " + getData();
	}

}
