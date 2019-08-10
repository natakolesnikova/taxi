package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

public class DriverUtil {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    public static Map<String, String> getResponseMap(final String successfulMessage) {
        Map<String, String> response = new HashMap<>();
        response.put(OK, successfulMessage);
        response.put(ERROR, "");
        return response;
    }
}
