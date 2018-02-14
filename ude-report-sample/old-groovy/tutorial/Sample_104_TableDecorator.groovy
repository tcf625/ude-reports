/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.header.HeaderPosition
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.InnerTable
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.itext2.table.decorator.RowBackgroundColor
import com.iisigroup.ude.report.itext2.table.decorator.TableBorder
import com.iisigroup.ude.report.itext2.table.decorator.TableHeader
import com.iisigroup.ude.report.table.format.Border
import com.iisigroup.ude.report.table.format.CellFormat
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.format.CellFormat.AlignV
import com.iisigroup.ude.report.table.format.celltype.LinePattern
import com.iisigroup.ude.report.table.format.celltype.LinePattern.Mode
import com.lowagie.text.PageSize;

import java.awt.Color

import org.apache.commons.lang3.StringUtils
import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_104_TableDecorator extends AbstractITextSampleGroovy {


    static void repeatCells(TableiText table, String text, int times) {
        for (int i = 0; i < times; i++) {
            table.addCell(text);
        }
    }

    int lines = 3;
    Mode borderMode = Mode.BORDER_BOX;

    @Before
    void setup(){
        this.lines = 3;
        borderMode = Mode.BORDER_BOX;
        LayoutInfo temp = new LayoutInfo(super.iTextConfig);
        temp.setHeaderExtra(30);
        temp.setFooterExtra(30);
        super.setLayoutInfo(temp);
    }
    //####################################################################
    //## [Method] sub-block : Colors
    //####################################################################

    @Test
    void test_colors() {
        super.setPageSize(PageSize.A5.rotate())
        this.lines=60
        this.groovyReport { PDFDocument pdfDocument->
            //pdfDocument.writeText(StringUtils.repeat(CHINESE_BLOCK, 30));
            RowBackgroundColor rbc = new RowBackgroundColor(new Color(0xE0F0F0)
                    , new Color(0xF0F0E0)
                    , new Color(0xF0E0F0))
            final TableiText table = pdfDocument.createTable(50, 3);
            table.getDefaultFormat()
                    .setBorder(Border.NONE)
                    .setBackgroundColor(Color.lightGray);
            repeatCells(table, "HEADER1", 3);
            repeatCells(table, "HEADER2", 3);
            repeatCells(table, "FOOTER", 3);
            table.setHeaderRows(3);
            table.setFooterRows(1);
            repeatCells(table, "TEST", 15);
            table.addCell("INNER TABLE \n整塊內部表格使用同一底色"
                    , new CellFormat().setAlignV(AlignV.MIDDLE)
                    , 2);
            InnerTable inner = table.createInnerTable(2, 0);
            repeatCells(inner, "TEST2", 5);
            inner.appendMe()
            repeatCells(table, "TEST", this.lines*3);
            table.addEventDecorators(rbc);
            table.appendMe()
            //pdfDocument.writeText(StringUtils.repeat(CHINESE_BLOCK, 30));
            pdfDocument.newPage()
        }
    }

    //####################################################################
    //## [Method] sub-block : Headers - BASIC
    //####################################################################

    @Test
    void test_basicHeader() {
        this.lines=3
        TableHeader tableHeader = new TableHeader();
        tableHeader.setHeader(HeaderPosition.CenterHeader, "表格一標題", 14);
        tableHeader.setHeader(HeaderPosition.LeftHeader, "左上1", 10);
        tableHeader.setHeader(HeaderPosition.LeftFooter, "左下1", 10);
        tableHeader.setHeader(HeaderPosition.RightHeader, "右上1", 10);
        tableHeader.setHeader(HeaderPosition.RightFooter, "右下1", 10);
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("表格標題輸出：");
            final TableiText table1 = pdfDocument.createTable(80, 3);
            repeatCells(table1, "TEST1", this.lines * 3);
            table1.addEventDecorators(tableHeader);
            table1.setSpacingAfterInCM(1);
            table1.setSpacingBeforeInCM(1);
            table1.appendMe();
            TableHeader tableHeader2 = new TableHeader();
            tableHeader2.setHeader(HeaderPosition.CenterHeader, "表格二標題", 14);
            tableHeader2.setHeader(HeaderPosition.RightHeader, "右上2", 12);
            tableHeader2.setHeader(HeaderPosition.RightFooter, "右下2", 12);
            final TableiText table2 = pdfDocument.createTable(80, 3);
            repeatCells(table2, "TEST2", this.lines * 3);
            table2.addEventDecorators(tableHeader2);
            table2.appendMe();

        }
    }


    //####################################################################
    //## [Method] sub-block : Headers
    //####################################################################



    @Test
    void test_outerHeader() {
        this.lines=80
        TableHeader tableHeader = new TableHeader();
        tableHeader.setHeader(HeaderPosition.LeftHeader, "左上", 10);
        tableHeader.setHeader(HeaderPosition.LeftFooter, "左下", 10);
        tableHeader.setHeader(HeaderPosition.RightHeader, "右上", 10);
        tableHeader.setHeader(HeaderPosition.RightFooter, "右下", 10);


        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("方案一：");
            pdfDocument.writeText("  與前段文字，需自行以 setSpacingAfter 留下輸出空間");
            pdfDocument.writeText("  自動跨頁後，可用 layoutInfo.headerExtra / footExtra 留下TABLE前空間");
            pdfDocument.writeText("  與後段文字，可以用 setSpacingBefore 留下輸出空間");
            pdfDocument.writeText("  setSpacingAfter/SpacingBefore = 0 ");
            drawHeaderTable1(pdfDocument, tableHeader, 0);

            pdfDocument.writeText("  setSpacingAfter/SpacingBefore = 10 ");
            drawHeaderTable1(pdfDocument, tableHeader, 10);

            pdfDocument.writeText("  setSpacingAfter/SpacingBefore = 15 ");
            drawHeaderTable1(pdfDocument, tableHeader, 15);


            pdfDocument.writeText("方案二：");
            pdfDocument.writeText("  空白HEADER 加上    tableHeader.setSkipTop()    ");
            pdfDocument.writeText("  空白FOOTER 加上    tableHeader.setSkipBottom() ");
            pdfDocument.writeText("");
            pdfDocument.writeText("");
            drawHeaderTable2(pdfDocument, tableHeader);
        }
    }
    private drawHeaderTable1(PDFDocument pdfDocument, TableHeader tableHeader, int spacing) {
        pdfDocument.writeText(StringUtils.repeat(CHINESE_BLOCK, 30));
        final TableiText table = pdfDocument.createTable(80, 3);
        table.getDefaultFormat()
                .setBorder(Border.NONE)
                .setBackgroundColor(Color.lightGray);
        table.setSpacingAfter(spacing);
        table.setSpacingBefore(spacing);
        repeatCells(table, "HEADER", 3);
        repeatCells(table, "FOOTER", 3);
        table.setHeaderRows(2); // SEE ITEXT DOCUMTNE
        table.setFooterRows(1); //     : 0/1 is Headers & 2/3 is Footer
        repeatCells(table, "TEST", this.lines*3);
        table.addEventDecorators(tableHeader);
        table.appendMe()
        pdfDocument.writeText(StringUtils.repeat(CHINESE_BLOCK, 30));
        pdfDocument.newPage()
    }

    private drawHeaderTable2(PDFDocument pdfDocument, TableHeader tableHeader) {
        pdfDocument.writeText(StringUtils.repeat(CHINESE_BLOCK, 30));
        final TableiText table = pdfDocument.createTable(80, 3);
        table.getDefaultFormat()
                .setBorder(Border.NONE)
                .setBackgroundColor(Color.lightGray);
        table.addCell("BLANK", new CellFormat().setAlignH(AlignH.CENTER).setBackgroundColor(new Color(240,240,240)), 3);
        repeatCells(table, "HEADER", 3);
        repeatCells(table, "FOOTER", 3);
        table.addCell("BLANK", new CellFormat().setAlignH(AlignH.CENTER).setBackgroundColor(new Color(240,240,240)), 3);
        table.setHeaderRows(4); // SEE ITEXT DOCUMTNE
        table.setFooterRows(2); //     : 0/1 is Headers & 2/3 is Footer
        tableHeader.setSkipTop(1);
        tableHeader.setSkipBottom(1);
        repeatCells(table, "TEST", this.lines*3);
        table.addEventDecorators(tableHeader);
        table.appendMe()
        pdfDocument.writeText(StringUtils.repeat(CHINESE_BLOCK, 30));
        pdfDocument.newPage()
    }


    //####################################################################
    //## [Method] sub-block : Borders
    //####################################################################



    @Test
    void test_outerBorder2Page() {
        this.lines=80
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");
            pdfDocument.writeText("外框跨頁");
            pdfDocument.writeText("");
            tableWithOuterBorder(pdfDocument, 0);
        }
    }

    @Test
    void test_outerBorder2PagePadding() {
        this.lines=80
        borderMode = Mode.PADDING_BOX;
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");
            pdfDocument.writeText("外框跨頁");
            pdfDocument.writeText("");
            tableWithOuterBorder(pdfDocument, 0);
        }
    }

    @Test
    public void test_outerBorder() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            Mode.values().each{  mode ->
                this.borderMode = mode;
                pdfDocument.newPage();
                pdfDocument.writeText(mode.name());
                tablesWithOuterBorder(pdfDocument);
            }
        }
    }


    private tablesWithOuterBorder(PDFDocument pdfDocument) {
        pdfDocument.writeText("");
        pdfDocument.writeText("輸出實線外框");
        pdfDocument.writeText("");
        tableWithOuterBorder(pdfDocument, 0);

        pdfDocument.writeText(" ");
        pdfDocument.writeText("外擴框線");
        pdfDocument.writeText(" ");
        tableWithOuterBorder(pdfDocument, -3);

        pdfDocument.writeText(" ");
        pdfDocument.writeText("外擴雙框線");
        pdfDocument.writeText(" ");
        tableWithOuterBorder(pdfDocument, -3, -5);

        pdfDocument.writeText(" ");
        pdfDocument.writeText("內縮框線");
        pdfDocument.writeText(" ");
        tableWithOuterBorder(pdfDocument, 1);
    }








    private tableWithOuterBorder(PDFDocument pdfDocument, float offset) {
        final TableiText table = pdfDocument.createTable(80, 3);
        table.getDefaultFormat().setBorder(Border.NONE)
                .setBackgroundColor(Color.lightGray);
        repeatCells(table, "HEADER", 3);
        repeatCells(table, "TEST", this.lines*3);
        table.addEventDecorators(new TableBorder(new LinePattern(borderMode, Border.BOX).setOffset(offset)));

        TableHeader tableHeader = new TableHeader();
        tableHeader.setHeader(HeaderPosition.RightHeader, "右上2", 10);
        tableHeader.setHeader(HeaderPosition.RightFooter, "右下2", 10);
        table.addEventDecorators(tableHeader);
        table.setSpacingAfterInCM(0.5f);
        table.setSpacingBeforeInCM(1f);

        table.setHeaderRows(1);
        table.appendMe()
    }

    private tableWithOuterBorder(PDFDocument pdfDocument, float offset1, float offset2) {
        final TableiText table = pdfDocument.createTable(80, 3);
        table.getDefaultFormat().setBorder(Border.NONE)
                .setBackgroundColor(Color.lightGray);
        repeatCells(table, "HEADER", 3);
        repeatCells(table, "TEST", this.lines*3);
        def l1 = new LinePattern(borderMode, Border.BOX).setOffset(offset1);
        def l2 = new LinePattern(borderMode, Border.BOX).setOffset(offset2).setWidth(1);
        table.addEventDecorators(new TableBorder(Arrays.asList(l1,l2)));

        TableHeader tableHeader = new TableHeader();
        tableHeader.setHeader(HeaderPosition.RightHeader, "右上2", 10);
        tableHeader.setHeader(HeaderPosition.RightFooter, "右下2", 10);
        table.addEventDecorators(tableHeader);
        table.setSpacingAfterInCM(0.5f);
        table.setSpacingBeforeInCM(1f);

        table.setHeaderRows(1);
        table.appendMe()
    }


}
