/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch05;

import java.awt.Color;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.util.concurrent.AtomicDouble;
import com.iisigroup.ude.report.itext2.text.font.FontInfo;
import com.iisigroup.ude.report.itext2.text.font.WindowsFont;
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.table.format.CellFormat.AlignH;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.pdf.PdfContentByte;

import ude.report.sample.AbstractSample;

public class Sample_PaintTool_Text extends AbstractSample {

    @Test
    public void test_writeText_basic() {
        final String text = StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 5);
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("單行文字：");
            final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
            final FontInfo fontInfo = new FontInfo(WindowsFont.MINGLIU, 12);
            contentByte.setLineWidth(0.25F);
            contentByte.setColorStroke(Color.LIGHT_GRAY);
            {
                final PointF basePoint = new PointF(200, 75);
                final AtomicDouble yPos = new AtomicDouble(0);
                PaintTool.Default_TL.drawLines(contentByte, basePoint, basePoint.moveY(pdfDocument.getPageInnerHeight()));
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, AlignH.LEFT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, AlignH.RIGHT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
                }
            }
            {
                final PointF basePoint = new PointF(400, 75);
                final AtomicDouble yPos = new AtomicDouble(0);
                PaintTool.Default_TL.drawLines(contentByte, basePoint, basePoint.moveY(pdfDocument.getPageInnerHeight()));
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeTextAbove(contentByte, fontInfo, text, point);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeTextAbove(contentByte, fontInfo, text, point, AlignH.LEFT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeTextAbove(contentByte, fontInfo, text, point, AlignH.RIGHT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
                }
            }
        });
    }

    @Test
    public void test_writeText_basic_16() {
        final String text = StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 5);
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("單行文字(字型16)：");
            final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
            final FontInfo fontInfo = new FontInfo(WindowsFont.MINGLIU, 16);
            contentByte.setLineWidth(0.25F);
            contentByte.setColorStroke(Color.LIGHT_GRAY);
            {
                final PointF basePoint = new PointF(200, 75);
                final AtomicDouble yPos = new AtomicDouble(0);
                PaintTool.Default_TL.drawLines(contentByte, basePoint, basePoint.moveY(pdfDocument.getPageInnerHeight()));
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, AlignH.LEFT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, AlignH.RIGHT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
                }
            }
            {
                final PointF basePoint = new PointF(400, 75);
                final AtomicDouble yPos = new AtomicDouble(0);
                PaintTool.Default_TL.drawLines(contentByte, basePoint, basePoint.moveY(pdfDocument.getPageInnerHeight()));
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeTextAbove(contentByte, fontInfo, text, point);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeTextAbove(contentByte, fontInfo, text, point, AlignH.LEFT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
                }
                {
                    final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 1.5));
                    PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                    PaintTool.Default_TL.writeTextAbove(contentByte, fontInfo, text, point, AlignH.RIGHT);
                    PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
                }
            }
        });
    }

    @Test
    public void test_writeText_lines() {
        final String text = StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 5) + "\n"
                + StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 6) + "\n"
                + StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 7);

        final AtomicDouble yPos = new AtomicDouble(0);
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("多行文字：預設行距為2，也可設定行距為0。");
            final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
            final FontInfo fontInfo = new FontInfo(WindowsFont.MINGLIU, 16);
            contentByte.setLineWidth(0.25F);
            contentByte.setColorStroke(Color.LIGHT_GRAY);
            final PointF basePoint = new PointF(pdfDocument.getPageInnerWidth() / 2, 75);
            PaintTool.Default_TL.drawLines(contentByte, basePoint, basePoint.moveY(pdfDocument.getPageInnerHeight()));

            // !! Align
            {
                final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 3.5));
                PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point);
                PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
            }
            {
                final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 3.5));
                PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, AlignH.LEFT);
                PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
            }
            {
                final PointF point = basePoint.addY((float) yPos.getAndAdd(fontInfo.size * 3.5));
                PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                PaintTool.Default_TL.writeText(contentByte, fontInfo, text, point, AlignH.RIGHT);
                PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
            }

            // !! Align
            yPos.set(0);
            {
                final PointF point = basePoint.add(200, (float) yPos.getAndAdd(fontInfo.size * 3.5));
                PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                PaintTool.Default_TL.writeTightText(contentByte, fontInfo, text, point);
                PaintTool.Default_TL.writeText(contentByte, fontInfo, "預設置中", point.moveX(32), AlignH.LEFT);
            }
            {
                final PointF point = basePoint.add(200, (float) yPos.getAndAdd(fontInfo.size * 3.5));
                PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                PaintTool.Default_TL.writeTightText(contentByte, fontInfo, text, point, AlignH.LEFT);
                PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.LEFT", point.moveX(32), AlignH.LEFT);
            }
            {
                final PointF point = basePoint.add(200, (float) yPos.getAndAdd(fontInfo.size * 3.5));
                PaintTool.Default_TL.drawLines(contentByte, point.moveX(0), point.moveX(pdfDocument.getPageInnerWidth()));
                PaintTool.Default_TL.writeTightText(contentByte, fontInfo, text, point, AlignH.RIGHT);
                PaintTool.Default_TL.writeText(contentByte, fontInfo, "AlignH.RIGHT", point.moveX(32), AlignH.LEFT);
            }
        });
    }

}
