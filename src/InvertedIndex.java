import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class InvertedIndex {

	public static Map<String, HashMap<String, Integer>> invertedIndex = new HashMap<String, HashMap<String, Integer>>();
	
	public static Map<String, HashMap<String, Integer>> getInvertedIndex(LinkedList<File> listOfFiles, HashMap<String, Map<String, Integer>> mapper) throws IOException
	{
		FileWriter writer = new FileWriter(new File("invertedIndex.txt"));
		
		for(String fileName : mapper.keySet()) {
			for(String word : mapper.get(fileName).keySet()) {
				if(invertedIndex.containsKey(word)) {
					invertedIndex.get(word).put(fileName, mapper.get(fileName).get(word));
				}else {
					HashMap<String, Integer> tempMap = new HashMap<String,Integer>();
					tempMap.put(fileName, mapper.get(fileName).get(word));
					invertedIndex.put(word, tempMap);
				}
			}
		}
		for(String word : invertedIndex.keySet()) {
			writer.write(word+ "  " + invertedIndex.get(word) +System.lineSeparator());
		}
		writer.close();
		return invertedIndex;
	}
	
	/*public static void main(String[] args) throws IOException {
		FileWriter writer = new FileWriter(new File("invertedIndex"));
		LinkedList<Object> listOfFiles;
		DirectIndexer t = new DirectIndexer();
		listOfFiles = t.listFiles("F:/eclipse/workspace/Parser/HTML body content files");
		
		
		HashMap<String, Map<String, Integer>> ind = IndexMapper.createMapFiles(listOfFiles);
		
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
	}*/
}
