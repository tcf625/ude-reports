/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.pdf;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.testkit.TableLog;

import ude.report.sample.AbstractSample;

public class Sample_Basic_InnerTable extends AbstractSample {

    @Test
    public void test_innerTable() {

        super.createPDF(pdfDocument -> {
            final TableiText table0 = pdfDocument.createTable(80, 4);
            table0.addCell("A1");
            table0.addCell("B1");
            table0.addCell("C1");
            table0.addCell("D1");

            // Row 2
            table0.addCell("A2");
            final float[] subWidths = { 3, 4, 5 };
            table0.addTable(subWidths, 2, innerTable -> {
                innerTable.addCell("BC-a");
                innerTable.addCell("BC-b");
                innerTable.addCell("BC-c");
            });
            table0.addCell("D2");

            table0.appendMe();
        });

        final Map<Integer, List<TableLog>> map = TABLE_LOGGER.get();
        Assert.assertEquals(2, map.size());
        Assert.assertEquals("table0有兩列", 3, map.get(1).get(0).yPos.length);
    }

}
