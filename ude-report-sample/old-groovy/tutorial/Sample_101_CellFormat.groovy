/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.font.CHTFontFactory
import com.iisigroup.ude.report.itext2.font.CNS11643
import com.iisigroup.ude.report.itext2.font.FontInfo
import com.iisigroup.ude.report.itext2.font.FontStyle
import com.iisigroup.ude.report.itext2.font.SubPhrase
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.table.format.Border
import com.iisigroup.ude.report.table.format.CellFormat
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.format.CellFormat.AlignV
import com.lowagie.text.DocumentException
import com.lowagie.text.PageSize

import java.awt.Color

import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_101_CellFormat extends AbstractITextSampleGroovy {

    private static final CellFormat CF_BLANK = new CellFormat().setBorder(0);
    private static final CellFormat CF_BLANK_DEFAULT = new CellFormat().setBorder(-1).setBackgroundColor(Color.LIGHT_GRAY);
    private static final CellFormat CF_CELL  = new CellFormat().setFontSize(10).setBorderWidth(3).setBackgroundColor(Color.LIGHT_GRAY);
    @Before
    void setup() {
        setPageSize(PageSize.A5.rotate());
    }
    static void repeatCells(TableiText table, String text, int times) {
        for (int i = 0; i < times; i++) {
            table.addCell(text);
        }
    }
    static void repeatBlankCells(TableiText table, int times, CellFormat cf) throws DocumentException {
        for (int i = 0; i < times; i++) {
            table.addCell(" ", cf);
        }
    }

    //####################################################################
    //## [Method] sub-block : Border
    //####################################################################

    @Test
    void test_bgcolor() {
        setPageSize(PageSize.A4.rotate());
        this.groovyReport { PDFDocument pdfDocument->
            final TableiText table = pdfDocument.createTable(98, 16);
            table.getDefaultFormat().setFontSize(8)
            for (int r = 0;r<255;r+=16){
                for (int g = 0;g<255;g+=16){
                    for (int b = 0;b<255;b+=16){
                        final CellFormat cf = new CellFormat()
                                .setBackgroundColor(new Color(r,g,b));
                        table.addCell(String.format("%X%X%X",r,g,b), cf);
                    }
                }
            }
            table.appendMe();
        }
    }
    //####################################################################
    //## [Method] sub-block : Border
    //####################################################################

    @Test
    void test_border_show() {
        this.groovyReport { PDFDocument pdfDocument->


            float[] width = [10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2];
            int cols = width.length;

            final TableiText table = pdfDocument.createTable(98, width);
            repeatBlankCells(table, cols, CF_BLANK_DEFAULT);
            repeatBlankCells(table, cols, CF_BLANK);

            drawCellWithBorders(table, Border.NONE, Border.N);

            drawCellWithBorders(table, Border.TOP, Border.BOTTOM, Border.LEFT, Border.RIGHT);
            drawCellWithBorders(table, Border.T, Border.B, Border.L, Border.R);

            drawCellWithBorders(table, Border.TB, Border.LR, Border.TL, Border.TR, Border.BL, Border.BR);
            drawCellWithBorders(table, Border.BT, Border.RL, Border.LT, Border.RT, Border.LB, Border.RB);

            drawCellWithBorders(table, Border.NT, Border.NB, Border.NL, Border.NR);

            drawCellWithBorders(table, Border.ALL, Border.BOX, Border.A);

            table.appendMe();
        }
    }


    void drawCellWithBorders(TableiText table, Border... bs){
        bs.each {
            drawCellWithBorder(table, it);
        }
        repeatBlankCells(table, table.getWidths().length - bs.length*2, CF_BLANK);
        repeatBlankCells(table, table.getWidths().length, CF_BLANK);
    }

    void drawCellWithBorder(TableiText table, Border b){
        table.addCell("Border." + b.name(), CF_CELL.clone().setBorder(b));
        table.addCell("", CF_BLANK);
    }



    @Test
    void test_border() {
        this.groovyReport { PDFDocument pdfDocument->

            CellFormat bdCellFormat = new CellFormat().setFontSize(10).setBorderWidth(3).setBackgroundColor(Color.LIGHT_GRAY);

            float[] width = [10, 2, 10, 2, 10, 2, 10];
            final TableiText table = pdfDocument.createTable(90, width);
            pdfDocument.writeText(" ");
            for (int j = 0; j <= 15; j++) {
                //StringUtils.leftPad(Integer.toString(j, 2), 4, "0")
                StringBuilder text = new StringBuilder();
                appendBorderName(j, text, Border.TOP, "TOP");
                appendBorderName(j, text, Border.BOTTOM, "BOTTOM");
                appendBorderName(j, text, Border.LEFT, "LEFT");
                appendBorderName(j, text, Border.RIGHT, "RIGHT");
                table.addCell(text.toString(), bdCellFormat.clone().setBorder(j));
                if (j % 4 == 3) {
                    repeatBlankCells(table, 7, CF_BLANK);
                } else {
                    repeatBlankCells(table, 1, CF_BLANK);
                }
            }
            repeatBlankCells(table, 7, CF_BLANK_DEFAULT);
            table.appendMe();
        }
    }

    void appendBorderName(int j, StringBuilder text, final Border borderCode, final String borderName) {
        if (borderCode.matchAny(j)) {
            if (text.length()>0){
                text.append(" & ");
            }
            text.append(borderName);
        }
    }

    //####################################################################
    //## [Method] sub-block : Align
    //####################################################################

    @Test
    void test_align()  {
        this.groovyReport { PDFDocument pdfDocument->
            final TableiText table = pdfDocument.createTable(90, 5);
            for (AlignH alignH : AlignH.values()) {
                for (AlignV alignV : AlignV.values()) {
                    CellFormat cf = new CellFormat().setFontSize(8)
                            .setAlignH(alignH)
                            .setAlignV(alignV).setPadding(0);
                    cf.setMinHeightInCM(2f);
                    table.addCell( "垂直：" + alignV + "\n水平：" + alignH, cf);
                }
            }
            table.appendMe();
        }
    }

    @Test
    void test_font()  {
        this.groovyReport { PDFDocument pdfDocument->
            final TableiText table = pdfDocument.createTable(30, 1);
            CellFormat cf = new CellFormat();
            table.addCell("預設", 1);
            table.addCell("粗體", cf.clone().setFontBold(true));
            table.addCell("楷體", cf.clone().setFontType(CNS11643.KAI));
            table.addCell("粗楷體", cf.clone().setFontType(CNS11643.KAI).setFontBold(true));
            for (int size = 8; size <= 20; size += 2) {
                table.addCell("大小" + size, cf.clone().setFontSize(size));
            }
            table.appendMe();
            table.getTable().deleteBodyRows();
            pdfDocument.writeText("");

            final CHTFontFactory risFontFactory = pdfDocument.getFontFactory();
            final FontInfo textBgGray10B = risFontFactory.createFontInfo(10, FontStyle.NORMAL, Color.BLACK);
            final FontInfo textBgGray10R = risFontFactory.createFontInfo(10, FontStyle.UNDERLINE, Color.RED);
            table.addCell(
                    new SubPhrase(textBgGray10B, "多種樣式、特殊字、換行範例\n"),
                    new SubPhrase(textBgGray10R, "|𠀡|𠀖|" + "第２字面中文\n"),
                    new SubPhrase(textBgGray10B, "|𠀡|𠀖|\n") //
                    );
            table.appendMe();
        }

    }

    @Test
    void test_padding()  {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("以方框顯示 padding=0時的貼齊效果。");
            final TableiText table = pdfDocument.createTable(100, 3);
            for (AlignH alignH : AlignH.values()) {
                for (AlignV alignV : AlignV.values()) {
                    CellFormat cf = new CellFormat()
                    cf.setAlignH(alignH)
                    cf.setAlignV(alignV)
                    cf.setPadding(0);
                    cf.setFontSize(6);
                    if (alignV == AlignV.TOP) {
                        cf.setPaddingV(10);
                        cf.setPaddingT(30);
                    }
                    if (alignV == AlignV.BOTTOM) {
                        cf.setPaddingV(10); // 同時設定左右
                        cf.setPaddingB(30);
                    }
                    if (alignH == AlignH.LEFT) {
                        cf.setPaddingR(20);
                    }
                    if (alignH == AlignH.RIGHT) {
                        cf.setPaddingL(20);
                    }
                    if (alignH == AlignH.CENTER) {
                        cf.setPaddingH(20); // 同時設定上下
                        table.addCell(StringUtils.repeat(CHINESE_BLOCK, 22)
                                + "\n邊界測試" + toPaddingString(cf) + "\n"
                                + StringUtils.repeat(CHINESE_BLOCK, 20) + "結束", cf);
                    } else {
                        table.addCell(StringUtils.repeat(CHINESE_BLOCK, 24)
                                + "\n邊界測試" + toPaddingString(cf) + "\n"
                                + StringUtils.repeat(CHINESE_BLOCK, 22) + "結束", cf);
                    }
                }
            }
            table.appendMe();
        }

    }

    String toPaddingString(CellFormat cf){
        String x = ""
        x += "B:${cf.paddingB}," ;
        x += "T:${cf.paddingT}," ;
        x += "L:${cf.paddingL}," ;
        x += "R:${cf.paddingR}" ;
        return x;


    }

    //####################################################################
    //## [Method] sub-block : rowHeight
    //####################################################################


    @Test
    void test_rowHeight() {
        this.groovyReport { PDFDocument pdfDocument->

            pdfDocument.writeText("setMinHeightInCM=0，則以文字大小自動決定列高");
            writeRowHeight_min(pdfDocument, 0);

            pdfDocument.writeText("setMinHeightInCM=0.5");
            writeRowHeight_min(pdfDocument, 0.5f);

        }
    }

    private void writeRowHeight_min(PDFDocument pdfDocument, min) {
        final TableiText table = pdfDocument.createTable(100, 5);
        table.setDefaultFormat(new CellFormat().setMinHeightInCM(min).setPadding(0));
        for (int s = 4; s < 18; s += 2) {
            table.getDefaultFormat().setFontSize(s);
            table.addCell("fontSize", 1);
            table.addCell("" + s, 1);
            table.addCell(CHINESE_BLOCK, 1);
            table.addCell("輸出中文方框滿版測試", 2);
        }
        table.appendMe()
    }


    //== [Method] Block Stop
    //================================================
    //== [Inner Class] Block Start
    //== [Inner Class] Block Stop
    //================================================
}
