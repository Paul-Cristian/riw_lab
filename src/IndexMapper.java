import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class IndexMapper {

	static HashMap<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
	
	public static HashMap<String, Map<String, Integer>> createMapFiles(LinkedList<File> listOfFiles) throws IOException {
		
		Path mappingFilesPath = Files.createDirectories(Paths.get("MappingFiles"));
		FileWriter writer = new FileWriter(new File(mappingFilesPath + "/MappingFile1.txt"));
		File previousFile = new File("");
		Map<String, Integer> directIndex = new HashMap<String, Integer>();
		int count = 1, ok = 0;
		
		DirectIndexer indexer = new DirectIndexer();
		indexer.getStopWords();
		
		while(!listOfFiles.isEmpty()) {
			File f = (File) listOfFiles.pop();
			String filePath = f.getAbsolutePath().toString();
			String currentWriterName = mappingFilesPath + "/MappingFile1.txt";
			
			if(!f.getParentFile().equals(previousFile.getParentFile()) && ok != 0) {
				count++;
				writer.close();
				currentWriterName = mappingFilesPath + "/MappingFile" + count + ".txt";
				writer = new FileWriter(new File(currentWriterName));
			}
				
			if(filePath.contains(".txt")) {
				directIndex = indexer.textParse(new File(filePath));
				map.put(filePath, directIndex);
				writer.append(filePath + ", " + directIndex + System.lineSeparator() + System.lineSeparator());
			}	

			previousFile = f;
			ok = 1;
		}
		
		writer.close();
		return map;
	}
}
