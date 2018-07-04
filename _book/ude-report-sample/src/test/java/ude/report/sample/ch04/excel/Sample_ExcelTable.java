/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.excel;

import org.junit.Test;

import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.util.lang8.date.Now;

import ude.report.sample.AbstractSample;

public class Sample_ExcelTable extends AbstractSample {

    @Test
    public void test_basic_types() {
        super.createExcel(document -> {
            int r = 0;
            final ExcelSheet<?> sheet = document.createSheet("sheet1");

            sheet.appendCell(new ExcelPoint(r++, 0), null);
            sheet.appendCell(new ExcelPoint(r++, 0), "");

            sheet.appendCell(new ExcelPoint(r++, 0), "A1");

            sheet.appendCell(new ExcelPoint(r++, 0), 100);
            sheet.appendCell(new ExcelPoint(r++, 0), 100L);
            sheet.appendCell(new ExcelPoint(r++, 0), 100.00001D);
            sheet.appendCell(new ExcelPoint(r++, 0), 100.00002D);
            sheet.appendCell(new ExcelPoint(r++, 0), 100.00002F);

            sheet.appendCell(new ExcelPoint(r++, 0), Now.date());
            sheet.appendCell(new ExcelPoint(r++, 0), Now.localDate());
            sheet.appendCell(new ExcelPoint(r++, 0), Now.localDateTime());
            sheet.appendCell(new ExcelPoint(r++, 0), Now.instant());
            sheet.appendCell(new ExcelPoint(r++, 0), Now.timestamp());

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
