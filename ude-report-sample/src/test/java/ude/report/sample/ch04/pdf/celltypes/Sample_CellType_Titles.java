/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.pdf.celltypes;

import java.awt.Color;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.CellFormat.AlignH;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.format.cellcreator.DiagonalTitle;
import com.iisigroup.ude.util.collections.UdeArrayUtils;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

public class Sample_CellType_Titles extends AbstractSample {

    @Test
    public void test_toTitle() {
        final String title = DiagonalTitle.titleJson("A", "B", null);
        System.out.println(title);
    }

    @Test
    public void test_basicSample() {
        super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A4.rotate());
            addSampleTable(pdfDocument, "地區", "人數", "年度", null);
        });
    }

    @Test
    public void test_basicSampleBlank() {
        super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A4.rotate());
            pdfDocument.writeText("空白文字視為有值，會畫出對角格線");
            addSampleTable(pdfDocument, "地區", "人數", "年度", "");
        });
    }

    @Test
    public void test_longText() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("三等分欄位的特別案例");
            final float[] widths = { 3, 5, 5, 8 };
            final TableiText table2 = pdfDocument.createTableWithRatio(90, widths);
            table2.setSpacingBefore(24);
            table2.addCell("說明");
            table2.addCell("傳入參數");
            table2.addCell("輸出結果");
            table2.addCell("文字表示");
            table2.addCell("較長");
            addTrisectionCell(table2, "地區地區地區地區", "人數", "各年度");
            table2.addCell("較小字型");
            addTrisectionCellWithFont(table2, "地區地區地區地區", "人數", "各年度", 4);
            table2.addCell("較小字型");
            addTrisectionCellWithFont(table2, "地區地區\n地區地區", "人數", "地區地區\n地區地區", 4);
            table2.addCell("換行字元");
            addTrisectionCell(table2, "地區\n地區", "人數", "年度\n年度");
            table2.appendMe();
        });
    }

    private void addSampleTable(final PDFDocument pdfDocument, final String xTitle, final String contentTitle,
            final String yTitle, final String empty) {
        final TableiText table = pdfDocument.createTableWithRatio(50, UdeArrayUtils.floats(10, 5, 5));
        table.getDefaultFormat().setAlignH(AlignH.CENTER);

        final CellFormat titleFormat = new CellFormat().setBackgroundColor(Color.LIGHT_GRAY).setFontSize(10).setPaddingV(6);
        table.addCell("傳入參數", titleFormat);
        table.addCell("輸出結果", titleFormat);
        table.addCell("文字表示", titleFormat);

        addTrisectionCell(table, xTitle, contentTitle, yTitle);

        addTrisectionCell(table, xTitle, empty, yTitle);
        addTrisectionCell(table, xTitle, contentTitle, empty);
        addTrisectionCell(table, empty, contentTitle, yTitle);

        addTrisectionCell(table, xTitle, empty, empty);
        addTrisectionCell(table, empty, empty, yTitle);
        addTrisectionCell(table, empty, contentTitle, empty);

        addTrisectionCell(table, empty, empty, empty);

        table.appendMe();

    }

    private void addTrisectionCell(final TableiText table, final String xTitle, final String contentTitle, final String yTitle) {
        addTrisectionCellWithFont(table, xTitle, contentTitle, yTitle, 10);
    }

    private void addTrisectionCellWithFont(final TableiText table, final String xTitle, final String contentTitle,
            final String yTitle, final int fontSize) {

        final String titleText = DiagonalTitle.titleJson(xTitle, contentTitle, yTitle);
        table.addCell(titleText, new CellFormat(AlignV.MIDDLE).setBackgroundColor(new Color(240, 240, 240)).setFontSize(8));
        table.addCell(titleText, new CellFormat(DiagonalTitle.DEFAULT).setFontSize(fontSize));
        table.addCell(DiagonalTitle.DEFAULT.asText(titleText),
                new CellFormat(AlignH.CENTER, AlignV.MIDDLE).setFontSize(6).setPadding(0));
    }

}
