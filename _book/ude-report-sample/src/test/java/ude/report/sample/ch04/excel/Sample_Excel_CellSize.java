/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.excel;

import org.junit.Test;

import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.iisigroup.ude.util.lang8.date.Now;

import ude.report.sample.AbstractSample;

public class Sample_Excel_CellSize extends AbstractSample {

    @Test
    public void rows() {
        super.createExcel(document -> {
            final ExcelSheet<?> sheet = document.createSheet("sheet1");
            for (int r = 1; r <= 20; r++) {
                sheet.appendCell(new ExcelPoint(r, 0), ReportTextUtils.CHINESE_BLOCK + (r * 10));
                sheet.getRealSheet().getRow(r).setHeightInPoints(r * 10);
            }
        });
    }

    @Test
    public void rate() {

        System.out.println(LengthUnit.ExcelPixel.trans(480));
        System.out.println(LengthUnit.ExcelPixel.trans(480));

        System.out.println(LengthUnit.CM.trans(12.45F));
        System.out.println(LengthUnit.CM.trans(11.5F));

        System.out.println(LengthUnit.CM.transTo(LengthUnit.ExcelPixel, 12.45F));
        System.out.println(LengthUnit.CM.transTo(LengthUnit.ExcelPixel, 11.5F));
    }

    @Test
    public void rowsInPixel() {
        super.createExcel(document -> {
            {
                final ExcelSheet<?> sheet = document.createSheet("sheet1");
                sheet.setColumnWidthInPixel(0, 100);
                for (int r = 1; r <= 20; r++) {
                    sheet.appendCell(new ExcelPoint(r, 0), ReportTextUtils.CHINESE_BLOCK + (r * 10));
                    sheet.setRowHeightInPixel(r, r * 5);
                }
            }

            {
                final ExcelSheet<?> sheet = document.createSheet("sheet2");
                for (int r = 1; r <= 3; r++) {
                    sheet.setRowHeightInPixel(r, LengthUnit.CM.transTo(LengthUnit.ExcelPixel, r));
                    sheet.setColumnWidthInPixel(r, LengthUnit.CM.transTo(LengthUnit.ExcelPixel, r));
                    sheet.appendCell(new ExcelPoint(r, r), ReportTextUtils.CHINESE_BLOCK);
                }

                sheet.setRowHeightInPixel(0, LengthUnit.CM.transTo(LengthUnit.ExcelPixel, 12.7F));
                sheet.setColumnWidthInPixel(0, LengthUnit.CM.transTo(LengthUnit.ExcelPixel, 12.7F));
                sheet.appendCell(new ExcelPoint(0, 0), ReportTextUtils.CHINESE_BLOCK);

            }

        });

    }

    @Test
    public void test_numberformat() {
        super.createExcel(document -> {
            int r = 0;
            final ExcelSheet<?> sheet = document.createSheet("sheet1");

            sheet.appendCell(new ExcelPoint(r++, 0), (Object) null, new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), "", new CellFormat().setTextFormat("#,###"));

            sheet.appendCell(new ExcelPoint(r++, 0), "A1", new CellFormat().setTextFormat("#,###"));

            sheet.appendCell(new ExcelPoint(r++, 0), "100", new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), "10.1", new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), "10.2", new CellFormat().setTextFormat("#,###"));

            sheet.appendCell(new ExcelPoint(r++, 0), 100, new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), 100L, new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), 100.00001D, new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), 100.00002D, new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), 100.00002F, new CellFormat().setTextFormat("#,###"));

            sheet.appendCell(new ExcelPoint(r++, 0), Now.date(), new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), Now.localDate(), new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), Now.localDateTime(), new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), Now.instant(), new CellFormat().setTextFormat("#,###"));
            sheet.appendCell(new ExcelPoint(r++, 0), Now.timestamp(), new CellFormat().setTextFormat("#,###"));

        });
    }

}
