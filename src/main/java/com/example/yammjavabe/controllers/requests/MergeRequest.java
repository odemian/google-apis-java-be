package com.example.yammjavabe.controllers.requests;

public class MergeRequest {
    private String draftId;
    private String userEmail;
    private String spreadsheetId;
    private Integer sheetId;
    private String emailHeader;

    public String getDraftId() {
        return draftId;
    }

    public MergeRequest setDraftId(String draftId) {
        this.draftId = draftId;
        return this;
    }

    public String getSpreadsheetId() {
        return spreadsheetId;
    }

    public MergeRequest setSpreadsheetId(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public MergeRequest setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public MergeRequest setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getEmailHeader() {
        return emailHeader;
    }

    public MergeRequest setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
        return this;
    }
}
