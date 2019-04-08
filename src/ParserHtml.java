import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserHtml {
	
	private static File inputFile;
	private static File outputFile1;
	private static File outputFile2;
	private static FileWriter writer;
	private static Document document;
	
	public ParserHtml(String htmlFileName) throws IOException {
		Path resultsPath = Files.createDirectories(Paths.get("ParserOutputs"));
		inputFile = new File(htmlFileName);
		outputFile1 = new File(resultsPath + "/output1.txt");
		outputFile2 = new File(resultsPath + "/output2.txt");
		document = Jsoup.parse(inputFile, null,"http://org.jsoup.com");
	}
	
	public void createWriter() throws IOException
	{
		writer = new FileWriter(outputFile1);
	}
	
	public void printTitle() {
		try {
			String title = document.title();
			
	        writer.write(title + System.lineSeparator());
	        
	        System.out.println("Titlul a fost scris in fisier cu succes.");
		} catch (IOException e) {
			  e.printStackTrace();
		  }	
	}
	
	public void printMeta() {
		try {
		 
			String keywords = document.select("meta[name=keywords]").get(0).attr("content");
			String description = document.select("meta[name=description]").get(0).attr("content");
			
			writer.append(keywords + System.lineSeparator());
			writer.append(description + System.lineSeparator());
	        
	        System.out.println("Keywords, description au fost scrise in fisier cu succes.");
		} catch (IOException e) {
			  e.printStackTrace();
		  }	
	}

	public void printTextBody() {
		try {

			String textBody = document.body().text();

			writer.write(textBody);
			writer.close();
			
			System.out.println("Textul a fost scris in fisier cu succes.");
		} catch (IOException e) {
			e.printStackTrace();
		    }
	}
	
	public void printHref() {
		  try {
			  
			  writer = new FileWriter(outputFile2);
			  String robots = document.select("meta[name=robots]").get(0).attr("content");
			  Elements links = document.select("a[href]");
            
			  writer.write(robots + System.lineSeparator());
			  for (Element link : links) {
				if(link.attr("abs:href").contains("#")) {
					continue;
				}
				else {
					writer.write("Link: " + link.attr("href") + System.lineSeparator());
					writer.write("Text: " + link.text() + System.lineSeparator());
				}
			  }
			  writer.close();
			  
			  System.out.println("Link-urile au fost scrise in fisier cu succes.");
		  } catch (IOException e) {
			  e.printStackTrace();
			  }
	}
}
