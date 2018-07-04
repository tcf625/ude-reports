/*
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
import com.lowagie.text.PageSize
import com.lowagie.text.pdf.PdfPCell

import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_100_TableSpan extends AbstractITextSampleGroovy {
    //================================================
    //== [Enumeration types] Block Start
    //== [Enumeration types] Block End
    //================================================
    //== [static variables] Block Start
    //== [static variables] Block Stop
    //================================================
    //== [instance variables] Block Start
    //== [instance variables] Block Stop
    //================================================
    //== [static Constructor] Block Start
    //== [static Constructor] Block Stop
    //================================================
    //== [Constructors] Block Start (含init method)
    //== [Constructors] Block Stop
    //================================================
    //== [Static Method] Block Start

    static void repeatCells(TableiText table, String text, int times) {
        for (int i = 0; i < times; i++) {
            table.addCell(text);
        }
    }

    //== [Static Method] Block Stop
    //================================================
    //== [Accessor] Block Start
    //== [Accessor] Block Stop
    //================================================
    //== [Overrided Method] Block Start (Ex. toString/equals+hashCode)

    @Before
    void setup(){
        super.setPageSize(PageSize.A5)
    }
    //== [Overrided Method] Block Stop
    //================================================
    //== [Method] Block Start
    //####################################################################
    //## [Method] sub-block :
    //####################################################################


    @Test
    public void test_columnSpans() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText(" ");
            final TableiText table = pdfDocument.createTable(80, 5);
            // row1
            repeatCells(table, "", 1);
            table.addCell("colSpans = 2", 2);
            repeatCells(table, "", 2);
            // row2
            repeatCells(table, "", 2);
            table.addCell("colSpans = 3", 3);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }


    @Test
    public void test_rowSpan_normal() throws Exception {
        super.setPageSize(PageSize.A4.rotate())
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText(" ");
            final TableiText table = pdfDocument.createTable(95, 7);
            table.getDefaultFormat().setAlignH(AlignH.CENTER);
            repeatCells(table, "", 7);
            table.addCell("");
            table.addCell("左邊保留1欄，右邊保留3欄",3);
            table.addCell("");
            table.addCell("左：5欄，右：1欄");
            table.addCell("");
            // start row with rowspans
            table.addCell("rowSpans = 3");
            InnerTable inner1 = table.createInnerTable(1, 3);
            repeatCells(inner1, "INNER DATA", 9);
            inner1.appendMe();
            table.addCell("rowSpans = 3");
            InnerTable inner2 = table.createInnerTable(5, 1);
            repeatCells(inner2, "INNER DATA", 3);
            inner2.appendMe();
            table.addCell("rowSpans = 3");
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }




    @Test
    public void test_rowSpan_dyna() throws Exception {
        super.setPageSize(PageSize.A4)
        this.groovyReport { PDFDocument pdfDocument->
            final TableiText table = pdfDocument.createTable(100, 7);
            //
            repeatCells(table, "", 7);
            //
            float[] subWidth = [2, 3, 2, 3, 2, 3, 2]
            InnerTable inner2 = table.createInnerTable(subWidth);
            repeatCells(inner2, " ", 12);
            table.addCell("使用 InnerTable 做出欄位不對齊效果", 2);
            inner2.appendMe(3);
            table.addCell("", 2);
            //
            repeatCells(table, "", 7);
            //
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }

    //####################################################################
    //## [Method] sub-block :
    //####################################################################

    @Test
    public void test_innerBorder_Default() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("沒有特別設定的時候，內表格的框線跟著外表格的設定值")
            pdfDocument.writeText("")
            final TableiText table = pdfDocument.createTable(50, 4);
            table.getDefaultFormat().setBorderWidth(1.5f);
            repeatCells(table, "", 4);
            //
            table.addCell("");
            InnerTable inner1 = table.createInnerTable(1, 1);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            table.addCell("");
            //
            repeatCells(table, "", 4);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }

    @Test
    public void test_innerBorder_innerThin() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("InnerTable setBorderWidth(0.5f)")
            pdfDocument.writeText("")
            final TableiText table = pdfDocument.createTable(50, 4);
            table.getDefaultFormat().setBorderWidth(1.5f);
            repeatCells(table, "", 4);
            //
            table.addCell("");
            InnerTable inner1 = table.createInnerTable(1, 1);
            inner1.getDefaultFormat().setBorderWidth(0.5f);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            table.addCell("");
            //
            repeatCells(table, "", 4);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }

    @Test
    public void test_innerBorder_innerThinAll() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("InnerTable setBorderWidth(0.5f)")
            pdfDocument.writeText("")
            final TableiText table = pdfDocument.createTable(50, 4);
            table.getDefaultFormat().setBorderWidth(1.5f);
            repeatCells(table, "", 4);
            // 把左邊欄位的右框線定義為無
            table.addCell("X", new CellFormat().setBorder(Border.NR));
            InnerTable inner1 = table.createInnerTable(1, 1);
            inner1.getDefaultFormat().setBorderWidth(0.5f);
            inner1.setBorderWidth(0.5f);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            // 把右邊欄位的左框線定義為無
            table.addCell("X", new CellFormat().setBorder(Border.NL));
            //
            repeatCells(table, "", 4);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }


    @Test
    public void test_innerBorder_innerNone() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("InnerTable setBorderWidth(0.5f)")
            pdfDocument.writeText("")
            final TableiText table = pdfDocument.createTable(50, 4);
            table.getDefaultFormat().setBorderWidth(1.5f);
            repeatCells(table, "", 4);
            // 把左邊欄位的右框線定義為無
            table.addCell("X", new CellFormat().setBorder(Border.NR));
            InnerTable inner1 = table.createInnerTable(1, 1);
            inner1.getDefaultFormat().setBorder(Border.NONE);
            inner1.setBorder(Border.NONE);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            // 把右邊欄位的左框線定義為無
            table.addCell("X", new CellFormat().setBorder(Border.NL));
            //
            repeatCells(table, "", 4);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }



    @Test
    public void test_innerBorder_innerThinAll_2() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("InnerTable setBorderWidth(0.5f)")
            pdfDocument.writeText("")
            final TableiText table = pdfDocument.createTable(50, 4);
            table.getDefaultFormat().setBorderWidth(1.5f);
            repeatCells(table, "", 4);

            InnerTable tableP = table.createInnerTable(0, 0);
            tableP.getDefaultFormat().setBorderWidth(0.5f);

            tableP.addCell("X");
            InnerTable inner1 = tableP.createInnerTable(1, 1);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            tableP.addCell("X");
            tableP.appendMe();



            //
            repeatCells(table, "", 4);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }







    @Test
    public void test_innerNoBorder() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->

            pdfDocument.writeText("內表無框線處理，外框加粗")

            final TableiText table = pdfDocument.createTable(100, 5);
            repeatCells(table, "", 5);
            //
            table.addCell("rowSpans = 3");
            InnerTable inner1 = table.createInnerTable(1, 0);
            inner1.setBorderWidth(3)
            inner1.getDefaultFormat().setBorder(Border.NONE);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            //
            repeatCells(table, "", 5);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }


    @Test
    public void test_allNoBorder() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("全無框線處理")
            final TableiText table = pdfDocument.createTable(100, 5);
            table.getDefaultFormat().setBorder(Border.NONE);
            repeatCells(table, "", 5);
            //
            table.addCell("rowSpans = 3");
            InnerTable inner1 = table.createInnerTable(1, 0);
            for (int i = 0; i < 3; i++) {
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
                inner1.addCell("R",new CellFormat().setAlignH(AlignH.RIGHT));
                inner1.addCell("L",new CellFormat().setAlignH(AlignH.LEFT));
            }
            inner1.appendMe();
            //
            repeatCells(table, "", 5);
            table.appendMe();
            pdfDocument.writeText(" ");
        }
    }


    //####################################################################
    //## [Method] sub-block : SHOW BUG
    //####################################################################

    @Test
    public void test_rowspans_bug() throws Exception {


        this.groovyReport { PDFDocument pdfDocument ->

            pdfDocument.writeText("使用 IText 原生的 setRowspan，");
            pdfDocument.writeText("當跨列欄位內容過長，但其它列高不足需換頁時，有問題。");
            pdfDocument.writeText("所以建議使用InnerTable實作跨列");

            pdfDocument.writeText("");
            pdfDocument.writeText("正常的小表格輸出應如下表：");
            writeNormalTable(pdfDocument)
            pdfDocument.newPage();

            pdfDocument.writeText("第一欄過長 & setSplitRows(true) ");
            writeBugSplitTable(pdfDocument, true);
            pdfDocument.newPage();

            pdfDocument.writeText("第一欄過長 & setSplitRows(false) ");
            pdfDocument.writeText("因為不允許分割欄位，所以連後半的欄位都不見了 ");
            writeBugSplitTable(pdfDocument, false);
            pdfDocument.newPage();


            pdfDocument.writeText("使用 Inner Table 做輸出");
            pdfDocument.writeText("");
            pdfDocument.writeText("setSplitLate(false)");
            pdfDocument.writeText("   If true the row will only split if it's the first one in an empty page. It's true by default.");
            pdfDocument.writeText("   若為預設的 True，則表格會從下頁開始輸出。");
            writeLargeTableWithInner(pdfDocument);
            pdfDocument.newPage();

        }
    }

    private writeBugSplitTable(PDFDocument pdfDocument, boolean splitRows) {
        final TableiText table = pdfDocument.createTable(100, 5);
        table.getTable().setSplitRows(splitRows);
        final PdfPCell mrCell = table.createCell(StringUtils.repeat("文字過長*200", 200));
        final int rowspan = 4;
        mrCell.setRowspan(rowspan);
        table.addCell(mrCell);
        table.addCell("左欄後半部的文字不見了。", 4)
        for (int i = 1; i < rowspan; i++) {
            repeatCells(table, StringUtils.repeat(i + "日", 20), 4);
        }
        for (int i = 0; i < 20; i++) {
            repeatCells(table, "測試", 5);
        }
        table.appendMe()
    }

    private void writeNormalTable(PDFDocument pdfDocument) {
        final TableiText table1 = pdfDocument.createTable(100, 5);

        final PdfPCell mrCell = table1.createCell(StringUtils.repeat("正常文字長度", 1));
        mrCell.setRowspan(4);
        table1.addCell(mrCell);



        for (int i = 0; i < 4; i++) {
            repeatCells(table1, StringUtils.repeat(i + "日", 20), 4);
        }
        table1.appendMe();
    }

    private void writeLargeTableWithInner(PDFDocument pdfDocument) {
        final TableiText table = pdfDocument.createTable(100, 5);
        table.setSplitLate(false);
        //table.setSpacingBefore(0);
        table.addCell("Start:" + StringUtils.repeat("文字過長*80", 200));

        final TableiText inner = table.createInnerTable(1, 0);
        for (int i = 0; i < 3; i++) {
            repeatCells(inner, StringUtils.repeat(i + "日", 20), 4);
        }
        inner.addCell("本列欄位被拉長!")
        repeatCells(inner, StringUtils.repeat("3日", 20), 3);
        repeatCells(inner, StringUtils.repeat("4日", 20), 4);
        inner.appendMe();

        for (int i = 0; i < 40; i++) {
            repeatCells(table, "", 5);
        }
        table.appendMe();
    }

    //== [Method] Block Stop
    //================================================
    //== [Inner Class] Block Start
    //== [Inner Class] Block Stop
    //================================================
}
