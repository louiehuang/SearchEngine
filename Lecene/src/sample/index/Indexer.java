package sample.index;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class Indexer {
	public static void main(String[] args){
		String indexPath = "index";
		String dataPath = "data";
		File indexDir = new File(indexPath);
		File dataDir = new File(dataPath);
		long start = new Date().getTime();
		int numIndexed = 0;
		
		try{
			numIndexed = index(indexDir, dataDir);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			long end = new Date().getTime();
			System.out.println("Indexing " + numIndexed + " files took " + (end-start) + "s");
		}	
	}
	
	public static int index(File indexDir, File dataDir) throws IOException{
		if(dataDir.exists() == false || dataDir.isDirectory() == false){
			throw new IOException(dataDir + " dose not exist or is not a directory");
		}
		IndexWriter writer = new IndexWriter(indexDir, new StandardAnalyzer(), true);
		writer.setUseCompoundFile(false);
		indexDirectory(writer, dataDir);
		int numIndexed = writer.docCount();
		writer.optimize();
		writer.close();
		return numIndexed;
	}
	
	private static void indexDirectory(IndexWriter writer, File dir) throws IOException{
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++){
			File f = files[i];
			if(f.isDirectory()){
				indexDirectory(writer, dir);
			}
			else if(f.getName().endsWith(".txt")){
				indexFile(writer, f);
			}
		}
	}
	
	private static void indexFile(IndexWriter writer, File f) throws IOException{
		if(f.isHidden() || !f.exists() || !f.canRead()){
			return;
		}
		else{
			System.out.println("Indexing " + f.getCanonicalPath());
			Document doc = new Document();
			doc.add(new Field("contents", new FileReader(f)));
			doc.add(new Field("filename", f.getCanonicalPath(), Field.Store.YES, Field.Index.TOKENIZED));
			writer.addDocument(doc);
		}
	}
}


