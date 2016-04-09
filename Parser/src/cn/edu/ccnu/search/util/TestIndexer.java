package cn.edu.ccnu.search.util;
import java.io.*;
import cn.edu.ccnu.search.index.IndexBuilder;

public class TestIndexer {
	public static void main(String args[]){
		try{
			IndexBuilder index = new IndexBuilder(ProperConfig.getValue("index.path"));
			index.build(ProperConfig.getValue("files.path"));
		}
		catch(IOException e){
			System.out.println("Index creating failed!");
			e.printStackTrace();
		}
		System.out.println("Index creating is done!");
	}
}
