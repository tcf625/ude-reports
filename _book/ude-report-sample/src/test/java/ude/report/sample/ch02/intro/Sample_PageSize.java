/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02.intro;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.iisigroup.ude.report.common.PoiDefaultSize;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.util.io.UdeFileUtils;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;

import ude.report.sample.AbstractSample;
import ude.report.sample.ch01.GSS0010;

/**
 * [二、頁面控制](documents/ch02/README.md)
 */
public class Sample_PageSize extends AbstractSample {

    @Test
    public void test_PDF_A3() throws IOException {
        // ! [TEST_INVOKE]
        final File pdfFile = super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A3);
            pdfDocument.writeText("A3");
        });
        // ! [TEST_ASSERT]
        try (BufferedInputStream in = UdeFileUtils.createInputStream(pdfFile)) {
            final PdfReader r = new PdfReader(in);
            final int numberOfPages = r.getNumberOfPages();
            Assert.assertEquals(1, numberOfPages);
            final Rectangle pageSize = r.getPageSize(1);
            Assert.assertEquals(pageSize.getWidth(), PageSize.A3.getWidth(), 0.01);
            Assert.assertEquals(pageSize.getHeight(), PageSize.A3.getHeight(), 0.01);
        }
    }

    @Test
    public void test_Excel_A3() {
        super.createExcel(document -> {
            final ExcelSheet<?> sheet = document.createSheet("sheet1");
            sheet.setPrintPageSize(PoiDefaultSize.A3);
        });
    }

    @Test
    public void test_MultiFormat() {
        final GSS0010 report = new GSS0010();
        super.createPDF(report);
        super.createExcel(report);
    }
}
