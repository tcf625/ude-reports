/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import java.awt.Color;

import org.junit.Before;

import com.iisigroup.ude.report.common.SimpleRectangle;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.itext2.sample.AbstractITextSample;
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.itext2.utils.barcode.BarcodeSupport;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class Sample_020_Barcode extends AbstractITextSample {

    protected String text = "";

    @Before
    public void setup() {
        this.setPageSize(PageSize.A4.rotate());
        this.text = "TEST60105";
    }

    protected SimpleRectangle drarBarcode(final PdfContentByte contentByte //
            , final BarcodeSupport barCode, PointF pos, int w, float h, boolean showText) {

        final SimpleRectangle rect = barCode.draw(contentByte, this.text, pos, w, h, showText);
        contentByte.saveState();
        contentByte.setLineWidth(0.2f);
        contentByte.setColorStroke(Color.lightGray);
        //PaintTool.CM_TL.drawBlock(contentByte, pos, pos.add(w, -h), 0.0f);
        PaintTool.Default.drawBlock(contentByte, rect, 0.0f);
        contentByte.restoreState();
        //LOGGER.info("{}", rect);
        return rect;
    }
}
