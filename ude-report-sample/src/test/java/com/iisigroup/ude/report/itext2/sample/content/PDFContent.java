/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.content;

import com.iisigroup.ude.report.itext2.PDFDocument;

/**
 * 基本功能測試輸出介面
 */
public interface PDFContent {

    void generatePDFContent(final PDFDocument pdfDocument);

}