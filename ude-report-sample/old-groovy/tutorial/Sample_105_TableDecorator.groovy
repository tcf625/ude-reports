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
class Sample_105_TableDecorator extends AbstractITextSampleGroovy {


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
            final TableiText table1 = pdfDocument.createTable(80, 3);
            repeatCells(table1, "TEST1", this.lines * 3);
            table1.addEventDecorators(tableHeader);
            //table1.setSpacingAfterInCM(1);
            //table1.setSpacingBeforeInCM(1);
            table1.appendMe();
            TableHeader tableHeader2 = new TableHeader();
            tableHeader2.setHeader(HeaderPosition.CenterHeader, "表格二標題", 14);
            tableHeader2.setHeader(HeaderPosition.RightHeader, "右上2", 12);
            tableHeader2.setHeader(HeaderPosition.RightFooter, "右下2", 12);
            final TableiText table2 = pdfDocument.createTable(80, 3);
            repeatCells(table2, "TEST2", this.lines * 3);
            table2.addEventDecorators(tableHeader2);
            table2.appendMe();
            pdfDocument.writeText("");
        }
    }

    @Test
    void test_basicHeader2() {
        this.lines=3
        TableHeader tableHeader = new TableHeader();
        tableHeader.setHeader(HeaderPosition.CenterHeader, "表格一標題", 14);
        tableHeader.setHeader(HeaderPosition.LeftHeader, "左上1", 10);
        tableHeader.setHeader(HeaderPosition.LeftFooter, "左下1", 10);
        tableHeader.setHeader(HeaderPosition.RightHeader, "右上1", 10);
        tableHeader.setHeader(HeaderPosition.RightFooter, "右下1", 10);
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");
            final TableiText table1 = pdfDocument.createTable(80, 3);
            repeatCells(table1, "TEST1", this.lines * 3);
            table1.addEventDecorators(tableHeader);
            //table1.setSpacingAfterInCM(1);
            //table1.setSpacingBeforeInCM(1);
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

}
