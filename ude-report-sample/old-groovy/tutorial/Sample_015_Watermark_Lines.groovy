package com.iisigroup.ude.report.itext2.sample.tutorial

/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
import static com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern.*
import static com.iisigroup.ude.report.itext2.header.HeaderPosition.*

import java.awt.Color

import org.junit.Before
import org.junit.Test

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.marker.markinfo.PropertiesMarkInfo
import com.iisigroup.ude.report.itext2.marker.watermark.LineInfo
import com.iisigroup.ude.report.itext2.marker.watermark.LinesWatermarkProperties
import com.iisigroup.ude.report.itext2.marker.watermark.TextWatermarkProperties
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.table.format.DocumentAlign
import com.iisigroup.ude.report.utils.PointF
import com.lowagie.text.PageSize

/**
 *
 */
class Sample_015_Watermark_Lines  extends AbstractITextSampleGroovy {

    @Before
    void setup() {
        this.setPageSize(PageSize.A4.rotate());
        final LayoutInfo layoutInfo = new LayoutInfo();
        this.setLayoutInfo(layoutInfo)
        this.setContentGenerater { PDFDocument pdfDocument ->

            pdfDocument.setSkipPager(true);
            pdfDocument.writeText("封面....");

            for (int s = 1; s <= 3; s++) {

                pdfDocument.newSection();
                pdfDocument.setSkipPager(true);
                pdfDocument.writeText("第" + pdfDocument.getSection() + "小節封面....");

                for (int i = 1; i <=  s*2-1; i++) {

                    pdfDocument.newPage();
                    pdfDocument.setSkipPager(false);
                    pdfDocument.writeText("TEST PAGE: " + s + "-" + i);
                    pdfDocument.writeText("PDF文件頁數:" + pdfDocument.getPageNumber());
                    TableiText table = pdfDocument.createTable(60, 5);
                    table.setHorizontalAlignment(DocumentAlign.CENTER);
                    for (int k = 1; k <= 90; k++) {
                        table.addCell("TEST");
                    }
                    table.appendMe();
                }
            }
        }
    }


    @Test
    void test_Cross() {
        List<LineInfo> lines = [];
        lines.add(new LineInfo(new PointF(0, 20), new PointF(0, -20)));
        lines.add(new LineInfo(new PointF(20, 0), new PointF(-20, 0)));
        final LinesWatermarkProperties markProp = new LinesWatermarkProperties(lines);
        markProp.setSplitX(2)
        markProp.setSplitY(2)
        super.setMarkInfos(new PropertiesMarkInfo(markProp));
    }

    @Test
    void test_MsSeam() {
        int r = 80;
        List<LineInfo> lines = [];
        LineInfo lineDiamond = new LineInfo(
                new PointF(r, 0) //
                , new PointF(0, r) //
                , new PointF(-r, 0) //
                , new PointF(0, -r) //
                , new PointF(r, 0) //
                )
        lineDiamond.setColor(Color.LIGHT_GRAY);
        lines.add(lineDiamond);
        final LinesWatermarkProperties markProp = new LinesWatermarkProperties(lines);
        final int dw = 14; // 位移(左)
        final int ch = 14; // 字高
        final TextWatermarkProperties p1 = create( "機", +dw, +ch);
        final TextWatermarkProperties p2 = create( "關", +dw, -ch);
        final TextWatermarkProperties p3 = create( "印", -dw, +ch);
        final TextWatermarkProperties p4 = create( "信", -dw, -ch);
        super.setMarkInfos(PropertiesMarkInfo.build(Arrays.asList(markProp, p1, p2, p3, p4)));
    }

    private TextWatermarkProperties create(String text, int x, int y){
        final TextWatermarkProperties p1 = new TextWatermarkProperties(text);
        p1.setX(x);
        p1.setY(y);
        p1.setRotate(0);
        return p1;
    }
}
