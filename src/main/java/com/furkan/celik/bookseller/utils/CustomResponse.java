package com.furkan.celik.bookseller.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author furkancelik
 **/

public class CustomResponse {

    public CustomResponse(Boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    private Boolean success;
    private Object data;
    private String message;

    public HashMap<String, Object> toHashMap(){
        HashMap<String, Object> map = new HashMap<>();

        map.put("success", this.success);
        if (data != null) map.put("data", this.data);
        map.put("message", this.message);

        return map;
    }
}
