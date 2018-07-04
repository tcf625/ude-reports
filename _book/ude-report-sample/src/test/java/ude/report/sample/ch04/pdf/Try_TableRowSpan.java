package ude.report.sample.ch04.pdf;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.testkit.PDFTableAsserts;
import com.iisigroup.ude.report.itext2.testkit.TableLog;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;

import ude.report.sample.AbstractSample;

public class Try_TableRowSpan extends AbstractSample {

    @Test
    public void test_deleteLastRow() {
        super.createPDF(pdfDocument -> {
            {
                final TableiText table = pdfDocument.createTable(5);
                table.addCell("A");
                table.addCell("B");
                table.addCell("C");
                table.addCell("D");
                table.addCell("E");

                table.addCell("F");
                table.deleteLastRow();
                table.appendMe();

                PDFTableAsserts.assertRows(1, 1);

            }
            pdfDocument.writeText("TableiText 在 deleteLastRow 前，會補滿最後一列。");

            {
                final TableiText table = pdfDocument.createTable(5);
                table.addCell("A");
                table.addCell("B");
                table.addCell("C");
                table.addCell("D");
                table.addCell("E");
                table.addCell("F");
                table.getTable().deleteLastRow();

                table.addCell("原生 API中，已填滿的列，才算是 lastRow", 4);
                table.appendMe();

                PDFTableAsserts.assertRows(2, 1);
            }
        });
    }

    @Test
    public void test_rowspan() {
        super.createPDF(pdfDocument -> {
            final TableiText table = pdfDocument.createTable(LengthUnit.PdfPixel, 40, 40, 40, 40);
            final PdfPCell pCell = table.createCell("AAA", new CellFormat().setBorderWidth(2F));
            pCell.setRowspan(5);
            table.addCell(pCell);
            for (int i = 0; i < 5; i++) {
                table.addCell("A" + i);
                table.addCell("B" + i);
                table.addCell("C" + i);

            }
            table.appendMe();
        });
        final Map<Integer, List<TableLog>> map = TABLE_LOGGER.get();
        Assert.assertEquals("table有5列", 5 + 1, map.get(1).get(0).yPos.length);
    }

    @Test
    public void test_rowspan_reset2() {

        super.createPDF(pdfDocument -> {
            final TableiText table = pdfDocument.createTable(LengthUnit.PdfPixel, 40, 40, 40, 40);
            table.addCell("ABCDEFGHIJKLMNOBPQRS");
            table.addCell("A");
            table.addCell("B");
            table.addCell("C");
            final PdfPRow lastRow = table.deleteLastRow();
            final PdfPCell[] cells = lastRow.getCells();
            for (int j = 0; j < cells.length; j++) {
                final PdfPCell pdfPCell = cells[j];
                if (j == 0) {
                    pdfPCell.setRowspan(2);
                }
                table.addCell(pdfPCell);
            }
            table.addCell("A");
            table.addCell("B");
            table.addCell("C");
            table.appendMe();
        });
    }

    @Test
    public void test_rowspan_reset() {
        super.createPDF(pdfDocument -> {
            final TableiText table = pdfDocument.createTable(LengthUnit.PdfPixel, 40, 40, 40, 40);

            table.addCell("ABCDEFGHIJKLMNOBPQRS");
            table.addCell("A");
            table.addCell("B");
            table.addCell("C");

            for (int i = 1; i <= 10; i++) {

                final List<PdfPRow> lastRows = table.deleteLastRows(i);
                if (!lastRows.isEmpty()) {
                    for (int r = 0; r < lastRows.size(); r++) {
                        final PdfPRow pdfPRow = lastRows.get(r);
                        if (r == 0) {
                            pdfPRow.getCells()[0].setRowspan(i + 1);
                        }
                        table.addRow(pdfPRow);
                    }
                }

                table.addCell("A" + i);
                table.addCell("B" + i);
                table.addCell("C" + i);
                final float totalHeight = table.getTable().getTotalHeight();
                LOGGER.info("totalHeight:{}", totalHeight);
                // table.appendMe();
            }

            table.appendMe();

            final PdfPRow lastRow = table.deleteLastRow();
            final PdfPCell[] cells = lastRow.getCells();
            assertNull(cells[0]);
            assertNotNull(cells[1]);
            assertNotNull(cells[2]);
            assertNotNull(cells[3]);
            assertEquals(4, cells.length);
            table.appendMe();

        });
    }

    //####################################################################
    //## [Method] sub-block :
    //####################################################################
}
