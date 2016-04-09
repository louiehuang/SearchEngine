package sample.search;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {
	public static void main(String args[]){
		String indexPath = "index";
		String q = "impossible";
		
		//不能找含符号的，如"I'm"和"impossible."
		
		File indexDir = new File(indexPath);
		if(!indexDir.exists() || !indexDir.isDirectory()){
			System.out.println(indexDir + " dose not exist or is not a directory");
		}
		else{
			try{
				search(indexDir, q);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void search(File indexDir, String q) throws IOException{
		Directory fsDir = FSDirectory.getDirectory(indexDir);	
		IndexSearcher isearcher = new IndexSearcher(fsDir);
		Term t = new Term("contents", q);
		Query query = new TermQuery(t);
		long start = new Date().getTime();
		Hits hits = isearcher.search(query);
		long end = new Date().getTime();
		System.out.println("Found " + hits.length() + " document(s)(in " + (end-start) + 
				" s)that matched query '" + q + "'");
		for(int i =0; i < hits.length(); i++){
			Document doc = hits.doc(i);
			System.out.println(doc.get("filename"));
		}
		
		
	}
	
	
}
