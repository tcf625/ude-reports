/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.pdf.celltypes;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.CellFormat.AlignH;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.format.cellcreator.LooseText;
import com.iisigroup.ude.util.collections.UdeArrayUtils;

import ude.report.sample.AbstractSample;

public class Sample_CellType_LooseText extends AbstractSample {

    @Test
    public void basic() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("");
            final TableiText table = pdfDocument.createTable(1);
            table.getDefaultFormat()//
                    .setFontSize(8) //
                    .setPadding(12) //
                    .setCellType(new LooseText(5));
            addCases(table, "一二三四\n一二三\nHow are you?");
            table.appendMe();
        });
    }

    private void addCases(final TableiText table, final String text) {
        table.innerTable(UdeArrayUtils.floats(1, 1, 1, 1, 1), subTable -> {
            subTable.setBorder(Border.A);
            subTable.addCell(text, new CellFormat(AlignV.TOP, AlignH.LEFT));
            subTable.addCell(text, new CellFormat(AlignV.TOP, AlignH.CENTER));
            subTable.addCell(text, new CellFormat(AlignV.TOP, AlignH.RIGHT));
            subTable.addCell(text, new CellFormat(AlignV.TOP, AlignH.JUSTIFIED));
            subTable.addCell(text, new CellFormat(AlignV.TOP, AlignH.JUSTIFIED_ALL));

            subTable.addCell(text, new CellFormat(AlignV.MIDDLE, AlignH.LEFT));
            subTable.addCell(text, new CellFormat(AlignV.MIDDLE, AlignH.CENTER));
            subTable.addCell(text, new CellFormat(AlignV.MIDDLE, AlignH.RIGHT));
            subTable.addCell(text, new CellFormat(AlignV.MIDDLE, AlignH.JUSTIFIED));
            subTable.addCell(text, new CellFormat(AlignV.MIDDLE, AlignH.JUSTIFIED_ALL));

            subTable.addCell(text, new CellFormat(AlignV.BOTTOM, AlignH.LEFT));
            subTable.addCell(text, new CellFormat(AlignV.BOTTOM, AlignH.CENTER));
            subTable.addCell(text, new CellFormat(AlignV.BOTTOM, AlignH.RIGHT));
            subTable.addCell(text, new CellFormat(AlignV.BOTTOM, AlignH.JUSTIFIED));
            subTable.addCell(text, new CellFormat(AlignV.BOTTOM, AlignH.JUSTIFIED_ALL));
        });
    }

    @Test
    public void justified_all() {
        super.createPDF(pdfDocument -> {
            {
                final TableiText table = pdfDocument.createTable(8);
                table.getDefaultFormat()//
                        .setFontSize(8) //
                        .setPadding(0) //
                        .setCellType(LooseText.JUSTIFIED_ALL);

                final String text = "一二三四\n一二三\nHow are you?";
                for (int span = 1; span <= 8; span <<= 1) {
                    for (int i = 0; i < 8 / span; i++) {
                        table.addCell(text, span);
                    }
                }
                table.appendMe();
            }
            {
                final TableiText table = pdfDocument.createTable(8);
                table.getDefaultFormat()//
                        .setFontSize(8) //
                        .setPadding(0) //
                        .setAlignH(AlignH.JUSTIFIED_ALL)//
                        .setCellType(LooseText.JUSTIFIED_ALL);

                final String text = "一二三四\n一二三\nHow are you?";
                for (int span = 1; span <= 8; span <<= 1) {
                    for (int i = 0; i < 8 / span; i++) {
                        table.addCell(text, span);
                    }
                }
                table.appendMe();
            }

        });
    }

    @Test
    public void justified_all_oneLine() {
        super.createPDF(pdfDocument -> {
            final TableiText table = pdfDocument.createTable(1);
            table.getDefaultFormat()//
                    .setFontSize(8) //
                    .setPadding(0) //
                    .setMinHeight(30) // 
                    .setAlignH(AlignH.JUSTIFIED_ALL) //
                    .setCellType(LooseText.JUSTIFIED_ALL);

            final String text = "一二三四";
            table.addCell(text, new CellFormat(AlignV.TOP));
            table.addCell(text, new CellFormat(AlignV.MIDDLE));
            table.addCell(text, new CellFormat(AlignV.BOTTOM));
            table.appendMe();
        });
    }

}
