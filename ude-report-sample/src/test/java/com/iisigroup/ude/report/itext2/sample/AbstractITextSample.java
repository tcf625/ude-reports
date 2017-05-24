///*
// * Copyright (c) 2010-2020 IISI.
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of IISI.
// */
//package com.iisigroup.ude.report.itext2.sample;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
//import org.junit.After;
//import org.junit.Before;
//
//import com.iisigroup.ude.report.DocumentFormat;
//import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
//import com.iisigroup.ude.report.itext2.commons.EncryptionInfo;
//import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
//import com.iisigroup.ude.report.itext2.marker.markinfo.MarkInfo;
//import com.iisigroup.ude.report.itext2.testkit.AbstractITextTestkit;
//import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Rectangle;
//
///**
// * @author tsaicf
// */
//public abstract class AbstractITextSample extends AbstractITextTestkit {
//
//    //================================================
//    //== [Enumeration types] Block Start
//    //== [Enumeration types] Block End
//    //================================================
//    //== [static variables] Block Start
//
//    /** Logger Object. */
//    protected static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AbstractITextSample.class);
//
//    protected final static String CHINESE_BLOCK = "█";
//
//    //== [static variables] Block Stop
//    //================================================
//    //== [instance variables] Block Start
//
//    /** The pageSize. */
//    Rectangle pageSize;
//
//    /** The layoutInfo. */
//    LayoutInfo layoutInfo;
//
//    /** The markInfos. */
//    List<? extends MarkInfo> markInfos = Collections.<MarkInfo>emptyList();
//
//    /** The properties. */
//    EncryptionInfo encryptionInfo;
//
//    //####################################################################
//    //## [instance variables] sub-block :
//    //####################################################################
//
//    SampleContent sampleContent;
//
//    //== [instance variables] Block Stop
//    //================================================
//    //== [static Constructor] Block Start
//    //== [static Constructor] Block Stop
//    //================================================
//    //== [Constructors] Block Start (含init method)
//
//    /**
//     *
//     */
//    public AbstractITextSample() {
//        super(new ITextTestConfig());
//    }
//
//    @Before
//    public void mySetup() {
//        this.pageSize = PageSize.A4;
//        this.layoutInfo = null;
//        this.markInfos = Collections.emptyList();
//        this.encryptionInfo = null;
//        this.sampleContent = null;
//        getPdfManager();
//    }
//
//    @After
//    public void myTeardown() {
//        if (this.sampleContent != null) {
//            this.generateTestReport();
//            this.sampleContent = null;
//        }
//        this.sampleContent = null;
//    }
//
//    //== [Constructors] Block Stop
//    //================================================
//    //== [Static Method] Block Start
//    //####################################################################
//    //## [Static Method] sub-block :
//    //####################################################################
//    //== [Static Method] Block Stop
//    //================================================
//    //== [Accessor] Block Start
//
//    /**
//     * @param pageSize the pageSize to set
//     */
//    public void setPageSize(final Rectangle pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    /**
//     * @param layoutInfo the layoutInfo to set
//     */
//    public void setLayoutInfo(final LayoutInfo layoutInfo) {
//        this.layoutInfo = layoutInfo;
//    }
//
//    /**
//     * @param markInfos the markInfos to set
//     */
//    public void setMarkInfos(final MarkInfo... markInfo) {
//        this.markInfos = Arrays.asList(markInfo);
//    }
//
//    /**
//     * @param markInfos the markInfos to set
//     */
//    public void setMarkInfos(final List<? extends MarkInfo> markInfos) {
//        this.markInfos = markInfos;
//    }
//
//    /**
//     * @param properties the properties to set
//     */
//    public void setEncryptionInfo(final EncryptionInfo encryptionInfo) {
//        this.encryptionInfo = encryptionInfo;
//    }
//
//    /**
//     * @param contentGenerater the contentGenerater to set
//     */
//    public void setContentGenerater(final SampleContent contentGenerater) {
//        this.sampleContent = contentGenerater;
//    }
//
//    //== [Accessor] Block Stop
//    //================================================
//    //== [Overrided Method] Block Start (Ex. toString/equals+hashCode)
//    //== [Overrided Method] Block Stop
//    //================================================
//    //== [Method] Block Start
//
//    public File generateTestReport(final AbstractPDFGenerator msGenerator) {
//        final File outFile = super.createFileByTestName("", DocumentFormat.PDF);
//        final File tempFile = toTempFile(outFile);
//        if (tempFile != null) {
//            msGenerator.generatePDF(getPdfManager(), tempFile);
//            try {
//                FileUtils.deleteQuietly(outFile);
//                FileUtils.moveFile(tempFile, outFile);
//                return outFile;
//            } catch (final IOException e) {
//                return tempFile;
//            }
//        } else {
//            msGenerator.generatePDF(getPdfManager(), outFile);
//            return outFile;
//        }
//    }
//
//    /**
//     * @param outFile
//     * @return
//     * @throws IOException
//     */
//    private File toTempFile(final File outFile) {
//        try {
//            final String baseName = FilenameUtils.getBaseName(outFile.getName());
//            return File.createTempFile(baseName, ".pdf", outFile.getParentFile());
//        } catch (final IOException e) {
//            //
//        }
//        return null;
//    }
//
//    //####################################################################
//    //## [Method] sub-block : 轉接 ReportGenerator 的 setup method
//    //####################################################################
//
//    public File generateTestReport(final SampleContent content) {
//        this.sampleContent = content;
//        return generateTestReport();
//    }
//
//    public File generateTestReport() {
//        if (this.sampleContent != null) {
//            final SampleGenerator myGenerator = new SampleGenerator(this);
//            final File generateTestReport = generateTestReport(myGenerator);
//            this.sampleContent = null;
//            return generateTestReport;
//        } else {
//            return null;
//        }
//    }
//
//    //== [Method] Block Stop
//    //================================================
//
//}
