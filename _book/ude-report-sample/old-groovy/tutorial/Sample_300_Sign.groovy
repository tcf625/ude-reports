/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.font.CHTFontFactories;
import com.iisigroup.ude.report.itext2.font.CHTFontFactory;
import com.iisigroup.ude.report.itext2.font.FontInfo;
import com.iisigroup.ude.report.itext2.font.FontStyle;
import com.iisigroup.ude.report.itext2.font.ParagraphBuilder;
import com.iisigroup.ude.report.itext2.font.WindowsFont;
import com.iisigroup.ude.report.itext2.header.AbstractPageHeader;
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy;
import com.iisigroup.ude.report.itext2.sample.SampleContent;
import com.iisigroup.ude.report.itext2.utils.SignatureUtils;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

class Sample_300_Sign extends AbstractITextSampleGroovy    {

    @Test
    void test()  {
//        File srcFile = super.groovyReport { PDFDocument pdfDocument ->
//            pdfDocument.writeText("檔案簽章測試 PAGE1");
//            pdfDocument.newPage();
//            pdfDocument.writeText("檔案簽章測試 PAGE2");
//        }
//        File destFile = UdeFileUtils.asSuffix(srcFile, "_signed");
//        SignatureUtils.sign(srcFile, destFile);
    }
}