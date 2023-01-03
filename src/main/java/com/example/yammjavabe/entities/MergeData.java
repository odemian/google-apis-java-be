package com.example.yammjavabe.entities;

import java.util.List;
import java.util.Map;

public class MergeData {
    private Map<String, Integer> headers;
    private List<List<Object>> data;

    public int getHeaderIndex (String header) {
        return headers.get(header);
    }

    public Map<String, Integer> getHeaders() {
        return headers;
    }

    public MergeData setHeaders(Map<String, Integer> headers) {
        this.headers = headers;
        return this;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public MergeData setData(List<List<Object>> data) {
        this.data = data;
        return this;
    }
}
