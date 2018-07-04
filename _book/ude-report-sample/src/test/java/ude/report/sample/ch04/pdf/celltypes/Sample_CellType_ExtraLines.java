/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.pdf.celltypes;

import java.awt.Color;
import java.util.function.Consumer;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.InnerTable;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.line.Base;
import com.iisigroup.ude.report.line.BorderLine;
import com.iisigroup.ude.report.line.OffsetMode;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.celldecorator.LinesDrawer;
import com.iisigroup.ude.util.collections.UdeArrayUtils;
import com.iisigroup.ude.util.internal.UdeRuntimeUtils;

import ude.report.sample.AbstractSample;

public class Sample_CellType_ExtraLines extends AbstractSample {

    @Test
    public void test_Borders() {

        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("在欄位中繪製額外線段");
            displayBase(pdfDocument, Base.BORDER);

            pdfDocument.writeText("在欄位中繪製額外線段");
            displayBase(pdfDocument, Base.PADDING);

        });
    }

    @Test
    public void test_Offset() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("在欄位中繪製額外線段: Offset = 3, 縮短");
            displayBase(pdfDocument, Base.BORDER, line -> line.setOffset(3));
            pdfDocument.writeText("在欄位中繪製額外線段: Offset = -3, 加長");
            displayBase(pdfDocument, Base.BORDER, line -> line.setOffset(-3));

            pdfDocument.writeText("在欄位中繪製額外線段: Offset = 3, 縮短");
            displayBase(pdfDocument, Base.PADDING, line -> line.setOffset(3));
            pdfDocument.writeText("在欄位中繪製額外線段: Offset = -3, 加長");
            displayBase(pdfDocument, Base.PADDING, line -> line.setOffset(-3));
        });
    }

    @Test
    public void test_OffsetLength() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("");
            displayBase(pdfDocument, Base.BORDER, UdeRuntimeUtils::doNothing);

            pdfDocument.writeText("在欄位中繪製額外線段: setLength = 10, 正向");
            displayBase(pdfDocument, Base.BORDER, line -> line.setLength(10));

            pdfDocument.writeText("在欄位中繪製額外線段: setLength = 10, 正向(逆時針)");
            displayBase(pdfDocument, Base.BORDER, line -> line.setLength(10).setClockwise(false));

            pdfDocument.writeText("在欄位中繪製額外線段: setLength = -10, 反向");
            displayBase(pdfDocument, Base.BORDER, line -> line.setLength(-10));

            pdfDocument.newPage();
            pdfDocument.writeText("");
            displayBase(pdfDocument, Base.PADDING, UdeRuntimeUtils::doNothing);
            pdfDocument.writeText("在欄位中繪製額外線段: setLength = 10, 正向");
            displayBase(pdfDocument, Base.PADDING, line -> line.setLength(10));
            pdfDocument.writeText("在欄位中繪製額外線段: setLength = 10, 正向(逆時針)");
            displayBase(pdfDocument, Base.PADDING, line -> line.setLength(10).setClockwise(false));

            pdfDocument.writeText("在欄位中繪製額外線段: setLength = -10, 反向");
            displayBase(pdfDocument, Base.PADDING, line -> line.setLength(-10));
        });
    }

    private void displayBase(final PDFDocument pdfDocument, final Base base) {
        displayBase(pdfDocument, base, null);
    }

    private void displayBase(final PDFDocument pdfDocument, final Base base, final Consumer<BorderLine> lineSetup) {

        final Consumer<BorderLine> lineSetup2 = ObjectUtils.defaultIfNull(lineSetup, UdeRuntimeUtils::doNothing);

        final TableiText table = pdfDocument.createTable(lineSetup == null ? 5 : 4);
        table.setSpacingBefore(20);
        table.setSpacingAfter(20);
        table.getDefaultFormat().setMinHeightInCM(2.0).setPadding(8).setFontSize(8);

        if (lineSetup == null) {
            displaySampleCell(table, base + "\nDefault", lineSetup2.andThen(line -> {
                line.setBase(base);
            }));
        }

        displaySampleCell(table, base + "\nDistance +6", lineSetup2.andThen(line -> {
            line.setBase(base).setDistance(6);
        }));

        displaySampleCell(table, base + "\nBOX\nDistance +6", lineSetup2.andThen(line -> {
            line.setBase(base).setOffsetMode(OffsetMode.BOX).setDistance(6);
        }));

        displaySampleCell(table, base + "\nDistance -6", lineSetup2.andThen(line -> {
            line.setBase(base).setDistance(-6);
        }));

        displaySampleCell(table, base + "\nBOX\nDistance -6", lineSetup2.andThen(line -> {
            line.setBase(base).setOffsetMode(OffsetMode.BOX).setDistance(-6);
        }));
        table.appendMe();

    }

    private void displaySampleCell(final TableiText table, final String titleText, final Consumer<BorderLine> lineSetup) {

        final InnerTable innerTable = table.createInnerTable(UdeArrayUtils.floats(1, 5, 1));
        innerTable.getDefaultFormat().setBorder(Border.N);

        final BorderLine line = new BorderLine();
        lineSetup.accept(line);

        final CellFormat format = new CellFormat(Border.ALL) //
                .setCellType(LinesDrawer.withBorder(line)) //
                .setBackgroundColor(new Color(0xE0E0E0));

        innerTable.addCell("");
        innerTable.addCell(titleText, format);
        innerTable.addCell("");

        innerTable.appendMe(1);
    }
}
