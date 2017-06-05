/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04;

import org.junit.Test;

import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelSheet;

import ude.report.sample.AbstractSample;

public class Sample_ExcelTable extends AbstractSample {

    @Test
    public void test_basic() {
        super.createExcel(document -> {
            final ExcelSheet<?> sheet = document.createSheet("sheet1");
            sheet.appendCell(new ExcelPoint(0, 0), "A1");
        });
    }

}
