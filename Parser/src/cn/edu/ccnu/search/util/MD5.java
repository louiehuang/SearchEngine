package cn.edu.ccnu.search.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;;

public class MD5 {
	private final static String[] hexDigits = {
			"0","1","2","3","4","5","6","7","8","9",
			"a","b","c","d","e","f"};
	
	public static String byteArrayToHexString(byte[] b){
		StringBuffer result = new StringBuffer();
		for(int i = 0; i < b.length; i++){
			result.append(byteToHexString(b[i]));
		}
		return result.toString();
	}
	
	private static String byteToHexString(byte b){
		int n = b;
		if(n < 0){
			n = n + 256;
		}
		int d1 = n/16, d2 = n%16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	public static String MD5Encode(String origin){
		String resultString = null;
		try{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		}
		catch(NoSuchAlgorithmException e){
			System.err.println("No such Algorithm called \"MD5\"!");
		}
		return resultString;
	}
	
}
