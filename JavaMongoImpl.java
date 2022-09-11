 import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class JavaMongoImpl {
	public static void main(String[] args) {
		try {
			MongoClient mongoClient = MongoClients.create();
			System.out.println("connnected");
			MongoDatabase database = mongoClient.getDatabase("Org");
			MongoCollection<Document> collection = database.getCollection("Emp");
			Document document = new Document("Eid", "102")
					.append("Ename", "tanmay")
					.append("Esalary", 100);
					
					//Inserting document into the collection
					collection.insertOne(document);
					System.out.println("Document inserted successfully");
					
					// Retrieving a collection
					System.out.println("Collection sampleCollection selected successfully");
					Document document1 = new Document("title", "MongoDB")
					.append("description", "database")
					.append("likes", 100)
					.append("url", "http://www.tutorialspoint.com/mongodb/")
					.append("by", "tutorials point");
					Document document2 = new Document("title", "RethinkDB")
					.append("description", "database")
					.append("likes", 200)
					.append("url", "http://www.tutorialspoint.com/rethinkdb/")
					.append("by", "tutorials point");
					
					List<Document> list = new ArrayList<Document>();
					list.add(document1);
					list.add(document2);
					collection.insertMany(list);
					// Update document
					collection.updateOne(Filters.eq("title", "MongoDB"), Updates.set("likes", 150));       
				    System.out.println("Document update successfully..."); 
				    // Deleting the documents 
				      collection.deleteOne(Filters.eq("title", "MongoDB")); 
				      System.out.println("Document deleted successfully...");
				      
				   // Dropping a Collection 
				      collection.drop(); 
				      System.out.println("Collection dropped successfully");
			      // Get all collections
				      for (String name : database.listCollectionNames()) { 
				          System.out.println(name); 
				       }
					// Getting the iterable object
					FindIterable<Document> iterDoc = collection.find();
					int i = 1;
					// Getting the iterator
					Iterator it = iterDoc.iterator();
					while (it.hasNext()) {
						System.out.println(it.next());
						i++;
					}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
}
