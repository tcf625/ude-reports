/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02.sec2;

import org.junit.Test;

import com.iisigroup.ude.report.common.PoiDefaultSize;
import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

public class Sample_Page_Margin extends AbstractSample {

    private LayoutInfo createLayout() {
        // ! 定義四周邊界大小.
        final float marginLeft = LengthUnit.CM.trans(2.54f);  // 左方以 公分為單位，合 1英吋/72pixel
        final float marginRight = 36;                         // 右方以 pixel 為單位，合 0.5英吋
        final float marginTop = LengthUnit.MM.trans(12.7f);   // 上方以 公厘為單位，合 0.5英吋/36pixel
        final float marginBottom = LengthUnit.INCH.trans(1);  // 下方以英吋為單位， 1英吋
        final LayoutInfo layoutInfo = new LayoutInfo(marginLeft, marginRight, marginTop, marginBottom);
        // ! 定義上下頁首尾間距.
        layoutInfo.setHeaderExtra(36);
        layoutInfo.setFooterExtra(72);

        layoutInfo.setTextHeader(ItemPosition.LeftHeader, "LH", 12);
        layoutInfo.setTextHeader(ItemPosition.CenterHeader, "CH", 12);
        layoutInfo.setTextHeader(ItemPosition.RightHeader, "RH", 12);
        layoutInfo.setTextHeader(ItemPosition.LeftFooter, "LF", 12);
        layoutInfo.setTextHeader(ItemPosition.CenterFooter, "CF", 12);
        layoutInfo.setTextHeader(ItemPosition.RightFooter, "RF", 12);

        return layoutInfo;
    }

    @Test
    public void test_PDF_Margin() {
        super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A4.rotate()); // ! 定義頁面大小
            pdfDocument.setLayoutInfo(createLayout());       // ! 設定版面資訊
        });
    }

    @Test
    public void test_Excel_Margin() {
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("sheet");
            sheet.setPrintPageSize(PoiDefaultSize.A4L);     // ! 定義頁面大小.
            sheet.setLayoutInfo(createLayout());            // ! 設定版面資訊
            for (int i = 0; i < 1000; i++) {
                sheet.appendCell(new ExcelPoint(i / 20, i % 20), "");
            }
        });
    }

}
