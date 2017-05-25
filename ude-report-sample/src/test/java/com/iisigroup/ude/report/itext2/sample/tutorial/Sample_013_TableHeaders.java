/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.itext2.sample.AbstractSample;
import com.iisigroup.ude.report.layout.ItemPosition;

public class Sample_013_TableHeaders extends AbstractSample {

    @Test
    public void test_PAGE_TABLE_HEIGHT2() {
        super.createPDF(pdfDocument -> {

            final LayoutInfo layoutInfo = new LayoutInfo(getiTextConfig());

            layoutInfo.setHeader(ItemPosition.LeftHeader, "TEST-HEADER", 14);
            pdfDocument.setLayoutInfo(layoutInfo);
            pdfDocument.writeText("TSET");
        });
    }

}
