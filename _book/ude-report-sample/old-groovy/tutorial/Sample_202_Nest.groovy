/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import org.junit.Test

import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy

/**
 *
 */
class Sample_202_Nest extends AbstractITextSampleGroovy {



    @Test
    public void test_BASIC_1() throws Exception {
        super.setPageSize(PageSize.A4)
        this.groovyReport { PDFDocument pdfDocument->
            final NestTableMetadata tableMeta = new NestTableMetadata("LIST");
            tableMeta.setWidthPercentage(50);
            tableMeta.setBorderWidth(1.5f);
            tableMeta.setDefaultColumnWidth(10);
            tableMeta.getDefaultHeaderFormat()
                    .setFontBold(true)
                    .setFontSize(14)
                    .setMinHeightInCM(1);
            tableMeta.getDefaultContentFormat()
                    .setAlignV(AlignV.MIDDLE);

            tableMeta.append("年度", new BeanProperty("text1"), 10);
            tableMeta.append("地區", new BeanProperty("text2"), 20);
            tableMeta.append("項目", new BeanProperty("text3"), 20);
            // 總計50

            tableMeta.nextRow();
            final TreeColumnMetadata col1 = tableMeta.append("值1", new BeanProperty("value1"), 10);
            col1.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            col1.append("值2", new BeanProperty("value2"), 10);
            col1.append("值3", new BeanProperty("value3"), 10);

            final NestTableMetadata sub = tableMeta.subTable();
            sub.append("開始日期", new BeanProperty("date2"), 20);
            sub.append("結束日期", new BeanProperty("date1"), 10);

            final PDFNestTableTransfer createTransfer = new PDFNestTableTransfer(pdfDocument, tableMeta);
            createTransfer.transTable(SAMPLE_VO.mockDataset());
        }
    }






    @Test
    public void test_NoborderPreColumn() throws Exception {
        super.setPageSize(PageSize.A3.rotate())
        File pdfFile = this.groovyReport { PDFDocument pdfDocument->
            NestTableMetadata tm = SAMPLE_METADATA_NEST.ML_6102.get();

            final PDFNestTableTransfer createTransfer = new PDFNestTableTransfer(pdfDocument, tm);
            createTransfer.transTable(SAMPLE_VO.mockDataset());
        }

        File csvFile = UdeFileUtils.asExtName(pdfFile, "csv");
        final NestTableMetadata tableMeta = SAMPLE_METADATA_NEST.ML_6102.get();
    }
}
