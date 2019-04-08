import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class MongoWriter {
	MongoClient client;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	List<DBObject> listMongo = new ArrayList<>();
	
	public MongoWriter(String collectionName) {
		client = new MongoClient();
		
		database = client.getDatabase("local");
		if(database.getCollection(collectionName) == null)
			database.createCollection(collectionName);
		
		collection = database.getCollection(collectionName);
		
	}

}
