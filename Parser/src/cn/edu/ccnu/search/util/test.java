package cn.edu.ccnu.search.util;
import java.io.IOException;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String keys = "ÔÆ";
		String[] results;
		
		try {
			results = new Query().getQueryResult(keys.split(""));
			
			System.out.println(results.length);
			for(int i = 0 ; i < results.length; i++)
				System.out.println(i + " " + results[i]);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}

}
