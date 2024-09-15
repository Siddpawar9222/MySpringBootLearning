package com.example.thirdpartyapis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    // Convert String to JsonNode
    public static JsonNode convertStringToJsonNode(String jsonString) throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

    // convert String  to json Object
    private Object convertStringToJson(String response) throws JsonProcessingException {
        TypeReference<Object> typeRef = new TypeReference<>() {
        };
        Object jsonData = objectMapper.readValue(response, typeRef);
        return jsonData;
    }

    //convert String to json List
    private List<Object> convertStringToJsonList(String response) throws JsonProcessingException {
        TypeReference<List<Object>> typeRef = new TypeReference<>() {
        };
        List<Object> listJson = objectMapper.readValue(response, typeRef);
        return listJson;
    }

    // Above two methods can be written in one method using generics
    // Convert String to any type of Object
    public static <T> T convertStringToObject(String jsonString, TypeReference<T> typeRef) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, typeRef);
    }
}
