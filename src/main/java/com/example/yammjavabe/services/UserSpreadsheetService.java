package com.example.yammjavabe.services;

import com.example.yammjavabe.utils.UserCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Service
@RequestScope
public class UserSpreadsheetService {
    private static final String APPLICATION_NAME = "Google Sheets Example";
    private Sheets sheetsService;

    @Autowired
    public UserSpreadsheetService (HttpServletRequest request) throws Exception {
        System.out.println("Create new UserSpreadsheetService");
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        sheetsService = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), UserCredential.getGoogleCredential(bearerToken))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public List<MatchedValueRange> getValues (String spreadsheetId, Integer sheetId, Integer[] rect) throws IOException, GeneralSecurityException {
        DataFilter dataFilter = new DataFilter();

        GridRange gridRange = new GridRange();
        if (rect != null && rect.length >= 2) {
            gridRange.setStartRowIndex(rect[0]);
            gridRange.setStartColumnIndex(rect[1]);
        }
        if (rect != null && rect.length >= 4) {
            gridRange.setEndRowIndex(rect[2]);
            gridRange.setEndColumnIndex(rect[3]);
        }

        gridRange.setSheetId(sheetId);

        dataFilter.setGridRange(gridRange);

        List<DataFilter> ranges = Arrays.asList(dataFilter);

        BatchGetValuesByDataFilterRequest dataFilterRequest = new BatchGetValuesByDataFilterRequest();
        dataFilterRequest.setDataFilters(ranges);
        BatchGetValuesByDataFilterResponse response = sheetsService.spreadsheets()
                .values()
                .batchGetByDataFilter(spreadsheetId, dataFilterRequest)
                .execute();
        return response.getValueRanges();
    }
}
