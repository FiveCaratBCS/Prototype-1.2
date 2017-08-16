package com.client;

import java.security.MessageDigest;

public class Sha256 {
	
	private Sha256() {
		
    }
	
	public static String getSHA_256(String str) {
        MessageDigest messDigest;
        String encodeStr = "";
        try {
            messDigest = MessageDigest.getInstance("SHA-256");
            messDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2_Hex(messDigest.digest());
        } catch (Exception e) {
            System.out.println("getSHA256 is error" + e.getMessage());
        }
        return encodeStr;
    }
	
	private static String byte2_Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }

}
