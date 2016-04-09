package cn.edu.ccnu.search.page;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import cn.edu.ccnu.search.util.*;

public class PageLib {
	public static void store(Page page){
		String storepath = ProperConfig.getValue("files.path") + "/" + page.getSummary();
		
		if(new File(storepath).exists() == true){
			System.out.println("Messgae: " + storepath + "is existed!");
			return;
		}
		
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(storepath));
			
			writer.append(page.getUrl());
			writer.newLine();
			
			writer.append(page.getTitle());
			writer.newLine();
			
			writer.append(String.valueOf(page.getScore()));
			writer.newLine();
			
			writer.append(page.getContext());
			
			writer.close();
		}
		catch(IOException e){
			System.out.println("Error:Processing" + page.getUrl() + "occurs error");
			e.printStackTrace();
		}
	}
	
	
}
