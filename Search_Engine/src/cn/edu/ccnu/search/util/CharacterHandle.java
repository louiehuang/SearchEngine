package cn.edu.ccnu.search.util;
import java.util.HashMap;

public class CharacterHandle {
	private static HashMap map = new HashMap();
	
	public static String trans(String line){
		map.put(" ", " ");
		map.put("£¬",",");
		map.put("¡£", ".");
		map.put("¡¶", "<");
		map.put("¡·", ">"); 
		map.put("¡´", "<"); 
		
		for(int i = 0; i < line.length(); i++){
			String charat = line.substring(i, i + 1);
			if(map.get(charat) != null){
				line = line.replace(charat, (String)map.get(charat));
			}
		}
		
		line = line.toLowerCase();
		return line;
	}
	
}
