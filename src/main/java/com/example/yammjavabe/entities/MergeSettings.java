package com.example.yammjavabe.entities;

public class MergeSettings {
    private String spreadsheetId;
    private Integer sheetId;
    private String draftId;
    private String emailHeader;

    public String getSpreadsheetId() {
        return spreadsheetId;
    }

    public MergeSettings setSpreadsheetId(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public MergeSettings setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    public String getDraftId() {
        return draftId;
    }

    public MergeSettings setDraftId(String draftId) {
        this.draftId = draftId;
        return this;
    }

    public String getEmailHeader() {
        return emailHeader;
    }

    public MergeSettings setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
        return this;
    }
}
