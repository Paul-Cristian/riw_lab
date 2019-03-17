import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class InvertedIndex {

	static Map<String, HashMap<String, Integer>> invertedIndex = new HashMap<String, HashMap<String, Integer>>();
	
	
	public static void main(String[] args) throws IOException {
		FileWriter writer = new FileWriter(new File("invertedIndex"));
		LinkedList<Object> listOfFiles;
		ParserText t = new ParserText();
		listOfFiles = t.listFiles("F:/eclipse/workspace/Parser/HTML body content files");
		
		
		HashMap<String, Map<String, Integer>> ind = Index.createMapFiles(listOfFiles);
		
		for(String fileName : ind.keySet()) {
			for(String word : ind.get(fileName).keySet()) {
				if(invertedIndex.containsKey(word)) {
					invertedIndex.get(word).put(fileName, ind.get(fileName).get(word));
				}else {
					HashMap<String, Integer> tempMap = new HashMap<String,Integer>();
					tempMap.put(fileName, ind.get(fileName).get(word));
					invertedIndex.put(word, tempMap);
				}
			}
		}
		for(String word : invertedIndex.keySet()) {
			writer.write(word+ "  " + invertedIndex.get(word) +System.lineSeparator());
		}
		
		writer.close();
	}
}
