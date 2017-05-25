///*
// * Copyright (c) 2010-2020 IISI.
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of IISI.
// */
//package com.iisigroup.ude.report.itext2.sample;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.commons.csv.CSVPrinter;
//
//import com.iisigroup.ude.report.csv.CSVGenerator;
//import com.iisigroup.ude.report.excel.ExcelDocument;
//import com.iisigroup.ude.report.excel.ExcelGenerator;
//import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
//import com.iisigroup.ude.report.itext2.PDFDocument;
//import com.iisigroup.ude.report.itext2.PDFGenerator;
//import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
//import com.iisigroup.ude.report.itext2.marker.markinfo.MarkInfo;
//import com.lowagie.text.Rectangle;
//
//import tw.gov.moi.ae.report.RisReportGenerator;
//
///**
// * PDFGenerator : 使用各 SAMPLE 自定的 SampleContent 輸出頁面內容
// * 適用於基本功能測試輸出
// *
// * 因為iText功能展現不會像 APO 程式，寫特定的專用PDFGenerator，然後輸出測試應獨立於原 Reporter。
// * 而是純粹示範單一功能，用 inner anonymous class較方便把測試程式跟PDFGenerator寫在一起比較方便.
// *
// */
//public class PDFSampleGenerator extends AbstractPDFGenerator {
//
//
//    @Override
//    public void generatePDFContent(final PDFDocument pdfDocument) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public LayoutInfo prepareLayoutInfo(final Rectangle secPageSize) {
//        return null;
//    }
//
//    @Override
//    public void generateCSVContent(final CSVPrinter csvPrinter) throws IOException {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void generateExcelContent(final ExcelDocument<?, ?> document) {
//        // TODO Auto-generated method stub
//
//    }
//
//    //
//    //    private final SampleContent sampleContent;
//    //    private AbstractITextSample sample;
//    //
//    //    @Override
//    //    public LayoutInfo prepareLayoutInfo(final Rectangle pageSize) {
//    //        return this.sample.layoutInfo;
//    //    }
//    //
//    //    @Override
//    //    public List<? extends MarkInfo> prepareMarkInfos() {
//    //        return this.sample.markInfos;
//    //    };
//    //
//    //    /**
//    //     * constructor.
//    //     *
//    //     * @param abstractITextSample
//    //     */
//    //    public SampleGenerator(final AbstractITextSample sample) {
//    //        super();
//    //        //super.setLayoutInfo(sample.layoutInfo);
//    //        //super.setMarkInfos(sample.markInfos);
//    //        super.setPageSize(sample.pageSize);
//    //        super.setEncryptionInfo(sample.encryptionInfo);
//    //        this.sample = sample;
//    //        this.sampleContent = sample.sampleContent;
//    //    }
//    //
//    //    @Override
//    //    public void generatePDFContent(final PDFDocument pdfDocument) {
//    //        if (this.sampleContent != null) {
//    //            this.sampleContent.generatePDFContent(pdfDocument);
//    //        } else {
//    //            pdfDocument.writeText("無內容產生(sampleContent is null)");
//    //        }
//    //    }
//
//}