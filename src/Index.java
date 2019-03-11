import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Index {

	public static void createMapFiles(LinkedList<Object> listOfFiles) throws IOException {
		FileWriter writer = new FileWriter(new File("F:/eclipse/workspace/Parser/MapingFiles/MappingFile1.txt"));
		FileWriter writerGlobal = new FileWriter(new File("F:/eclipse/workspace/Parser/MapingFiles/MappingFile.txt"));
		
		File previousFile = new File("");
		int count = 1, ok = 0;
		Map<String, Integer> myMap;
		ParserText t = new ParserText();
		t.getStopWords();
		
		while(!listOfFiles.isEmpty()) {
			File f = (File) listOfFiles.pop();
			String filePath = f.toString();
			String currentWriterName = "F:/eclipse/workspace/Parser/MapingFiles/MappingFile1.txt";
			
			if(!f.getParentFile().equals(previousFile.getParentFile()) && ok != 0) {
				count++;
				writer.close();
				currentWriterName = "F:/eclipse/workspace/Parser/MapingFiles/" + "MappingFile" + count + ".txt";
				writer = new FileWriter(new File(currentWriterName));
			}
				
			if(filePath.contains(".txt")) {
				File file = new File(currentWriterName);
				myMap = t.textParse(new File(filePath));
				writer.append(filePath + ", " + myMap + System.lineSeparator() + System.lineSeparator());
				writerGlobal.write(filePath + ", " + file.toString() + System.lineSeparator());
			}	

			previousFile = f;
			ok = 1;
		}
		writer.close();
		writerGlobal.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Object> listOfFiles;
		String fName = "";
		ParserText t = new ParserText();
		
	    System.out.println("Introduceti directorul : ");
	    Scanner in = new Scanner(System.in);
	    fName = in.nextLine();
	    
	    listOfFiles = t.listFiles(fName);

	    try {
			createMapFiles(listOfFiles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println(fName);
	    in.close();
		
	}
}
