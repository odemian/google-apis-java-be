package com.example.yammjavabe.utils;

import com.example.yammjavabe.entities.MergeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeDataUtils {
    public static MergeData createMergeData (List<List<Object>> data) {
        MergeData mergeData = new MergeData();
        Map<String, Integer> headers = new HashMap<>();

        int length = data.get(0).size();
        for (int i = 0; i < length; i++) {
            String key = (String) data.get(0).get(i);
            headers.put(key.trim(), i);
        }

        List<List<Object>> sheetData = new ArrayList<>(data);
        sheetData.remove(0);

        mergeData.setHeaders(headers);
        mergeData.setData(sheetData);
        return mergeData;
    }
}
