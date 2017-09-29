/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04;

import java.awt.Color;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.util.collections.UdeArrayUtils;

import ude.report.sample.AbstractSample;

public class Sample_CellFormat extends AbstractSample {

    @Test
    public void test_bgColor() {
        super.createPDF(pdfDocument -> {
            final TableiText table = pdfDocument.createTable(98, 16);
            table.getDefaultFormat().setFontSize(8).setMinHeightInCM(3F);
            for (int r = 0; r < 255; r += 32) {
                for (int g = 0; g < 255; g += 32) {
                    for (int b = 0; b < 255; b += 32) {
                        final CellFormat cf = new CellFormat().setBackgroundColor(new Color(r, g, b));
                        table.addCell(String.format("%X%X%X", r, g, b), cf);
                    }
                }
            }
            table.appendMe();
        });
    }

    @Test
    public void test_basicWidths() {
        super.createPDF(pdfDocument -> {
            final TableiText table1 = pdfDocument.createTable(100, 5);
            addWidthInfoCells(table1);
            table1.appendMe();
            final float[] widths = { 1, 2, 3, 4, 5 };
            final TableiText table2 = pdfDocument.createTable(100, widths);
            addWidthInfoCells(table2);
            table2.setSpacingBeforeInCM(0.5F);
            table2.appendMe();
        });
    }

    private static void addWidthInfoCells(final TableiText table) {
        final float[] widths = table.getWidths();
        final float totalWidth = UdeArrayUtils.sum(widths);
        for (int j = 0; j < widths.length; j++) {
            table.addCell("" + j);
        }
        for (final float width : widths) {
            table.addCell("" + (int) width + "/" + (int) totalWidth);
        }
    }

}
