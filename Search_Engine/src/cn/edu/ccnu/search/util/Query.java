package cn.edu.ccnu.search.util;
import java.io.*;
import org.apache.lucene.search.*;
import org.apache.lucene.index.Term;
import org.apache.lucene.document.*;

public class Query {
	private String INDEX_STORE_PATH = "E:/IR/workspace/Parser/indexes";
	private IndexSearcher searcher;
	private BooleanQuery query;
	
	public String[] getQueryResult(String[] keys) throws IOException{
		searcher = new IndexSearcher(INDEX_STORE_PATH);
		query = new BooleanQuery();
		if(keys == null){
			return null;
		}
		
		int length = keys.length;
		TermQuery[] term = new TermQuery[length];
		for(int i = 0; i < length; i++){
			term[i] = new TermQuery(new Term("context", keys[i]));
			query.add(term[i], BooleanClause.Occur.MUST);
		}
		
		//Sort sort = new Sort(new SortField("score"));
		Hits hits = searcher.search(query);
		length = hits.length();
		String[] result = new String[length];
		for(int i = 0; i < length; i++){
			Document doc = hits.doc(i);
			String tmp = doc.getField("title").stringValue();
			tmp = tmp + "|" + doc.getField("url").stringValue();
			tmp = tmp + "|" + doc.getField("context").stringValue();
			result[i] = tmp;
		}
		
		return result;
	}
	
	
}
