/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04;

import java.awt.Color;

import org.junit.Test;

import com.iisigroup.ude.report.data.source.Const;
import com.iisigroup.ude.report.data.source.image.BarcodeImage;
import com.iisigroup.ude.report.data.source.image.BarcodeImage.BarcodeType;
import com.iisigroup.ude.report.itext2.table.CellFactory;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.itext2.utils.barcode.Barcode39Drawer;
import com.iisigroup.ude.report.itext2.utils.barcode.BarcodeSupport;
import com.iisigroup.ude.report.itext2.utils.barcode.QRCodeDrawer;
import com.iisigroup.ude.report.utils.Coordinate;
import com.iisigroup.ude.report.utils.PointF;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import ude.report.sample.AbstractSample;

public class Sample_CellFormat_BarCode extends AbstractSample {
    @Test
    public void test_codtes() {
        super.createPDF(pdfDocument -> {

            final TableiText table = pdfDocument.createTable(4);
            //table.getDefaultFormat().setFontSize(8).setMinHeightInCM(3F);
            {
                final BarcodeImage barcodeImage = BarcodeImage.type(BarcodeType.CODE39, 100, 20).apply(Const.define("A123456"));
                table.addCell(barcodeImage);
                table.addCell(barcodeImage);
                table.addCell(barcodeImage);
                table.addCell(barcodeImage);
            }
            {
                final BarcodeSupport barCode39 = new Barcode39Drawer(Coordinate.CM_TL);
                barCode39.setSize(10);
                barCode39.setBaseline(10);
                barCode39.setTextAlignment(Element.ALIGN_CENTER);

                final CellFactory factory = barCode39.factory("1000000000", 3.0, 0.45, false);
                final PdfPCell cell = factory.create(table);
                cell.setCellEvent((final PdfPCell cella, final Rectangle position, final PdfContentByte[] canvases) -> {
                    final float left = (position.getLeft() + position.getRight()) / 2;
                    final float top = (position.getBottom() + position.getTop()) / 2;

                    final PdfContentByte lineDC = canvases[PdfPTable.TEXTCANVAS];
                    lineDC.saveState();
                    lineDC.setColorFill(Color.white);
                    lineDC.setLineWidth(0.25F);
                    final PointF p1 = new PointF(left, top);
                    final PointF p2 = new PointF(left + 50, top - 20);
                    PaintTool.Default.drawBlock(lineDC, p1, p2, 0, PdfContentByte::fill);
                    lineDC.restoreState();
                    lineDC.beginText();
                    lineDC.setFontAndSize(pdfDocument.getFontFamily().getBaseFont(), 7);
                    PaintTool.Default.drawTextAlignLeft(lineDC, "A123", left, top - 10);
                    lineDC.endText();

                });
                table.addCell(cell);

                table.addCell(barCode39.factory("1000000000", 3.0, 0.55, true));
                table.addCell(barCode39.factory("1000000000", 3.0, 0.65, true));
                table.addCell(barCode39.factory("1000000000", 3.0, 0.75, true));
            }
            {
                final BarcodeSupport barCode39 = new QRCodeDrawer(Coordinate.CM_TL);
                table.addCell(barCode39.factory("1000000000", 1.0, 1.0, false));
                table.addCell(barCode39.factory("1000000000", 3.0, 1.0, false));
                table.addCell(barCode39.factory("1000000000", 1.0, 3.0, false));
                table.addCell(barCode39.factory("1000000000", 3.0, 3.0, false));
            }

            table.appendMe();
        });
    }

}
