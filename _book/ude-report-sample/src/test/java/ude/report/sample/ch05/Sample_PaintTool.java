/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch05;

import java.awt.Color;

import org.junit.Test;

import com.iisigroup.ude.report.common.SimpleRectangle;
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.itext2.utils.barcode.Barcode39Drawer;
import com.iisigroup.ude.report.itext2.utils.barcode.BarcodeSupport;
import com.iisigroup.ude.report.utils.Coordinate;
import com.iisigroup.ude.report.utils.PointF;
import com.lowagie.text.pdf.PdfContentByte;

import ude.report.sample.AbstractSample;

public class Sample_PaintTool extends AbstractSample {

    protected SimpleRectangle drarBarcode(final PdfContentByte contentByte //
            , final BarcodeSupport barCode, final PointF pos, final int w, final float h, final String text,
            final boolean showText) {

        final SimpleRectangle rect = barCode.draw(contentByte, text, pos, w, h, showText);
        contentByte.saveState();
        contentByte.setLineWidth(0.2f);
        contentByte.setColorStroke(Color.lightGray);
        PaintTool.Default.drawBlock(contentByte, rect, 20.0f);
        contentByte.restoreState();
        return rect;
    }

    @Test
    public void test_barCode39() {
        super.createPDF(pdfDocument -> {
            final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
            final Barcode39Drawer barCode39 = new Barcode39Drawer(Coordinate.CM_TL);
            // FIXED SIZE
            drarBarcode(contentByte, barCode39, new PointF(3, 4), 5, 0.8f, "TEST60105", true);
            drarBarcode(contentByte, barCode39, new PointF(10.5f, 4), 5, 0.8f, "TEST60105", false);
            // DEFUALT SIZE
            drarBarcode(contentByte, barCode39, new PointF(8.5f, 8), -1, -1, "TEST60105", false);
        });
    }

}
