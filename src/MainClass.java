import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws IOException {
        /* 
         * Declaration 
         * parser,  ParserHtml object - extracts Title, Meta informations, Body content and links of a .html file
         * indexer, DirectIndexer object - gets stopwords, obtains files contained in a directory, performs direct indexing
         * searcher, Searcher object - performs search, using invertedIndex and a string given as parameters for search method
        */
        ParserHtml parser = new ParserHtml("aboutOpenCV.html");
        DirectIndexer indexer = new DirectIndexer();
        Searcher searcher = new Searcher();
        
        HashMap<String, Map<String, Integer>> mapper = new HashMap<>();
        Map<String, HashMap<String, Integer>> invertedIndex;
        LinkedList<File> listOfFiles;

        parser.createWriter();
        parser.printTitle();
        parser.printMeta();
        parser.printTextBody();
        parser.printHref();
        
        System.out.println("\nIntroduceti directorul de analizat(ParserOutputs = directorul cu rezultatele obtinute dupa parsarea html): ");
        Scanner in = new Scanner(System.in);
        String directoryName = in.nextLine();
        
        listOfFiles = indexer.listFiles(directoryName);        
        try {
            mapper = IndexMapper.createMapFiles(listOfFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        invertedIndex = IndirectIndexer.getInvertedIndex(listOfFiles, mapper);

        System.out.print("Search words: ");
        String searchInput = in.nextLine();
        searcher.search(invertedIndex, searchInput);
        
        in.close();
    }
}
