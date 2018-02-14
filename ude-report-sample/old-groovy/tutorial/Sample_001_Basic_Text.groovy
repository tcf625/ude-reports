/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.font.CHTFontFactories
import com.iisigroup.ude.report.itext2.font.CHTFontFactory
import com.iisigroup.ude.report.itext2.font.FontInfo
import com.iisigroup.ude.report.itext2.font.FontStyle
import com.iisigroup.ude.report.itext2.font.ParagraphBuilder
import com.iisigroup.ude.report.itext2.font.WindowsFont
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.sample.SampleContent
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.DocumentAlign
import com.lowagie.text.PageSize

import java.awt.Color

import org.junit.Before
import org.junit.Test

public class Sample_001_Basic_Text extends AbstractITextSampleGroovy {

    String text = "中文，第二字面：「" + "𠀝" + "」(下/上)";

    @Before
    void setup() {
        this.setPageSize(PageSize.A4);
    }

    @Test
    public void test_NormalText()  {
        super.groovyReport { PDFDocument pdfDocument ->
            pdfDocument.writeText(text);
            pdfDocument.writeText(text + "Size:10", 10);
            pdfDocument.writeText(text + "Size:18", 18);
            pdfDocument.writeText(text, 10, DocumentAlign.RIGHT);
            pdfDocument.writeText(text, 10, DocumentAlign.CENTER);
            TableiText t0 = pdfDocument.createTable(50, 1);
            t0.addCell("TEST表格");
            t0.appendMe();

            final CHTFontFactory kaiFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.KAI);
            pdfDocument.setFontInfo(kaiFactory.createFontInfo(10, Color.BLUE));
            pdfDocument.writeText("轉成標楷體，無第二字面字體");
            pdfDocument.writeText(text);
            pdfDocument.writeText(text + "Size:10", 10);
            pdfDocument.writeText(text + "Size:18", 18);
            pdfDocument.writeText(text, 10, DocumentAlign.RIGHT);
            pdfDocument.writeText(text, 10, DocumentAlign.CENTER);

            TableiText t = pdfDocument.createTable(50, 1);
            t.addCell("TEST表格");
            t.appendMe();
        }
    }

    @Test
    void test_Builder(){
        super.groovyReport {PDFDocument pdfDocument ->
            final CHTFontFactory kaiFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.KAI);
            final FontInfo redFont = pdfDocument.getFontFactory().createFontInfo(10, FontStyle.STRIKETHRU, Color.RED);
            final FontInfo kaiFont = kaiFactory.createFontInfo(10, FontStyle.UNDERLINE, Color.BLUE, Color.LIGHT_GRAY);
            final ParagraphBuilder builder = pdfDocument.paragraphBuilder();
            builder.addText("中文，第二字面：") //
                    .addText("𠀝", redFont) //
                    .addText("(下/上)", kaiFont);
            builder.appendMe();
        }
    }
}