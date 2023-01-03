package com.example.yammjavabe.utils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateProcessingUtils {

    /**
     * Markers are case-sensitive
     */
    public static String createTemplate (String rawTemplate, Map<String, Integer> headers, List<Object> data) {
        Pattern pattern = Pattern.compile("\\{\\{(.*?)}}");
        Matcher matcher = pattern.matcher(rawTemplate);
        String finalTemplate = rawTemplate;
        while (matcher.find()) {
            try {
                String name = matcher.group(1).trim();
                Integer index = headers.get(name);
                if (index != null) {
                    finalTemplate = finalTemplate.replace(matcher.group(0), data.get(index).toString());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return finalTemplate;
    }
}
