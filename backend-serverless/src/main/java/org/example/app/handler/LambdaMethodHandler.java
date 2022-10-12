package org.example.app.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import org.example.app.model.Policy;
import org.example.app.model.Response;


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

    public Policy registerPolicy(Policy policyRegister, Context context) throws RuntimeException{

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        Policy checkExist = mapper.load(Policy.class, policyRegister.getName());
        if(checkExist == null){
            mapper.save(policyRegister);
            return policyRegister;
        }
        return policyRegister;
    }



}
