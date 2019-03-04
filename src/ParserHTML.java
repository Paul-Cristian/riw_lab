import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserHTML {
	
	private static File inputFile = new File("index.html");
	private static File outputFile1 = new File("output1.txt");
	private static File outputFile2 = new File("output2.txt");
	private static FileWriter writer;
	private static Document document;
	
	public ParserHTML() throws IOException {
		// TODO Auto-generated constructor stub
		document = Jsoup.parse(inputFile, null,"http://org.jsoup.com");
	}
	public void createWriter() throws IOException
	{
		writer = new FileWriter(outputFile1);
	}
	
	private void printTitle() {
		try {
			//document = Jsoup.parse(inputFile, null,"http://org.jsoup.com");
			String title = document.title();
			
	        writer.write(title + System.lineSeparator());
	        
	        System.out.println("Titlul a fost scris in fisier cu succes.");
		} catch (IOException e) {
			  e.printStackTrace();
		  }	
	}
	
	public void printMeta() {
		try {
			//document = Jsoup.parse(inputFile, null,"http://org.jsoup.com");
		 
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
			//document = Jsoup.parse(inputFile, null,"http://org.jsoup.com");
			String textBody = document.body().text();

			writer.write(textBody);
			writer.close();
			
			System.out.println("Textul a fost scris in fisier cu succes.");
		} catch (IOException e) {
			e.printStackTrace();
		    }
	}
	
	private void printHref() {
		  try {
			  writer = new FileWriter(outputFile2);
			  //document = Jsoup.parse(inputFile, null,"http://org.jsoup.com");
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
	
	public static void main(String[] args) throws IOException {
		ParserHTML p = new ParserHTML();
		ParserText t = new ParserText();
		p.createWriter();
			
		p.printTitle();
		p.printMeta();
		p.printTextBody();
		p.printHref();
		
		t.getStopWords();
		t.listFiles();
		
		//t.textParse(new File("1.txt"));
	}
}
