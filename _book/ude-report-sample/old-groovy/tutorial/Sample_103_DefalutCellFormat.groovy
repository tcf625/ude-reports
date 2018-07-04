/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.font.CNS11643
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.InnerTable;
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.table.format.Border
import com.iisigroup.ude.report.table.format.CellFormat
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.format.CellFormat.AlignV
import com.iisigroup.ude.report.table.format.celltype.TrisectionTitle
import com.lowagie.text.DocumentException
import com.lowagie.text.PageSize

import java.awt.Color

import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_103_DefalutCellFormat extends AbstractITextSampleGroovy {

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
            table.addCell(" ", 1, cf);
        }
    }

    //####################################################################
    //## [Method] sub-block : Border
    //####################################################################


    @Test
    void test() {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("顯示TABLE's DefaultCellFormat的繼承效果!" );
            final TableiText table = pdfDocument.createTable(100, 4);
            table.getDefaultFormat()
                    .setAlignH(AlignH.CENTER)
                    .setAlignV(AlignV.MIDDLE)
                    .setMinHeightInCM(2f)
                    .setFontSize(6)
            ;
            table.addCell("$CHINESE_BLOCK 預設 $CHINESE_BLOCK " )
            table.addCell("setBackgroundColor(Color)" , new CellFormat().setBackgroundColor(Color.LIGHT_GRAY));
            table.addCell("setBorder(int)" , new CellFormat().setBorder (Border.NONE));
            table.addCell("setBorderWidth(Float)" , new CellFormat().setBorderWidth (2f));
            table.addCell("setAlignH(ALIGN)" , new CellFormat().setAlignH (AlignH.LEFT));
            table.addCell("setAlignV(ALIGN)" , new CellFormat().setAlignV (AlignV.TOP));
            table.addCell("setMinHeightInCM(Float)" , new CellFormat().setMinHeightInCM( 2.9f ));
            table.addCell("setFontBold(boolean)" , new CellFormat().setFontBold( true ));
            table.addCell("setFontSize(int)" , new CellFormat().setFontSize( 10 ));
            table.addCell("setFontType(FontType)" , new CellFormat().setFontType( CNS11643.KAI ));
            table.addCell("setPaddingL(Float)" , new CellFormat().setAlignH (AlignH.LEFT).setAlignV (AlignV.UNDEFINED).setPaddingL( 10 ));
            table.addCell("setPaddingR(Float)" , new CellFormat().setAlignH (AlignH.RIGHT).setAlignV (AlignV.UNDEFINED).setPaddingR( 10 ));
            table.addCell("setPaddingB(Float)" , new CellFormat().setAlignH (AlignH.UNDEFINED).setAlignV (AlignV.BOTTOM).setPaddingB( 10 ));
            table.addCell("setPaddingT(Float)" , new CellFormat().setAlignH (AlignH.UNDEFINED).setAlignV (AlignV.TOP).setPaddingT( 10 ));
            table.addCell("setCellType(Celltype)" , new CellFormat().setCellType( new TrisectionTitle ("地區", "setCellType", "年度") ));
            table.addCell("")
            table.appendMe();
        }
    }


    @Test
    void test_InnerTable() {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("顯示Inner TABLE's DefaultFormat 繼承效果!" );
            final TableiText table = pdfDocument.createTable(100, 4);
            table.getDefaultFormat()
                    .setAlignH(AlignH.CENTER)
                    .setAlignV(AlignV.MIDDLE)
                    .setMinHeightInCM(2f)
                    .setFontSize(6)
            ;
            table.addCell("$CHINESE_BLOCK 預設 $CHINESE_BLOCK" )
            appendInnerTable(table, "$CHINESE_BLOCK 子表格 $CHINESE_BLOCK",  {});
            appendInnerTable(table, "setBackgroundColor(Color)",  {it.setBackgroundColor(Color.LIGHT_GRAY)});
            appendInnerTable(table, "setBorder(int)" ,  {it.setBorder (Border.NONE)});
            appendInnerTable(table, "setBorderWidth(Float)" ,  {it.setBorderWidth (2f)});
            appendInnerTable(table, "setAlignH(ALIGN)" ,  {it.setAlignH (AlignH.LEFT)});
            appendInnerTable(table, "setAlignV(ALIGN)" ,  {it.setAlignV (AlignV.TOP)});
            appendInnerTable(table, "setMinHeightInCM(Float)" ,  {it.setMinHeightInCM( 2.9f )});
            appendInnerTable(table, "setFontBold(boolean)" ,  {it.setFontBold( true )});
            appendInnerTable(table, "setFontSize(int)" ,  {it.setFontSize( 10 )});
            appendInnerTable(table, "setFontType(FontType)" ,  {it.setFontType( CNS11643.KAI )});
            appendInnerTable(table, "setPaddingL(Float)" ,  {it.setAlignH (AlignH.LEFT).setAlignV (AlignV.UNDEFINED).setPaddingL( 10 )});
            appendInnerTable(table, "setPaddingR(Float)" ,  {it.setAlignH (AlignH.RIGHT).setAlignV (AlignV.UNDEFINED).setPaddingR( 10 )});
            appendInnerTable(table, "setPaddingB(Float)" ,  {it.setAlignH (AlignH.UNDEFINED).setAlignV (AlignV.BOTTOM).setPaddingB( 10 )});
            appendInnerTable(table, "setPaddingT(Float)" ,  {it.setAlignH (AlignH.UNDEFINED).setAlignV (AlignV.TOP).setPaddingT( 10 )});
            appendInnerTable(table, "setCellType(Celltype)" ,  {it.setCellType( new TrisectionTitle ("地區", "setCellType", "年度") )});
            table.appendMe();
        }
    }

    void appendInnerTable(final TableiText table, final String text, Closure<CellFormat> setFormats ){
        float[] SingleWidths = [1];
        InnerTable it1 = table.createInnerTable(SingleWidths)
        CellFormat cf = new CellFormat();
        setFormats.call(cf);
        it1.addCell(text, cf);
        it1.appendMe();
    }


    @Test
    void test_InnerTable2() {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("顯示雙層的 Inner TABLE's DefaultFormat 繼承效果!" );
            final TableiText table = pdfDocument.createTable(100, 4);
            table.getDefaultFormat()
                    .setAlignH(AlignH.CENTER)
                    .setAlignV(AlignV.MIDDLE)
                    .setMinHeightInCM(2f)
                    .setFontSize(6)
            ;
            table.addCell("$CHINESE_BLOCK 預設 $CHINESE_BLOCK" )
            InnerTable t1 = table.createInnerTable(1, 0);
            t1.getDefaultFormat().setFontSize(12);
            t1.addCell(CHINESE_BLOCK);
            InnerTable t2 = t1.createInnerTable(1, 1);
            t2.getDefaultFormat().setAlignV(AlignV.TOP).setFontSize(16);
            t2.addCell(CHINESE_BLOCK);
            t2.addCell(CHINESE_BLOCK);
            t2.appendMe()
            t1.addCell(CHINESE_BLOCK);
            t1.appendMe()
            table.appendMe();
        }
    }


    //== [Method] Block Stop
    //================================================
    //== [Inner Class] Block Start
    //== [Inner Class] Block Stop
    //================================================
}
