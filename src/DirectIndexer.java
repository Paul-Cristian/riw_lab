import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DirectIndexer {
	private List<String> stopWordsList;
	public void getStopWords()
	{
		File stopWordsFile = new File("StopWords.txt");
		stopWordsList = new ArrayList<>();
		
		try {
			Scanner scan = new Scanner(stopWordsFile);
			while(scan.hasNext()) {
				String line = scan.nextLine();
				stopWordsList.add(line.toString());
			}
			//System.out.println(stopWordsList);
			scan.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public LinkedList<File> listFiles(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		LinkedList<Object> queueDirectories = new LinkedList<>();
		LinkedList<File> queueFiles = new LinkedList<>();

		do {
			if(!queueDirectories.isEmpty())
				path = queueDirectories.pop().toString();
			
			folder = new File(path);

			if(folder.listFiles() != null)
				listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					queueFiles.add(file);
				}
				else if(file.isDirectory()) {
					queueDirectories.add(file);
				}
			}
		} while(!queueDirectories.isEmpty());

		return queueFiles;
//		while(!queueFiles.isEmpty()) {
//			textParse(new File(queueFiles.pop().toString()));
//		}
	}
	
	public Map<String, Integer> textParse(File file) 
	{
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		String dictionar = "";
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				String line = scan.nextLine().toLowerCase();
				StringBuilder word = new StringBuilder();
				for(int i = 1; i < line.length() - 1; ++i) {
					if(Character.isLetterOrDigit(line.charAt(i))) {
						word.append(line.charAt(i));
					}
					else if(line.charAt(i) == '\'' && Character.isLetterOrDigit(line.charAt(i+1)) && Character.isLetterOrDigit(line.charAt(i - 1))) {
						word.append(line.charAt(i));
					}else {
						if(word.length() > 0 && !stopWords(word.toString())) {
							dictionar = word.toString();
							if(!myMap.containsKey(dictionar)) {
								myMap.put(dictionar, 1);
							}
							else {
								myMap.put(dictionar, myMap.get(dictionar) + 1);
							}
						}
						word.setLength(0);
					}
				}
			}
			/*if(!myMap.isEmpty())
				System.out.println(myMap);*/
			scan.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return myMap;
	}

	public static int stringCompare(String str1, String str2) 
	{ 
		if (str1.length() < str2.length()) { 
			return -1; 
        }  
        else if (str1.length() > str2.length()) { 
        	return -1; 
        }
        else if (str1.length() == str2.length()) {
			for (int i = 0; i < str1.length() &&  i < str2.length(); i++) { 
				if ((int)str1.charAt(i) ==  (int)str2.charAt(i)) { 
					continue; 
				}  
				else { 
					return -1; 
				} 
			}
        }
		return 0; 
	} 
	
	public boolean stopWords(String s) {
		//FileWriter same = new FileWriter(new File("same.txt"));		
		//System.out.println(stopWordsList);
		
		for(String str : stopWordsList) {
			if(stringCompare(str, s) == 0) {
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}
}
