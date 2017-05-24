/*
 * Copyright (c) 2010-2020 IIS.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package ude.report.sample.ch01;

import java.util.EnumSet;
import java.util.Set;

import com.iisigroup.ude.report.DocumentFormat;

/**
 * 報表資訊清單
 */
public enum AllReports implements ReportDefinition {

    GSS0010("折抵役期空白清冊"), //
    GSS0011("折抵役期資料匯出", DocumentFormat.CSV), //
    ;

    private final String reportName;
    private final Set<DocumentFormat> suppertedFormats;

    private AllReports(final String reportName) {
        this.reportName = reportName;
        this.suppertedFormats = EnumSet.of(DocumentFormat.PDF, DocumentFormat.EXCEL);
    }

    private AllReports(final String reportName, final DocumentFormat first, final DocumentFormat... rest) {
        this.reportName = reportName;
        this.suppertedFormats = EnumSet.of(first, rest);
    }

    @Override
    public String getReportName() {
        return this.reportName;
    }

    @Override
    public String getReportCode() {
        return name();
    }

    @Override
    public Set<DocumentFormat> getSuppertedFormats() {
        return this.suppertedFormats;
    }

}