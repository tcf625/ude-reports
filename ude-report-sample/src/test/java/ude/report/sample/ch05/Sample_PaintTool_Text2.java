/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch05;

import java.awt.Color;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.util.concurrent.AtomicDouble;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.text.font.FontInfo;
import com.iisigroup.ude.report.itext2.text.font.WindowsFont;
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.table.format.CellFormat.AlignH;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;

import ude.report.sample.AbstractSample;

public class Sample_PaintTool_Text2 extends AbstractSample {

    @Test
    public void test_writeText_AlignV() {
        final String text = StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 5) + "ABC";
        super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A3.rotate());
            pdfDocument.writeText("單行文字：");
            final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
            final FontInfo fontInfo = new FontInfo(WindowsFont.MINGLIU, 12);
            contentByte.setLineWidth(0.25F);
            contentByte.setColorStroke(Color.LIGHT_GRAY);
            {
                drawTestSet(text, pdfDocument, contentByte, fontInfo, new PointF(200, 75), AlignV.TOP);
                drawTestSet(text, pdfDocument, contentByte, fontInfo, new PointF(400, 75), AlignV.MIDDLE);
                drawTestSet(text, pdfDocument, contentByte, fontInfo, new PointF(600, 75), AlignV.BOTTOM);
            }

        });
    }

    private void drawTestSet(final String text, final PDFDocument pdfDocument, final PdfContentByte contentByte,
            final FontInfo fontInfo, final PointF basePoint, final AlignV alignV) {
        final AtomicDouble yPos = new AtomicDouble(0);
        PaintTool.Default_TL.drawLines(contentByte, basePoint, basePoint.moveY(pdfDocument.getPageInnerHeight()));
        {
            final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
            PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
            PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, 0, AlignH.CENTER, alignV, 0);
            PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
        }
        {
            final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
            PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
            PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, 0, AlignH.LEFT, alignV, 0);
            PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
        }
        {
            final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
            PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
            PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, 0, AlignH.RIGHT, alignV, 0);
            PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
        }
    }

}
