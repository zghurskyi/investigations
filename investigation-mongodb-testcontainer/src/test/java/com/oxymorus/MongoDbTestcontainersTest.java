package com.oxymorus;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoDbTestcontainersTest {

    private static final String MONGO_IMAGE = "mongo:4.2.0-bionic";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AUTH_SOURCE_DB = "admin";
    private static final int MONGO_PORT = 27017;
    private static final String TEST_DATABASE = "testDatabase";
    private static final String TEST_COLLECTION = "testCollection";

    @ClassRule
    public static final GenericContainer<?> MONGODB = new GenericContainer<>(DockerImageName.parse(MONGO_IMAGE))
            .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)
            .withEnv("MONGO_INITDB_DATABASE", TEST_DATABASE)
            .withExposedPorts(MONGO_PORT);

    @Test
    public void shouldWriteAndReadMongoDocument() {
        ServerAddress serverAddress = new ServerAddress(MONGODB.getHost(), MONGODB.getMappedPort(MONGO_PORT));
        MongoCredential credential = MongoCredential.createCredential(USERNAME, AUTH_SOURCE_DB, PASSWORD.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();

        MongoClient mongoClient = new MongoClient(serverAddress, credential, options);
        MongoDatabase database = mongoClient.getDatabase(TEST_DATABASE);
        MongoCollection<Document> collection = database.getCollection(TEST_COLLECTION);

        Document expected = new Document("name", "foo").append("foo", 1).append("bar", "string");
        collection.insertOne(expected);

        Document actual = collection.find(new Document("name", "foo")).first();
        assertThat(actual).isEqualTo(expected);
    }
}