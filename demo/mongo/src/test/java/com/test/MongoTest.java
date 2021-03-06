package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class MongoTest {
	final static Logger logger = Logger.getLogger(MongoTest.class);
	static MongoClient mongoClient = null;
	DBCollection userColl = null;
	@BeforeClass
	public static void setupBeforeClass() throws UnknownHostException{
		mongoClient = new MongoClient("127.0.0.1", 27017);
	}
	@AfterClass
	public static void tearBeforeClass(){
		mongoClient.close();
	}
	@Before
	public void setup(){
		DB testDB = mongoClient.getDB("test");
		userColl = testDB.getCollection("user");
	}
	@Test
	public void testQuery() throws IOException {
		//1. test query
		DBObject query = (DBObject)JSON.parse(getStringFromResource("test.json"));
		DBCursor cursor = userColl.find(query);
		logger.info("query count : " + cursor.count());
		while(cursor.hasNext()){
			DBObject ret = cursor.next();
			logger.info("query result : " + ret.toMap());
		}
		
		//2. test aggregate
		BasicDBList aggregate = (BasicDBList)JSON.parse(getStringFromResource("aggregate.json"));
		logger.info("==========aggregate query input : " + aggregate);
		DBObject firstQ = (DBObject)aggregate.remove(0);
		AggregationOutput aop = userColl.aggregate(firstQ, aggregate.toArray(new DBObject[0]));
		logger.info("aggegate result: " + aop.getCommandResult().toMap());
	}
	
	@Test
	public void testUpdate() {
		//1.
		BasicDBObject query = new BasicDBObject().append("name", "lang")
				.append("age", new BasicDBObject("$lte", 30));
		BasicDBObject update = new BasicDBObject("$inc", new BasicDBObject("age", 1));
		WriteResult wr= userColl.update(query, update);
		logger.info("write result:" + wr);
		
		//2.
		DBCursor dbc  = userColl.find(new BasicDBObject("age" , new BasicDBObject("$type" , 2)));
		while(dbc.hasNext()){
			DBObject dbo = dbc.next();
			int age = Integer.parseInt((String)dbo.get("age"));
			userColl.update(dbo, new BasicDBObject("$set", new BasicDBObject("age", age)));
		}
	}
	
	@Test
	public void testInsert() {
		//BasicDBObject father = new BasicDBObject().append("name", "wenhui").append("age", "53").append("sex", "m");
		BasicDBObject mather = new BasicDBObject().append("name", "huayun").append("age", "49").append("sex", "f");
		userColl.insert(mather);
	}
	
	
	private String getStringFromResource(String str) throws IOException{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		return sb.toString();
	}
}
