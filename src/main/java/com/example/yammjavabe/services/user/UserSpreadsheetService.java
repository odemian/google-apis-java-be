package com.example.yammjavabe.services.user;

import com.example.yammjavabe.utils.UserCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
 * UserSpreadsheetService is not a Spring service, because it has to be bind to the user token
 * ../request/SpreadsheetService is a request scope Spring service that inherits all functions from UserGmailService and can be used as part of Spring lifecycle
 * This is because in some cases we need to manually create token bind services (ex: merge runs on separate thread, and cannot access RequestScope components)
 */
public class UserSpreadsheetService {
    private static final String APPLICATION_NAME = "Google Sheets Example";
    private Sheets sheetsService;

    public UserSpreadsheetService (String bearerToken) throws Exception {
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
