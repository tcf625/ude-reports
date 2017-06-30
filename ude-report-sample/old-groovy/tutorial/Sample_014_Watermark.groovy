package com.iisigroup.ude.report.itext2.sample.tutorial

/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
import static com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern.*
import static com.iisigroup.ude.report.itext2.header.HeaderPosition.*

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.font.CNS11643
import com.iisigroup.ude.report.itext2.marker.markinfo.PropertiesMarkInfo
import com.iisigroup.ude.report.itext2.marker.watermark.ImageWatermarkProperties
import com.iisigroup.ude.report.itext2.marker.watermark.TextWatermarkProperties
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.table.format.DocumentAlign
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle

import java.awt.Color

import org.apache.commons.lang3.SerializationUtils
import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_014_Watermark  extends AbstractITextSampleGroovy {

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
    void test_TextWaterMark_BASIC() {
        final TextWatermarkProperties markProp = new TextWatermarkProperties("測試測試「" + "𠀝" + "」");
        markProp.setFontType(CNS11643.KAI);
        markProp.setFontSize(48);
        super.setMarkInfos(new PropertiesMarkInfo(markProp));
    }

    @Test
    void test_TextWaterMark_Backslash() {
        final TextWatermarkProperties markProp = new TextWatermarkProperties("測試測試「" + "𠀝" + "」");
        markProp.setFontType(CNS11643.KAI);
        markProp.setFontSize(48);
        markProp.setRotate(-2);
        super.setMarkInfos(new PropertiesMarkInfo(markProp));
    }

    @Test
    void test_TextWaterMark_Multiple() {
        final TextWatermarkProperties markProp1 = new TextWatermarkProperties("測試測試「" + "𠀝" + "」");
        markProp1.setFontType(CNS11643.KAI);
        markProp1.setFontSize(40);
        markProp1.setRotate(30);
        markProp1.setColor(Color.blue);
        markProp1.setBold(true);
        markProp1.setItalic(true);
        markProp1.setUnderline(true);
        markProp1.setStrikethru(true);
        markProp1.setX(-150);
        markProp1.setY(0);

        final TextWatermarkProperties markProp2 = SerializationUtils.clone(markProp1);
        markProp2.setRotate(60);
        markProp2.setFontSize(30);
        markProp2.setX(250);
        markProp2.setY(0);
        markProp2.setAlign(DocumentAlign.LEFT);

        final TextWatermarkProperties markProp3 = SerializationUtils.clone(markProp2);
        markProp3.setUnderline(false);
        markProp3.setStrikethru(false);
        markProp3.setAlign(DocumentAlign.RIGHT);

        super.setMarkInfos(new PropertiesMarkInfo(markProp1)
                , new PropertiesMarkInfo(markProp2), new PropertiesMarkInfo(markProp3));
    }



    @Test
    void test_TextWaterMark_Multi() {
        final TextWatermarkProperties markProp = new TextWatermarkProperties("測試測試「" + "𠀝" + "」");
        markProp.setFontType(CNS11643.KAI);
        markProp.setFontSize(26);
        markProp.setRotate(45);
        markProp.setColor(Color.GREEN);
        markProp.setBold(true);
        markProp.setItalic(true);
        markProp.setUnderline(true);
        markProp.setStrikethru(true);
        markProp.setX(30);
        markProp.setY(90);

        final TextWatermarkProperties markProp2 = SerializationUtils.clone(markProp);
        markProp2.setRotate(90);
        markProp2.setColor(Color.BLUE);
        markProp2.setX(0);
        markProp2.setY(0);
        super.setMarkInfos(new PropertiesMarkInfo(markProp), new PropertiesMarkInfo(markProp2));
    }


    /**
     * 中空字
     */
    @Test
    void test_TextWaterMark_Hellow() {
        final TextWatermarkProperties markProp = new TextWatermarkProperties("測試「" + "𠀝" + "」");
        markProp.setFontType(CNS11643.KAI);
        markProp.setColor(Color.gray);
        markProp.setFontSize(80);
        markProp.setHollow(true)
        markProp.setRotate(0);
        markProp.setItalic(true);
        super.setMarkInfos(new PropertiesMarkInfo(markProp));
    }


    /** 分割 */
    @Test void test_TextWaterMark_Split_1X2() {
        doSplitTest( PageSize.A4, 1, 2, null);
    }
    @Test void test_TextWaterMark_Split_2X2() {
        doSplitTest( PageSize.A4, 2, 2, null);
    }
    @Test void test_TextWaterMark_Split_2X1() {
        doSplitTest( PageSize.A4, 2, 1, null);
    }
    @Test void test_TextWaterMark_Split_2X3() {
        doSplitTest( PageSize.A4, 2, 3, null);
    }
    @Test void test_TextWaterMark_Split_0X0() {
        doSplitTest( PageSize.A4, 0, 0, DocumentAlign.LEFT);
    }
    @Test void test_TextWaterMark_Split_0X3() {
        doSplitTest( PageSize.A4, 0, 3, DocumentAlign.LEFT);
    }
    @Test void test_TextWaterMark_Split_3X0() {
        doSplitTest( PageSize.A4, 3, 0, DocumentAlign.LEFT);
    }
    @Test void test_TextWaterMark_Split_2X3_ROTATE() {
        doSplitTest( PageSize.A4.rotate(), 2, 3, null);
    }
    private doSplitTest(Rectangle pageSize, int sx, int sy, DocumentAlign align) {
        super.setPageSize(pageSize);
        final TextWatermarkProperties markProp = new TextWatermarkProperties("測試「" + "𠀝" + "」");
        markProp.setSplitX(sx);
        markProp.setSplitY(sy);
        markProp.setFontSize(40);
        markProp.setColor(Color.BLUE);
        if (align!=null){
            markProp.setAlign(align);
        }
        super.setMarkInfos(new PropertiesMarkInfo(markProp))
    }





    @Test
    void test_TextWaterMark_Image() {
        final ImageWatermarkProperties markProp = new ImageWatermarkProperties("classpath:RCSS00001.png");
        super.setMarkInfos(new PropertiesMarkInfo(markProp));
    }



    @Test
    void test_TextWaterMark_Image_Rotate() {
        final ImageWatermarkProperties markProp = new ImageWatermarkProperties("classpath:RCSS00001.png");
        super.groovyReport {  PDFDocument pdfDocument ->
            for(int i = 0 ; i<=360 ; i+=30){
                markProp.setRotate(i)
                pdfDocument.setMarkers(Arrays.asList( new PropertiesMarkInfo(markProp)));
                pdfDocument.writeText("角度：" + i)
                pdfDocument.newPage()
            }
        }
    }

    @Test
    void test_TextWaterMark_Image_Scale() {
        final ImageWatermarkProperties markProp = new ImageWatermarkProperties("classpath:RCSS00001.png");
        super.groovyReport {  PDFDocument pdfDocument ->
            for(float i = 0.4 ; i<2.01 ; i+=0.2){
                markProp.setScaleRatio(i)
                pdfDocument.setMarkers(Arrays.asList( new PropertiesMarkInfo(markProp)));
                pdfDocument.writeText("縮放：" + i)
                pdfDocument.newPage()
            }
        }
    }




    @Test
    void test_TextWaterMark_Image2() {
        final ImageWatermarkProperties markProp0 = new ImageWatermarkProperties("classpath:RCSS00001.png");
        markProp0.setX(-100);
        markProp0.setY(-150);
        markProp0.setAlpha(200);

        final ImageWatermarkProperties markProp1 = new ImageWatermarkProperties("classpath:RCSS00001.png");
        markProp1.setRotate(45);
        markProp1.setAlpha(127);
        markProp1.setScaleRatio(0.3f);
        markProp1.setX(-100);
        markProp1.setY(-150);


        final ImageWatermarkProperties markProp2 = SerializationUtils.clone(markProp1);
        markProp2.setScaleRatio(1f);
        markProp2.setRotate(45);
        markProp2.setX(0);
        markProp2.setY(0);

        final ImageWatermarkProperties markProp3 = SerializationUtils.clone(markProp2);
        markProp3.setScaleRatio(1.5f);
        markProp3.setX(300);
        markProp3.setY(300);


        super.setMarkInfos(new PropertiesMarkInfo(markProp0), new PropertiesMarkInfo(markProp1), new PropertiesMarkInfo(markProp2)
                , new PropertiesMarkInfo(markProp3)
                );

        //super.setMarkInfos(new PropertiesMarkInfo(markProp2));
    }
}
