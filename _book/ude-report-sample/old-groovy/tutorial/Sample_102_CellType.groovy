/*dfc
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.InnerTable
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.table.format.Border
import com.iisigroup.ude.report.table.format.CellFormat
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.format.CellFormat.AlignV
import com.iisigroup.ude.report.table.format.celltype.ExtraLines
import com.iisigroup.ude.report.table.format.celltype.LinePattern
import com.iisigroup.ude.report.table.format.celltype.TrisectionTitle
import com.lowagie.text.DocumentException
import com.lowagie.text.PageSize
import com.lowagie.text.pdf.PdfContentByte

import java.awt.Color

import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_102_CellType extends AbstractITextSampleGroovy {

    private static final CellFormat CF_BLANK = new CellFormat().setBorder(0);
    private static final CellFormat CF_BLANK_DEFAULT = new CellFormat().setBorder(-1).setBackgroundColor(Color.LIGHT_GRAY);

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
    //## [Method] sub-block :
    //####################################################################

    @Test
    void test_Borders() {
        this.groovyReport { PDFDocument pdfDocument ->
            //float[] dashUnits = [10f, 15];
            LinePattern baseLinePtn = new LinePattern(LinePattern.Mode.PADDING, Border.BOX)
                    .setOffset(0)
                    .setColor(Color.RED)
            //.setDashUnits(dashUnits)
            ;
            final float[] widths = [1, 8, 1, 8, 1, 8, 1, 8, 1]
            final TableiText table = pdfDocument.createTable(90, widths);
            table.getDefaultFormat().setFontSize(8).setPadding(10).setBackgroundColor(Color.LIGHT_GRAY).setBorder(15);

            def bb = baseLinePtn.clone().setMode(LinePattern.Mode.BORDER)
            def bp = baseLinePtn.clone().setMode(LinePattern.Mode.PADDING)
            def bbb = baseLinePtn.clone().setMode(LinePattern.Mode.BORDER_BOX)
            def bpb = baseLinePtn.clone().setMode(LinePattern.Mode.PADDING_BOX)


            table.addCell("", new CellFormat().setBorder(0), 9);

            table.addCell("", new CellFormat().setBorder(0))
            addBorderCell(table, CHINESE_BLOCK + "BORDER       \n兩端對齊外框", bb);
            addBorderCell(table, CHINESE_BLOCK + "PADDING      \n兩端對齊外框留白", bp);
            addBorderCell(table, CHINESE_BLOCK + "BORDER_BOX   \n兩端隨OFFSET縮放       ", bbb);
            addBorderCell(table, CHINESE_BLOCK + "PADDING_BOX  \n兩端隨OFFSET縮放          ", bpb);
            table.addCell("", new CellFormat().setBorder(0), 9);

            table.addCell("", new CellFormat().setBorder(0))
            addBorderCell(table, "  offset 2         ", bb.clone().setOffset(2));
            addBorderCell(table, "  offset 2         ", bp.clone().setOffset(2));
            addBorderCell(table, "  offset 2         ", bbb.clone().setOffset(2));
            addBorderCell(table, "  offset 2         ", bpb.clone().setOffset(2));
            table.addCell("", new CellFormat().setBorder(0), 9);

            table.addCell("", new CellFormat().setBorder(0))
            addBorderCell(table, "  offset -2        ", bb.clone().setOffset(-2));
            addBorderCell(table, "  offset -2        ", bp.clone().setOffset(-2));
            addBorderCell(table, "  offset -2        ", bbb.clone().setOffset(-2));
            addBorderCell(table, "  offset -2        ", bpb.clone().setOffset(-2));
            table.addCell("", new CellFormat().setBorder(0), 9);

            table.appendMe();
        }
    }

    private void addBorderCell(final TableiText table, final String text, LinePattern baseLinePtn ) {
        table.addCell(text, new CellFormat().setCellType(new ExtraLines(baseLinePtn)));
        table.addCell(""  , new CellFormat().setBorder(0))
    }


    @Test
    void test_DottedBorders() {
        this.groovyReport { PDFDocument pdfDocument ->
            pdfDocument.writeText("");

            float[] dashUnits = [2f, 2f];
            LinePattern baseLinePtn = new LinePattern(LinePattern.Mode.BORDER, Border.BOX)
                    .setOffset(0)
                    .setColor(Color.BLUE)
                    .setDashUnits(dashUnits);

            final TableiText table = pdfDocument.createTable(50, 4);
            table.getDefaultFormat().setBorder(0).setCellType(new ExtraLines(baseLinePtn));
            table.addCell("TEST");
            table.addCell("TEST");
            table.addCell("TEST",2);
            InnerTable inner =  table.createInnerTable(0, 1);
            inner.addCell("TEST",3);
            inner.addCell("TEST",3);
            inner.appendMe();
            table.addCell("TEST");
            table.appendMe();
        }
    }


    @Test
    void test_DoubleUnderlines() {

        this.groovyReport { PDFDocument pdfDocument ->
            float[] dashUnits = [2f, 2f];

            LinePattern line1 =
                    new LinePattern(LinePattern.Mode.PADDING, Border.B)
                    .setOffset(0)
                    .setLengthDx(1)
                    .setColor(Color.BLUE);
            LinePattern line2 = line1.clone()
                    .setOffset(-1);


            final TableiText table = pdfDocument.createTable(90, 4);
            table.getDefaultFormat()
                    .setBorder(Border.N)
                    .setPadding(6)
                    .setAlignH(AlignH.JUSTIFIED_ALL);
            CellFormat headerCF = new CellFormat()
                    .setCellType(new ExtraLines(line1,line2));
            table.addCell("HEADER" , headerCF);
            table.addCell("HEAD" , headerCF);
            table.addCell("HEADER" , headerCF);
            table.addCell("HEAD" , headerCF);

            table.addCell(CHINESE_BLOCK);
            table.addCell(CHINESE_BLOCK);
            table.addCell(CHINESE_BLOCK);
            table.addCell(CHINESE_BLOCK);
            table.appendMe();
        }
    }





    @Test
    void test_Borders_Helix() {
        this.groovyReport { PDFDocument pdfDocument ->
            float[] dashUnits = [2f, 1f];
            LinePattern p1 = new LinePattern(LinePattern.Mode.PADDING_BOX, Border.BOTTOM).setColor(new Color(240,0,0)).setDashUnits(dashUnits).setCap(PdfContentByte.LINE_CAP_ROUND);
            LinePattern p2 = new LinePattern(LinePattern.Mode.PADDING_BOX, Border.LEFT)  .setColor(new Color(230,0,0)).setDashUnits(dashUnits).setCap(PdfContentByte.LINE_CAP_ROUND);
            LinePattern p3 = new LinePattern(LinePattern.Mode.PADDING_BOX, Border.TOP)   .setColor(new Color(200,0,0)).setDashUnits(dashUnits).setCap(PdfContentByte.LINE_CAP_ROUND);
            LinePattern p4 = new LinePattern(LinePattern.Mode.PADDING_BOX, Border.RIGHT) .setColor(new Color(210,0,0)).setDashUnits(dashUnits).setCap(PdfContentByte.LINE_CAP_ROUND);
            def lines = []
            for (i in 0 .. 25) {
                lines.add(p1.clone().setOffset(i*2+0));
                lines.add(p2.clone().setOffset(i*2+0.5));
                lines.add(p3.clone().setOffset(i*2+1));
                lines.add(p4.clone().setOffset(i*2+1.5));
            }

            final TableiText table = pdfDocument.createTable(80, 1);
            final CellFormat baseCF = new CellFormat().setAlignH(AlignH.CENTER)
                    .setAlignV(AlignV.MIDDLE)
                    .setMinHeightInCM(10)
                    .setPadding(10);
            table.addCell(CHINESE_BLOCK + "實驗品", baseCF.clone().setCellType(new ExtraLines(lines)));
            table.appendMe();

        }
    }

    @Test
    void test_Borders_growth_ring() {
        this.groovyReport { PDFDocument pdfDocument ->
            float[] dashUnits = [2f, 1f];
            LinePattern p1 = new LinePattern(LinePattern.Mode.PADDING_BOX, Border.BOX)
                    .setColor(new Color(240,0,0))
                    .setWidth(1.3f)
                    //.setDashUnits(dashUnits)
                    .setCap(PdfContentByte.LINE_CAP_ROUND);
            def lines = []
            for (i in 0 .. 40) {
                lines.add(p1.clone().setOffset(i * 2.8));
            }

            final TableiText table = pdfDocument.createTable(80, 1);
            final CellFormat baseCF = new CellFormat().setAlignH(AlignH.CENTER)
                    .setAlignV(AlignV.MIDDLE)
                    .setMinHeightInCM(10)
                    .setPadding(10);
            table.addCell(CHINESE_BLOCK + "實驗品", baseCF.clone().setCellType(new ExtraLines(lines)));
            table.appendMe();

        }
    }



    //####################################################################
    //## [Method] sub-block :
    //####################################################################



    @Test
    void test_TrisectionTitle() {
        this.groovyReport { PDFDocument pdfDocument ->
            final TableiText table = pdfDocument.createTable(90, 6);
            table.addCell("傳入參數");
            table.addCell("輸出結果");
            table.addCell("文字表示");
            table.addCell("傳入參數");
            table.addCell("輸出結果");
            table.addCell("文字表示");
            addTrisectionCell(table, new TrisectionTitle("地區", "人數", "年度"));
            addTrisectionCell(table, new TrisectionTitle("地區", "人數", null));
            addTrisectionCell(table, new TrisectionTitle("地區", null, "年度"));
            addTrisectionCell(table, new TrisectionTitle("地區", null, null));
            addTrisectionCell(table, new TrisectionTitle(null, "人數", "年度"));
            addTrisectionCell(table, new TrisectionTitle(null, "人數", null));
            addTrisectionCell(table, new TrisectionTitle(null, null, "年度"));
            addTrisectionCell(table, new TrisectionTitle(null, null, null));
            table.appendMe();

            pdfDocument.newPage();
            pdfDocument.writeText("三等分欄位的特別案例")
            float[] widths = [3, 5, 5, 8]
            final TableiText table2 = pdfDocument.createTable(90, widths);
            table2.setSpacingBefore(24);
            table2.addCell("說明");
            table2.addCell("傳入參數");
            table2.addCell("輸出結果");
            table2.addCell("文字表示");
            table2.addCell("較長");
            addTrisectionCell(table2, new TrisectionTitle("地區地區地區地區", "人數", "各年度"));
            table2.addCell("較小字型");
            addTrisectionCell(table2, new TrisectionTitle("地區地區地區地區", "人數", "各年度"),6);
            table2.addCell("換行");
            addTrisectionCell(table2, new TrisectionTitle("地區\n地區", "人數", "年度\n年度"));
            table2.appendMe();
        }
    }

    private void addTrisectionCell(final TableiText table, final TrisectionTitle cellType) throws DocumentException {
        final String text = String.format("%s;%s;%s", cellType.getxTitle(), cellType.getContentTitle(), cellType.getyTitle());
        table.addCell(text, new CellFormat().setBackgroundColor(Color.LIGHT_GRAY).setFontSize(10));
        table.addCell(new CellFormat().setCellType(cellType));
        table.addCell(cellType.asText(""), new CellFormat().setAlignH(AlignH.CENTER).setAlignV(AlignV.MIDDLE).setFontSize(10));
    }

    private void addTrisectionCell(final TableiText table, final TrisectionTitle cellType , int fontSize) throws DocumentException {
        final String text = String.format("%s;%s;%s", cellType.getxTitle(), cellType.getContentTitle(), cellType.getyTitle());
        table.addCell(text, new CellFormat().setBackgroundColor(Color.LIGHT_GRAY).setFontSize(10));
        table.addCell(new CellFormat().setCellType(cellType).setFontSize(fontSize));
        table.addCell(cellType.asText(""), new CellFormat().setAlignH(AlignH.CENTER).setAlignV(AlignV.MIDDLE).setFontSize(10));
    }
}
