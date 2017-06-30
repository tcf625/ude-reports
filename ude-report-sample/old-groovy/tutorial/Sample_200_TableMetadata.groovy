/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateFormatUtils
import org.junit.Test
import org.mockito.Mockito

import com.iisigroup.ude.report.data.op.number.ColNumber
import com.iisigroup.ude.report.data.source.BarcodeImage
import com.iisigroup.ude.report.data.source.BeanProperty
import com.iisigroup.ude.report.data.source.Const
import com.iisigroup.ude.report.data.source.Counter
import com.iisigroup.ude.report.data.source.BarcodeImage.BarcodeType
import com.iisigroup.ude.report.data.source.datetime.YYYMMDD
import com.iisigroup.ude.report.data.source.text.ToText
import com.iisigroup.ude.report.data.source.transfer.JoinCollection
import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.transfer.PDFNormalTableTransfer
import com.iisigroup.ude.report.table.NormalTableMetadata
import com.iisigroup.ude.report.table.TreeTableMetadata
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.format.CellFormat.AlignV
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata
import com.lowagie.text.PageSize

/**
 *
 */
class Sample_200_TableMetadata extends AbstractITextSampleGroovy {



    private static TreeColumnMetadata find(List<TreeColumnMetadata> dataColumns, String name) {
        for (TreeColumnMetadata TreeColumnMetadata : dataColumns) {
            final String columnName = TreeColumnMetadata.getName();
            if (StringUtils.equals(name, columnName)) {
                return TreeColumnMetadata;
            }
        }
        return Mockito.mock(TreeColumnMetadata.class);
    }


    //####################################################################
    //## [Method] sub-block : 基本標題 -
    //####################################################################

    public NormalTableMetadata BASIC_METADATA(String captionText) {
        final TreeTableMetadata tableMeta = new TreeTableMetadata(captionText);
        tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 20);
        TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 20);
        TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 20);
        TreeColumnMetadata col_D = tableMeta.append("值1", new BeanProperty("value1"), 20);
        col_D.getContentFormat().setAlignH(AlignH.RIGHT);
        return tableMeta;
    }


    @Test
    void test_BASIC_Caption() {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("建立TableMetadata時可傳入Caption文字。若為空字串就不顯示標題，也不佔用空間。");
            pdfDocument.writeText("標題部分會每頁重複顯示(預設為無)。");
            pdfDocument.writeText("");
            pdfDocument.writeText("無標題表格");
            pdfDocument.writeText("");
            new PDFNormalTableTransfer(pdfDocument, BASIC_METADATA("")).transTable(SAMPLE_VO.mockDataset());
            pdfDocument.newPage();
            pdfDocument.writeText("");
            pdfDocument.writeText("含標題表格：(標題：測試表格)");
            pdfDocument.writeText("");
            new PDFNormalTableTransfer(pdfDocument, BASIC_METADATA("標題：測試表格")).transTable(SAMPLE_VO.mockDataset());
        }
    }

    //####################################################################
    //## [Method] sub-block : 欄位格式、型態支援
    //####################################################################

    @Test
    void test_BASIC_FromBean() {
        setPageSize(PageSize.A4)
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("text4  是字串，使用 AsInteger 時，可套用數值格式 TextFormat。");
            pdfDocument.writeText("value3 是INT，使用 asText 時，轉為字串，不套用數值格式。");
            pdfDocument.writeText("");

            final NormalTableMetadata tm = new NormalTableMetadata("欄位格式、型態支援展示");
            tm.getDefaultContentFormat()
                    .setAlignV(AlignV.MIDDLE)
                    .setTextFormat("#,##0");
            TreeColumnMetadata colA1 = tm.append("Text4 As Object ", new BeanProperty("text4"), 60);
            TreeColumnMetadata colA2 = tm.append("Text4 As String ", new BeanProperty("text4").asText(), 60);
            TreeColumnMetadata colA3 = tm.append("Text4 As Integer", new BeanProperty("text4").asInteger(), 60);
            TreeColumnMetadata colB1 = tm.append("Value3 As Object ", new BeanProperty("value3"), 60);
            TreeColumnMetadata colB2 = tm.append("Value3 As String ", new BeanProperty("value3").asText(), 60);
            TreeColumnMetadata colB3 = tm.append("Value3 As Integer", new BeanProperty("value3").asInteger(), 60);
            new PDFNormalTableTransfer(pdfDocument, tm).transTable(SAMPLE_VO.mockDataset());
        }
    }

    @Test
    void test_BASIC_FromBean_Date() {
        setPageSize(PageSize.A4.rotate())
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("Data1 : java.util.date");
            pdfDocument.writeText("Data2 : java.sql.date");
            pdfDocument.writeText("Data3 : null");
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("日期欄位格式、型態支援展示");
            tableMeta.getDefaultContentFormat()
                    .setAlignV(AlignV.MIDDLE).setAlignH(AlignH.CENTER);

            final TreeColumnMetadata g1 = tableMeta.append("預設格式顯示");
            g1.append("date1", new BeanProperty("date1"), 50);
            g1.append("date2", new BeanProperty("date2"), 50);
            g1.append("date3", new BeanProperty("date3"), 30);

            final TreeColumnMetadata g2 = tableMeta.append("搭配西元格式顯示");
            g2.append("date1", new BeanProperty("date1"), 60).getContentFormat().setTextFormat("yy/MM/dd H:m:s");
            g2.append("date2", new BeanProperty("date2"), 40).getContentFormat().setTextFormat("yy/MM/dd");
            g2.append("date3", new BeanProperty("date3"), 30);


            final TreeColumnMetadata g3 = tableMeta.append("轉為民國格式 (twy/MM/dd)");
            g3.append("date1", new YYYMMDD(new BeanProperty("date1")), 40);
            g3.append("date2", new YYYMMDD(new BeanProperty("date2")), 40);
            g3.append("date2 - 民國", new YYYMMDD(new BeanProperty("date2"), "民國y年M月d日"), 60);
            g3.append("date3 - 民國", new YYYMMDD(new BeanProperty("date3"), "民國y年M月d日"), 60);

            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(SAMPLE_VO.mockDataset(100));
        }
    }

    @Test
    void test_BASIC_FromBean_ROCDate() {
        setPageSize(PageSize.A4)
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("日期欄位格式、型態支援展示");
            tableMeta.setWidthPercentage(90);
            tableMeta.getDefaultContentFormat()
                    .setAlignV(AlignV.MIDDLE).setAlignH(AlignH.CENTER);

            final TreeColumnMetadata g3 = tableMeta.append("轉為民國格式 (twy/MM/dd)");
            g3.append("date1", new YYYMMDD(new BeanProperty("date1")), 40);
            g3.append("date2", new YYYMMDD(new BeanProperty("date2")), 40);
            g3.append("date2-twy/M/d", new YYYMMDD(new BeanProperty("date2"), "twy/M/d"), 60);
            g3.append("date2-yyyy/M/d", new YYYMMDD(new BeanProperty("date2"), "yyyy/M/d"), 60);
            g3.append("date2-民國", new YYYMMDD(new BeanProperty("date2"), "民國y年M月d日"), 60);
            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(SAMPLE_VO.mockDataset(100));
        }
    }



    @Test
    void test_BASIC_FromBean_ToDate() {
        setPageSize(PageSize.A4.rotate())
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("無法解析者，顯示空白");
            pdfDocument.writeText("  目前分別接收預設格式：yyyy/MM/dd HH:mm:ss、yyyy/MM/dd");
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("日期欄位格式、型態支援展示");
            tableMeta.getDefaultContentFormat()
                    .setAlignV(AlignV.MIDDLE).setAlignH(AlignH.CENTER);

            final TreeColumnMetadata total = tableMeta.append("內容");

            final TreeColumnMetadata g1 = total.append("文字日期\n內容");
            g1.append("text1", new BeanProperty("text1"), 30);
            g1.append("text2", new BeanProperty("text2"), 50);

            final TreeColumnMetadata g3 = total.append("字串 asDate後，再搭配格式顯示 yy/MM/dd HH:mm:ss");
            g3.append("text1", new BeanProperty("text1").asDate(), 60).getContentFormat().setTextFormat("yy/MM/dd HH:mm:ss");
            g3.append("text2", new BeanProperty("text2").asDate(), 40).getContentFormat().setTextFormat("yy/MM/dd HH:mm:ss");

            final TreeColumnMetadata g4 = tableMeta.append("字串 asDateTime後，再搭配格式顯示 yy/MM/dd HH:mm:ss");
            g4.append("text1", new BeanProperty("text1").asDateTime(), 60).getContentFormat().setTextFormat("yy/MM/dd HH:mm:ss");
            g4.append("text2", new BeanProperty("text2").asDateTime(), 40).getContentFormat().setTextFormat("yy/MM/dd HH:mm:ss");

            final List<SAMPLE_VO> values = SAMPLE_VO.mockDataset(100);
            values.each {
                it.text1 = DateFormatUtils.format(it.date1, "yyyy/MM/dd");
                it.text2 = DateFormatUtils.format(it.date1, "yyyy/MM/dd HH:mm:ss");
                it.text3 = "";
            }
            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(values);
        }
    }
    //####################################################################
    //## [Method] sub-block : 欄位格式、型態支援 - 常數值
    //####################################################################

    @Test
    void test_BASIC_Const_Value() {
        setPageSize(PageSize.A4)
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("常值顯示");
            tableMeta.getDefaultContentFormat()
                    .setTextFormat("#,##0")
                    .setAlignV(AlignV.MIDDLE)
                    .setAlignH(AlignH.CENTER);

            tableMeta.append("CONST:Text", new Const("text1"), 30);
            tableMeta.append("CONST:Text", new Const("1024"), 30);
            tableMeta.append("CONST:Number", new Const(1024), 30);
            tableMeta.append("Counter starts with 0", new Counter(0), 30);
            tableMeta.append("Counter starts with 1", new Counter(1), 30);

            final List<SAMPLE_VO> values = SAMPLE_VO.mockDataset(100);
            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(values);
        }
    }

    //####################################################################
    //## [Method] sub-block : Function
    //####################################################################

    @Test
    void test_BASIC_Function() {
        setPageSize(PageSize.A4)
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("列內公式計算");
            tableMeta.getDefaultContentFormat()
                    .setTextFormat("#,##0")
                    .setAlignV(AlignV.MIDDLE)
                    .setAlignH(AlignH.CENTER);

            final TreeColumnMetadata c1 =tableMeta.append("value1", new BeanProperty("value1"));
            final TreeColumnMetadata c2 =tableMeta.append("value2", new BeanProperty("value2"));
            final TreeColumnMetadata c3 =tableMeta.append("value3", new BeanProperty("value3"));

            final TreeColumnMetadata c12 = tableMeta.append("1+2", new ColNumber(c1).add(c2) );
            tableMeta.append("1+2-3", new ColNumber(c12).subtract(c3));
            tableMeta.prepend("1+2+3", new ColNumber(c12).add(c3));

            final List<SAMPLE_VO> values = SAMPLE_VO.mockDataset(100);
            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(values);
        }

    }

    //####################################################################
    //## [Method] sub-block : 欄位格式、型態支援
    //####################################################################

    @Test
    void test_BASIC_DataMerge() {
        setPageSize(PageSize.A4.rotate())
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("常值顯示");
            tableMeta.getDefaultContentFormat()
                    .setTextFormat("#,##0")
                    .setAlignV(AlignV.MIDDLE)
                    .setAlignH(AlignH.CENTER);

            final Const const1 = new Const("1024ABC")
            tableMeta.append("LIST", new BeanProperty("list1"));
            tableMeta.append("字串合併(預設每列3個)", new JoinCollection(new BeanProperty("list1")));
            tableMeta.append("字串合併、每列3個", new JoinCollection(new BeanProperty("list1"), 3));
            tableMeta.append("字串合併、每列4個", new JoinCollection(new BeanProperty("list1"), 4 , (char)'、'));

            BeanProperty s1 = new BeanProperty("text1");
            BeanProperty s2 = new BeanProperty("text2");
            BeanProperty s3 = new BeanProperty("text3");

            tableMeta.append("text1", s1);
            tableMeta.append("text2", s2);
            tableMeta.append("text3", s3);

            tableMeta.append("join  ", ToText.join("+", s1, s2, s3));
            tableMeta.append("format", ToText.format("%s = %s : %s", s1, s2, s3));




            final List<SAMPLE_VO> values = SAMPLE_VO.mockDataset(100);
            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(values);
        }

    }


    //####################################################################
    //## [Method] sub-block : 欄位格式、型態支援
    //####################################################################

    @Test
    void test_BASIC_Barcode() {
        setPageSize(PageSize.A4.rotate())
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("");

            final NormalTableMetadata tableMeta = new NormalTableMetadata("常值顯示");
            tableMeta.getDefaultContentFormat()
                    .setTextFormat("#,##0")
                    .setAlignV(AlignV.MIDDLE)
                    .setAlignH(AlignH.CENTER);

            final Const const1 = new Const("1024ABC")
            tableMeta.append("CONST:Text", const1);
            tableMeta.append("CONST:Text", new BeanProperty("value1"));
            tableMeta.append("CODE39 ", new BarcodeImage(const1, BarcodeType.CODE39));
            tableMeta.append("CODE128", new BarcodeImage(const1, BarcodeType.CODE128));
            tableMeta.append("QRCODE ", new BarcodeImage(const1, BarcodeType.QRCODE));

            tableMeta.append("CODE39 ", new BarcodeImage(const1, BarcodeType.CODE39 , 2 ,0.5 ));
            tableMeta.append("CODE128", new BarcodeImage(const1, BarcodeType.CODE128 , 2 ,0.5));
            tableMeta.append("QRCODE ", new BarcodeImage(const1, BarcodeType.QRCODE, 1, 1));
            tableMeta.append("QRCODE ", new BarcodeImage(const1, BarcodeType.QRCODE, 2, 2));
            tableMeta.append("QRCODE ", new BarcodeImage(const1, BarcodeType.QRCODE, 3, 3));

            final List<SAMPLE_VO> values = SAMPLE_VO.mockDataset(100);
            new PDFNormalTableTransfer(pdfDocument, tableMeta).transTable(values);
        }
    }



    //####################################################################
    //## [Method] sub-block : 欄位格式、型態支援
    //####################################################################

}
