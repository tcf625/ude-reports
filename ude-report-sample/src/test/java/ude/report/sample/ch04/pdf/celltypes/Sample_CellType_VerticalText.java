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
import com.iisigroup.ude.report.table.format.cellcreator.VerticalText;
import com.iisigroup.ude.util.collections.UdeArrayUtils;

import ude.report.sample.AbstractSample;

public class Sample_CellType_VerticalText extends AbstractSample {

    @Test
    public void forDebug() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("");
            final TableiText table = pdfDocument.createTable(1);
            table.getDefaultFormat().setMinHeightInCM(2.0).setCellType(VerticalText.DEFAULT);
            addCases(table, "直列");
            table.appendMe();
        });
    }

    @Test
    public void testVerticalTitle() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("");
            final TableiText table = pdfDocument.createTable(2);
            table.getDefaultFormat().setMinHeightInCM(2.0).setCellType(VerticalText.DEFAULT);
            addCases(table, "直列");
            addCases(table, "直列文字");
            addCases(table, "直列文字過長");
            addCases(table, "直列文字過長了");
            addCases(table, "多列文字處理\n第二");
            addCases(table, "多列文字處理\n第二\n第三\n");
            addCases(table, "民國103年");
            addCases(table, "民國103年1月10日");
            addCases(table, "VerticalTitle 英文過長"); // TODO
            table.appendMe();
        });
    }

    private void addCases(final TableiText table, final String text) {
        table.innerTable(UdeArrayUtils.floats(1, 1, 1, 1, 1), subTable -> {
            subTable.setBorder(Border.A);
            subTable.setBorderWidth(2F);
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
}
