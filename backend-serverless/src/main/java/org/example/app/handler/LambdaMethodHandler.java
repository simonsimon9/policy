package org.example.app.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.example.app.model.Policy;
import org.example.app.model.Response;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.common.settings.Settings;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaMethodHandler {
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input);
        return "Hello World - " + input;
    }

    public Response getAll(){
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<Policy> scanResult = mapper.scan(Policy.class, scanExpression);



        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*"); // cors header, you can add another header fields
        headers.put("Access-Control-Allow-Methods", "GET");
        headers.put("Access-Control-Allow-Headers", "Content-Type");
        return new Response(200, headers, scanResult);

    }

    public Response registerPolicy(Policy policyRegister) throws RuntimeException{
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*"); // cors header, you can add another header fields
        headers.put("Access-Control-Allow-Methods", "OPTIONS, POST");
        headers.put("Access-Control-Allow-Headers", "Content-Type");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Policy checkExist = mapper.load(Policy.class, policyRegister.getName());
        if(checkExist == null){
            mapper.save(policyRegister);
            return new Response(200, headers, "success");
        }


        return new Response(200, headers, "no");
    }

    public String dynamoToOpen(DynamodbEvent ddbevent) throws IOException {

        String secretName = "opensearch-policy";
        Region region = Region.of("us-east-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();
        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretUserName = GetSecretValueRequest.builder()
                .secretId("username")
                .build();
        GetSecretValueRequest getSecretPassword = GetSecretValueRequest.builder()
                .secretId("password")
                .build();
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        GetSecretValueResponse userName = client.getSecretValue(getSecretUserName);
        GetSecretValueResponse password = client.getSecretValue(getSecretPassword);
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName.secretString(), password.secretString()));


        RestClientBuilder builder = RestClient.builder(new HttpHost("https://search-policies-execeoprwflm2pu3h5lke3u72a.us-east-1.es.amazonaws.com"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestHighLevelClient openClient = new RestHighLevelClient(builder);

//       for (DynamodbEvent.DynamodbStreamRecord record : ddbevent.getRecords()){
//
//       }
        //Create a non-default index with custom settings and mappings.
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("custom-index");

        createIndexRequest.settings(Settings.builder() //Specify in the settings how many shards you want in the index.
                .put("index.number_of_shards", 4)
                .put("index.number_of_replicas", 3)
        );
        //Create a set of maps for the index's mappings.
        HashMap<String, String> typeMapping = new HashMap<String,String>();
        typeMapping.put("type", "integer");
        HashMap<String, Object> ageMapping = new HashMap<String, Object>();
        ageMapping.put("age", typeMapping);
        HashMap<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("properties", ageMapping);
        createIndexRequest.mapping(mapping);
        CreateIndexResponse createIndexResponse = openClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        openClient.close();
        return "bro";
    }



}


