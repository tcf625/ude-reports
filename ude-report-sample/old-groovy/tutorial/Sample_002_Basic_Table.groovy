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
import com.iisigroup.ude.report.itext2.font.CNS11643
import com.iisigroup.ude.report.itext2.font.FontInfo
import com.iisigroup.ude.report.itext2.font.FontStyle
import com.iisigroup.ude.report.itext2.font.SubPhrase
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.table.format.DocumentAlign
import com.iisigroup.ude.report.utils.Coordinate
import com.lowagie.text.DocumentException
import com.lowagie.text.PageSize

import java.awt.Color

import org.junit.Test

/**
 *
 */
class Sample_002_Basic_Table extends AbstractITextSampleGroovy {

    String text2 = "中文，第二字面：「" + "𠀝" + "」(下/上)";
    private TableiText setContent(TableiText table) {
        float[] widths = table.getWidths()
        float totalWidth = 0
        widths.each { totalWidth += it; }
        widths.each {
            table.addCell("" + (int) it + "/" + (int) totalWidth);
        }
        table.addCell(text2, 5);

        final CHTFontFactory risFontFactory = CHTFontFactories.INSTANCE.getFactory(CNS11643.SUNG);
        final FontInfo textBgGray10 = risFontFactory.createFontInfo(10, FontStyle.NORMAL, Color.BLACK);
        final FontInfo textBgGray12 = risFontFactory.createFontInfo(14, FontStyle.BOLD, Color.BLACK, Color.LIGHT_GRAY);
        table.addCell( 5,
                new SubPhrase(textBgGray10, "多種樣式、特殊字、換行範例\n"),
                new SubPhrase(textBgGray10, "|𠀡|𠀖|\n"),
                new SubPhrase(textBgGray12, "|𠀡|𠀖|" + "第２字面中文\n"),
                new SubPhrase(textBgGray10, "|𠀡|𠀖|\n") //
                );
        return table;
    }

    private outputTable(TableiText table) {
        table.setSpacingBefore(6)
        table.setHorizontalAlignment(DocumentAlign.LEFT)
        setContent(table)
        table.appendMe()
        writeLastBottom(table)
    }

    private void writeLastBottom(final TableiText table) throws DocumentException {
        PDFDocument pdfDocument = table.getPdfDocument();
        def bottom = table.getLastRange().bottom();
        pdfDocument.writeText ""
        pdfDocument.writeText "表格輸出結果位置"
        pdfDocument.writeText "底部座標 in Pixel：$bottom "
        pdfDocument.writeText "底部座標 in CM：" + Coordinate.CM_BL.reverseTrans(bottom)
    }

    @Test
    void test_Monospaced() {
        super.groovyReport { PDFDocument pdfDocument ->
            pdfDocument.writeText ("等寬表格，指定表格寬度佔頁面比例 80 " )
            outputTable(pdfDocument.createTable(80, 5))
        }
    }

    @Test
    void test_Widths() {
        this.pageSize = PageSize.A4.rotate()
        super.groovyReport { PDFDocument pdfDocument ->
            pdfDocument.writeText ("指定欄寬比例，及表格寬度佔頁面比例")
            float[] widths = [1, 2, 3, 4, 5];
            outputTable(pdfDocument.createTable(90,widths));
        }
    }

    @Test
    void test_Widths_TotalCM() {
        this.pageSize = PageSize.A3.rotate()
        super.groovyReport { PDFDocument pdfDocument ->
            pdfDocument.writeText ("指定欄寬比例，及表格寬度(公分)" );
            float[] widths = [1, 2, 3, 4, 5];
            pdfDocument.writeText ("$CHINESE_BLOCK 自動加總為15公分：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,widths));
            
            pdfDocument.writeText ("$CHINESE_BLOCK 8公分：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,8,widths));

            pdfDocument.writeText ("$CHINESE_BLOCK 16公分：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,16,widths));

            pdfDocument.writeText ("$CHINESE_BLOCK 500公分(超過頁寬、以100%計算)：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,500,widths));
        }
    }

    @Test
    void test_Widths_TotalLargeCM() {
        this.pageSize = PageSize.A3.rotate()
        super.groovyReport { PDFDocument pdfDocument ->
            pdfDocument.writeText ("指定欄寬比例，及表格寬度(公分)" );
            float[] widths = [10, 20, 30, 40, 50];
            pdfDocument.writeText ("$CHINESE_BLOCK 8公分：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,widths));

            pdfDocument.writeText ("$CHINESE_BLOCK 16公分：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,10,widths));

            pdfDocument.writeText ("$CHINESE_BLOCK 500公分(超過頁寬、以100%計算)：");
            outputTable(pdfDocument.createTable(Coordinate.CM_BL,500,widths));


            pdfDocument.newPage();

            TableiText t1 = pdfDocument.createTable(Coordinate.CM_BL,widths);
            PaintTool.CM_TL.drawTable(pdfDocument.getPdfWriter().getDirectContent(), setContent(t1), 1, 10)
            
            TableiText t2 = pdfDocument.createTable(Coordinate.CM_BL,10,widths);
            PaintTool.CM_TL.drawTable(pdfDocument.getPdfWriter().getDirectContent(), setContent(t2), 1, 20)

                        
        }
    }
}
