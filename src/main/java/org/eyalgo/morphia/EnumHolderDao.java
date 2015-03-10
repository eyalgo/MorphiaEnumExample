package org.eyalgo.morphia;

import java.net.UnknownHostException;
import java.util.Iterator;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

public class EnumHolderDao extends BasicDAO<EnumHolder, ObjectId> {

	public EnumHolderDao(Datastore datastore) {
		super(datastore);
	}
	
	public void insertEnumHolder(EnumHolder input) {
		Query<EnumHolder> query = createQuery();
		getDs().updateFirst(query, input, true);
	}

	public Iterator<EnumHolder> queryByEnum(String theEnum) {
        Query<EnumHolder> query = createQuery();
        MyEnum myEnum = MyEnum.valueOf(theEnum); // we do not fail here...
        query.criteria("myEnum").equal(myEnum); // this is where we fail
        return query.iterator();
    }
	
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		
		
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		Datastore datastore = new Morphia().createDatastore(client, "db_for_tests");
		EnumHolderDao dao = new EnumHolderDao(datastore);
		if (false) {
			EnumHolder input = new EnumHolder();
			input.setMyEnum(MyEnum.AEnum);
			input.setOtherField("some string");
			dao.insertEnumHolder(input);
		}
//		if (true) return;
		
		
		Iterator<EnumHolder> iterator = dao.queryByEnum("AEnum");
		while (iterator.hasNext()) {
			EnumHolder enumHolder = iterator.next();
			System.out.println(enumHolder);
		}
		
		Thread.sleep(10000);
	}
}
