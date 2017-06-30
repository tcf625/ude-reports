/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.pdf.celltypes;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.celltype.ExtraLines;
import com.iisigroup.ude.report.table.format.celltype.LinePattern;
import com.iisigroup.ude.report.table.format.celltype.LinePattern.Mode;

import ude.report.sample.AbstractSample;

public class Sample_CellType_ExtraLines extends AbstractSample {

    @Test
    public void test_borders() {

        super.createPDF(pdfDocument -> {

            pdfDocument.writeText("用於在欄位中繪製額外線段");
            pdfDocument.writeText("");

            final TableiText table = pdfDocument.createTable(9);
            table.getDefaultFormat().setMinHeightInCM(2.0);
            table.getDefaultFormat().setBorder(Border.N);

            final Mode mode = LinePattern.Mode.BORDER;
            final LinePattern line = new LinePattern(mode, Border.ALL);

            table.addCell("");
            table.addCell("  default ", new CellFormat(new ExtraLines(line)));
            table.addCell("");

            table.addCell("");
            table.addCell("  offset  2 ", new CellFormat(new ExtraLines(line.clone().setOffset(2))));
            table.addCell("");

            table.addCell("");
            table.addCell("  offset -2 ", new CellFormat(new ExtraLines(line.clone().setOffset(-2))));
            table.addCell("");

            table.appendMe();

        });
    }
}
