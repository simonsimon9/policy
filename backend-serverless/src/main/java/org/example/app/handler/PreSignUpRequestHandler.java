package org.example.app.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class PreSignUpRequestHandler implements RequestHandler {

    @Override
    public Object handleRequest(Object requestObject, Context context) {
        Map requestObjectMap = (Map) requestObject;

        Map<String, Object> responseData = (Map) requestObjectMap.get("response");
        responseData.put("autoConfirmUser", true);
        responseData.put("autoVerifyEmail", false);
        responseData.put("autoVerifyPhone", false);

        //logger here
        return requestObject;
    }
}
